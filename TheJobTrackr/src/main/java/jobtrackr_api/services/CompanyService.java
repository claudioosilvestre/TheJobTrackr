package jobtrackr_api.services;

import jobtrackr_api.dtos.CompanyRequestDTO;
import jobtrackr_api.dtos.CompanyResponseDTO;
import jobtrackr_api.models.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyResponseDTO> listCompanies();

    CompanyResponseDTO createCompany(CompanyRequestDTO companyRequestDTO);

    CompanyResponseDTO findById(Long id);

    void deleteById(Long id);
}
