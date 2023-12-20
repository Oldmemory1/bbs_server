package com.mxy.bbs_server.controller;

import com.mxy.bbs_server.entity.PostData;
import com.mxy.bbs_server.entity.PostType;
import com.mxy.bbs_server.service.SamePostTypeListService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/samePostTypeList")
public class SamePostTypeListController {
    private SamePostTypeListService service;

    public SamePostTypeListController(SamePostTypeListService service) {
        this.service = service;
    }
    @PostMapping("/query")
    public List<PostData> getSamePostTypeList(PostType type){
        return service.getPostListByPostType(type);
    }
}
