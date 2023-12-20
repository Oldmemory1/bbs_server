package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 处理点赞和收藏请求
 */
@Data
@AllArgsConstructor
public class ActionRequest {
    private Boolean isTargetPost;
    private String postId;
    private String reviewId;
    private String username;
}
