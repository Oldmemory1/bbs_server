package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostData {
    private String id;
    private String date;
    private String owner;
    private String title;
    private String content;
    private String images;
    private Integer likeNum;
    private String reviews;
}
