package org.nexters.cultureland.api.user.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity(name = "USER") @NoArgsConstructor
@Getter @ToString @Setter
public class User {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(unique = true, nullable = false)
    private Long userId;

    private String userName;

    private LocalDateTime createdBy = LocalDateTime.now();

    /*
       OneTomMany 설정
     */
    //    private List<Post> posts;

    @Builder
    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
