package com.example.leetcode_clone.submittions.dto;

import java.util.Date;

import com.example.leetcode_clone.submittions.SubmittionStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmittionListDTO {
    private String problemSlug;
    private String profileSlug;
    private SubmittionStatus status;
    private Date createdAt;
}