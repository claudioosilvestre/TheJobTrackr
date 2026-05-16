package jobtrackr_api.services;

import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.models.JobStatus;
import jobtrackr_api.repositories.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationImpl implements JobApplication {

    private JobApplicationRepository jobApplicationRepository;

    @Override
    public List<JobApplicationResponseDTO> listApplications() {

        List<JobApplicationResponseDTO> response =

        return
    }

    @Override
    public JobApplicationResponseDTO findById(Long id) {
        return null;
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
    public void setJobApplicationRepository(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }
}
