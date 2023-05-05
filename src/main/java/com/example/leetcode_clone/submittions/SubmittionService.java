package com.example.leetcode_clone.submittions;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.leetcode_clone.users.ProfileService;

@Service
public class SubmittionService {
    private ModelMapper modelMapper;
    private SubmittionRepository submittionRepository;
    private ProfileService profileService;

    SubmittionService(ModelMapper modelMapper, SubmittionRepository submittionRepository, ProfileService profileService){
        this.modelMapper = modelMapper;
        this.submittionRepository = submittionRepository;
        this.profileService = profileService;
    }

    public List<SubmittionListDto> getSubmittionListForProblemSlug(Integer page, Integer size, String problem_slug) {
        Page<SubmittionEntity> submittions = this.submittionRepository.findAllByProfileAndProblemSlug(
            PageRequest.of(page, size, Sort.by("createdAt").descending()),
            this.profileService.findEntityById(Long.valueOf(1)),
            problem_slug
        );
        List<SubmittionListDto> submittionDtos = submittions
            .stream()
            .map((submittion) -> this.modelMapper.map(submittion, SubmittionListDto.class))
            .toList();
        
        return submittionDtos;
    }

    public SubmittionEntity getSubmittionEntityById(Long id){
        return this.submittionRepository.findById(id).orElseThrow(() -> {
            throw new SubmittionNotFoundException(id);
        });
    }

    public SubmittionDetailDto getSubmittionById(Long id) {
        var submittion = getSubmittionEntityById(id);
        return this.modelMapper.map(submittion, SubmittionDetailDto.class);
    }

    public static class SubmittionNotFoundException extends IllegalArgumentException {
        public SubmittionNotFoundException(Long id) {
            super("Submittion with id: "+ id + " not found");
        }
    }
}
