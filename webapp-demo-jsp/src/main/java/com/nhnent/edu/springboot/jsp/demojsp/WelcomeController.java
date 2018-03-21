package com.nhnent.edu.springboot.jsp.demojsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping({"/",""})
    public String welcome(ModelMap model) {
        model.put("message", "Welcome to SpringBoot");
        return "welcome";
    }
}
