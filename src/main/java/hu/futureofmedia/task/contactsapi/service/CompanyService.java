package hu.futureofmedia.task.contactsapi.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.exception.ResourceNotFoundException;
import hu.futureofmedia.task.contactsapi.repositories.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Page<Company> findAllCompany(Pageable pageable){
        return companyRepository.findAll(pageable);
    }

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public Company updateCompany(Long companyId, Company companyRequest){
        return companyRepository.findById(companyId).map(company -> {
            companyRequest.setId(company.getId());
            companyRequest.setCreateDate(company.getCreateDate());
            return companyRepository.save(companyRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("ContactId " + companyId + "not found"));
    }

    public void deleteCompany(Long companyId){
        companyRepository.deleteById(companyId);
    }


}
