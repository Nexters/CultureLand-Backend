package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.model.Diary;
import org.nexters.cultureland.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUser(User user);

    @Query(value = "select date_format(sometime,'%m') as d, count(*) from diary where user_seq = :userId and year(sometime) = :year group by d", nativeQuery = true)
    List<Object[]> countByUser(@Param("userId") Long userId, @Param("year") String year);
}
