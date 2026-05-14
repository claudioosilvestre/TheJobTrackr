package jobtrackr_api.converters;

import jobtrackr_api.dtos.CompanyRequestDTO;
import jobtrackr_api.dtos.CompanyResponseDTO;
import jobtrackr_api.models.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter {

    public CompanyResponseDTO toResponseDTO(Company company) {

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

        companyResponseDTO.setId(company.getId());
        companyResponseDTO.setName(company.getName());
        companyResponseDTO.setIndustry(company.getIndustry());
        companyResponseDTO.setWebsite(company.getWebsite());

        return companyResponseDTO;
    }

    public Company toEntity(CompanyRequestDTO companyRequestDTO) {

        Company company = new Company();

        company.setName(companyRequestDTO.getName());
        company.setIndustry(companyRequestDTO.getIndustry());
        company.setWebsite(companyRequestDTO.getWebsite());

        return company;
    }
}
