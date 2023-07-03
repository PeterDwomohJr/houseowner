package com.dwomo.houseowner.aggregate.valueObject;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Message {

    //@Pattern(
            //regexp = "^(?!.*(?:\\b\\d{10}\\b|(?i)at\\b|@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,4}\\b|\\b(?:facebook|twitter|instagram)\\.com\\b)).*$",
            //message = "You cannot share any personal communication information.")
    private String message;
    @CreatedBy
    private String createdBy;
    private LocalDateTime timeCreated = LocalDateTime.now();
}
