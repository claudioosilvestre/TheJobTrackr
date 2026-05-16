package jobtrackr_api.services;

import jobtrackr_api.models.JobApplication;
import jobtrackr_api.converters.JobApplicationConverter;
import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.exceptions.JobApplicationNotFoundException;
import jobtrackr_api.models.JobStatus;
import jobtrackr_api.repositories.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationImpl implements JobApplication {

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
        return null;
    }

    @Override
    public JobApplicationResponseDTO updateStatus(JobStatus newStatus) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

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
