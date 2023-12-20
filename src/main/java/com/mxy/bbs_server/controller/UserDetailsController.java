package com.mxy.bbs_server.controller;

import com.mxy.bbs_server.entity.User;
import com.mxy.bbs_server.entity.UserDetailsRequest;
import com.mxy.bbs_server.response.user.UserResponse;
import com.mxy.bbs_server.response.userDetails.UserDetailsResponse;
import com.mxy.bbs_server.service.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/UserDetails")
public class UserDetailsController {
    private UserDetailsService userDetailsService;
    public UserDetailsResponse update(UserDetailsRequest request){
        return userDetailsService.update(request);
    }




}
