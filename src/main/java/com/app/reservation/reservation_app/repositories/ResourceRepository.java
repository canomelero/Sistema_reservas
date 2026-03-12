package com.app.reservation.reservation_app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.reservation.reservation_app.models.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long> {

}
