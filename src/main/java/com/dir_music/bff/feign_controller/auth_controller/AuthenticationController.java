package com.dir_music.bff.feign_controller.auth_controller;


import com.dir_music.bff.feign_controller.auth_controller.input.AuthControllerChangePasswordInput;
import com.dir_music.bff.feign_controller.auth_controller.input.AuthControllerPasswordLoginInput;
import com.dir_music.bff.feign_controller.auth_controller.input.AuthControllerRegisterInput;
import com.dir_music.bff.feign_controller.auth_controller.input.TokenValidationControllerInput;
import com.dir_music.bff.feign_controller.auth_controller.output.AuthControllerAccessTokenOutput;
import com.dir_music.bff.feign_controller.auth_controller.output.AuthControllerPasswordLoginOutput;
import com.dir_music.bff.feign_controller.auth_controller.output.RegisterOutput;
import com.dir_music.bff.feign_controller.auth_controller.output.TokenValidationControllerOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "dir-authentication-service")
public interface AuthenticationController {

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    ResponseEntity<RegisterOutput> register(
            @RequestBody AuthControllerRegisterInput authControllerRegisterInput
    );

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    ResponseEntity<AuthControllerPasswordLoginOutput> login(
            @RequestBody AuthControllerPasswordLoginInput authInput
    );

    @GetMapping(path = "/refresh", produces = "application/json")
    ResponseEntity<AuthControllerAccessTokenOutput> refreshToken(
            @RequestParam(name = "refreshToken") String refreshToken
    );

    @PostMapping(path = "/change-password", consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> changePassword(
            @RequestBody AuthControllerChangePasswordInput authControllerChangePasswordInput
    );

    @PostMapping(path = "/token-validation/get-claims", consumes = "application/json", produces = "application/json")
    ResponseEntity<TokenValidationControllerOutput> getClaims(
            @RequestBody TokenValidationControllerInput tokenValidationControllerInput
    );
}
