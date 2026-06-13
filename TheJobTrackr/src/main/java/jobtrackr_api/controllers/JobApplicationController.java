package jobtrackr_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jobtrackr_api.converters.JobApplicationConverter;
import jobtrackr_api.dtos.ApplicationStatsResponseDTO;
import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.models.JobStatus;
import jobtrackr_api.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Job-Applications", description = "Manage job applications")
@RestController
@RequestMapping("/job-applications")
public class JobApplicationController {

    private JobApplicationService jobApplicationService;

    @Operation(summary = "List all job applications")
    @GetMapping
    public ResponseEntity<List<JobApplicationResponseDTO>> listAll() {

        return ResponseEntity.ok(jobApplicationService.listApplications());
    }

    @Operation(summary = "Find job application by id")
    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponseDTO> findById(@PathVariable Long id) {

        JobApplicationResponseDTO jobApplicationResponseDTO = jobApplicationService.findById(id);

        return ResponseEntity.ok(jobApplicationResponseDTO);
    }

    @Operation(summary = "Create job application")
    @PostMapping
    public ResponseEntity<JobApplicationResponseDTO> createJobApplication(@Valid @RequestBody JobApplicationRequestDTO jobApplicationRequestDTO) {

        JobApplicationResponseDTO jobApplicationResponseDTO = jobApplicationService.createApplication(jobApplicationRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationResponseDTO);
    }

    @Operation(summary = "Update job application status")
    @PatchMapping("/{id}/status")
    public ResponseEntity<JobApplicationResponseDTO> updateStatus(@PathVariable Long id, @RequestBody JobStatus jobStatus) {


        JobApplicationResponseDTO jobApplicationResponseDTO = jobApplicationService.updateStatus(id, jobStatus);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(jobApplicationResponseDTO);
    }

    @Operation(summary = "List job applications status")
    @GetMapping("/stats")
    public ResponseEntity<ApplicationStatsResponseDTO> listApplicationStats() {

        return ResponseEntity.ok(jobApplicationService.getApplicationStats());
    }


    @Operation(summary = "Delete job application by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplicationById(@PathVariable Long id) {

        jobApplicationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @Autowired
    public void setJobApplicationService(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }
}
