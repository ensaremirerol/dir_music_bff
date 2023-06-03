package com.dir_music.bff.feign_controller.user_controller_feign.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserControllerRegisterInput{
    private String userName;
    private String phoneNumber;
    private String password;
    private Date dateOfBirth;
}
