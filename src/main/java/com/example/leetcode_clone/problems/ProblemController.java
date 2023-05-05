package com.example.leetcode_clone.problems;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problems")
public class ProblemController {
    private ProblemService problemService;

    ProblemController(ProblemService problemService){
        this.problemService = problemService;
    }

    @GetMapping("/")
    ResponseEntity<List<ProblemListDto>> getProblemList(
        @RequestParam(value="page", defaultValue = "0") String pageString,
        @RequestParam(value="size", defaultValue = "2") String sizeString
    ){
        Integer page = Integer.parseInt(pageString);
        Integer size = Integer.parseInt(sizeString);

        List<ProblemListDto> problemListDtos = this.problemService.getProblemList(page, size);
        return ResponseEntity.ok(problemListDtos);
    }

    @PostMapping("/")
    public ResponseEntity<CreateProblemResponseDto> createProblem(@RequestBody CreateProblemDTO createProblemDTO){
        var problem = this.problemService.createProblem(createProblemDTO);
        return ResponseEntity.created(URI.create(problem.getResourceURL())).body(problem);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProblemDetailDto> getProblemBySlug(@PathVariable String slug){
        var problem = this.problemService.getProblem(slug);
        return ResponseEntity.ok(problem);
    }

    @PostMapping("/{slug}")
    public ResponseEntity<CreateProblemResponseDto> updateProblemBySlug(@PathVariable String slug, @RequestBody CreateProblemDTO createProblemDTO){
        var problem = this.problemService.updateProblem(slug, createProblemDTO);
        return ResponseEntity.accepted().body(problem);
    }

    @DeleteMapping("/{slug}")
    ResponseEntity<String> deleteProblem(@PathVariable String slug){
        try {
            this.problemService.deleteProblem(slug);
        } catch (ProblemService.ProblemNotFoundException e) {
        }
        return ResponseEntity.accepted().body("Problem deleted successfully");
    }

    @ExceptionHandler(ProblemService.ProblemNotFoundException.class)
    ResponseEntity<String> handleProblemNotFoundException(ProblemService.ProblemNotFoundException e){
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }
}
