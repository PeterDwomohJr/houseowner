package com.houseowner.property.write;

import com.houseowner.property.DTOs.MessageDTO;
import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.DTOs.UpdateMessageDTO;
import com.houseowner.property.services.MessageService;
import com.houseowner.property.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.logging.Logger;

@RestController
public class PropertyWriteController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private MessageService messageService;
    private static final Logger LOGGER = Logger.getLogger(PropertyWriteController.class.getName());


    //@PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v0/property/create")
    public void createProperty(@RequestBody Mono<PropertyDTO> propertyDTO)
    {
        LOGGER.info("You have successfully called the /v0/property/create API");
        propertyService.saveProperty(propertyDTO);
    }


    //@PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("/v0/property/update/{id}")
    public void updateProperty(@PathVariable String id, @RequestBody Mono<PropertyDTO> propertyDTO)
    {
        LOGGER.info("You have successfully called the /v0/property/update/{id} API");
        propertyService.updateProperty(id, propertyDTO);
    }


    //@PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/v0/property/delete/{id}")
    public void deleteProperty(@PathVariable String id)
    {
        LOGGER.info("You have successfully called the /v0/property/delete/{id} API");
        propertyService.deleteProperty(id);
    }


    //@PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/v0/property/buy/{id}")
    public void buyProperty(@PathVariable String id)
    {
        LOGGER.info("You have successfully called the /v0/property/buy/{id} API");
        propertyService.buyProperty(id);
    }


    //@PreAuthorize("hasRole('EMPLOYEE')")
    @PatchMapping("/v0/property/status/{id}")
    public void changePropertyStatus(@PathVariable String id)
    {
        LOGGER.info("You have successfully called the /v0/property/status/{id} API");
        propertyService.changePropertyStatus(id);
    }


    //@PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v0/message/create")
    public void createMessage(@RequestBody MessageDTO messageDTO)
    {
        LOGGER.info("You have successfully called the /message/create API");
        messageService.createMessage(messageDTO);
    }


    //@PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("/v0/message/update")
    public void updateMessage(@RequestBody UpdateMessageDTO updateMessageDTO)
    {
        LOGGER.info("You have successfully called the /message/update API");
        messageService.updateMessage(updateMessageDTO);
    }


    //@PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("/v0/message/delete")
    public void deleteMessage(@PathVariable String id)
    {
        LOGGER.info("You have successfully called the /message/delete API");
        messageService.deleteMessage(id);
    }
}
