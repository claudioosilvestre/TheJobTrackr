package jobtrackr_api.services;

import jobtrackr_api.converters.JobApplicationConverter;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.of(jobApplication));

        assertThrows(InvalidStatusException.class, () -> {
            jobApplicationServiceImpl.updateStatus(1L, JobStatus.OFFER);
        });
    }

}
