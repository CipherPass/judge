package com.example.leetcode_clone.submittions;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.leetcode_clone.submittions.dto.SubmittionRunCreationDTO;
import com.example.leetcode_clone.submittions.dto.SubmittionDetailDTO;
import com.example.leetcode_clone.submittions.dto.SubmittionListDTO;
import com.example.leetcode_clone.submittions.dto.SubmittionResponseDTO;

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

    @PostMapping("/problems/{problemSlug}/submittions_run")
    ResponseEntity<SubmittionResponseDTO> runSolution(
            @PathVariable String problemSlug,
            @RequestBody SubmittionRunCreationDTO submittionRunCreationDTO
    ){
        submittionRunCreationDTO.setProblemSlug(problemSlug);
        submittionRunCreationDTO.setAuthUserID(Long.valueOf(1));

        SubmittionResponseDTO response = this.submittionService.submitRunCode(submittionRunCreationDTO);
        return ResponseEntity.created(URI.create(response.getResourceURL())).body(response);
    }

    @PostMapping("/problems/{problem_slug}/submittions_submit")
    ResponseEntity<SubmittionResponseDTO> submitSolution(
            @RequestBody SubmittionRunCreationDTO submittionRunCreationDTO
    ){
//        SubmittionResponseDTO response = this.submittionService.submitCode(submittionRunCreationDTO);
//        return ResponseEntity.created(URI.create(response.getResourceURL())).body(response);
        return null;
    }

    @GetMapping("/submittions/{id}")
    public ResponseEntity<SubmittionDetailDTO> getSubmittionById(@PathVariable Long id){
        var submittion = this.submittionService.getSubmittionById(id);
        return ResponseEntity.ok(submittion);
    }
}
