package com.dwomo.userdata.controllers;

import com.dwomo.userdata.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/property")
public class PropertyController {

    private final UserService userService;



    public PropertyController(UserService userService)
    {
        this.userService = userService;
    }
}
