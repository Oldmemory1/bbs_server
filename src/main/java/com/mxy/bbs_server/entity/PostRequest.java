package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class PostRequest {
    private String id;
    private String owner;
    private String title;
    private String content;
    private MultipartFile[] images;
}
