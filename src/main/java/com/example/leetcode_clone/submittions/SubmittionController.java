package com.example.leetcode_clone.submittions;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.leetcode_clone.submittions.dto.SubmittionDetailDTO;
import com.example.leetcode_clone.submittions.dto.SubmittionListDTO;

@RestController
@RequestMapping("/api")
public class SubmittionController {
    private SubmittionService submittionService;

    SubmittionController(SubmittionService submittionService){
        this.submittionService = submittionService;
    }

    @GetMapping("/problems/{problem_slug}/submittions")
    ResponseEntity<List<SubmittionListDTO>> getProblemList(
        @PathVariable String problem_slug,
        @RequestParam(value="page", defaultValue = "0") String pageString,
        @RequestParam(value="size", defaultValue = "2") String sizeString
    ){
        Integer page = Integer.parseInt(pageString);
        Integer size = Integer.parseInt(sizeString);

        List<SubmittionListDTO> submittionListDtos = this.submittionService.getSubmittionListForProblemSlug(page, size, problem_slug);
        return ResponseEntity.ok(submittionListDtos);
    }

    @GetMapping("/submittions/{id}")
    public ResponseEntity<SubmittionDetailDTO> getSubmittionById(@PathVariable Long id){
        var submittion = this.submittionService.getSubmittionById(id);
        return ResponseEntity.ok(submittion);
    }
}
