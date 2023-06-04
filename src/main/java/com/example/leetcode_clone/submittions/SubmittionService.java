package com.example.leetcode_clone.submittions;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.shared.kafka.KafkaConstant;
import com.example.shared.kafka.dto.RunSolutionDTO;
import com.example.leetcode_clone.problems.ProblemService;
import com.example.leetcode_clone.submittions.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.leetcode_clone.users.ProfileService;

import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class SubmittionService {
    private ModelMapper modelMapper;
    private SubmittionRepository submittionRepository;
    private ProfileService profileService;
    private ProblemService problemService;
    @Autowired
    private KafkaTemplate<String, RunSolutionDTO> submittionRunQueue;
    @Autowired
    private KafkaTemplate<String, RunSolutionDTO> submittionSubmitQueue;

    SubmittionService(ModelMapper modelMapper, SubmittionRepository submittionRepository, ProfileService profileService, ProblemService problemService){
        this.modelMapper = modelMapper;
        this.submittionRepository = submittionRepository;
        this.profileService = profileService;
        this.problemService = problemService;
    }

    public List<SubmittionListDTO> getSubmittionListForProblemSlug(Integer page, Integer size, String problem_slug) {
        Page<SubmittionEntity> submittions = this.submittionRepository.findAllByProfileAndProblemSlug(
            PageRequest.of(page, size, Sort.by("createdAt").descending()),
            this.profileService.findEntityById(Long.valueOf(1)),
            problem_slug
        );
        List<SubmittionListDTO> submittionDtos = submittions
            .stream()
            .map((submittion) -> this.modelMapper.map(submittion, SubmittionListDTO.class))
            .toList();
        
        return submittionDtos;
    }

    public SubmittionEntity getSubmittionEntityById(Long id){
        return this.submittionRepository.findById(id).orElseThrow(() -> {
            throw new SubmittionNotFoundException(id);
        });
    }

    public SubmittionDetailDTO getSubmittionById(Long id) {
        var submittion = getSubmittionEntityById(id);
        return this.modelMapper.map(submittion, SubmittionDetailDTO.class);
    }

    public SubmittionResponseDTO submitRunCode(SubmittionRunCreationDTO submittionRunCreationDTO) {
        UUID uuid = UUID.randomUUID();
        RunSolutionDTO runDTO = this.modelMapper.map(submittionRunCreationDTO, RunSolutionDTO.class);
        runDTO.setCreatedAt(new Date());
        runDTO.setUuid(uuid.toString());
        runCode(runDTO);

        // update uuid status as in Queue
        SubmittionResponseDTO response = new SubmittionResponseDTO();
        response.setUuid(uuid.toString());
        response.setCreatedAt(new Date());
        return response;
    }

    public static class SubmittionNotFoundException extends IllegalArgumentException {
        public SubmittionNotFoundException(Long id) {
            super("Submittion with id: "+ id + " not found");
        }
    }

    protected void submitCode(RunSolutionDTO msg) {
        submittionSubmitQueue.send(KafkaConstant.CODE_RUN_TOPIC_NAME, msg);
    }
    
    protected void runCode(RunSolutionDTO runSolutionDTO) {
        var future=submittionRunQueue.send(KafkaConstant.CODE_RUN_TOPIC_NAME, runSolutionDTO);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, RunSolutionDTO> result) {
                // Callback for successful send
                System.out.println("Message sent successfully: " + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                // Callback for failed send
                System.err.println("Error sending message: " + ex.getMessage());
            }
        });
    }
}
