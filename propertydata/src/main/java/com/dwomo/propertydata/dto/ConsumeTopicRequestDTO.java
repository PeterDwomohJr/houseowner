package com.dwomo.propertydata.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumeTopicRequestDTO {

    private String topicName;
    private String subscriptionName;
}
