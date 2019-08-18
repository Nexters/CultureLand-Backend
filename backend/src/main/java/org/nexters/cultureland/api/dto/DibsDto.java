package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.nexters.cultureland.api.model.Dibs;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DibsDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String place;
    private String startDate;
    private String endDate;

    public DibsDto(Long id, String imageUrl, String title, String place, String startDate, String endDate) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DibsDto(Dibs dibs) {
        this.id = dibs.getId();
        this.endDate = dibs.getEndDate();
        this.imageUrl = dibs.getImageUrl();
        this.place = dibs.getPlace();
        this.startDate = dibs.getStartDate();
        this.title = dibs.getTitle();
    }
}
