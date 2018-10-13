package com.proaktiv.io;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proaktiv.io.services.ScratchCardService;

@SpringBootApplication
public class CellulantCardVerificationApplication implements CommandLineRunner{

	@Autowired
	private ScratchCardService cardService;
	
	public static void main(String[] args) {
		SpringApplication.run(CellulantCardVerificationApplication.class, args);
	}
	
	/**
	 * on start up it reads all cards from file
	 * checks if they are valid,
	 * retrieves bank details for valid cards and
	 * saves the bank details to the database.
	 */
	@Override
	public void run(String... args) throws Exception {
		final File file = new File(SCRATCH_CARDS_FILE);
		cardService.verifyScratchCardsFromFile(file);
	}

	private static final String SCRATCH_CARDS_FILE = "./src/main/resources/Card_Numbers.csv";
}
