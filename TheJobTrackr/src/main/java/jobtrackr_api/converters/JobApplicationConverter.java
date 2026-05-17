package jobtrackr_api.converters;

import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.models.JobApplication;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationConverter {

    public JobApplicationResponseDTO toResponseDTO(JobApplication jobApplication) {

        JobApplicationResponseDTO jobApplicationResponseDTO = new JobApplicationResponseDTO();

        jobApplicationResponseDTO.setId(jobApplication.getId());
        jobApplicationResponseDTO.setPosition(jobApplication.getPosition());
        jobApplicationResponseDTO.setJobStatus(jobApplication.getJobStatus());
        jobApplicationResponseDTO.setNotes(jobApplication.getNotes());
        jobApplicationResponseDTO.setUser(jobApplication.getUser());
        jobApplicationResponseDTO.setCompany(jobApplication.getCompany());

        return jobApplicationResponseDTO;
    }

    public JobApplication toRequestDTO(JobApplicationRequestDTO jobApplicationRequestDTO) {

        JobApplication jobApplication = new JobApplication();

        jobApplication.setPosition(jobApplicationRequestDTO.getPosition());
        jobApplication.setJobStatus(jobApplicationRequestDTO.getJobStatus());
        jobApplication.setNotes(jobApplicationRequestDTO.getNotes());

        return jobApplication;
    }
}
