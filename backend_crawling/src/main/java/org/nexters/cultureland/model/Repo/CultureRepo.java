package org.nexters.cultureland.model.Repo;

import org.nexters.cultureland.model.vo.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CultureRepo extends JpaRepository<Culture, Long> {
}

