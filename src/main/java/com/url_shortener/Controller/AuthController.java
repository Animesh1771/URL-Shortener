package com.url_shortener.Controller;

import com.url_shortener.Service.UserDetailsImp;
import com.url_shortener.Service.UserService;
import com.url_shortener.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserService userService;
    private final UserDetailsImp userDetailsImp;
    private final JwtUtil jwtUtil;

    @PostMapping ("/register/{name}/{email}/{password}")
    public String register(@PathVariable String name,
                           @PathVariable String email,
                           @PathVariable String password) {
        try {
            userService.register(name,email,password);
            return "register success full"+name+" "+email+" "+password;
        } catch (IllegalArgumentException e) {
            return "register fail";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails userDetails = userDetailsImp.loadUserByUsername(email);

            return jwtUtil.generateToken(userDetails);
        }
        catch (Exception e){
            log.error("Exception occurred while login",e);
            return "Invalid credentials";
        }
    }

}
