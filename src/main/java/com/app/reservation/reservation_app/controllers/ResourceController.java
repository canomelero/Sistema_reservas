package com.app.reservation.reservation_app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.reservation.reservation_app.dto.CreateResourceRequest;
import com.app.reservation.reservation_app.exceptions.EntityNotFoundException;
import com.app.reservation.reservation_app.models.Resource;
import com.app.reservation.reservation_app.services.ResourceService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/api/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceServ;
    
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody CreateResourceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resourceServ.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResourceById(@PathVariable Long id) {
        Optional<Resource> optionalResource = resourceServ.getResourceById(id);

        if(optionalResource.isPresent()) {
            return ResponseEntity.ok(optionalResource.get());
        }

        throw new EntityNotFoundException("resource", id);
    }
}
