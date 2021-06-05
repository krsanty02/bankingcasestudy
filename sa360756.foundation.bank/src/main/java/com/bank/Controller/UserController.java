package com.bank.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Account;
import com.bank.entity.Customer;
import com.bank.error.ErrorResponse;
import com.bank.error.UserNotFound;
import com.bank.service.UserService;
import com.bank.service.UserServiceImpl;
import com.bank.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/saveCustomer")
	public ResponseEntity<Customer> getSave(@RequestBody Customer customer) {
		System.out.println(customer.getAddress());
		System.out.println(customer);
		// System.out.println(customer.getAccount().getAccountType().getSaving().getIndividual());
		Customer cust = service.getSaveCustomer(customer);
		System.out.println("customer" + cust.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(cust);
	}

	@GetMapping("/findByCustomerId/{id}")
	public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable("id") int id) {
		System.out.println("findByCustomerId{id}");
		System.out.println("id" + id);
		Optional<Customer> customer = service.findbyCustomerId(id);
		if (customer.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		else
			throw new UserNotFound("User Not Found");
	}

	@GetMapping("/findAllCustomer")
	public ResponseEntity<Object> getAllCustomer() {
		System.out.println("findAllCustomer");

		List<Customer> customer = service.findAllCustomer();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(Customer c:customer) {
			System.out.println(c.getAddress());
		}
		if (customer != null)
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		else
			throw new UserNotFound("User Not Found");
	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer cust) {
		System.out.println("update Customer");
		int id = cust.getId();
		Optional<Customer> customer = service.findbyCustomerId(id);
		if (customer.isPresent()) {
			Customer cust1 = service.getSaveCustomer(cust);
			return ResponseEntity.status(HttpStatus.OK).body(cust1);
		} else
			throw new UserNotFound("User Not Found");
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id) {
		System.out.println("delete Customer");
		Optional<Customer> customer = service.findbyCustomerId(id);
		if (customer.isPresent()) {
			String msg = service.deleteCustomer(id);
			return ResponseEntity.status(HttpStatus.OK).body(msg);
		} else
			throw new UserNotFound("User Not Found");
	}

	@DeleteMapping("/deleteAllCustomer")
	public ResponseEntity<Object> deleteAllCustomer() {
		System.out.println("delete All Customer");
		String msg = service.deleteAllCustomer();

		return ResponseEntity.ok().body(msg);
	}

	@GetMapping("/findByAccountId/{id}")
	public ResponseEntity<Object> getAccountById(@PathVariable("id") int id) {
		System.out.println("findByAccountId{id}");
		System.out.println("id" + id);
		Optional<Account> account = service.findbyAccountId(id);
		if (account.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(account);
		} else
			throw new UserNotFound("User Not Found");
	}

	@GetMapping("/findAllAccount")
	public ResponseEntity<Object> getAllAccountById() {
		System.out.println("findAllAccountId");
		List<Account> account = service.findAllAccounnt();
		if (account != null) {
			return ResponseEntity.status(HttpStatus.OK).body(account);
		} else
			throw new UserNotFound("User Not Found");
	}

	/*
	 * @PutMapping("/updateAccount") public ResponseEntity<Object>
	 * updateAccount(@RequestBody Account account) {
	 * System.out.println("update Account"); int id = account.getAccountNumber();
	 * Optional<Account> act = service.findbyAccountId(id); if (act.isPresent()) {
	 * Account acct = service.getSaveAccount(account); return
	 * ResponseEntity.status(HttpStatus.OK).body(acct); } else throw new
	 * UserNotFound("User Not Found"); }
	 */

	/*
	 * @PostMapping("/saveAccount") public ResponseEntity<Account>
	 * getSaveAccount(@RequestBody Account account) { Account act =
	 * service.getSaveAccount(account); return
	 * ResponseEntity.status(HttpStatus.CREATED).body(act); }
	 */
	@PostMapping("/checkBalance")
	public ResponseEntity<Object> checkBalance(@RequestBody String request) {
		System.out.println("check Balance");
		JSONObject obj = new JSONObject(request);
		int accountNumber = obj.getInt("accountNumber");
		int amount = obj.getInt("amount");
		System.out.println("accountNumber" + accountNumber);
		System.out.println("amount" + amount);
		Optional<Account> account=service.findbyAccountId(accountNumber);
		if(account.isPresent()) {
		String msg = service.checkBalance(accountNumber, amount);

		return ResponseEntity.status(HttpStatus.OK).body(msg);
		}
		else
			throw new UserNotFound("User Not Found");
	}

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<Object> UsernotFoundException(UserNotFound ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode("Not Found");
		error.setMessage(ex.getMessage());
		error.setTimestamp(LocalDateTime.now());
System.out.println("11111111");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}
}
