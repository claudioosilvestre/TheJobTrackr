package jobtrackr_api.controllers;

import jakarta.validation.Valid;
import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.services.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-applications")
public class GeminiController {

    private GeminiService geminiService;


    @PostMapping("/{id}/analyze")
    public ResponseEntity<String> analyzeJobCompatibility(@PathVariable Long id, @Valid @RequestBody JobApplicationRequestDTO jobApplicationRequestDTO) {

        geminiService.analyzeCompatibility()
    }


    @Autowired
    public void setGeminiService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }
}
