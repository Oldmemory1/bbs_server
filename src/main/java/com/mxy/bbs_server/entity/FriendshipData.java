package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FriendshipData {
    private String username;
    private String myConcerned;
    private String myFans;
    private Integer numConcerned;
    private Integer numFans;
}

