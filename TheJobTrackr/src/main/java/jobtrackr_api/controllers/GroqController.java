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
@RequestMapping("/analysis")
public class GroqController {

    private GroqService groqService;
    private JobApplicationService jobApplicationService;

    public GroqController(GroqService groqService, JobApplicationService jobApplicationService) {
        this.groqService = groqService;
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> analyzeJobCompatibility(@PathVariable Long id, @Valid @RequestBody AnalyzeRequestDTO analyzeRequestDTO) {
        
        JobApplicationResponseDTO jobApplicationResponseDTO  = jobApplicationService.findById(id);
        
        String result = groqService.analyzeCompatibility(analyzeRequestDTO.getJobDescription(), jobApplicationResponseDTO.getPosition());

        return ResponseEntity.ok(result);
    }
}
