package jobtrackr_api.services;

import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.models.JobStatus;

import java.util.List;

public interface JobApplicationService {

    List<JobApplicationResponseDTO> listApplications();

    JobApplicationResponseDTO findById(Long id);

    JobApplicationResponseDTO createApplication(JobApplicationRequestDTO jobApplicationRequestDTO);

    JobApplicationResponseDTO updateStatus(Long id, JobStatus newStatus);

    void deleteById(Long id);
}
