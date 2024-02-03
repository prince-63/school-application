package com.yourschool.repository;

import com.yourschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    //List<Contact> findByStatus(String status);

    Page<Contact> findByStatus(String status, Pageable pageable);

    List<Contact> getByStatus(String status);
}
