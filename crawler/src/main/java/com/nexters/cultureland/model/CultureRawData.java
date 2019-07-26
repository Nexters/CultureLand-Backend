package com.nexters.cultureland.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "CULTURE_ROWDATA")
@Getter @Setter @ToString @NoArgsConstructor
public class CultureRawData {
    @Id
    @GeneratedValue
    private Long id;

    private String imageUrl;
    private String title;
    private String place;

    @ManyToOne
    private Culture culture;

    public CultureRawData(String imageUrl, String title, String place, String date, Culture culture) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.date = date;
        this.culture = culture;
    }
}
