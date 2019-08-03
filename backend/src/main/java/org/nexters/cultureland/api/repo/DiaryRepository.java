package org.nexters.cultureland.repo;

import org.nexters.cultureland.model.Diary;
import org.nexters.cultureland.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUser(User user);
}
