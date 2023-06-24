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


    //@PreAuthorize("hasRole('AUTHENTICATED_USER)")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v0/property/create")
    public void createProperty(@RequestBody PropertyDTO propertyDTO)
    {
        logger.info("You have successfully called the /v0/property/create API");
        propertyService.createProperty(propertyDTO);
    }


    //@PreAuthorize("hasRole('AUTHENTICATED_USER)")
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


    @PreAuthorize("hasRole('AUTHENTICATED_USER)")
    @PatchMapping("/v0/property/update/{id}")
    public void updateProperty(@PathVariable String id, @RequestBody PropertyDTO propertyDTO)
    {
        logger.info("You have successfully called the /v0/property/update/{id} API");
        propertyService.updateProperty(id, propertyDTO);
    }


    @PreAuthorize("hasRole('AUTHENTICATED_USER)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/v0/property/delete/{id}")
    public void deleteProperty(@PathVariable String id)
    {
        logger.info("You have successfully called the /v0/property/delete/{id} API");
        propertyService.deleteProperty(id);
    }


    @PreAuthorize("hasRole('AUTHENTICATED_USER)")
    @PostMapping("/v0/property/buy/{id}")
    public void buyProperty(@PathVariable String id)
    {
        logger.info("You have successfully called the /v0/property/buy/{id} API");
        propertyService.buyProperty(id);
    }


    @PreAuthorize("hasRole('ADMIN)")
    @PatchMapping("/v0/property/status/{id}")
    public void changePropertyStatus(@PathVariable String id)
    {
        logger.info("You have successfully called the /v0/property/status/{id} API");
        propertyService.changePropertyStatus(id);
    }
}
