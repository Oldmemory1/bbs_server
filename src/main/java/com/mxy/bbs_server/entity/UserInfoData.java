package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoData {
    private String username;
    private String nickname;
    private String personalSign;
    private String avatarUrl;
    private String myPosts;
    private String myCollections;
}
