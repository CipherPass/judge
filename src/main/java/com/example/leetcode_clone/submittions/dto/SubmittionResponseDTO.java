package com.example.leetcode_clone.submittions.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmittionResponseDTO {
    private Date createdAt;
    private String uuid;

    public String getResourceURL(){
        return "/api/submittion_status/"+uuid;
    }
}
