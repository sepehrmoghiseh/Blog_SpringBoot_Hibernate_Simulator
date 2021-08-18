package com.pazandish.weblog.service;

import com.pazandish.weblog.domain.PostEntity;

import com.pazandish.weblog.domain.UsersEntity;
import com.pazandish.weblog.domain.WhoLikesEntity;
import com.pazandish.weblog.domain.enumeration.Role;
import com.pazandish.weblog.repository.UsersRepository;
import com.pazandish.weblog.repository.WhoLikesRepository;
import org.springframework.stereotype.Service;
import com.pazandish.weblog.repository.PostRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;
    private final WhoLikesRepository whoLikesRepository;

    public PostService(PostRepository postRepository, UsersRepository usersRepository, WhoLikesRepository whoLikesRepository) {
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
        this.whoLikesRepository = whoLikesRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public void save(PostEntity postEntity) {
        PostEntity postEntity1 = new PostEntity();
        if ((usersRepository.findByUserName(postEntity.getUser().getUserName()).get().getRole().equals(Role.ADMIN) || usersRepository.findByUserName(postEntity.getUser().getUserName()).get().getRole().equals(Role.SENDER)) && usersRepository.findByUserName(postEntity.getUser().getUserName()).get().isBlocked() == false) {

            postEntity1.setContent(postEntity.getContent());
            postEntity1.setLikes(0);
            postEntity1.setTitle(postEntity.getTitle());
            postEntity1.setUser(usersRepository.findByUserName(postEntity.getUser().getUserName()).get());
            postEntity1.setImage(postEntity.getImage());
            postEntity1.setLikes(0);
            postRepository.save(postEntity1);
        }


    }

    public List<String> whoLiked(int id) {
        List<String> userNames = new ArrayList<>();
        PostEntity postEntity = postRepository.findById(id).get();
        List<WhoLikesEntity> whoLikesEntities = postEntity.getWhoLikesEntityList();
        int i;
        for (i = 0; i < whoLikesEntities.size(); i++) {
            userNames.add(whoLikesEntities.get(i).getUser().getUserName());
        }
        return userNames;
    }

    public String deletePost(int id, String userName) {

        if (postRepository.findById(id).isPresent()) {
            PostEntity postEntity = postRepository.findById(id).get();
            if (postEntity.getUser().getUserName().equals(userName) || postEntity.getUser().getRole().equals(Role.ADMIN)) {
                whoLikesRepository.deleteByPost(postRepository.findById(id).get());
                postRepository.deleteById(id);
                return "successful";
            }
        }
        return "unsuccessful";

    }
    public List<Integer> findPost(String userName){
        UsersEntity usersEntity=usersRepository.findByUserName(userName).get();
       List<PostEntity>postEntities=  postRepository.findByUser(usersEntity);
       List<Integer>ids=new ArrayList<>();
        for (int i = 0; i <postEntities.size() ; i++) {
            ids.add(postEntities.get(i).getId());
        }
        return ids;
    }

    public long postCount(String userName){
        if(usersRepository.findByUserName(userName).isPresent()){
            UsersEntity usersEntity=usersRepository.findByUserName(userName).get();
            return postRepository.countAllByUser(usersEntity);
        }
        return 0;
    }

    public HashMap<Integer,String> searchByTitle(String title){
        List<PostEntity>postEntityList=postRepository.findByTitleContainingIgnoreCase(title);
        HashMap<Integer, String> searched=new HashMap<>();
        for (int i = 0; i <postEntityList.size() ; i++) {
            searched.put(i,postEntityList.get(i).getTitle()+": "+postEntityList.get(i).getContent());
        }
        return searched;
    }
    public HashMap<Integer,String> order(){
        List<PostEntity>postEntityList=postRepository.findAllByOrderBySendDateDesc();
        HashMap<Integer, String> searched=new HashMap<>();
        for (int i = 0; i <postEntityList.size() ; i++) {
            searched.put(i,postEntityList.get(i).getSendDate()+" _ "+postEntityList.get(i).getTitle()+": "+postEntityList.get(i).getContent());
        }
        return searched;
    }
}
