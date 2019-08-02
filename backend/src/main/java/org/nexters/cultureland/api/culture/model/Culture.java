package org.nexters.cultureland.api.culture.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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
    @JsonManagedReference
    private List<CultureRawData> cultureRawDatas = new ArrayList<>();

    public Culture(String cultureName, List<CultureRawData> cultureRawDatas) {
        this.cultureName = cultureName;
        this.cultureRawDatas = cultureRawDatas;
    }
}
