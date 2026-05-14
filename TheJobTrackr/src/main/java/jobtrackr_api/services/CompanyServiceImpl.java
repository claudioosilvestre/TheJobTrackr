package jobtrackr_api.services;

import jobtrackr_api.exceptions.CompanyAlreadyExistsException;
import jobtrackr_api.exceptions.CompanyNotFoundException;
import jobtrackr_api.models.Company;
import jobtrackr_api.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Override
    public List<Company> listCompanies() {

        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
            if (companyRepository.findByName(company.getName()).isPresent()) {
                throw new CompanyAlreadyExistsException();
            }

            companyRepository.save(company);

            return company;
    }

    @Override
    public Company findById(Long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException());
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
}
