package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {
	/*
	 * @Query(value="INSERT INTO ACCOUNT WHERE ID=:customerid AND ") void
	 * updateBalance(@Param ("customerid") int customerid,@Param ("amnt") int amnt);
	 */

}
