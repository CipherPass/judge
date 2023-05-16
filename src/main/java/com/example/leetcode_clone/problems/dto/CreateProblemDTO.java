package com.example.leetcode_clone.problems.dto;

import com.example.leetcode_clone.problems.Difficulty;
import com.example.leetcode_clone.problems.SolutionCheckStrategy;
import com.example.leetcode_clone.problems.TestStrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProblemDTO {
    private String slug;
    private String title;
    private String description;
    private Difficulty difficulty;
    private TestStrategy testStrategy;
    private SolutionCheckStrategy solutionCheckStrategy;
}
