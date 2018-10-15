package com.proaktiv.io.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellulant.wsdl.DetailsType;
import com.cellulant.wsdl.GetBankResponseType;
import com.proaktiv.io.services.BankService;
import com.proaktiv.io.services.ScratchCardService;
import com.proaktiv.io.soap.gateways.BankTypeGateway;

@Service
public class ScratchCardServiceImpl implements ScratchCardService {
	private static final Logger log = LoggerFactory.getLogger(ScratchCardServiceImpl.class);

	@Autowired
	private BankTypeGateway gateway;
	@Autowired
	private BankService service;
	@Override
	public boolean verifyScratchCard(final String number) {
		if(number.length() == 23) {
			String[] sets = new String[4];
			try {
				sets = number.split("-");
			} catch (Exception e) {
				log.info("Exception: "+e.getMessage());
			}			
			return crossCheckCard(sets);
		}
		return false;
	}

	public String getBic(final String[] sets) {
		return concat(extractWithoutCheckSum(sets[0]), 
				extractWithoutCheckSum(sets[2]));//setA.concat(setC);
	}

	private String extractWithoutCheckSum(final String set) {
		final String result = set.substring(0, 4);
		return result;
	}
	
	public String getCardValue(final String[] sets) {
		return concat(extractWithoutCheckSum(sets[3]), 
				extractWithoutCheckSum(sets[1]));//setD.concat(setB);
	}
	
	private boolean crossCheckCard(final String[] sets) {			
		for (int i = 0; i <= sets.length - 1; i++) {
			final String firstFour = sets[i].substring(0, 4);
			final String fifthDigit = sets[i].substring(4);
			
			final Integer firstFourInt = Integer.parseInt(firstFour);
			final Integer fifthDigitInt = Integer.parseInt(fifthDigit);
			
			Integer checkSum = firstFourInt;			

			do {				
				checkSum = convertToOctal(checkSum);	
				if(!isValid(checkSum))
					checkSum = addDigits(checkSum);				
			}while(!isValid(checkSum));
			if(checkSum != fifthDigitInt)
				return false;
		}
		return true;
	}
	
	private Integer addDigits(final Integer n) {
		if(n == 0)
			return 0;
		return addDigits(n/10) + n % 10;
	}
	
	private Integer convertToOctal(final Integer n) {
		return Integer.parseInt(Integer.toOctalString(n));
	}

	private boolean isValid(final Integer n) {
		if(n >=10)
			return false;
		return true;	
	}
	
	private String concat(final String a, final String b) {
		final StringBuilder builder = new StringBuilder(a.trim())
				.append(b.trim());
		return builder.toString();
	}
	
	public DetailsType retrieveBankDetails(final String bic) {
		final GetBankResponseType response = gateway.getDetailsType(bic);
		return response.getDetails();
	}
	
	@Override
	public void verifyScratchCardsFromFile(final File file) {
		String[] sets = new String[4];
		DetailsType bankDetails = new DetailsType();
		try (final Scanner inputStream = new Scanner(file)){			
			inputStream.next();
			
			while (inputStream.hasNext()) {
				final String cardNumber = inputStream.next();
				final Boolean isValid = verifyScratchCard(cardNumber);
				if(isValid) {
					sets = cardNumber.split("-");
					final String bic = getBic(sets);
					bankDetails = retrieveBankDetails(bic);					
				}					
			}			
			service.save(bankDetails, getCardValue(sets));		
		} catch (FileNotFoundException e) {
			log.info("FileNotFoundException: "+e.getMessage());
		} catch (NullPointerException e) {
			log.info("NullPointerException: "+e.getMessage());
		} catch (Exception e) {
			log.info("Exception: "+e.getMessage());
		} 
	}
}
