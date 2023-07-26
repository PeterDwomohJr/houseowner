package com.dwomo.propertydata.controllers;

import com.dwomo.propertydata.dto.PropertyCreatedEventDTO;
import com.dwomo.propertydata.events.handlers.PropertyEventHandler;
import com.dwomo.propertydata.repositories.PropertyRepository;
import com.dwomo.propertydata.services.PropertyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v0/property")
public class PropertyController {

    private final PropertyEventHandler propertyEventHandler;
    private final PropertyService propertyService;



    public PropertyController(PropertyEventHandler propertyEventHandler, PropertyService propertyService)
    {
        this.propertyEventHandler = propertyEventHandler;
        this.propertyService = propertyService;
    }


    @GetMapping
    public Flux<PropertyCreatedEventDTO> getProperties()
    {
        return propertyService.getProperties();
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user')")
    public Mono<PropertyCreatedEventDTO> getProperty(@PathVariable String id)
    {
        return propertyService.getProperty(id);
    }
}
