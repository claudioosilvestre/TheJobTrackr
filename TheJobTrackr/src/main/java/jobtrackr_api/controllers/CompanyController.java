package jobtrackr_api.controllers;

import jakarta.validation.Valid;
import jobtrackr_api.dtos.CompanyRequestDTO;
import jobtrackr_api.dtos.CompanyResponseDTO;
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
    public ResponseEntity<List<CompanyResponseDTO>> listCompanies() {

        return ResponseEntity.ok(companyService.listCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> findById(@PathVariable Long id) {

        CompanyResponseDTO companyResponseDTO = companyService.findById(id);

        return ResponseEntity.ok(companyResponseDTO);
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {

        CompanyResponseDTO companyResponseDTO = companyService.createCompany(companyRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(companyResponseDTO);
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
