package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.nexters.cultureland.api.model.CultureRawData;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WishListDto {
    private Long wishListId;

    private WIshListCultureDto cultureInfo;

    public WishListDto(long wishListId, CultureRawData cultureRawData) {
        this.wishListId = wishListId;
        cultureInfo = WIshListCultureDto.builder()
                .cultureName(cultureRawData.getCulture().getCultureName())
                .endDate(cultureRawData.getEndDate())
                .id(cultureRawData.getId())
                .imageUrl(cultureRawData.getImageUrl())
                .place(cultureRawData.getPlace())
                .startDate(cultureRawData.getStartDate())
                .title(cultureRawData.getTitle())
                .build();
    }
}
