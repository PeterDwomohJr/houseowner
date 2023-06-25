package com.houseowner.property.write;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@RestController
public class PropertyWriteController {

    @Autowired
    private PropertyService propertyService;
    private static final Logger logger = Logger.getLogger(PropertyWriteController.class.getName());


    @PreAuthorize("hasRole('AUTHENTICATED_USER)")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v0/property/create")
    public void createProperty(@RequestBody PropertyDTO propertyDTO)
    {
        logger.info("You have successfully called the /v0/property/create API");
        propertyService.createProperty(propertyDTO);
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
