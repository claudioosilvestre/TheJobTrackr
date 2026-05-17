package jobtrackr_api.services;

import jobtrackr_api.converters.CompanyConverter;
import jobtrackr_api.dtos.CompanyRequestDTO;
import jobtrackr_api.dtos.CompanyResponseDTO;
import jobtrackr_api.models.Company;
import jobtrackr_api.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTestsImpl {

    @InjectMocks
    private CompanyServiceImpl companyServiceImpl;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyConverter companyConverter;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void createCompany_withValidData_ShouldReturnResponseDTO() {

        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();
        companyRequestDTO.setName("Google");
        companyRequestDTO.setIndustry("Technology");
        companyRequestDTO.setWebsite("https://google.com");

        Company company = new Company();
        company.setName("Google");

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
        companyResponseDTO.setName("Google");

        when(companyRepository.findByName("Google")).thenReturn(Optional.empty());
        when(companyConverter.toEntity(companyRequestDTO)).thenReturn(company);
        when(companyRepository.save(company)).thenReturn(company);
        when(companyConverter.toResponseDTO(company)).thenReturn(companyResponseDTO);

        CompanyResponseDTO result = companyServiceImpl.createCompany(companyRequestDTO);

        assertNotNull(result);
        assertEquals("Google", result.getName());
        verify(companyRepository).save(company);
    }
}
