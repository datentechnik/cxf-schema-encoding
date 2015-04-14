package com.datentechnik.cxf.test;

import java.io.IOException;
import java.net.URL;

import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

@ServiceMode(value = Service.Mode.PAYLOAD)
@WebServiceProvider(targetNamespace = "http://reference.e-government.gv.at/namespace/elaktrans/2#", serviceName = "ElakTransService", portName = "ElakTrans", wsdlLocation = "wsdl/ElakTrans_20.wsdl")
public class ElakTransProvider implements Provider<StreamSource> {

	@Override
	public StreamSource invoke(StreamSource request) {
		URL xmlResource = getClass().getResource("BeispielResponse.xml");
		try {
			return new StreamSource(xmlResource.openStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
