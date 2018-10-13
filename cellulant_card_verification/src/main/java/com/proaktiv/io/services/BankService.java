package com.proaktiv.io.services;

import java.util.List;

import com.cellulant.wsdl.DetailsType;
import com.proaktiv.io.models.Bank;

public interface BankService {

	/**
	 * saves bank entity to database
	 * @param bank
	 * @return
	 */
	public Bank save(Bank bank);
	
	/**
	 * saves bank entity with details from DetailsType
	 * and also checks if bank already exists
	 * @param details
	 * @param cardValue
	 * @return
	 */
	public Bank save(DetailsType details, String cardValue);
	
	/**
	 * retrieves all banks saved in database
	 * @return
	 */
	public List<Bank> findAll();
}
