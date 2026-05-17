package jobtrackr_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jobtrackr_api.models.JobStatus;

public class JobApplicationRequestDTO {

    @NotBlank
    private String position;

    private JobStatus jobStatus;

    private String notes;

    private Long userId;

    private Long companyId;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
