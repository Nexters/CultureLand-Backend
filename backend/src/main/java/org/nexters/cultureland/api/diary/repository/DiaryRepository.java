package org.nexters.cultureland.api.diary.repository;

import org.nexters.cultureland.api.diary.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
