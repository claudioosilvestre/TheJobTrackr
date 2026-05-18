package jobtrackr_api.dtos;

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
