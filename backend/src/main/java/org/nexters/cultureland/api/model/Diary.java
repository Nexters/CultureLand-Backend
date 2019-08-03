package org.nexters.cultureland.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nexters.cultureland.api.dto.DiaryCreateDto;
import org.nexters.cultureland.api.dto.DiaryDto;
import org.nexters.cultureland.api.dto.DiatyUpdateDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"})
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime sometime;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String withWho;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "CREATED_BY")
    private LocalDateTime createdBy = LocalDateTime.now();

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(foreignKey = @ForeignKey(name = "DIARY_CULTURE_FK"))
    private Culture culture;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "DIARY_USER_FK"), nullable = false)
    private User user;

    public Diary(DiaryCreateDto diaryDto, Culture culture, User user){
        this.title = diaryDto.getTitle();
        this.sometime = LocalDateTime.parse(diaryDto.getSometime());
        this.place = diaryDto.getPlace();
        this.withWho = diaryDto.getWithWho();
        this.content = diaryDto.getContent();
        this.culture = culture;
        this.user = user;
    }

    public void update(final DiatyUpdateDto diaryDto) {
        title = diaryDto.getTitle();
        this.sometime = LocalDateTime.parse(diaryDto.getSometime());
        place = diaryDto.getPlace();
        withWho = diaryDto.getWithWho();
        content = diaryDto.getContent();
    }
}
