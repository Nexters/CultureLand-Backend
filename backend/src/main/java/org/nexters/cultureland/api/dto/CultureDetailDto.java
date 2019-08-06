package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
public class CultureDetailDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String place;
    private String startDate;
    private String endDate;
    private String cultureName;

    public CultureDetailDto(Long id, String imageUrl, String title, String place, String startDate, String endDate) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CultureDetailDto(Long id, String imageUrl, String title, String place, String startDate, String endDate, String cultureName) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cultureName = cultureName;
    }
}
