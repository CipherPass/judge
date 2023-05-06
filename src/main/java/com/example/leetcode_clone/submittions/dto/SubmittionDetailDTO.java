package com.example.leetcode_clone.submittions.dto;

import java.util.Date;

import com.example.leetcode_clone.submittions.SubmittionStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SubmittionDetailDTO {
    private Problem problem;
    private String profileSlug;
    private String code;
    private SubmittionStatus status;
    private Date createdAt;
    
    @Getter
    @Setter
    public static class Problem{
        private String problemSlug;
        private String problemTitle;
        private String resourceURL;
    }
}