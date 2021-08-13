package domain;

import config.AppConstant;

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
    Integer id;
    @Column(name = "content")
    String content;
    @Column(name = "title")
    String title;
    @Column(name = "likes")
    Integer likes;
    @Column(name = "image")
    Byte[] image;
    @Column(name = "send_date")
    private java.sql.Timestamp sendDate = dateTime();
    @OneToMany(mappedBy = "post")
    private List<CommentEntity> commentEntityList = new ArrayList<CommentEntity>();
    @OneToMany(mappedBy = "post")
    private List<WhoLikesEntity> whoLikesEntityList = new ArrayList<WhoLikesEntity>();
    @ManyToOne
    @JoinColumn(name = "sender")
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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }


    private Timestamp dateTime() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
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
