package com.proaktiv.io.soap.gateways;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.cellulant.wsdl.GetBankResponseType;
import com.cellulant.wsdl.GetBankType;
import com.cellulant.wsdl.ObjectFactory;

public class BankTypeGateway extends WebServiceGatewaySupport{
	private static final Logger log = LoggerFactory.getLogger(BankTypeGateway.class);

	@SuppressWarnings("unchecked")
	public GetBankResponseType getDetailsType(final String bic) {		
		JAXBElement<GetBankResponseType> jaxbElement = null;
		final GetBankType request = new GetBankType();
		request.setBlz(bic);
		try {
			jaxbElement = (JAXBElement<GetBankResponseType>) getWebServiceTemplate()
												.marshalSendAndReceive(new ObjectFactory()
													.createGetBank(request));

		} catch (Exception e) {
			log.info("Error occured: "+e.getMessage());
		}
		return jaxbElement.getValue();
	}
}
