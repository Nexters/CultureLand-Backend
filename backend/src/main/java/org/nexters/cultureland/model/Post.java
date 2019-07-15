package org.nexters.cultureland.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "POST")
@Getter @Setter @NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long seq;

    private String title;

    /*
        ManyToOne 설정
     */
    //    private User user;
}
