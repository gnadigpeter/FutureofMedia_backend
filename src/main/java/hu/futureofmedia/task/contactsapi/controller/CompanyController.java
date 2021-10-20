package hu.futureofmedia.task.contactsapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.service.CompanyService;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/company")
public class CompanyController {
    


    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
             this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Company>> getAllCompany(Pageable pageable) {
        Page<Company> company = companyService.findAllCompany(pageable);
        return new ResponseEntity<>(company, HttpStatus.OK);
    } 

    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@Valid @RequestBody Company company) {
        Company newCompany = companyService.addCompany(company);
        return new ResponseEntity<>(newCompany, HttpStatus.OK);
    }

    @PutMapping("/update/companyId/{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable (value = "companyId") Long companyId, 
                                @Valid @RequestBody Company companyRequest) {
        
        Company updateCompany = companyService.updateCompany(companyId, companyRequest);
        return new ResponseEntity<>(updateCompany, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/companyId/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {
       companyService.deleteCompany(companyId);
       return new ResponseEntity<>(HttpStatus.OK);
    }


}
