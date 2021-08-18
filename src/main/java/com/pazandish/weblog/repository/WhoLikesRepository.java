package com.pazandish.weblog.repository;

import com.pazandish.weblog.domain.PostEntity;
import com.pazandish.weblog.domain.UsersEntity;
import com.pazandish.weblog.domain.WhoLikesEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface WhoLikesRepository extends JpaRepository<WhoLikesEntity, Integer> {

    Optional<WhoLikesEntity> findByPostAndUser(PostEntity postEntity, UsersEntity usersEntity);

    @Transactional
    void deleteByPost(PostEntity post);

    @Transactional
    void deleteByUserAndPost(UsersEntity usersEntity,PostEntity postEntity);


}
