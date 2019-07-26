package org.nexters.cultureland.model.vo;

import lombok.*;
import org.nexters.cultureland.model.vo.CultureRawData;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="culture")
@Getter @ToString @NoArgsConstructor
public class Culture {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String cultureName;

    @OneToMany(mappedBy = "culture")
    @JoinColumn(name="culture_id")
    private List<CultureRawData> cultureRawDatas;

    @Builder
    public Culture(String cultureName, List<CultureRawData> cultureRawDatas) {
        this.cultureName = cultureName;
        this.cultureRawDatas = cultureRawDatas;
    }
}
