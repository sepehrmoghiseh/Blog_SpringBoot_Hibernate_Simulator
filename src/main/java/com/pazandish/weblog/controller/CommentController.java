package com.pazandish.weblog.controller;

import com.pazandish.weblog.domain.CommentEntity;
import com.pazandish.weblog.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Resource
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/newcomment")
    public String newComment(@RequestBody CommentEntity commentEntity) {
        return commentService.commenting(commentEntity);
    }

    @GetMapping("/{id}/comments")
    public HashMap<String, String> comments(@PathVariable int id) {
        return commentService.postComments(id);
    }

    @GetMapping("/{userName}/usercomments")
    public HashMap<Integer, String> comments(@PathVariable String userName) {
        return commentService.userComments(userName);
    }

    @DeleteMapping("/{userName}/{id}/deletcomment")
    public String deleteComment(@PathVariable int id, @PathVariable String userName) {
        return commentService.deleteComments(id, userName);
    }

}
