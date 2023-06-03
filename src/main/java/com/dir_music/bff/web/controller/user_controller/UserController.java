package com.dir_music.bff.web.controller.user_controller;



import com.dir_music.bff.feign_controller.user_controller_feign.UserControllerFeign;
import com.dir_music.bff.feign_controller.user_controller_feign.input.UserControllerRegisterInput;
import com.dir_music.bff.feign_controller.user_controller_feign.output.UserControllerUserProfileOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController("UserController")
@RequestMapping("/user")
public class UserController {

    final UserControllerFeign userControllerFeign;

    @Autowired
    public UserController(UserControllerFeign userControllerFeign){
        this.userControllerFeign = userControllerFeign;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserControllerUserProfileOutput> getUser(@PathVariable("userId") Long id){

        long requesterId =
                (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isDetailed = requesterId == id;

        return userControllerFeign.getUserById(id, isDetailed);
    }

    @PostMapping("/register")
    public ResponseEntity<UserControllerUserProfileOutput> createUser(@RequestBody UserControllerRegisterInput userModel) {
        return userControllerFeign.createUser(userModel);
    }
}
