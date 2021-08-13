package service;

import domain.PostEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PostRepository;

import javax.annotation.PostConstruct;
@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;

    @PostConstruct
    public void gatherHouse() {
        PostEntity postEntity = new PostEntity();
        postEntity.setContent("heyooo");
        postRepository.save(postEntity);

    }
}
