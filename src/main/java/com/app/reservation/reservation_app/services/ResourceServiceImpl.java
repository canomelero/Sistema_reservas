package com.app.reservation.reservation_app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.reservation.reservation_app.dto.CreateResourceRequest;
import com.app.reservation.reservation_app.models.Resource;
import com.app.reservation.reservation_app.repositories.ResourceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRep;

    @Transactional
    @Override
    public Resource create(CreateResourceRequest request) {
        Resource resource = new Resource(
            request.getType(),
            request.getName(),
            request.getCapacity(),
            request.getActive()
        );

        return resourceRep.save(resource);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Resource> getResourceById(Long id) {
        return resourceRep.findById(id);
    }
}
