package org.nexters.cultureland.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WIshListCultureDto {
    private long id;

    private String imageUrl;

    private String title;

    private String place;

    private String startDate;

    private String endDate;

    private String cultureName;

    @Builder
    public WIshListCultureDto(long id, String imageUrl, String title, String place, String startDate, String endDate, String cultureName) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cultureName = cultureName;
    }
}
