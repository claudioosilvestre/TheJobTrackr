package jobtrackr_api.services;

import jobtrackr_api.exceptions.InvalidStatusException;
import jobtrackr_api.converters.JobApplicationConverter;
import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.exceptions.JobApplicationNotFoundException;
import jobtrackr_api.models.JobApplication;
import jobtrackr_api.models.JobStatus;
import jobtrackr_api.repositories.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private JobApplicationRepository jobApplicationRepository;
    private JobApplicationConverter jobApplicationConverter;

    @Override
    public List<JobApplicationResponseDTO> listApplications() {

       List<JobApplicationResponseDTO> response = jobApplicationRepository.findAll().stream()
                .map(jobApp -> jobApplicationConverter.toResponseDTO(jobApp))
                .toList();

        return response;
    }

    @Override
    public JobApplicationResponseDTO findById(Long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobApplicationNotFoundException());

        return jobApplicationConverter.toResponseDTO(jobApplication);
    }

    @Override
    public JobApplicationResponseDTO createApplication(JobApplicationRequestDTO jobApplicationRequestDTO) {
        if(jobApplicationRequestDTO == null) {
            throw new IllegalArgumentException("Job Application cannot be null");
        }
        JobApplication jobApplication = jobApplicationConverter.toRequestDTO(jobApplicationRequestDTO);

        jobApplicationRepository.save(jobApplication);

        return jobApplicationConverter.toResponseDTO(jobApplication);
    }

    @Override
    public JobApplicationResponseDTO updateStatus(JobApplicationRequestDTO jobApplicationRequestDTO, JobStatus newStatus) {
        if(newStatus == null) {
            throw new IllegalArgumentException("Job status must be valid");
        }
        if(jobApplicationRequestDTO == null) {
            throw new IllegalArgumentException("Job application must be valid");
        }

        if(!jobApplicationRequestDTO.getJobStatus().canTransitionTo(newStatus)) {
            throw new InvalidStatusException();
        }

        jobApplicationRequestDTO.setJobStatus(newStatus);

        JobApplication jobApplication = jobApplicationConverter.toRequestDTO(jobApplicationRequestDTO);

        return jobApplicationConverter.toResponseDTO(jobApplication);
    }

    @Override
    public void deleteById(Long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        jobApplicationRepository.deleteById(findById(id).getId());
    }


    @Autowired
    public void setJobApplicationConverter(JobApplicationConverter jobApplicationConverter) {
        this.jobApplicationConverter = jobApplicationConverter;
    }

    @Autowired
    public void setJobApplicationRepository(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }
}
