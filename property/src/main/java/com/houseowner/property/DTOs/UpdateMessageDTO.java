package com.houseowner.property.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public record UpdateMessageDTO(
  String messageId,
  String propertyId,
  String newContent
) {}
