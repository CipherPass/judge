package com.example.leetcode_clone.submittions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmittionRunCreationDTO {
    private String languageSlug;
    private String code;
    private String problemSlug;
    private Long authUserID;
}