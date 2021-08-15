package com.pazandish.weblog.controller;

import com.pazandish.weblog.domain.WhoLikesEntity;
import com.pazandish.weblog.service.WhoLikesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/liking")
public class WhoLikesController {
    @Resource
    private final WhoLikesService whoLikesService;

    public WhoLikesController(WhoLikesService whoLikesService) {
        this.whoLikesService = whoLikesService;
    }

    @PostMapping("/like")
    public void like(@RequestBody WhoLikesEntity whoLikesEntity) {

        whoLikesService.likeAction(whoLikesEntity);
    }

    @DeleteMapping("/dislike/{userName}/{id}")
    public String disLike(@PathVariable String userName,@PathVariable int id) {

       return whoLikesService.disLikeAction(userName,id);
    }
}
