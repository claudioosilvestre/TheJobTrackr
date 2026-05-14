package jobtrackr_api.controllers;

import jakarta.validation.Valid;
import jobtrackr_api.models.Company;
import jobtrackr_api.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;


    @GetMapping
    public ResponseEntity<List<Company>> listCompanies() {

        return ResponseEntity.ok(companyService.listCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {

        Company company = companyService.findById(id);

        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {

        companyService.createCompany(company);

        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        companyService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
