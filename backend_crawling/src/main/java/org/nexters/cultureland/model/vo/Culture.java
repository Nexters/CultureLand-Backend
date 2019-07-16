package org.nexters.cultureland.model.vo;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Culture")
public class Culture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cid")
    private Long cid;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "img",nullable = false)
    private String img;

    @Column(name = "genre",nullable = false)
    private String genre;

    @Column(name = "start_date",nullable = false)
    private String start_date;

    @Column(name = "end_date")
    private String end_date;

    @Column(name = "place",nullable = false)
    private String place;

    public Culture() {
        System.out.println("null?");
    }

    @Builder
    public Culture(String title, String img, String genre, String start_date, String end_date, String place) {
        this.title = title;
        this.img = img;
        this.genre = genre;
        this.start_date = start_date;
        this.end_date = end_date;
        this.place = place;
    }

}
