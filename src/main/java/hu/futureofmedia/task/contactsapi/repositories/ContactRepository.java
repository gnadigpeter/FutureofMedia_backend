package hu.futureofmedia.task.contactsapi.repositories;

import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.Stts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

    Page<Contact> findAll(Pageable pageable);
    Page<Contact> findByCompanyId(Long companyId, Pageable pageable);
    Optional<Contact> findByIdAndCompanyId(Long Id, Long companyId);
    Page<Contact> findByStatus(Stts active, Pageable pageable);


}
