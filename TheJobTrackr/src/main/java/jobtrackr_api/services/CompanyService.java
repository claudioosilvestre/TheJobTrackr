package jobtrackr_api.services;

import jobtrackr_api.models.Company;

import java.util.List;

public interface CompanyService {

    List<Company> listCompanies();

    Company createCompany(Company company);

    Company findById(Long id);

    void deleteById(Long id);
}
