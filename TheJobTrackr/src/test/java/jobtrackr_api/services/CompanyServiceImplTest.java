package jobtrackr_api.services;

import jobtrackr_api.converters.CompanyConverter;
import jobtrackr_api.dtos.CompanyRequestDTO;
import jobtrackr_api.dtos.CompanyResponseDTO;
import jobtrackr_api.exceptions.CompanyAlreadyExistsException;
import jobtrackr_api.exceptions.CompanyNotFoundException;
import jobtrackr_api.models.Company;
import jobtrackr_api.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {

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
    public void listAllCompanies() {

        Company company = new Company();
        company.setName("Google");

        CompanyResponseDTO dto = new CompanyResponseDTO();
        dto.setName("Google");

        when(companyRepository.findAll()).thenReturn(List.of(company));
        when(companyConverter.toResponseDTO(company)).thenReturn(dto);

        List<CompanyResponseDTO> result = companyServiceImpl.listCompanies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Google", result.get(0).getName());

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

    @Test
    public void createCompany_withExistedCompany_shouldThrow_CompanyAlreadyExistsException(){
        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();

        companyRequestDTO.setName("Google");
        companyRequestDTO.setIndustry("Technology");
        companyRequestDTO.setWebsite("https://google.com");

        Company company = new Company();
        company.setName("Google");

        when(companyRepository.findByName("Google")).thenReturn(Optional.of(company));

        assertThrows(CompanyAlreadyExistsException.class, () -> {
           companyServiceImpl.createCompany(companyRequestDTO);
        });
    }

    @Test
    public void findCompanyById_withValidData_shouldReturn_ResponseDTO() {

        Company company = new Company();
        company.setName("Google");
        company.setIndustry("Technology");
        company.setWebsite("https://google.com");

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
        companyResponseDTO.setId(company.getId());
        companyResponseDTO.setName(company.getName());
        companyResponseDTO.setIndustry(company.getIndustry());
        companyResponseDTO.setWebsite(company.getWebsite());

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyConverter.toResponseDTO(company)).thenReturn(companyResponseDTO);

        CompanyResponseDTO result = companyServiceImpl.findById(1L);

        assertNotNull(result);
        assertEquals("Google", result.getName());

    }

    @Test
    public void findCompanyById_withInvalidData_shouldThrow_CompanyNotFoundException() {

        when(companyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> {
            companyServiceImpl.findById(1L);
        });
    }

    @Test
    public void deleteById_withValidData() {
        Company company = new Company();

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        companyServiceImpl.deleteById(1L);

        verify(companyRepository).deleteById(1L);
    }

}
