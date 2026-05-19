package jobtrackr_api.dtos;

import jakarta.validation.constraints.NotBlank;

public class AnalyzeRequestDTO {
    
    @NotBlank
    private String jobDescription;

    public AnalyzeRequestDTO() {

    }
    
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
