package com.nexters.cultureland.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "CULTURE")
@Getter @Setter @ToString @NoArgsConstructor
public class Culture {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String cultureName;

    @OneToMany(mappedBy = "culture")
    private List<CultureRawData> cultureRawDatas;

    @Builder
    public Culture(String cultureName, List<CultureRawData> cultureRawDatas) {
        this.cultureName = cultureName;
        this.cultureRawDatas = cultureRawDatas;
    }
}
