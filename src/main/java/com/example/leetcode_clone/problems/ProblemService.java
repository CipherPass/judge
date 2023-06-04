package com.example.leetcode_clone.problems;

import java.util.List;

import com.example.leetcode_clone.languages.LanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.leetcode_clone.problems.dto.CreateProblemDTO;
import com.example.leetcode_clone.problems.dto.ProblemDetailDTO;
import com.example.leetcode_clone.problems.dto.ProblemListDTO;

@Service
public class ProblemService {

    private ModelMapper modelMapper;
    private ProblemRepository problemRepository;
    private LanguageService languageService;

    ProblemService(ModelMapper modelMapper, ProblemRepository problemRepository, LanguageService languageService){
        this.modelMapper = modelMapper;
        this.problemRepository = problemRepository;
        this.languageService = languageService;
    }

    public List<ProblemListDTO> getProblemList(Integer page, Integer size) {
        Page<ProblemEntity> problems = this.problemRepository.findAll(PageRequest.of(page, size, Sort.by("id").ascending()));
        List<ProblemListDTO> problemDtos = problems
            .stream()
            .map(problem -> this.modelMapper.map(problem, ProblemListDTO.class))
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

    public ProblemDetailDTO getProblem(Long id){
        ProblemEntity problem = getProblemEntityById(id);

        ProblemDetailDTO problemDto = this.modelMapper.map(problem, ProblemDetailDTO.class);
        problemDto.setLanguageAvailable(this.languageService.getAllLangauges());
        problemDto.setDefaultLanguage(this.languageService.getDefaultLanguage());

        return problemDto;
    }
    public ProblemDetailDTO getProblem(String slug){
        ProblemEntity problem = getProblemEntityBySlug(slug);

        ProblemDetailDTO problemDto = this.modelMapper.map(problem, ProblemDetailDTO.class);
        problemDto.setLanguageAvailable(this.languageService.getAllLangauges());
        problemDto.setDefaultLanguage(this.languageService.getDefaultLanguage());

        return problemDto;
    }

    public ProblemDetailDTO createProblem(CreateProblemDTO createProblemDTO) {
        var problem = this.modelMapper.map(createProblemDTO, ProblemEntity.class);
        problem = this.problemRepository.save(problem);
        return this.modelMapper.map(problem, ProblemDetailDTO.class);
    }

    public ProblemDetailDTO updateProblem(String slug, CreateProblemDTO createProblemDTO) {
        var problem = getProblemEntityBySlug(slug);
        return this.modelMapper.map(updateProblem(createProblemDTO, problem), ProblemDetailDTO.class);
    }
    public ProblemDetailDTO updateProblem(Long id, CreateProblemDTO createProblemDTO) {
        var problem = getProblemEntityById(id);
        return this.modelMapper.map(updateProblem(createProblemDTO, problem), ProblemDetailDTO.class);
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
