package com.mxy.bbs_server.controller;

import com.mxy.bbs_server.entity.PostRequest;
import com.mxy.bbs_server.entity.PostType;
import com.mxy.bbs_server.response.post.PostResponse;
import com.mxy.bbs_server.service.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    public PostResponse add(PostRequest postRequest) throws IOException {
        return postService.add(postRequest);
    }

    @PostMapping("/addWithoutImage")
    public PostResponse add(String id, String owner, String title, String content, PostType type) throws IOException {
        return postService.add(new PostRequest(id, owner, title, content, new MultipartFile[0],type));
    }

    @PostMapping("/query")
    public PostResponse query(@RequestBody PostRequest postRequest) {
        return postService.query(postRequest);
    }

    @GetMapping("/query/{postId}")
    public PostResponse query(@PathVariable("postId") String postId) {
        return postService.query(new PostRequest(postId, "", "", "", null,null));
    }
}
