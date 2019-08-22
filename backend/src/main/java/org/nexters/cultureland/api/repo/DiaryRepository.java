package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.dto.DiaryDto;
import org.nexters.cultureland.api.model.Diary;
import org.nexters.cultureland.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query(value = "select date_format(sometime,'%m') as d, count(*) from diary where user_seq = :userId and year(sometime) = :year group by d", nativeQuery = true)
    List<Object[]> countByUser(@Param("userId") Long userId, @Param("year") String year);

    @Query("select d.culture.cultureName, count(d.culture) from Diary d where d.user.seq = :userId group by d.culture.id")
    List<Object[]> countByCategories(@Param("userId") Long userId);

    @Query("select count(d) from Diary d where d.favorite = true and d.user.seq = :userId")
    Long countByUserFavoriteDiary(@Param("userId") Long userId);

    Page<DiaryDto> findByCulture_CultureNameAndUser(String cultureName, User user, Pageable pageable);

    @Query(value = "select * from diary where date_format(sometime, '%Y%m') = :date and user_seq = :userId", nativeQuery = true)
    Page<Diary> findByUserAndSometime(@Param("date") String date, @Param("userId") long userId, Pageable pageable);

}
