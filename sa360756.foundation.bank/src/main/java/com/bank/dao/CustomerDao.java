package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{

}
