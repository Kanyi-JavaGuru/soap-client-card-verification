package com.proaktiv.io.services;

import java.io.File;

public interface ScratchCardService {

	/**
	 * verifies if scratch card is valid or not
	 * @param number
	 * @return
	 */
	public boolean verifyScratchCard(String number);
	
	/**
	 * reads cards numbers stored in a file and 
	 * verifies if scratch cards are valid or not
	 * @param file
	 */
	public void verifyScratchCardsFromFile(File file);
}
