package com.example.leetcode_clone.problems.dto;

import com.example.leetcode_clone.problems.Difficulty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemListDTO {
    private String slug;
    private String title;
    private Difficulty difficulty;
    private Double acceptance;
    private String resourceURL;
}
