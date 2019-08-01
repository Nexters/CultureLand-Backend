package org.nexters.cultureland.api.diary.repository;

import org.nexters.cultureland.api.diary.model.Diary;
import org.nexters.cultureland.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUser(User user);
}
