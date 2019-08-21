package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.nexters.cultureland.api.model.CultureRawData;
import org.nexters.cultureland.api.model.WishList;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WishListDto {
    private Long wishListId;
    private CultureIdImgDto cultureInfo;

    public WishListDto(long id, CultureRawData cultureRawData) {
        this.wishListId = id;
        cultureInfo = CultureIdImgDto.builder()
                .id(cultureRawData.getId())
                .startDate(cultureRawData.getStartDate())
                .endDate(cultureRawData.getEndDate())
                .imageUrl(cultureRawData.getImageUrl())
                .build();
    }
}
