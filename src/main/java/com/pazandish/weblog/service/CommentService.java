package com.pazandish.weblog.service;

import com.pazandish.weblog.domain.CommentEntity;
import com.pazandish.weblog.domain.PostEntity;
import com.pazandish.weblog.domain.UsersEntity;
import com.pazandish.weblog.domain.enumeration.Role;
import com.pazandish.weblog.repository.CommentRepository;
import com.pazandish.weblog.repository.PostRepository;
import com.pazandish.weblog.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UsersRepository usersRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.usersRepository = usersRepository;
        this.postRepository = postRepository;
    }

    public String commenting(CommentEntity commentEntity) {
        CommentEntity commentEntity1 = new CommentEntity();
        if (!usersRepository.findByUserName(commentEntity.getUser().getUserName()).get().isBlocked()
                && postRepository.findById(commentEntity.getPost().getId()).isPresent() &&
                commentRepository.findByPostAndUser(postRepository.findById(commentEntity.getPost().getId()).get(), usersRepository.findByUserName(commentEntity.getUser().getUserName()).get()).isEmpty()) {
            commentEntity1.setPost(postRepository.findById(commentEntity.getPost().getId()).get());
            commentEntity1.setUser(usersRepository.findByUserName(commentEntity.getUser().getUserName()).get());
            commentEntity1.setContent(commentEntity.getContent());
            commentRepository.save(commentEntity1);
            return "user: " + commentEntity1.getUser().getUserName() + "\n post: " + commentEntity1.getPost().getId() +
                    "\n content: " + commentEntity1.getContent();
        }
        return "unsuccessful";
    }

    public HashMap<String, String> postComments(int id) {
        PostEntity postEntity = postRepository.findById(id).get();
        HashMap<String, String> commentList = new HashMap<String, String>();
        for (int i = 0; i < postEntity.getCommentEntityList().size(); i++) {
            commentList.put(postEntity.getCommentEntityList().get(i).getUser().getUserName(), postEntity.getCommentEntityList().get(i).getContent());
        }
        return commentList;
    }

    public HashMap<Integer, String> userComments(String userName) {
        UsersEntity usersEntity = usersRepository.findByUserName(userName).get();
        HashMap<Integer, String> commentList = new HashMap<>();
        List<CommentEntity> commentEntity = commentRepository.findByUser(usersEntity);
        for (int i = 0; i < commentEntity.size(); i++) {
            commentList.put(commentEntity.get(i).getPost().getId(), commentEntity.get(i).getContent());
        }
        return commentList;
    }

    public String deleteComments(int id, String userName) {
        if ((commentRepository.findById(id).get().getUser().equals(usersRepository.findByUserName(userName).get()) ||
                usersRepository.findByUserName(userName).get().getRole().equals(Role.ADMIN)) &&
                !usersRepository.findByUserName(userName).get().isBlocked()) {
            commentRepository.deleteById(id);
            return "successful";
        }
        return "unsuccessful";
    }
}