package vangoh74.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vangoh74.backend.dto.AppUserDto;

import java.security.Principal;

@RestController
@RequestMapping("api/user/")
public class AppUserController {

    @GetMapping("me")
    public AppUserDto getLoggedInUser(Principal principal) {
        return new AppUserDto(principal.getName());
    }
}
