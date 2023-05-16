package com.example.leetcode_clone.problems;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.example.leetcode_clone.commons.Auditable;
import com.example.leetcode_clone.contests.ContestProblemEntity;
import com.example.leetcode_clone.submittions.SubmittionEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "problems")
public class ProblemEntity extends Auditable {

    @Column(name = "slug", nullable = false, unique = true, length = 50)
    private String slug;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;


    @Column(precision=1, nullable = true)
    private Double acceptance;

    @Enumerated(EnumType.STRING)
    private TestStrategy testStrategy;

    @Enumerated(EnumType.STRING)
    private SolutionCheckStrategy solutionCheckStrategy;

    @Basic(fetch = FetchType.LAZY)
    @ManyToMany(mappedBy = "problem")
    private List<ContestProblemEntity> contests;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "problem")
    private List<SubmittionEntity> submittions;

    public String getResourceURL(){
        return "/problems/"+this.slug+"/";
    }
}
