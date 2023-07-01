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
        return propertyService.getProperties();
    }


    @GetMapping({"/{id}"})
    public Mono<PropertyDTO> getProperty(@PathVariable String id)
    {
        return propertyService.getProperty(id);
    }


    @GetMapping("/count")
    public Mono<Long> getPropertyCount()
    {
        return propertyService.getPropertyCount();
    }


    @GetMapping("/active")
    public Flux<PropertyDTO> getActiveProperties()
    {
        return propertyService.getActiveProperties();
    }



    @GetMapping("/pending")
    public Flux<PropertyDTO> getPendingProperties()
    {
        return propertyService.getPendingProperties();
    }



    @GetMapping("/soft-deleted")
    public Flux<PropertyDTO> getSoftDeletedProperties()
    {
        return propertyService.getSoftDeletedProperties();
    }


    @GetMapping("/non-deleted")
    public Flux<PropertyDTO> getNonSoftDeleteProperties()
    {
        return propertyService.getNonSoftDeletedProperties();
    }



    @GetMapping("/range")
    public Flux<PropertyDTO> getPropertiesBetweenRange(@RequestParam BigDecimal min, @RequestParam BigDecimal max)
    {
        return propertyService.getPropertiesBetweenPriceRange(min, max);
    }



    @PostMapping
    public Mono<PropertyDTO> saveProperty(@RequestBody Mono<PropertyDTO> propertyDTOMono)
    {
        return propertyService.saveProperty(propertyDTOMono);
    }


    @PutMapping("/{id}")
    public Mono<PropertyDTO> updateProperty(@PathVariable String id, @RequestBody Mono<PropertyDTO> propertyDTOMono)
    {
        return propertyService.updateProperty(id, propertyDTOMono);
    }


    @DeleteMapping("/{id}")
    public Mono<Void> deleteProperty(@PathVariable String id)
    {
        return propertyService.deleteProperty(id);
    }



    @DeleteMapping("/{id}/soft")
    public Mono<Void> softDeleteProperty(@PathVariable String id)
    {
        return propertyService.softDeleteProperty(id);
    }


    @DeleteMapping
    public Mono<Void> deleteAllProperties()
    {
        return propertyService.deleteAllProperties();
    }


    @PatchMapping("/{id}")
    public Mono<PropertyDTO> setPropertyStatus(@PathVariable String id, @RequestParam String status)
    {
        return propertyService.setPropertyStatus(id, status);
    }
}
