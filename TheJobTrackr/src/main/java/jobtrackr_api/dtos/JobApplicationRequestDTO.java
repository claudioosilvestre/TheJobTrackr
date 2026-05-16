package jobtrackr_api.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jobtrackr_api.models.JobStatus;

public class JobApplicationRequestDTO {

    @NotBlank
    private String position;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    private String notes;

    
}
