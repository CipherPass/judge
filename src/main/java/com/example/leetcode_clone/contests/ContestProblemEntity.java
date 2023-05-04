package com.example.leetcode_clone.contests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.example.leetcode_clone.commons.Auditable;
import com.example.leetcode_clone.problems.ProblemEntity;

@Entity(name = "contest_problem")
public class ContestProblemEntity extends Auditable {
    @ManyToOne
    private ProblemEntity problem;

    @ManyToOne
    private ContestEntity contest;

    @Column(nullable = false)
    private Integer marks;

    @Column(nullable = false)
    private Integer index;
}
