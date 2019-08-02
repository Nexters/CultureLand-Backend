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
    private String startDate;
    private String endDate;
    private String imageUrl;
    private String title;
    private String place;
    //private String

    @ManyToOne
    private Culture culture;


    public CultureRawData(String imageUrl, String title, String place, String startDate, String endDate, Culture culture) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.culture = culture;
    }

}
