package org.nexters.cultureland.api.diary.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nexters.cultureland.api.diary.DiaryDto;
import org.nexters.cultureland.api.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "DIARY_USER_FK"), nullable = false)
    private User user;

    @Builder
    private Diary(final Long id, final String title, final String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Builder
    private Diary(final Long id, final String title, final LocalDateTime sometime, final String where, final String withWho, final String content, final User user) {
        this.id = id;
        this.title = title;
        this.sometime = sometime;
        this.place = where;
        this.withWho = withWho;
        this.content = content;
        this.user = user;
    }

    public void update(final DiaryDto diaryDto) {
        title = diaryDto.getTitle();
        sometime = LocalDateTime.parse(diaryDto.getSometime());
        place = diaryDto.getPlace();
        withWho = diaryDto.getWithWho();
        content = diaryDto.getContent();
    }
}
