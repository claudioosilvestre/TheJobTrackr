package jobtrackr_api.services;

import jobtrackr_api.dtos.ApplicationStatsResponseDTO;
import jobtrackr_api.exceptions.CompanyNotFoundException;
import jobtrackr_api.exceptions.InvalidStatusException;
import jobtrackr_api.converters.JobApplicationConverter;
import jobtrackr_api.dtos.JobApplicationRequestDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.exceptions.JobApplicationNotFoundException;
import jobtrackr_api.exceptions.UserNotFoundException;
import jobtrackr_api.models.Company;
import jobtrackr_api.models.JobApplication;
import jobtrackr_api.models.JobStatus;
import jobtrackr_api.models.User;
import jobtrackr_api.repositories.CompanyRepository;
import jobtrackr_api.repositories.JobApplicationRepository;
import jobtrackr_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private JobApplicationRepository jobApplicationRepository;
    private JobApplicationConverter jobApplicationConverter;
    private UserRepository userRepository;
    private CompanyRepository companyRepository;

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

        User user = userRepository.findById(jobApplicationRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException());


        Company company = companyRepository.findById(jobApplicationRequestDTO.getCompanyId())
                        .orElseThrow(() -> new CompanyNotFoundException());

        jobApplication.setUser(user);
        jobApplication.setCompany(company);
        jobApplication.setJobStatus(JobStatus.APPLIED);

        jobApplicationRepository.save(jobApplication);

        return jobApplicationConverter.toResponseDTO(jobApplication);
    }

    @Override
    public JobApplicationResponseDTO updateStatus(Long id, JobStatus newStatus) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }
        if(newStatus == null) {
            throw new IllegalArgumentException("Job status must be valid");
        }

        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobApplicationNotFoundException());

        if(!jobApplication.getJobStatus().canTransitionTo(newStatus)) {
            throw new InvalidStatusException();
        }

        jobApplication.setJobStatus(newStatus);
        jobApplicationRepository.save(jobApplication);

        JobApplicationResponseDTO jobApplicationResponseDTO = jobApplicationConverter.toResponseDTO(jobApplication);

        return jobApplicationResponseDTO;
    }

    @Override
    public ApplicationStatsResponseDTO getApplicationStats() {

        Map<JobStatus, Long> countByStatus = jobApplicationRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(jobApplication -> jobApplication.getJobStatus(), Collectors.counting()));

        Long total = countByStatus.values().stream()
                .mapToLong(count -> count).sum();

        ApplicationStatsResponseDTO applicationStatusResponseDTO = new ApplicationStatsResponseDTO();
        applicationStatusResponseDTO.setCountByStatus(countByStatus);
        applicationStatusResponseDTO.setTotalApplications(total);

        return applicationStatusResponseDTO;
    }

    @Override
    public void deleteById(Long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        jobApplicationRepository.deleteById(findById(id).getId());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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
