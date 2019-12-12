package main.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RedirectController {

    @RequestMapping(value = {"/register", "/login", "/main", "/profile"})
    public String redirectExample() {
        return "redirect:" + "/";
    }
}