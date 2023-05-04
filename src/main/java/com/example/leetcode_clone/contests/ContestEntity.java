package com.example.leetcode_clone.contests;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.example.leetcode_clone.commons.Auditable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "contests")
public class ContestEntity extends Auditable {
    @Column(nullable = false, unique = true, length = 50)
    private String slug;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @OneToMany(mappedBy = "contest")
    private List<ContestProblemEntity> contestProblems;
}
