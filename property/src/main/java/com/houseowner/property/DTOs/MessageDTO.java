package com.houseowner.property.DTOs;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public record MessageDTO(

        String propertyId,
        String content,
        String senderName,
        String receiverName,
        boolean deleted
) {}
