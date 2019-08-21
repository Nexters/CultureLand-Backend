package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUser(User user);
}
