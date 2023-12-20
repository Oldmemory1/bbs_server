package com.mxy.bbs_server.service;

import com.mxy.bbs_server.entity.*;
import com.mxy.bbs_server.mapper.UserInfoMapper;
import com.mxy.bbs_server.mapper.UserMapper;
import com.mxy.bbs_server.response.user.UserResponse;
import com.mxy.bbs_server.response.user.UserResponseFailedReason;
import com.mxy.bbs_server.response.userinfo.LikeOrNotResponse;
import com.mxy.bbs_server.response.userinfo.UserInfoResponse;
import com.mxy.bbs_server.response.userinfo.UserInfoResponseFailedReason;
import com.mxy.bbs_server.utility.Const;
import com.mxy.bbs_server.utility.NginxHelper;
import com.mxy.bbs_server.utility.Utility;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {
    private final UserInfoMapper userInfoMapper;
    private final UserMapper userMapper;

    public UserInfoService(UserInfoMapper userInfoMapper, UserMapper userMapper) {
        this.userInfoMapper = userInfoMapper;
        this.userMapper = userMapper;
    }

    public UserInfoResponse update(UserInfoRequest userInfoRequest) throws IOException {
        final var userInfoToQuery = new UserInfoData(userInfoRequest.getUsername(), null, null, null, null, null);
        if (userInfoMapper.query(userInfoToQuery) == null) {
            return new UserInfoResponse(false, UserInfoResponseFailedReason.USERNAME_DOES_NOT_EXIST, null);
        }
        final var avatar = Utility.saveAvatar(userInfoRequest.getAvatar(), userInfoRequest.getUsername());
        final var previousUserInfo = userInfoMapper.query(userInfoToQuery);
        userInfoMapper.update(new UserInfoData(userInfoRequest.getUsername(), userInfoRequest.getNickname(), userInfoRequest.getPersonalSign(), avatar, previousUserInfo.getMyPosts(), previousUserInfo.getMyCollections()));
        final var userInfoRes = userInfoMapper.query(userInfoToQuery);
        return new UserInfoResponse(true, null, new UserInfo(userInfoRes.getUsername(),
                userInfoRes.getNickname(),
                userInfoRes.getPersonalSign(),
                userInfoRes.getAvatarUrl(),
                Utility.fromJson(userInfoRes.getMyPosts(), ArrayList.class),
                Utility.fromJson(userInfoRes.getMyCollections(), ArrayList.class)
        ));
    }

    public UserInfoResponse query(UserInfoRequest userInfoRequest) {
        var userInfoRes = userInfoMapper.query(new UserInfoData(userInfoRequest.getUsername(), null, null, null, null, null));
        if (userInfoRes == null) {
            return new UserInfoResponse(false, UserInfoResponseFailedReason.USERNAME_DOES_NOT_EXIST, null);
        }
        return new UserInfoResponse(true, null,
                new UserInfo(userInfoRes.getUsername(),
                        userInfoRes.getNickname(),
                        userInfoRes.getPersonalSign(),
                        userInfoRes.getAvatarUrl(),
                        Utility.fromJson(userInfoRes.getMyPosts(), ArrayList.class),
                        Utility.fromJson(userInfoRes.getMyCollections(), ArrayList.class)
                )
        );
    }

    public UserInfoResponse login(User user) {
        if (userMapper.query(user) == null) {
            //username不存在
            return new UserInfoResponse(false, UserInfoResponseFailedReason.USERNAME_DOES_NOT_EXIST, null);
        }
        if(userMapper.query(user) .getPassword()!=user.getPassword()){
            //密码不符
            return new UserInfoResponse(false, UserInfoResponseFailedReason.WRONG_PASSWORD, null);
        }
        return new UserInfoResponse(true, null, userInfoMapper.queryByUser(user));
    }

    public LikeOrNotResponse postQuery(LikeOrNotRequest lOn) {
        Boolean iLike=false;
        Boolean iFavorite=false;
        var userInfoRes = userInfoMapper.query(new UserInfoData(lOn.getUsername(), null, null, null, null, null));
        final List<String> myCollections = Utility.fromJson(userInfoRes.getMyCollections(), List.class);
        for (final String collection : myCollections) {
            if (collection.equals(lOn.getPostId())) {
                return new LikeOrNotResponse(null,true);
            }
        }
        return new LikeOrNotResponse(null,false);
    }
}
