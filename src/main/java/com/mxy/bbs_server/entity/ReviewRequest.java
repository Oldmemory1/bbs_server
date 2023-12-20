package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ReviewRequest {
    private String id;
    private String targetPost;
    private String username;
    private String content;
    private MultipartFile[] images;
}
