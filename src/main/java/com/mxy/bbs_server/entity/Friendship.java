package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Friendship {
    private String username;
    private List<String> myConcerned;
    private List<String> myFans;
    private Integer numConcerned;
    private Integer numFans;
}
