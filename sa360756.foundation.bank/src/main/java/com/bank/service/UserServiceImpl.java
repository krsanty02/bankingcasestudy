package com.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.AccountDao;
import com.bank.dao.CustomerDao;
import com.bank.entity.Account;
import com.bank.entity.AccountType;
import com.bank.entity.Customer;
import com.bank.entity.Saving;
import com.bank.error.UserNotFound;
import com.bank.service.UserServiceImpl;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AccountDao accountDao;

	public Customer getSaveCustomer(Customer customer) {

		Customer cust = customerDao.save(customer);
		//System.out.println("Sevice" + cust.toString());
		return cust;
	}

	public Optional<Customer> findbyCustomerId(int id) {
		System.out.println("findbyCustomerId");
		Optional<Customer> customer = customerDao.findById(id);
		return customer;
	}

	public List<Customer> findAllCustomer() {
		System.out.println("service findall Customer");
		List<Customer> customer = customerDao.findAll();
		return customer;
	}

	public String deleteCustomer(int id) {
		System.out.println("delelete customer"+id);
		
		customerDao.deleteById(id);
		return "record deleted";

	}

	public String deleteAllCustomer() {
		customerDao.deleteAll();
		String msg = "All records are deleted";
		return msg;
	}

	public Optional<Account> findbyAccountId(int id) {
		Optional<Account> account = accountDao.findById(id);
		return account;
	}

	public List<Account> findAllAccounnt() {
		List<Account> account = accountDao.findAll();

		return account;
	}

	public Account getSaveAccount(Account account) {

		return accountDao.save(account);
	}

	public String checkBalance(int accountNumber, int amount) {
		Account account = accountDao.getById(accountNumber);
		// Optional<Account> acnt=accountDao.findById(accountNumber);
		if (account != null) {
			int balance = account.getBalance();
			if (balance < amount) {
				return "Insufficient Balance";
			} else {
				// int customerid=account.get().getCustomer().getId();
				int amnt = balance - amount;
				account.setBalance(amnt);
				accountDao.save(account);
			}
			return "transaction Successfull";
		} else
			System.out.println(">>>>>>>.3>");
		throw new UserNotFound("User Not Found");

	}

}
