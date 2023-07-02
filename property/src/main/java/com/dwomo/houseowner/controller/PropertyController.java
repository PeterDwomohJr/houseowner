package com.dwomo.houseowner.controller;

import com.dwomo.houseowner.dto.PropertyDTO;
import com.dwomo.houseowner.service.PropertyService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;


    public PropertyController(PropertyService propertyService)
    {
        this.propertyService = propertyService;
    }



    @GetMapping
    public Flux<PropertyDTO> getProperties()
    {
        // This will return all the properties in the property repository, both active and pending
        return propertyService.getProperties();
    }


    @GetMapping({"/{id}"})
    public Mono<PropertyDTO> getProperty(@PathVariable String id)
    {
        // This will return a single property that is associated with the included id
        return propertyService.getProperty(id);
    }


    @GetMapping("/count")
    public Mono<Long> getPropertyCount()
    {
        // This will return the number of properties that have been created and are found in the property repository
        return propertyService.getPropertyCount();
    }


    @GetMapping("/active")
    public Flux<PropertyDTO> getActiveProperties()
    {
        // Active properties are properties that have been verified to be genuine
        return propertyService.getActiveProperties();
    }



    @GetMapping("/pending")
    public Flux<PropertyDTO> getPendingProperties()
    {
        // Pending properties are ones that have not been verified to be genuine
        return propertyService.getPendingProperties();
    }



    @GetMapping("/soft-deleted")
    public Flux<PropertyDTO> getSoftDeletedProperties()
    {
        // Soft deletion marks the property as deleted but do not remove it from the property repository
        return propertyService.getSoftDeletedProperties();
    }


    @GetMapping("/non-deleted")
    public Flux<PropertyDTO> getNonSoftDeleteProperties()
    {
        // This returns all the properties have not been marked as deleted
        return propertyService.getNonSoftDeletedProperties();
    }



    @GetMapping("/range")
    public Flux<PropertyDTO> getPropertiesBetweenRange(@RequestParam BigDecimal min, @RequestParam BigDecimal max)
    {
        return propertyService.getPropertiesBetweenPriceRange(min, max);
    }
}
