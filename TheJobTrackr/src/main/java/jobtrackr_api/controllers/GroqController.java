package jobtrackr_api.controllers;

import jakarta.validation.Valid;
import jobtrackr_api.dtos.AnalyzeRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.services.GroqService;
import jobtrackr_api.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-applications")
public class GroqController {

    private GroqService groqService;
    private JobApplicationService jobApplicationService;

    @PostMapping("/{id}/analyze")
    public ResponseEntity<String> analyzeJobCompatibility(@PathVariable Long id, @Valid @RequestBody AnalyzeRequestDTO analyzeRequestDTO) {
        
        JobApplicationResponseDTO jobApplicationResponseDTO  = jobApplicationService.findById(id);
        
        String result = groqService.analyzeCompatibility(analyzeRequestDTO.getJobDescription(), jobApplicationResponseDTO.getPosition());

        return ResponseEntity.ok(result);
    }

    @Autowired
    public void setJobApplicationService(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @Autowired
    public void setGroqService(GroqService groqService) {
        this.groqService = groqService;
    }
}
