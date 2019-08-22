package org.nexters.cultureland.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "culture_rowdata")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CultureRawData {

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

    @Column
    private Long popular;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "culture_id")
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    @JsonBackReference
    private Culture culture;

    @OneToMany(mappedBy = "cultureRawData", cascade = CascadeType.ALL)
    private List<WishList> wishLists;

    @Builder
    public CultureRawData(String imageUrl, String title, String place, String startDate, String endDate, Long popular, Culture culture) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.popular = popular;
        this.culture = culture;
    }
}
