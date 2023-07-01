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
        return propertyService.getProperties(id);
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

    @GetMapping("/range")
    public Flux<PropertyDTO> getPropertiesBetweenRange(@RequestParam BigDecimal min, @RequestParam BigDecimal max)
    {
        return propertyService.getPropertiesBetweenPriceRange(min, max);
    }



    @PostMapping
    public Mono<PropertyDTO> saveProperty(@RequestBody Mono<PropertyDTO> productDtoMono)
    {
        return propertyService.saveProperty(productDtoMono);
    }


    @PutMapping("/{id}")
    public Mono<PropertyDTO> updateProperty(@PathVariable String id, @RequestBody Mono<PropertyDTO> productDtoMono)
    {
        return propertyService.updateProperty(id, productDtoMono);
    }


    @DeleteMapping("/{id}")
    public Mono<Void> deleteProperty(@PathVariable String id)
    {
        return propertyService.deleteProperty(id);
    }


    @DeleteMapping
    public Mono<Void> deleteAllProperties()
    {
        return propertyService.deleteAllProperties();
    }


    @PutMapping("/active/{id}")
    public Mono<PropertyDTO> setPropertyStatusActive(@PathVariable String id)
    {
        return propertyService.setPropertyStatusActive(id);
    }
}
