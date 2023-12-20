package com.mxy.bbs_server.service;

import com.mxy.bbs_server.entity.UserDetails;
import com.mxy.bbs_server.entity.UserDetailsRequest;
import com.mxy.bbs_server.mapper.UserDetailsMapper;
import com.mxy.bbs_server.response.userDetails.UserDetailsResponse;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    private final UserDetailsMapper userDetailsMapper;

    public UserDetailsService(UserDetailsMapper userDetailsMapper) {
        this.userDetailsMapper = userDetailsMapper;
    }

    public UserDetailsResponse update(UserDetailsRequest userDetailsRequest){
        UserDetails details=userDetailsMapper.getUserDetailByUsername(userDetailsRequest.getUsername());
        userDetailsMapper.updateUserDetailsByUsername(new UserDetails(userDetailsRequest.getUsername(),  details.getGender(),userDetailsRequest.getBirthday(), details.getRegisterTime(),userDetailsRequest.getSignature()));
        return new UserDetailsResponse(true);
    }


}
