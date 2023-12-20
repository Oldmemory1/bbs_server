package com.mxy.bbs_server.controller;

import com.mxy.bbs_server.entity.FansAndConcernedRequest;
import com.mxy.bbs_server.response.FansAndConcerned.FansAndConcernedResponse;
import com.mxy.bbs_server.service.FansAndConcernedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/FansAndConcerned")
@CrossOrigin("*")
public class FansAndConcernedController {
    private final FansAndConcernedService fansAndConcernedService;

    public FansAndConcernedController(FansAndConcernedService fansAndConcernedService) {
        this.fansAndConcernedService = fansAndConcernedService;
    }


    @PostMapping("/get")
    public FansAndConcernedResponse fansAndConcernedResponse(@RequestBody FansAndConcernedRequest fansAndConcernedRequest) {
       return fansAndConcernedService.queryFansAndConcernedResponseByUsername(fansAndConcernedRequest);
    }
}
