package domain;

import config.AppConstant;

import javax.persistence.*;

@Entity
@Table(name = AppConstant.TABLE_PREFIX + "Comment")
public class CommentEntity {
    @Id
    @GeneratedValue
    @Column
    Integer id;
    @Column
    String Content;
    @ManyToOne
    @JoinColumn(name = "userName")
    private UsersEntity user;
    @ManyToOne
    @JoinColumn(name = "post")
    private PostEntity post;

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
