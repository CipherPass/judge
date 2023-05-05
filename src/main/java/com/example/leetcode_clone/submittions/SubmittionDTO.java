package com.example.leetcode_clone.submittions;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class SubmittionListDto {
    private String problemSlug;
    private String profileSlug;
    private SubmittionStatus status;
    private Date createdAt;
}

@Getter
@Setter
class SubmittionDetailDto {
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