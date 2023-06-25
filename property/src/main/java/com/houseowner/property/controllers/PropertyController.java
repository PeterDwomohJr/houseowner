package com.houseowner.property.controllers;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.aggregates.entities.Property;
import com.houseowner.property.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.logging.Logger;

@RestController
public class PropertyController {

    @Autowired
    private  PropertyService propertyService;
    private static final Logger logger = Logger.getLogger(PropertyController.class.getName());


    @PreAuthorize("hasRole('AUTHENTICATED_USER)")
    @GetMapping("/v0/property/{id}")
    public Mono<Property> getProperty(@PathVariable String id)
    {
        logger.info("You have successfully called the /v0/property/{id} API");
        return propertyService.getProperty(id);
    }


    @GetMapping("/v0/properties")
    public Flux<Property> listProperties()
    {
        logger.info("You have successfully called the /v0/properties API");
        return propertyService.listProperties();
    }
}
