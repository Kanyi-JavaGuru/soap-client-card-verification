package com.proaktiv.io.services;

import java.io.File;

public interface ScratchCardService {

	public boolean verifyScratchCard(String number);
	
	public void verifyScratchCardsFromFile(File file);
}
