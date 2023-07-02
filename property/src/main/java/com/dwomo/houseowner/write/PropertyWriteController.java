package com.dwomo.houseowner.write;

import com.dwomo.houseowner.dto.PropertyDTO;
import com.dwomo.houseowner.service.PropertyService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/property")
public class PropertyWriteController {

    private final PropertyService propertyService;



    public PropertyWriteController(PropertyService propertyService) {
        this.propertyService = propertyService;
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
        // call the property service to soft-delete the property by setting the delete field of property to true
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
