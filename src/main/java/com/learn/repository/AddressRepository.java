package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
