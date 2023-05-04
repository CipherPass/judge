package com.example.leetcode_clone.submittions;

import com.example.leetcode_clone.commons.Auditable;
import com.example.leetcode_clone.problems.ProblemEntity;
import com.example.leetcode_clone.users.ProfileEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "submittions")
public class SubmittionEntity extends Auditable {
    @OneToOne
    private ProblemEntity problem;

    @OneToOne
    private ProfileEntity profile;

    @Enumerated(value = EnumType.STRING)
    private SubmittionStatus status;
}
