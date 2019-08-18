package org.nexters.cultureland.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "culture")
@Getter
@NoArgsConstructor
public class Culture {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String cultureName;

    @OneToMany(mappedBy = "culture")
    @JsonBackReference
    private List<Diary> diaries = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "culture")
    @JsonManagedReference
    private List<CultureRawData> cultureRawDatas = new ArrayList<>();

    public Culture(String cultureName, List<CultureRawData> cultureRawDatas) {
        this.cultureName = cultureName;
        this.cultureRawDatas = cultureRawDatas;
    }
}
