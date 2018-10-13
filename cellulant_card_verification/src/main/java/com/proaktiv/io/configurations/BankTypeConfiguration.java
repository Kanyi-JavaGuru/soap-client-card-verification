package com.proaktiv.io.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.proaktiv.io.soap.gateways.BankTypeGateway;

@Configuration
public class BankTypeConfiguration {

	/**
	 * sets up bean for marshaller
	 * which serializes and deserializes
	 * wsdl objects to and from pojos
	 * @return
	 */
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.cellulant.wsdl");
		return marshaller;
	}
	
	/**
	 * sets up bean for BankTypeGateway
	 * and adds the marshaller
	 * @param marshaller
	 * @return
	 */
	@Bean
	public BankTypeGateway bankTypeService(Jaxb2Marshaller marshaller) {
		BankTypeGateway service = new BankTypeGateway();
		service.setDefaultUri("http://www.thomas-bayer.com/axis2/services/BLZService");
		service.setMarshaller(marshaller);
		service.setUnmarshaller(marshaller);
		return service;
	}
}
