package com.url_shortener.Controller;

import com.url_shortener.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {
    private final UserService userService;

    @PostMapping ("/register/{name}/{email}/{password}")
    public String register(@PathVariable String name,
                           @PathVariable String email,
                           @PathVariable String password) {
        try {
            //log.info("userService called");
            userService.register(name,email,password);
            return "register success full"+name+" "+email+" "+password;
        } catch (IllegalArgumentException e) {
            return "register fail";
        }
    }

}
