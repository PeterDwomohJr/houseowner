package com.dwomo.propertydata.write;


import com.dwomo.propertydata.dto.ConsumeTopicRequestDTO;
import com.dwomo.propertydata.dto.PropertyCreatedEventDTO;
import com.dwomo.propertydata.events.handlers.PropertyEventHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/property")
public class PropertyWriteController {

    private final PropertyEventHandler propertyEventHandler;



    public PropertyWriteController(PropertyEventHandler propertyEventHandler)
    {
        this.propertyEventHandler = propertyEventHandler;
    }



    @PostMapping()
    public void saveProperty(@RequestBody ConsumeTopicRequestDTO consumeTopicRequestDTO)
    {
        propertyEventHandler.saveProperty(consumeTopicRequestDTO);
    }
}
