package com.mxy.bbs_server.service;

import com.mxy.bbs_server.entity.*;
import com.mxy.bbs_server.mapper.PostMapper;
import com.mxy.bbs_server.mapper.ReviewMapper;
import com.mxy.bbs_server.mapper.UserInfoMapper;
import com.mxy.bbs_server.response.action.ActionResponse;
import com.mxy.bbs_server.utility.Utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionService {

    private final PostMapper postMapper;

    private final ReviewMapper reviewMapper;

    private final UserInfoMapper userInfoMapper;

    public ActionService(PostMapper postMapper, ReviewMapper reviewMapper, UserInfoMapper userInfoMapper) {
        this.postMapper = postMapper;
        this.reviewMapper = reviewMapper;
        this.userInfoMapper = userInfoMapper;
    }

    public ActionResponse like(ActionRequest actionRequest) {
        if (actionRequest.getIsTargetPost()) {
            return likePost(actionRequest.getPostId());
        }
        return likeReview(actionRequest.getReviewId());
    }

    public ActionResponse favor(ActionRequest actionRequest, Boolean isCancel) {
        final var targetPost = actionRequest.getPostId();
        final var who = actionRequest.getUsername();
        final var userInfo = userInfoMapper.query(
                new UserInfoData(who, "", "", "", "", "")
        );
        final ArrayList<String> myCollections = Utility.fromJson(userInfo.getMyCollections(), ArrayList.class);
        if (!isCancel) {
            myCollections.add(targetPost);
            userInfoMapper.update(new UserInfoData(
                    who,
                    userInfo.getNickname(),
                    userInfo.getPersonalSign(),
                    userInfo.getAvatarUrl(),
                    userInfo.getMyPosts(),
                    Utility.toJson(myCollections)
            ));
        } else {
            final var newCollections = new ArrayList<String>();
            for (var postId: myCollections) {
                if (!postId.equals(targetPost)) {
                    newCollections.add(postId);
                }
            }
            userInfoMapper.update(new UserInfoData(
                    who,
                    userInfo.getNickname(),
                    userInfo.getPersonalSign(),
                    userInfo.getAvatarUrl(),
                    userInfo.getMyPosts(),
                    Utility.toJson(newCollections)
            ));
        }
        return new ActionResponse(true);
    }

    private ActionResponse likePost(String postId) {
        final var previousPost = postMapper.query(new PostData(postId, null, null, null, null, null, null, null,null));
        previousPost.setLikeNum(previousPost.getLikeNum() + 1);
        postMapper.update(previousPost);
        return new ActionResponse(true);
    }

    private ActionResponse likeReview(String reviewId) {
        final var previousReview = reviewMapper.query(new ReviewData(reviewId, null, null, null, null, null, null));
        previousReview.setLikeNum(previousReview.getLikeNum() + 1);
        reviewMapper.update(previousReview);
        return new ActionResponse(true);
    }
}
