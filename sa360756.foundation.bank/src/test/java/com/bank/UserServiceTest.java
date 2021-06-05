package com.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.dao.AccountDao;
import com.bank.dao.CustomerDao;
import com.bank.entity.Account;
import com.bank.entity.AccountType;
import com.bank.entity.Customer;
import com.bank.entity.Saving;
import com.bank.service.UserService;
import com.bank.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserServiceImpl.class)
public class UserServiceTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private CustomerDao customerDao;
	@MockBean
	private AccountDao accountDao;

	@Autowired
	private UserService userService;

	@Test
	public void getSaveCustomerTest() throws Exception {
		Customer mockCustomer = new Customer();
		mockCustomer.setId(1);
		mockCustomer.setAddress("patna");
		mockCustomer.setName("rahul");
		Account account = new Account();
		account.setAccountNumber(2345567);
		account.setBalance(80000);
		AccountType accountType = new AccountType();
		accountType.setCurrent("");
		Saving saving = new Saving();
		saving.setIndividual("");
		saving.setJoint("Yes");
		accountType.setSaving(saving);
		account.setAccountType(accountType);
		mockCustomer.setAccount(account);
		Mockito.when(customerDao.save(mockCustomer)).thenReturn(mockCustomer);

		assertThat(userService.getSaveCustomer(mockCustomer)).isEqualTo(mockCustomer);

	}

	@Test
	public void findbyCustomerIdTest() throws Exception {
		Customer mockCustomer = new Customer();
		mockCustomer.setId(1);
		mockCustomer.setAddress("patna");
		mockCustomer.setName("rahul");
		Account account = new Account();
		account.setAccountNumber(2345567);
		account.setBalance(80000);
		AccountType accountType = new AccountType();
		accountType.setCurrent("");
		Saving saving = new Saving();
		saving.setIndividual("");
		saving.setJoint("Yes");
		accountType.setSaving(saving);
		account.setAccountType(accountType);
		mockCustomer.setAccount(account);

		Mockito.when(customerDao.findById(1)).thenReturn((Optional.of(mockCustomer)));
		assertThat(userService.findbyCustomerId(1)).isEqualTo(Optional.of(mockCustomer));
	}

	@Test
	public void TestgetAllCustomer() throws Exception {
		Customer mockCustomer = new Customer();
		mockCustomer.setId(1);
		mockCustomer.setAddress("patna");
		mockCustomer.setName("rahul");
		Account account = new Account();
		account.setAccountNumber(2345567);
		account.setBalance(80000);
		AccountType accountType = new AccountType();
		accountType.setCurrent("");
		Saving saving = new Saving();
		saving.setIndividual("");
		saving.setJoint("Yes");
		accountType.setSaving(saving);
		account.setAccountType(accountType);
		mockCustomer.setAccount(account);

		Customer mockCustomer1 = new Customer();
		mockCustomer1.setId(1);
		mockCustomer1.setAddress("patna1");
		mockCustomer1.setName("rahul");
		Account account1 = new Account();
		account1.setAccountNumber(2345567);
		account1.setBalance(80000);
		AccountType accountType1 = new AccountType();
		accountType1.setCurrent("");
		Saving saving1 = new Saving();
		saving1.setIndividual("");
		saving1.setJoint("Yes");
		accountType1.setSaving(saving);
		account1.setAccountType(accountType1);
		mockCustomer1.setAccount(account1);

		List<Customer> cust = new ArrayList<Customer>();
		cust.add(mockCustomer1);
		cust.add(mockCustomer);

		Mockito.when(customerDao.findAll()).thenReturn(cust);
	    assertThat(userService.findAllCustomer()).isEqualTo(cust);
	}
	  
	
	@Test
	public void TestdeleteCustomer() throws Exception {
		Customer mockCustomer = new Customer();
		mockCustomer.setId(1);
		mockCustomer.setAddress("patna");
		mockCustomer.setName("rahul");
		Account account = new Account();
		account.setAccountNumber(2345567);
		account.setBalance(80000);
		AccountType accountType = new AccountType();
		accountType.setCurrent("");
		Saving saving = new Saving();
		saving.setIndividual("");
		saving.setJoint("Yes");
		accountType.setSaving(saving);
		account.setAccountType(accountType);
		mockCustomer.setAccount(account);
		
		//Mockito.when(customerDao.findById(1)).thenReturn(Optional.of(mockCustomer));
		userService.deleteCustomer(mockCustomer.getId());
		//Mockito.when(customerDao.existsById(Optional.of(mockCustomer).get().getId())).thenReturn(false);
		System.out.println("mockCustomer"+customerDao.existsById(Optional.of(mockCustomer).get().getId()));
		assertFalse(customerDao.existsById(Optional.of(mockCustomer).get().getId()));
		 
		
	}
	  
	@Test 
	public void TestdeleteAllCustomer() throws Exception { 
		Customer mockCustomer = new Customer();
		mockCustomer.setId(1);
	  mockCustomer.setAddress("patna");
	  mockCustomer.setName("rahul"); 
	  Account account = new Account(); 
	  account.setAccountNumber(2345567);
	  account.setBalance(80000);
	  AccountType accountType = new AccountType();
	  accountType.setCurrent("");
	  Saving saving = new Saving();
	  saving.setIndividual(""); 
	  saving.setJoint("Yes");
	  accountType.setSaving(saving); 
	  account.setAccountType(accountType);
	  mockCustomer.setAccount(account);
	  
	  Customer mockCustomer1 = new Customer();
		mockCustomer1.setId(1);
	  mockCustomer1.setAddress("patna");
	  mockCustomer1.setName("rahul"); 
	  Account account1 = new Account(); 
	  account1.setAccountNumber(2345567);
	  account1.setBalance(80000);
	  AccountType accountType1 = new AccountType();
	  accountType1.setCurrent("");
	  Saving saving1 = new Saving();
	  saving1.setIndividual(""); 
	  saving1.setJoint("Yes");
	  accountType1.setSaving(saving); 
	  account1.setAccountType(accountType);
	  mockCustomer1.setAccount(account1);
	  
	  List<Customer> cust= new ArrayList<>();
	  cust.add(mockCustomer1);
	  cust.add(mockCustomer);
	  Mockito.when(customerDao.findAll()).thenReturn (cust);
		userService.deleteAllCustomer();
		//Mockito.when(customerDao.existsById(Optional.of(mockCustomer).get().getId())).thenReturn(false);
		System.out.println("mockCustomer1"+customerDao.existsById(cust.get(0).getId()));
		
		assertFalse(customerDao.existsById(cust.get(0).getId()));
	  
	  }
	@Test
	public void TestcheckBalance() throws Exception {
		Customer mockCustomer = new Customer();
		mockCustomer.setId(1);
		mockCustomer.setAddress("patna");
		mockCustomer.setName("rahul");
		Account account = new Account();
		account.setAccountNumber(2345567);
		account.setBalance(80000);
		AccountType accountType = new AccountType();
		accountType.setCurrent("");
		Saving saving = new Saving();
		saving.setIndividual("");
		saving.setJoint("Yes");
		accountType.setSaving(saving);
		account.setAccountType(accountType);
		mockCustomer.setAccount(account);
		
		Mockito.when(accountDao.getById(account.getAccountNumber())).thenReturn(account);
		userService.checkBalance(account.getAccountNumber(), 8000);
		System.out.println(accountDao.getById(account.getAccountNumber()).getBalance());
		assertThat(accountDao.getById(account.getAccountNumber()).getBalance()).isEqualTo(72000);
		
		
	}
	@Test
	public void findbyAccountIdTest() throws Exception {
		Account account = new Account();
		account.setAccountNumber(2345567);
		account.setBalance(80000);
		AccountType accountType = new AccountType();
		accountType.setCurrent("");
		Saving saving = new Saving();
		saving.setIndividual("");
		saving.setJoint("Yes");
		accountType.setSaving(saving);
		account.setAccountType(accountType);
		

		Mockito.when(accountDao.findById(1)).thenReturn((Optional.of(account)));
		assertThat(userService.findbyAccountId(1)).isEqualTo(Optional.of(account));
	}
	
	  
		@Test 
		public void TestfindAllAccounnt() throws Exception { 
		 Account account = new Account(); 
		  account.setAccountNumber(2345567);
		  account.setBalance(80000);
		  AccountType accountType = new AccountType();
		  accountType.setCurrent("");
		  Saving saving = new Saving();
		  saving.setIndividual(""); 
		  saving.setJoint("Yes");
		  accountType.setSaving(saving); 
		  account.setAccountType(accountType);
		 
		  
		
		  Account account1 = new Account(); 
		  account1.setAccountNumber(2345567);
		  account1.setBalance(80000);
		  AccountType accountType1 = new AccountType();
		  accountType1.setCurrent("");
		  Saving saving1 = new Saving();
		  saving1.setIndividual(""); 
		  saving1.setJoint("Yes");
		  accountType1.setSaving(saving); 
		  account1.setAccountType(accountType);
		  List<Account>acnt= new ArrayList<>();
		  acnt.add(account1);
		  acnt.add(account);
		  Mockito.when(accountDao.findAll()).thenReturn (acnt);
			assertThat(userService.findAllAccounnt().size()).isEqualTo(2);
		  
		  }
		@Test
		public void TestgetSaveAccount() throws Exception {
			Account account = new Account(); 
			  account.setAccountNumber(2345567);
			  account.setBalance(80000);
			  AccountType accountType = new AccountType();
			  accountType.setCurrent("");
			  Saving saving = new Saving();
			  saving.setIndividual(""); 
			  saving.setJoint("Yes");
			  accountType.setSaving(saving); 
			  account.setAccountType(accountType);
			  Mockito.when(accountDao.save(account)).thenReturn (account);
				assertThat(userService.getSaveAccount(account)).isEqualTo((account));
		}
	 

}
