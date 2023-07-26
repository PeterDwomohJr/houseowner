package com.dwomo.userdata.events.domain;

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

    private String frontPictureURL;
    private String backPictureURI;
    private List<String> metadata;
}
