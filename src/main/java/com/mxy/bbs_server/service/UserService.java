package com.mxy.bbs_server.service;

import com.mxy.bbs_server.entity.FriendshipData;
import com.mxy.bbs_server.entity.User;
import com.mxy.bbs_server.entity.UserDetails;
import com.mxy.bbs_server.entity.UserInfoData;
import com.mxy.bbs_server.mapper.FriendMapper;
import com.mxy.bbs_server.mapper.UserDetailsMapper;
import com.mxy.bbs_server.mapper.UserInfoMapper;
import com.mxy.bbs_server.mapper.UserMapper;
import com.mxy.bbs_server.response.user.UserResponse;
import com.mxy.bbs_server.response.user.UserResponseFailedReason;
import com.mxy.bbs_server.utility.Const;
import com.mxy.bbs_server.utility.NginxHelper;
import com.mxy.bbs_server.utility.Utility;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final FriendMapper friendMapper;
    private final UserInfoMapper userInfoMapper;
    private UserDetailsMapper userDetailsMapper;

    public UserService(UserMapper userMapper, FriendMapper friendMapper, UserInfoMapper userInfoMapper) {
        this.userMapper = userMapper;
        this.friendMapper = friendMapper;
        this.userInfoMapper = userInfoMapper;
    }

    public UserResponse add(User user) {
        if (userMapper.query(user) != null) {
            //username已经存在
            return new UserResponse(false, UserResponseFailedReason.USERNAME_ALREADY_EXIST, user);
        }
        userMapper.add(user);
        friendMapper.add(new FriendshipData(
                user.getUsername(),
                null,
                null,
                0,
                0

        ));
        userInfoMapper.add(new UserInfoData(
                user.getUsername(),
                Const.DEFAULT_NICKNAME,
                Const.DEFAULT_PERSONAL_SIGN,
                NginxHelper.getAbsoluteUrl(Const.DEFAULT_AVATAR_URL),
                Utility.toJson(Const.DEFAULT_POSTS),
                Utility.toJson(Const.DEFAULT_COLLECTIONS))
        );
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        userDetailsMapper.addUserDetails(new UserDetails(user.getUsername(),"","",dtf.format(now),""));
        return new UserResponse(true, null, user);
    }

    public UserResponse query(User user) {
        var userRes = userMapper.query(user);
        if (userRes == null) {
            return new UserResponse(false, UserResponseFailedReason.USERNAME_DOES_NOT_EXIST, user);
        }
        return new UserResponse(true, null, userRes);
    }
}
