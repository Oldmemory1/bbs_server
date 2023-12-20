package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeOrNotRequest {
    private String postId;
    private String username;
}

