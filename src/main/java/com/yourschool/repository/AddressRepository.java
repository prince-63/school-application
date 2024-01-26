package com.yourschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.yourschool.model.Address;

@Repository
@RepositoryRestResource(path = "address")
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
