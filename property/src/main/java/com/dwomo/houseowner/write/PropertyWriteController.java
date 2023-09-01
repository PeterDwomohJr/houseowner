package com.dwomo.houseowner.write;

import com.dwomo.houseowner.dto.PropertyEventDTO;
import com.dwomo.houseowner.events.domain.Message;
import com.dwomo.houseowner.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("api/v0/property")
public class PropertyWriteController {

    private final PropertyService propertyService;



    public PropertyWriteController(PropertyService propertyService)
    {
        // uses the constructor to inject the PropertyService
        this.propertyService = propertyService;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<PropertyEventDTO> save(@Valid @RequestBody Mono<PropertyEventDTO> propertyCreatedEventDTOMono)
    {
        return propertyService.save(propertyCreatedEventDTOMono);
    }


    @PutMapping()
    @PreAuthorize("hasAuthority('admin')")
    public Mono<PropertyEventDTO> update(@Valid @RequestBody Mono<PropertyEventDTO> propertyCreatedEventDTOMono)
    {
        return propertyService.update(propertyCreatedEventDTOMono);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    @PreAuthorize("hasAuthority('user')")
    public Mono<Void> delete()
    {
        // delete the property that is associated with the entered id
        return propertyService.delete();
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/soft")
    @PreAuthorize("hasAuthority('user')")
    public Mono<PropertyEventDTO> softDelete()
    {
        // call the property service to soft-delete the property by setting the delete field of property to true
        return propertyService.softDelete();
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public Mono<Void> deleteProperties()
    {
        // deletes all the properties that have been saved in the property collection
        return propertyService.deleteProperties();
    }


    @PatchMapping()
    @PreAuthorize("hasAuthority('admin')")
    public Mono<PropertyEventDTO> setPropertyStatus(@RequestParam String status)
    {
        return propertyService.setPropertyStatus(status);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/message")
    @PreAuthorize("hasAuthority('user')")
    public Mono<PropertyEventDTO> createMessage(@RequestBody List<Message> messages)
    {
        return propertyService.createMessage(messages);
    }
}
