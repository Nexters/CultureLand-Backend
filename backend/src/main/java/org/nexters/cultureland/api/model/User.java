package org.nexters.cultureland.api.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "USER")
@NoArgsConstructor
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(unique = true, nullable = false)
    private Long userId;

    private String userName;

    private String eMail;

    private LocalDateTime createdBy = LocalDateTime.now();

    /*
       OneTomMany 설정
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diary> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WishList> wishLists;

    @Builder
    public User(Long userId, String userName, String eMail) {
        this.eMail = eMail;
        this.userId = userId;
        this.userName = userName;
    }

    public void updateUser(String userName, String eMail) {
        this.eMail = eMail;
        this.userName = userName;
    }
}
