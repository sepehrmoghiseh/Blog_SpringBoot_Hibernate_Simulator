package com.pazandish.weblog.repository;

import com.pazandish.weblog.domain.PostEntity;
import com.pazandish.weblog.domain.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {


    Optional<PostEntity> findByTitle(String title);

    @Query("update PostEntity post set post.likes=:newLike where post.id=:id2")
    @Transactional
    @Modifying
    int setLike(@Param("newLike") int newLike, @Param("id2") int id2);

    List<PostEntity> findByUser(UsersEntity user);


}
