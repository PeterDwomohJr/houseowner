package com.houseowner.property.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UpdateMessageDTO {
    private String messageId;
    private String propertyId;
    private String newContent;
}
