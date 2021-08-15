package com.pazandish.weblog.domain;

import com.pazandish.weblog.config.AppConstant;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = AppConstant.TABLE_PREFIX + "Posts")
public class PostEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "likes")
    private Integer likes;
    @Column(name = "image")
    private byte[] image;
    @Column(name = "send_date")
    private java.sql.Timestamp sendDate = dateTime();
    @OneToMany(mappedBy = "post")
    private List<CommentEntity> commentEntityList = new ArrayList<CommentEntity>();
    @OneToMany(mappedBy = "post")
    private List<WhoLikesEntity> whoLikesEntityList = new ArrayList<WhoLikesEntity>();
    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private UsersEntity user;

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    private Timestamp dateTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss a");
        String strDate = dateFormat.format(date);

        try {
            return new Timestamp(dateFormat.parse(strDate).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public List<WhoLikesEntity> getWhoLikesEntityList() {
        return whoLikesEntityList;
    }

    public void setWhoLikesEntityList(List<WhoLikesEntity> whoLikesEntityList) {
        this.whoLikesEntityList = whoLikesEntityList;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
