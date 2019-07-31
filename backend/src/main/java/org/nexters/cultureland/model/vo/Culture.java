package org.nexters.cultureland.model.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.nexters.cultureland.model.vo.CultureRawData;

import javax.persistence.*;
import java.util.ArrayList;
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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "culture")
    @JsonBackReference
    private List<CultureRawData> cultureRawDatas = new ArrayList<>();

    @Builder
    public Culture(String cultureName, List<CultureRawData> cultureRawDatas) {
        this.cultureName = cultureName;
        this.cultureRawDatas = cultureRawDatas;
    }
}
