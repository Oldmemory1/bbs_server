package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReviewData {
    private String id;
    private String targetPost;
    private String date;
    private String username;
    private String content;
    private String images;
    private Integer likeNum;
}
