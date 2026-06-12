package jobtrackr_api.services;

import jobtrackr_api.converters.JobApplicationConverter;
import jobtrackr_api.dtos.ApplicationStatsResponseDTO;
import jobtrackr_api.dtos.JobApplicationResponseDTO;
import jobtrackr_api.models.Company;
import jobtrackr_api.models.JobApplication;
import jobtrackr_api.models.JobStatus;
import jobtrackr_api.models.User;
import jobtrackr_api.repositories.JobApplicationRepository;
import jobtrackr_api.exceptions.InvalidStatusException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceImplTest {

    @InjectMocks
    private JobApplicationServiceImpl jobApplicationServiceImpl;

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @Mock
    private JobApplicationConverter jobApplicationConverter;


    @Test
    public void updateStatus_withValidData_shouldReturnResponseDTO() {

        Company company = new Company();
        company.setName("Google");
        company.setIndustry("Technology");
        company.setWebsite("https://google.com");

        User user = new User();
        user.setFirstName("João");
        user.setLastName("António");
        user.setEmail("joaoantonio@email.com");

        JobApplication jobApplication = new JobApplication();
        jobApplication.setCompany(company);
        jobApplication.setUser(user);
        jobApplication.setPosition("Programmer");
        jobApplication.setNotes("Testing code");
        jobApplication.setJobStatus(JobStatus.APPLIED);

        JobApplicationResponseDTO jobApplicationResponseDTO = new JobApplicationResponseDTO();
        jobApplicationResponseDTO.setId(jobApplication.getId());
        jobApplicationResponseDTO.setUser(jobApplication.getUser());
        jobApplicationResponseDTO.setPosition(jobApplication.getPosition());
        jobApplicationResponseDTO.setNotes(jobApplication.getNotes());
        jobApplicationResponseDTO.setCompany(jobApplication.getCompany());
        jobApplicationResponseDTO.setJobStatus(jobApplication.getJobStatus());

        
        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.of(jobApplication));
        when(jobApplicationConverter.toResponseDTO(jobApplication)).thenReturn(jobApplicationResponseDTO);

        jobApplicationServiceImpl.updateStatus(1L, JobStatus.INTERVIEW);

        assertEquals(JobStatus.INTERVIEW, jobApplication.getJobStatus());
        verify(jobApplicationRepository).save(jobApplication);
    }


    @Test
    public void updateStatus_withInvalidData_shouldReturnInvalidStatusException() {

        Company company = new Company();
        company.setName("Google");
        company.setIndustry("Technology");
        company.setWebsite("https://google.com");

        User user = new User();
        user.setFirstName("João");
        user.setLastName("António");
        user.setEmail("joaoantonio@email.com");

        JobApplication jobApplication = new JobApplication();
        jobApplication.setCompany(company);
        jobApplication.setUser(user);
        jobApplication.setPosition("Programmer");
        jobApplication.setNotes("Testing code");

        jobApplication.setJobStatus(JobStatus.APPLIED);

        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.of(jobApplication));

        assertThrows(InvalidStatusException.class, () -> {
            jobApplicationServiceImpl.updateStatus(1L, JobStatus.OFFER);
        });
    }

    @Test
    public void getApplicationStats_shouldReturnAllApplicationStats() {

        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobStatus(JobStatus.OFFER);

        JobApplication jobApplication1 = new JobApplication();
        jobApplication1.setJobStatus(JobStatus.INTERVIEW);

        JobApplication jobApplication2 = new JobApplication();
        jobApplication2.setJobStatus(JobStatus.APPLIED);

        List<JobApplication> jobApplicationList = new ArrayList<>();
        jobApplicationList.add(jobApplication);
        jobApplicationList.add(jobApplication1);
        jobApplicationList.add(jobApplication2);

        when(jobApplicationRepository.findAll()).thenReturn(jobApplicationList);

        ApplicationStatsResponseDTO applicationStatsResponseDTO = jobApplicationServiceImpl.getApplicationStats();

        assertNotNull(applicationStatsResponseDTO);
        assertEquals(3L,(long) applicationStatsResponseDTO.getTotalApplications());
        assertEquals(1, (long) applicationStatsResponseDTO.getCountByStatus().get(JobStatus.APPLIED));
        assertEquals(1, (long) applicationStatsResponseDTO.getCountByStatus().get(JobStatus.INTERVIEW));
        assertEquals(1, (long) applicationStatsResponseDTO.getCountByStatus().get(JobStatus.OFFER));
        verify(jobApplicationRepository, times(1)).findAll();
    }

}
