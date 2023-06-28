package com.houseowner.property.controllers;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.aggregates.entities.Message;
import com.houseowner.property.services.MessageService;
import com.houseowner.property.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.logging.Logger;

@RestController
public class PropertyController {

    @Autowired
    private  PropertyService propertyService;
    @Autowired
    private MessageService messageService;
    private static final Logger LOGGER = Logger.getLogger(PropertyController.class.getName());


    //@PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/v0/property/{id}")
    public Mono<PropertyDTO> getProperty(@PathVariable String id)
    {
        LOGGER.info("You have successfully called the /v0/property/{id} API");
        return propertyService.getProperty(id);
    }


    @GetMapping("/v0/properties")
    public Flux<PropertyDTO> listProperties()
    {
        LOGGER.info("You have successfully called the /v0/properties API");
        return propertyService.listProperties();
    }


    //@PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/v0/message/{id}")
    public Mono<Message> getMessage(@PathVariable String id)
    {
        LOGGER.info("You have successfully called the /v0/message/{id} API");
        return messageService.getMessage(id);
    }


    @GetMapping("/v0/messages")
    public Flux<Message> listMessages()
    {
        LOGGER.info("You have successfully called the /v0/messages API");
        return messageService.listMessages();
    }
}
