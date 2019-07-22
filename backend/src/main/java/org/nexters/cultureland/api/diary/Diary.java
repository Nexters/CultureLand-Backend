package org.nexters.cultureland.api.diary;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Lob
    private String content;

    @Column(name = "CREATED_BY")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdBy = LocalDateTime.now();

    @Builder
    private Diary(final Long id, final String title, final String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
