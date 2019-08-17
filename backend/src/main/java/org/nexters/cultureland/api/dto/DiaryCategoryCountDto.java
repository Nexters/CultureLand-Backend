package org.nexters.cultureland.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryCategoryCountDto {

    private int totalNumberOfDiaryCount;
    private int likedDiaryCount;
    private int exhibitionCount;
    private int concertCount;
    private int playCount;
    private int etcCount;

    @Builder
    private DiaryCategoryCountDto(final int totalNumberOfDiaryCount, final int likedDiaryCount, final int exhibitionCount, final int concertCount, final int playCount, final int etcCount) {
        this.totalNumberOfDiaryCount = totalNumberOfDiaryCount;
        this.likedDiaryCount = likedDiaryCount;
        this.exhibitionCount = exhibitionCount;
        this.concertCount = concertCount;
        this.playCount = playCount;
        this.etcCount = etcCount;
    }
}
