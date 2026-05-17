package jobtrackr_api.controllers;

import jakarta.validation.Valid;
import jobtrackr_api.converters.JobApplicationConverter;
import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.models.JobStatus;
import jobtrackr_api.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobapplication")
public class JobApplicationController {

    private JobApplicationService jobApplicationService;
    private JobApplicationConverter jobApplicationConverter;

    @GetMapping
    public ResponseEntity<List<JobApplicationResponseDTO>> listAll() {

        return ResponseEntity.ok(jobApplicationService.listApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponseDTO> findById(@PathVariable Long id) {

        JobApplicationResponseDTO jobApplicationResponseDTO = jobApplicationService.findById(id);

        return ResponseEntity.ok(jobApplicationResponseDTO);
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponseDTO> createJobApplication(@Valid @RequestBody JobApplicationRequestDTO jobApplicationRequestDTO) {

        JobApplicationResponseDTO jobApplicationResponseDTO = jobApplicationService.createApplication(jobApplicationRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationResponseDTO);
    }

    @PatchMapping
    public ResponseEntity<JobApplicationResponseDTO> updateStatus(@Valid @RequestBody JobApplicationRequestDTO jobApplicationRequestDTO, @RequestBody JobStatus jobStatus) {

        JobApplicationResponseDTO jobApplicationResponseDTO = jobApplicationService.updateStatus(jobApplicationRequestDTO, jobStatus);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(jobApplicationResponseDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteJobApplicationById(@PathVariable Long id) {

        jobApplicationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @Autowired
    public void setJobApplicationConverter(JobApplicationConverter jobApplicationConverter) {
        this.jobApplicationConverter = jobApplicationConverter;
    }

    @Autowired
    public void setJobApplicationService(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }
}
