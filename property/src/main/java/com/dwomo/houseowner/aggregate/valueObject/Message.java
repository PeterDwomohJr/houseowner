package com.dwomo.houseowner.aggregate.valueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Message {

    private String message;
    @CreatedBy
    private String createdBy;
    private LocalDateTime timeCreated = LocalDateTime.now();
}
