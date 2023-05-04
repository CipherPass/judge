package com.example.leetcode_clone.submittions;

import com.example.leetcode_clone.commons.Auditable;
import com.example.leetcode_clone.problems.ProblemEntity;
import com.example.leetcode_clone.users.ProfileEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "submittions")
public class SubmittionEntity extends Auditable {
    @ManyToOne
    private ProblemEntity problem;

    @ManyToOne
    private ProfileEntity profile;

    @Column(nullable = false, length = 2000)
    private String code;

    @Enumerated(value = EnumType.STRING)
    private SubmittionStatus status;
}
