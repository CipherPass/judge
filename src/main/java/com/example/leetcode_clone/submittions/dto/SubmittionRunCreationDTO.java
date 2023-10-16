package com.example.leetcode_clone.submittions.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmittionRunCreationDTO {
    private String languageSlug;
    private String code;
    private String problemSlug;
    private Long authUserID;
    private ArrayList<TestCase> testCases;

    @Getter
    @Setter
    public static class TestCase {
        private String input;
    }
}