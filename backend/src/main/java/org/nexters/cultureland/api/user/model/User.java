package org.nexters.cultureland.api.user.model;


import lombok.*;
import org.nexters.cultureland.api.diary.model.Diary;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "USER") @NoArgsConstructor
@Getter @ToString
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diary> posts;

    @Builder
    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
