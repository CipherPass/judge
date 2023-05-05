package com.example.leetcode_clone.problems;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    private ModelMapper modelMapper;
    private ProblemRepository problemRepository;

    ProblemService(ModelMapper modelMapper, ProblemRepository problemRepository){
        this.modelMapper = modelMapper;
        this.problemRepository = problemRepository;
    }

    public List<ProblemListDto> getProblemList(Integer page, Integer size) {
        Page<ProblemEntity> problems = this.problemRepository.findAll(PageRequest.of(page, size, Sort.by("id").ascending()));
        List<ProblemListDto> problemDtos = problems
            .stream()
            .map(problem -> this.modelMapper.map(problem, ProblemListDto.class))
            .toList();
        
        return problemDtos;
    }

    public ProblemEntity getProblemEntityBySlug(String slug){
        ProblemEntity problem = this.problemRepository.findBySlug(slug).orElseThrow(() -> {
            throw new ProblemNotFoundException(slug);
        });
        return problem;
    }
    public ProblemEntity getProblemEntityById(Long id){
        ProblemEntity problem = this.problemRepository.findById(id).orElseThrow(() -> {
            throw new ProblemNotFoundException(id);
        });
        return problem;
    }

    public ProblemDetailDto getProblem(Long id){
        ProblemEntity problem = getProblemEntityById(id);
        ProblemDetailDto problemDto = this.modelMapper.map(problem, ProblemDetailDto.class);
        return problemDto;
    }
    public ProblemDetailDto getProblem(String slug){
        ProblemEntity problem = getProblemEntityBySlug(slug);
        ProblemDetailDto problemDto = this.modelMapper.map(problem, ProblemDetailDto.class);
        return problemDto;
    }

    public CreateProblemResponseDto createProblem(CreateProblemDTO createProblemDTO) {
        var problem = this.modelMapper.map(createProblemDTO, ProblemEntity.class);
        problem = this.problemRepository.save(problem);
        return this.modelMapper.map(problem, CreateProblemResponseDto.class);
    }

    public CreateProblemResponseDto updateProblem(String slug, CreateProblemDTO createProblemDTO) {
        var problem = getProblemEntityBySlug(slug);
        return this.modelMapper.map(updateProblem(createProblemDTO, problem), CreateProblemResponseDto.class);
    }
    public CreateProblemResponseDto updateProblem(Long id, CreateProblemDTO createProblemDTO) {
        var problem = getProblemEntityById(id);
        return this.modelMapper.map(updateProblem(createProblemDTO, problem), CreateProblemResponseDto.class);
    }
    private ProblemEntity updateProblem(CreateProblemDTO source, ProblemEntity destination){
        if(source.getSlug() != null)destination.setSlug(source.getSlug());
        if(source.getTitle() != null)destination.setTitle(source.getTitle());
        if(source.getDescription() != null)destination.setDescription(source.getDescription());
        if(source.getTestStrategy() != null)destination.setTestStrategy(source.getTestStrategy());
        if(source.getSolutionCheckStrategy() != null)destination.setSolutionCheckStrategy(source.getSolutionCheckStrategy());

        return destination;
    }

    public static class ProblemNotFoundException extends IllegalArgumentException {
        public ProblemNotFoundException(String slug) {
            super("Problem with slug: "+ slug + " not found");
        }
        public ProblemNotFoundException(Long id) {
            super("Problem with id: "+ id + " not found");
        }
    }

    public void deleteProblem(String slug) {
        // TODO: should have permission
        var problem = getProblemEntityBySlug(slug);
        this.problemRepository.delete(problem);
    }
}
