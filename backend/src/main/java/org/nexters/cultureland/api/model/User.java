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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Dibs> dibses;

    @Builder
    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public void addDibsCulture(Dibs dibs) {
        if (this.dibses == null) {
            this.dibses = new ArrayList<>();
        }
        this.dibses.add(dibs);
    }
}
