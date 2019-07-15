package org.nexters.cultureland.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;


@Entity(name = "USER") @NoArgsConstructor
@Getter @ToString @Setter
public class User {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(unique = true, nullable = false)
    private String userId;

    private String userName;

    private String accessToken;

    /*
       OneTomMany 설정
     */
    //    private List<Post> posts;

    @Builder
    public User(String userId, String userName, String accessToken) {
        this.userId = userId;
        this.userName = userName;
        this.accessToken = accessToken;
    }
}
