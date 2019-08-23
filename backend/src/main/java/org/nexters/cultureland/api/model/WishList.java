package org.nexters.cultureland.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity(name = "wish_list")
@Table(name = "wish_list")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "culture_rowdata_id")
    @JsonBackReference
    private CultureRawData cultureRawData;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "USER_SEQ")
    @JsonBackReference
    private User user;

    @Builder
    public WishList(CultureRawData cultureRawData, User user) {
        this.cultureRawData = cultureRawData;
        this.user = user;
    }
}
