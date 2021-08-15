package com.pazandish.weblog.domain;

import com.pazandish.weblog.config.AppConstant;

import javax.persistence.*;

@Entity
@Table(name = AppConstant.TABLE_PREFIX + "Comment")
public class CommentEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "content", nullable = false)
    private String Content;
    @ManyToOne
    @JoinColumn(name = "userName", nullable = false)
    private UsersEntity user;
    @ManyToOne
    @JoinColumn(name = "post", nullable = false)
    private PostEntity post;

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }


    public UsersEntity getUsers() {
        return user;
    }

    public void setUsers(UsersEntity user) {
        this.user = user;
    }

    public PostEntity getPostEntity() {
        return post;
    }

    public void setPostEntity(PostEntity postEntity) {
        this.post = postEntity;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
