package com.dwomo.houseowner.aggregate.valueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Picture {

    private String pictureURL;
    private List<String> metadata;
}
