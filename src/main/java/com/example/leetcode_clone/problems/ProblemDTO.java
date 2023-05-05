package com.example.leetcode_clone.problems;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CreateProblemDTO {
    private String slug;
    private String title;
    private String description;
    private TestStrategy testStrategy;
    private SolutionCheckStrategy solutionCheckStrategy;
}

@Getter
@Setter
class CreateProblemResponseDto {
    private Long id;
    private Date createdAt;
    private String slug;
    private String title;
    private String description;
    private TestStrategy testStrategy;
    private SolutionCheckStrategy solutionCheckStrategy;

    private String resourceURL;
}

@Getter
@Setter
class ProblemDetailDto {
    private String slug;
    private String title;
    private String description;
}

@Getter
@Setter
class ProblemListDto {
    private String slug;
    private String title;
    private String resourceURL;
}
