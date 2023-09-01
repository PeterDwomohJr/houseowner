package com.dwomo.houseowner.events.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Message {

    private String message;
    @CreatedBy
    private String createdBy;
    private Date createdAt = new Date();
}
