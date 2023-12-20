package com.mxy.bbs_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConcernedRequest {
    private String concernedId;
    private String username;
}
