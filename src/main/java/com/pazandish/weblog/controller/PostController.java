package com.pazandish.weblog.controller;

import com.pazandish.weblog.domain.PostEntity;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import com.pazandish.weblog.service.PostService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Resource
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/postsave")
    public void postSave(@RequestBody PostEntity postEntity) {
        postService.save(postEntity);
    }

    @GetMapping("/{id}/likes")
    public List<String> likes(@PathVariable int id) {
        return postService.whoLiked(id);
    }

    @DeleteMapping("/{userName}/{id}/delete")
    public String deletePost(@PathVariable int id, @PathVariable String userName) {
        return postService.deletePost(id, userName);
    }

    @GetMapping("/{userName}/findposts")
    public List<Integer>findPosts(@PathVariable String userName){
       return postService.findPost(userName);
    }

}
