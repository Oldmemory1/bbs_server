package com.mxy.bbs_server.service;

import com.mxy.bbs_server.entity.*;
import com.mxy.bbs_server.mapper.FriendMapper;
import com.mxy.bbs_server.response.FansAndConcerned.FansAndConcernedResponse;
import com.mxy.bbs_server.response.review.ReviewResponse;
import com.mxy.bbs_server.response.review.ReviewResponseFailedReason;
import com.mxy.bbs_server.utility.Utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FansAndConcernedService {
    private final FriendMapper friendMapper;

    public FansAndConcernedService(FriendMapper friendMapper) {
        this.friendMapper = friendMapper;
    }
    public FansAndConcernedResponse queryFansAndConcernedResponseByUsername(FansAndConcernedRequest fansAndConcernedRequest){
        final var friendshipData =friendMapper.query(new FriendshipData(fansAndConcernedRequest.getUsername(),
                null,null,null,null));
        return new FansAndConcernedResponse(
                true,
                new Friendship(friendshipData.getUsername(),Utility.fromJson(friendshipData.getMyConcerned(), ArrayList.class),
                        Utility.fromJson(friendshipData.getMyFans(), ArrayList.class),
                        friendshipData.getNumConcerned(),
                        friendshipData.getNumFans()));
    }




}
