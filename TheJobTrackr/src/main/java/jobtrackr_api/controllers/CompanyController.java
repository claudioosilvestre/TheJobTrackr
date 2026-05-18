package jobtrackr_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import jakarta.validation.Valid;
import jobtrackr_api.dtos.CompanyRequestDTO;
import jobtrackr_api.dtos.CompanyResponseDTO;
import jobtrackr_api.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Companies", description = "Manage job application companies")
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    @Operation(summary = "List all the companies")
    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> listCompanies() {

        return ResponseEntity.ok(companyService.listCompanies());
    }

    @Operation(summary = "Find company by id")
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> findById(@PathVariable Long id) {

        CompanyResponseDTO companyResponseDTO = companyService.findById(id);

        return ResponseEntity.ok(companyResponseDTO);
    }

    @Operation(summary = "Create a new company")
    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {

        CompanyResponseDTO companyResponseDTO = companyService.createCompany(companyRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(companyResponseDTO);
    }

    @Operation(summary = "Delete company by id")
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
