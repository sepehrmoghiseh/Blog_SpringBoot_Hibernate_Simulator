package com.pazandish.weblog.domain;

import com.pazandish.weblog.config.AppConstant;
import com.pazandish.weblog.domain.enumeration.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = AppConstant.TABLE_PREFIX + "users")
public class UsersEntity {

    @OneToMany(mappedBy = "user")
    private final List<CommentEntity> commentList = new ArrayList<CommentEntity>();
    @OneToMany(mappedBy = "user")
    private final List<PostEntity> postEntityList = new ArrayList<PostEntity>();
    @OneToMany(mappedBy = "user")
    private final List<WhoLikesEntity> whoLikesEntityList = new ArrayList<WhoLikesEntity>();
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(unique = true, name = "user_name", nullable = false)
    private String userName;
    @Column(name = "password", nullable = false)
    private String passWord;
    @Column(name = "email_Address", nullable = false)
    private String emailAdress;
    @Column(name = "blocked", nullable = false)
    private boolean blocked;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = Role.USER;
    }

    public List<CommentEntity> getCommentList() {
        return commentList;
    }

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public List<WhoLikesEntity> getWhoLikesEntityList() {
        return whoLikesEntityList;
    }
}
