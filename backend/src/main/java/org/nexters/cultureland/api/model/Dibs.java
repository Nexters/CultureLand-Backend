package org.nexters.cultureland.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dibs {
    @Id
    //@Column(name = "rowId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageUrl;

    @Column
    private String title;

    @Column
    private String place;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "USER_SEQ")
    @JsonBackReference
    private User user;

    @Builder
    public Dibs(String imageUrl, String title, String place, String startDate, String endDate, User user) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }
}
