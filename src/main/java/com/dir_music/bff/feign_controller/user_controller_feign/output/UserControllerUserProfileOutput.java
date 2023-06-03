package com.dir_music.bff.feign_controller.user_controller_feign.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserControllerUserProfileOutput {
    private String userName;
    private Integer followerCount;
    private boolean includePrivateInformation;
    private Date dateOfBirth;
    private String phoneNumber;
}
