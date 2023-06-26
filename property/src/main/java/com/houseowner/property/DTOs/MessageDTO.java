package com.houseowner.property.DTOs;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageDTO {

    private String propertyId;
    private String content;
    private String senderName;
    private String receiverName;
    private boolean deleted;
}
