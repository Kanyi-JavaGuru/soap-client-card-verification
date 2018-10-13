package com.proaktiv.io.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proaktiv.io.models.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{

	/**
	 * retrieves Optional bank object
	 * @param bankName
	 * @return
	 */
	public Optional<Bank> findByName(String bankName);

}
