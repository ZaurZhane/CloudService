package ru.netology.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.dto.LoginRequest;
import ru.netology.dto.AuthenticationResponse;
import ru.netology.service.AuthenticationService;
import org.springframework.http.HttpStatus;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/cloud")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest Request) {

        return authenticationService.login(Request);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String token) {

        authenticationService.logout(token);

        return ResponseEntity.ok(HttpStatus.OK);

    }

}
