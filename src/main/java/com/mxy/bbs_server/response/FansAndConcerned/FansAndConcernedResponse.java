package com.mxy.bbs_server.response.FansAndConcerned;

import com.mxy.bbs_server.entity.Friendship;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FansAndConcernedResponse {
    private Boolean success;
    private Friendship friendship;
}
