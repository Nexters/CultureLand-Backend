package org.nexters.cultureland.api.diary.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nexters.cultureland.api.diary.DiaryDto;
import org.nexters.cultureland.api.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn(foreignKey = @ForeignKey(name = "DIARY_USER_FK"), nullable = false)
    private User user;

    public Diary(DiaryDto diaryDto, User user){
        this.title = diaryDto.getTitle();
        this.sometime = diaryDto.getSometime();
        this.place = diaryDto.getPlace();
        this.withWho = diaryDto.getWithWho();
        this.content = diaryDto.getContent();
        this.user = user;
    }

    public void update(final DiaryDto diaryDto) {
        title = diaryDto.getTitle();
        sometime = diaryDto.getSometime();
        place = diaryDto.getPlace();
        withWho = diaryDto.getWithWho();
        content = diaryDto.getContent();
    }
}
