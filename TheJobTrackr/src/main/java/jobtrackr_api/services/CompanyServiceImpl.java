package jobtrackr_api.services;

import jobtrackr_api.converters.CompanyConverter;
import jobtrackr_api.dtos.CompanyRequestDTO;
import jobtrackr_api.dtos.CompanyResponseDTO;
import jobtrackr_api.exceptions.CompanyAlreadyExistsException;
import jobtrackr_api.exceptions.CompanyNotFoundException;
import jobtrackr_api.models.Company;
import jobtrackr_api.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private CompanyConverter companyConverter;

    @Override
    public List<CompanyResponseDTO> listCompanies() {

        List<CompanyResponseDTO> response = companyRepository.findAll().stream()
                .map(companyConverter::toResponseDTO)
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public CompanyResponseDTO createCompany(CompanyRequestDTO companyRequestDTO) {
        if (companyRequestDTO == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
            if (companyRepository.findByName(companyRequestDTO.getName()).isPresent()) {
                throw new CompanyAlreadyExistsException();
            }

            Company company = companyRepository.save(companyConverter.toEntity(companyRequestDTO));

            CompanyResponseDTO savedCompany = companyConverter.toResponseDTO(company);

            return savedCompany;
    }

    @Override
    public CompanyResponseDTO findById(Long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException());

        return companyConverter.toResponseDTO(company);
    }

    @Override
    public void deleteById(Long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }


        companyRepository.deleteById(findById(id).getId());
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setCompanyConverter(CompanyConverter companyConverter) {
        this.companyConverter = companyConverter;
    }
}
