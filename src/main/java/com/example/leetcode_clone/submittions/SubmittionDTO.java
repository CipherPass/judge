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
