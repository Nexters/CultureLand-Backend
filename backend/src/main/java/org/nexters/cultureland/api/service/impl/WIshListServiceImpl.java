package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.exception.CultureNotFoundException;
import org.nexters.cultureland.api.model.CultureRawData;
import org.nexters.cultureland.api.model.WishList;
import org.nexters.cultureland.api.repo.CultureRawRepository;
import org.nexters.cultureland.api.repo.WishListRepository;
import org.nexters.cultureland.api.service.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WIshListServiceImpl implements WishListService {
    private final CultureRawRepository RawRepository;
    private final WishListRepository wishListRepository;

    @Override
    public boolean isWishlistByCultureInfoId(long userId, long cultureInfoId){
        CultureRawData cultureRawData = RawRepository.findById(cultureInfoId)
                .orElseThrow(() -> new CultureNotFoundException("CULTURE INFO DATA NOT FOUND"));

        List<WishList> wishLists = cultureRawData.getWishLists();
        for(WishList wishList : wishLists){
            if(wishList.getUser().getUserId() == userId){
                return true;
            }
        }
        return false;
    }

    public WIshListServiceImpl(final CultureRawRepository rawRepository, final WishListRepository wishListRepository) {
        RawRepository = rawRepository;
        this.wishListRepository = wishListRepository;
    }
}
