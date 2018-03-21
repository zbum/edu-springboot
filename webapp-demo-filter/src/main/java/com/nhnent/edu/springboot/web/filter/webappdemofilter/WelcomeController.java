package com.nhnent.edu.springboot.web.filter.webappdemofilter;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping({"/", ""})
    public String welcome(ModelMap model) {
        model.put("message", "Welcome to SpringBoot");
        return "welcome";
    }
}
