package com.pazandish.weblog.repository;

import com.pazandish.weblog.domain.CommentEntity;
import com.pazandish.weblog.domain.PostEntity;
import com.pazandish.weblog.domain.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    Optional<CommentEntity> findByPostAndUser(PostEntity postEntity, UsersEntity usersEntity);
    List<CommentEntity> findByUser(UsersEntity usersEntity);
}
