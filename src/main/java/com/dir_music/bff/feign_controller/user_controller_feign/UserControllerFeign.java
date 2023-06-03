package com.dir_music.bff.feign_controller.user_controller_feign;


import com.dir_music.bff.feign_controller.user_controller_feign.input.UserControllerRegisterInput;
import com.dir_music.bff.feign_controller.user_controller_feign.input.UserControllerUpdateInput;
import com.dir_music.bff.feign_controller.user_controller_feign.output.UserControllerUserProfileOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("dir-user-service")
public interface UserControllerFeign {



    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserControllerUserProfileOutput> getUserById(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "isDetailed", defaultValue = "false") boolean isDetailed
    );

    @PostMapping("/register")
    ResponseEntity<UserControllerUserProfileOutput> createUser(@RequestBody UserControllerRegisterInput userModel);

    @PutMapping(path = "/{userId}")
    ResponseEntity<UserControllerUserProfileOutput> updateUser
            (@RequestBody UserControllerUpdateInput userControllerUpdateInput,
             @PathVariable("userId") Long userId);

    @DeleteMapping(path = "/{userId}")
    ResponseEntity<Object> deleteUser(
            @PathVariable("userId") Long userId);

}
