package com.dir_music.bff.web.controller.auth_controller;


import com.dir_music.bff.feign_controller.auth_controller.AuthenticationControllerFeign;
import com.dir_music.bff.feign_controller.auth_controller.input.AuthControllerChangePasswordInput;
import com.dir_music.bff.feign_controller.auth_controller.input.AuthControllerPasswordLoginInput;
import com.dir_music.bff.feign_controller.auth_controller.input.AuthControllerRegisterInput;
import com.dir_music.bff.feign_controller.auth_controller.input.TokenValidationControllerInput;
import com.dir_music.bff.feign_controller.auth_controller.output.AuthControllerAccessTokenOutput;
import com.dir_music.bff.feign_controller.auth_controller.output.AuthControllerPasswordLoginOutput;
import com.dir_music.bff.feign_controller.auth_controller.output.RegisterOutput;
import com.dir_music.bff.feign_controller.auth_controller.output.TokenValidationControllerOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AuthenticationController")
@RequestMapping("/")
public class AuthenticationController {

    final AuthenticationControllerFeign authenticationControllerFeign;

    public AuthenticationController(AuthenticationControllerFeign authenticationControllerFeign) {
        this.authenticationControllerFeign = authenticationControllerFeign;
    }


    @CrossOrigin
    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    ResponseEntity<RegisterOutput> register(
            @RequestBody AuthControllerRegisterInput authControllerRegisterInput
    ) {
        return authenticationControllerFeign.register(authControllerRegisterInput);
    }

    @CrossOrigin
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    ResponseEntity<AuthControllerPasswordLoginOutput> login(
            @RequestBody AuthControllerPasswordLoginInput authInput
    ) {
        return authenticationControllerFeign.login(authInput);
    }

    @CrossOrigin
    @GetMapping(path = "/refresh", produces = "application/json")
    ResponseEntity<AuthControllerAccessTokenOutput> refreshToken(
            @RequestParam(name = "refreshToken") String refreshToken
    ) {
        return authenticationControllerFeign.refreshToken(refreshToken);
    }

    @CrossOrigin
    @PostMapping(path = "/change-password", consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> changePassword(
            @RequestBody AuthControllerChangePasswordInput authControllerChangePasswordInput
    ) {
        return authenticationControllerFeign.changePassword(authControllerChangePasswordInput);
    }

    @CrossOrigin
    @PostMapping(path = "/token-validation/get-claims", consumes = "application/json", produces = "application/json")
    ResponseEntity<TokenValidationControllerOutput> getClaims(
            @RequestBody TokenValidationControllerInput tokenValidationControllerInput
    ) {
        return authenticationControllerFeign.getClaims(tokenValidationControllerInput);
    }

}
