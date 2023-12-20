package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ReviewRequest {
    @Setter
    private String id;
    private String targetPost;
    private String username;
    private String content;
    private MultipartFile[] images;
}
