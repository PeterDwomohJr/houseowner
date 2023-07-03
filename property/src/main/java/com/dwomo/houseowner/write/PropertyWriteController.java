package com.dwomo.houseowner.write;

import com.dwomo.houseowner.aggregate.valueObject.Message;
import com.dwomo.houseowner.dto.PropertyDTO;
import com.dwomo.houseowner.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("v0/property")
public class PropertyWriteController {

    private final PropertyService propertyService;



    public PropertyWriteController(PropertyService propertyService)
    {
        // uses the constructor to inject the PropertyService
        this.propertyService = propertyService;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<PropertyDTO> saveProperty(@Valid @RequestBody Mono<PropertyDTO> propertyDTOMono)
    {
        return propertyService.saveProperty(propertyDTOMono);
    }


    @PutMapping("/{id}")
    public Mono<PropertyDTO> updateProperty(@PathVariable String id, @Valid @RequestBody Mono<PropertyDTO> propertyDTOMono)
    {
        return propertyService.updateProperty(id, propertyDTOMono);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Void> deleteProperty(@PathVariable String id)
    {
        // delete the property that is associated with the entered id
        return propertyService.deleteProperty(id);
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/soft")
    public Mono<Void> softDeleteProperty(@PathVariable String id)
    {
        // call the property service to soft-delete the property by setting the delete field of property to true
        return propertyService.softDeleteProperty(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public Mono<Void> deleteAllProperties()
    {
        // deletes all the properties that have been saved in the property collection
        return propertyService.deleteAllProperties();
    }


    @PatchMapping("/{id}")
    public Mono<PropertyDTO> setPropertyStatus(@PathVariable String id, @RequestParam String status)
    {
        return propertyService.setPropertyStatus(id, status);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{id}/message")
    public Mono<PropertyDTO> createMessage(@PathVariable String id, @RequestBody List<Message> messages)
    {
        return propertyService.createMessage(id, messages);
    }
}
