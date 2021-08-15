package com.pazandish.weblog.domain;

import com.pazandish.weblog.config.AppConstant;

import javax.persistence.*;

@Entity
@Table(name = AppConstant.TABLE_PREFIX + "who_liked")
public class WhoLikesEntity {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "post", nullable = false)
    private PostEntity post;
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private UsersEntity user;

    public int getId() {
        return id;
    }


    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
