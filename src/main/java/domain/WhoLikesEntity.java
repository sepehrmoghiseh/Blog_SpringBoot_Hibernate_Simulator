package domain;

import config.AppConstant;

import javax.persistence.*;

@Entity
@Table(name = AppConstant.TABLE_PREFIX + "who_liked")
public class WhoLikesEntity {
    @Id
    @GeneratedValue
    int id;
    @ManyToOne
    @JoinColumn(name = "post")
    private PostEntity post;
    @ManyToOne
    @JoinColumn(name = "user")
    private UsersEntity user;
}
