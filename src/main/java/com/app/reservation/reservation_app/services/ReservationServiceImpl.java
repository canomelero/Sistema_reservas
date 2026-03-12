package com.app.reservation.reservation_app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.reservation.reservation_app.dto.CreateReservationRequest;
import com.app.reservation.reservation_app.exceptions.EntityNotFoundException;
import com.app.reservation.reservation_app.exceptions.ReservationExistsException;
import com.app.reservation.reservation_app.models.Reservation;
import com.app.reservation.reservation_app.models.Resource;
import com.app.reservation.reservation_app.models.User;
import com.app.reservation.reservation_app.models.enums.ReservationStatus;
import com.app.reservation.reservation_app.repositories.ReservationRepository;
import com.app.reservation.reservation_app.repositories.ResourceRepository;
import com.app.reservation.reservation_app.repositories.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRep;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private ResourceRepository resourceRep;
    
    @Transactional
    @Override
    public Reservation create(CreateReservationRequest reservationRequest) {
        Optional<User> optionalUser = userRep.findById(reservationRequest.getUserId());
        Optional<Resource> optionalResource = resourceRep.findById(reservationRequest.getResourceId());
        User user = null;
        Resource resource = null;

        if(optionalUser.isEmpty()) {
            throw new EntityNotFoundException("user", reservationRequest.getUserId());
        } else {
            user = optionalUser.get();
        }

        if(optionalResource.isEmpty()) {
            throw new EntityNotFoundException("resource", reservationRequest.getResourceId());
        } else {
            resource = optionalResource.get();
        }

        Reservation reservation = new Reservation(
            reservationRequest.getStartDate(),
            reservationRequest.getEndDate(),
            reservationRequest.getStatus(),
            user,
            resource
        );

        return this.createReservation(reservation);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRep.findById(id);
    }

    private Reservation createReservation(Reservation reservation) {
        Resource resource = reservation.getResource();
        LocalDateTime startNew = reservation.getStartDate();
        LocalDateTime endNew = reservation.getEndDate();
        List<Reservation> reservationList = reservationRep.findByResourceId(resource.getId());

        for(Reservation r : reservationList) {
            LocalDateTime startRes = r.getStartDate();
            LocalDateTime endRes = r.getEndDate();
            ReservationStatus status = r.getStatus();

            if(sameDate(startRes, endRes, startNew, endNew)
                && inHourRange(startRes, endRes, startNew, endNew)) {
                if(status == ReservationStatus.ACTIVE) {
                    throw new ReservationExistsException();  
                } else {
                    reservation = r;
                    reservation.setStatus(ReservationStatus.ACTIVE);
                } 
            }
        }

        return reservationRep.save(reservation);
    }

    private boolean sameDate(LocalDateTime startRes, LocalDateTime endRes, 
                                    LocalDateTime startNew, LocalDateTime endNew) {
        LocalDate startResDate = startRes.toLocalDate();
        LocalDate endResDate = endRes.toLocalDate();
        LocalDate startNewDate = startNew.toLocalDate();
        LocalDate endNewDate = endNew.toLocalDate();
        
        return startResDate.equals(startNewDate) && endResDate.equals(endNewDate);
    }

    private boolean inHourRange(LocalDateTime startRes, LocalDateTime endRes, 
                                    LocalDateTime startNew, LocalDateTime endNew) {
        LocalTime startResTime = startRes.toLocalTime();
        LocalTime endResTime = endRes.toLocalTime();
        LocalTime startNewTime = startNew.toLocalTime();
        LocalTime endNewTime = endNew.toLocalTime();
        int startResHour = startRes.getHour();
        int endResHour = endRes.getHour();
        int startNewHour = startNew.getHour();
        int endNewHour = endNew.getHour();

        return (startResTime.equals(startNewTime) && endResTime.equals(endNewTime)) 
                    || (startNewHour >= startResHour && startNewHour <= endResHour) 
                        || (endNewHour >= startResHour && endNewHour <= endResHour);
    }
}
