package com.bank.service;

import java.util.List;
import java.util.Optional;

import com.bank.entity.Account;
import com.bank.entity.Customer;

public interface UserService {
	public Customer getSaveCustomer(Customer customer);
	public Optional<Customer> findbyCustomerId(int id);
	public List<Customer> findAllCustomer();
	public String deleteCustomer(int id);
	public String deleteAllCustomer();
	public Optional<Account> findbyAccountId(int id);
	public List<Account> findAllAccounnt();
	public Account getSaveAccount(Account account);
	public String checkBalance(int accountNumber, int amount);
}
