package com.proaktiv.io.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellulant.wsdl.DetailsType;
import com.proaktiv.io.models.Bank;
import com.proaktiv.io.repository.BankRepository;
import com.proaktiv.io.services.BankService;

@Service
public class BankServiceImpl implements BankService {
	private static final Logger log = LoggerFactory.getLogger(BankServiceImpl.class);

	@Autowired
	private BankRepository repo;
	
	@Override
	public Bank save(final Bank bank) {
		log.info("saved: "+bank);
		return repo.save(bank);
	}

	@Override
	public List<Bank> findAll() {
		return repo.findAll();
	}

	@Override
	public Bank save(final DetailsType details, final String cardValue) {
		final Optional<Bank> bank = repo.findByName(details.getBezeichnung());
		if(bank.isPresent())
			return bank.get();
		return repo.save(new Bank(details.getBezeichnung(), details.getBic(), 
				details.getOrt(), cardValue));
	}
}
