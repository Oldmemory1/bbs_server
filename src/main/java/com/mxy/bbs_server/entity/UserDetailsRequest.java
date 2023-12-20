package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsRequest {
    private String username;
    private String nickname;
    private String avatar;
    private String birthday;
    private String gender;
    private String signature;
}
