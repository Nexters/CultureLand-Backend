package com.nexters.cultureland.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "CULTURE_ROWDATA")
@Getter @Setter @ToString @NoArgsConstructor
public class CultureRawData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String startDate;
    @Column(updatable = false)
    private String endDate;
    @Column(updatable = false)
    private String imageUrl;
    @Column(updatable = false)
    private String title;
    @Column(updatable = false)
    private String place;
    private Long popular;

    @ManyToOne
    private Culture culture;


    public CultureRawData(String imageUrl, String title, String place, String startDate, String endDate, Long popular, Culture culture) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.culture = culture;
        this.popular = popular;
    }

}
