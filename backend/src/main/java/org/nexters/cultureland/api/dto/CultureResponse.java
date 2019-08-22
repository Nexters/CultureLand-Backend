package org.nexters.cultureland.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CultureResponse {
    private boolean nextPage;
    private int count;
    private long totalCount;
    private List<CultureIdImgDto> contents;

    @Builder
    public CultureResponse(boolean nextPage, int count, long totalCount, List<CultureIdImgDto> contents) {
        this.nextPage = nextPage;
        this.count = count;
        this.totalCount = totalCount;
        this.contents = contents;
    }
}
