package com.pazandish.weblog.service;

import com.pazandish.weblog.domain.PostEntity;
import com.pazandish.weblog.domain.WhoLikesEntity;
import com.pazandish.weblog.repository.PostRepository;
import com.pazandish.weblog.repository.UsersRepository;
import com.pazandish.weblog.repository.WhoLikesRepository;
import org.springframework.stereotype.Service;

@Service
public class WhoLikesService {
    private final WhoLikesRepository whoLikesRepository;
    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    public WhoLikesService(WhoLikesRepository whoLikesRepository, PostRepository postRepository, UsersRepository usersRepository) {
        this.whoLikesRepository = whoLikesRepository;
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
    }

    public void likeAction(WhoLikesEntity whoLikesEntity) {
        WhoLikesEntity whoLikesEntity1 = new WhoLikesEntity();
        if (usersRepository.findByUserName(whoLikesEntity.getUser().getUserName()).get().isBlocked() == false &&
                whoLikesRepository.findByPostAndUser(postRepository.findById(whoLikesEntity.getPost().getId()).get(), usersRepository.findByUserName(whoLikesEntity.getUser().getUserName()).get()).isEmpty()) {
            whoLikesEntity1.setUser(usersRepository.findByUserName(whoLikesEntity.getUser().getUserName()).get());
            whoLikesEntity1.setPost(postRepository.findById(whoLikesEntity.getPost().getId()).get());
            System.out.println(postRepository.findById(whoLikesEntity.getPost().getId()).get().getWhoLikesEntityList());

            whoLikesRepository.save(whoLikesEntity1);
        }
        postRepository.setLike(postRepository.findById(whoLikesEntity.getPost().getId()).get().getWhoLikesEntityList().size(), postRepository.findById(whoLikesEntity.getPost().getId()).get().getId());
    }

    public String disLikeAction(String userName, int id) {
        if (whoLikesRepository.findByPostAndUser(postRepository.findById(id).get(), usersRepository.findByUserName(userName).get()).isPresent()) {
            whoLikesRepository.deleteByUserAndPost(usersRepository.findByUserName(userName).get(), postRepository.findById(id).get());
            return "successful";
        }
        postRepository.setLike(postRepository.findById(id).get().getWhoLikesEntityList().size(), postRepository.findById(id).get().getId());

        return "unsuccessful";
    }
}