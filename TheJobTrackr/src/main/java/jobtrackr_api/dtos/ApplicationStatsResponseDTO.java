package jobtrackr_api.dtos;

import jobtrackr_api.models.JobStatus;

import java.util.Map;

public class ApplicationStatsResponseDTO {

    private Long totalApplications;

    private Map<JobStatus, Long> countByStatus;


    public Long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(Long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public Map<JobStatus, Long> getCountByStatus() {
        return countByStatus;
    }

    public void setCountByStatus(Map<JobStatus, Long> countByStatus) {
        this.countByStatus = countByStatus;
    }
}
