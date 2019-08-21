package org.nexters.cultureland.api.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "USER")
@NoArgsConstructor
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long seq;

    @Column(unique = true, nullable = false)
    private Long userId;

    private String userName;

    private LocalDateTime createdBy = LocalDateTime.now();

    /*
       OneTomMany 설정
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diary> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WishList> wishLists;

    @Builder
    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public void addDibsCulture(WishList wishList) {
        if (this.wishLists == null) {
            this.wishLists = new ArrayList<>();
        }
        this.wishLists.add(wishList);
    }
}
