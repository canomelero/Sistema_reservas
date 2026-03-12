package com.app.reservation.reservation_app.services;

import java.util.Optional;

import com.app.reservation.reservation_app.dto.CreateResourceRequest;
import com.app.reservation.reservation_app.models.Resource;

public interface ResourceService {
    Resource create(CreateResourceRequest request);
    Optional<Resource> getResourceById(Long id);
}
