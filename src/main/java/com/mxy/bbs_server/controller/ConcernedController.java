package com.mxy.bbs_server.controller;



import com.mxy.bbs_server.entity.ConcernedRequest;
import com.mxy.bbs_server.response.concerned.ConcernedResponse;
import com.mxy.bbs_server.service.ConcernedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Concerned")
@CrossOrigin("*")


public class ConcernedController {
    private final ConcernedService concernedService;

    public ConcernedController(ConcernedService concernedService) {
        this.concernedService = concernedService;
    }


    @PostMapping("/concerned")
    public ConcernedResponse concerned(@RequestBody ConcernedRequest concernedRequest) {
        return concernedService.concerned(concernedRequest, false);
    }

    @PostMapping("/cancelconcerned")
    public ConcernedResponse cancelconcerned(@RequestBody ConcernedRequest concernedRequest) {
        return concernedService.concerned(concernedRequest, true);
    }
}
