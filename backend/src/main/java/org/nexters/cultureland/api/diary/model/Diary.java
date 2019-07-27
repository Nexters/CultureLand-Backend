package org.nexters.cultureland.api.diary.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nexters.cultureland.api.diary.DiaryDto;

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
    private String title;

    @Lob
    private String content;

    @Column(name = "CREATED_BY")
    private LocalDateTime createdBy = LocalDateTime.now();

    @Builder
    private Diary(final Long id, final String title, final String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void update(final DiaryDto diaryDto) {
        title = diaryDto.getTitle();
        content = diaryDto.getContent();
    }
}
