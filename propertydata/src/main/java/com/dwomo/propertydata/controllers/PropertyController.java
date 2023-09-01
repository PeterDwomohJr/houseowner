package com.dwomo.propertydata.controllers;

import com.dwomo.propertydata.dto.PropertyCreatedEventDTO;
import com.dwomo.propertydata.services.PropertyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v0/cache-property")
public class PropertyController {
    private final PropertyService propertyService;



    public PropertyController(PropertyService propertyService)
    {
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


    @GetMapping("/count")
    public Mono<Long> getCount()
    {
        return propertyService.getCount();
    }
}
