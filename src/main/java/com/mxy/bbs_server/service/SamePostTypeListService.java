package com.mxy.bbs_server.service;

import com.mxy.bbs_server.entity.PostData;
import com.mxy.bbs_server.entity.PostType;
import com.mxy.bbs_server.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SamePostTypeListService {
    private final PostMapper postMapper;

    public SamePostTypeListService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
    public List<PostData> getPostListByPostType(PostType type){
        return postMapper.queryByPostType(type);
    }
}
