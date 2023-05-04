package com.example.leetcode_clone.problems;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {
    Optional<ProblemEntity> findBySlug(String slug);
    Page<ProblemEntity> findAll(Pageable pageable);
}
