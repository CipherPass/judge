package com.example.leetcode_clone.submittions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leetcode_clone.users.ProfileEntity;

public interface SubmittionRepository extends JpaRepository<SubmittionEntity, Long> {
    Page<SubmittionEntity> findAllByProfileAndProblemSlug(Pageable pageable, ProfileEntity profile, String problemSlug);
}
