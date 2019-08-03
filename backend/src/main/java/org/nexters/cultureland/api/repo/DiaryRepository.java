package org.nexters.cultureland.api.repo;

import org.nexters.cultureland.api.model.Diary;
import org.nexters.cultureland.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUser(User user);
}
