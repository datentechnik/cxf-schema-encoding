package com.datentechnik.cxf.test;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.SOAPBinding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SchemaEncodingTest {
	
	public static final String XMLNS_ELAKTRANS = "http://reference.e-government.gv.at/namespace/elaktrans/2#";
	
	private Dispatch<StreamSource> dispatch;
	
	@PostConstruct
	public void init() throws Exception {
		
		QName portName = new QName("http://test.dt_i.at/test/", "ElakTrans");
		
		Service service = Service.create(new QName("http://test.dt_i.at/test/", "ElakTransService"));
		
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, "local://elaktrans");
		
		dispatch = service.createDispatch(portName, StreamSource.class, Mode.MESSAGE);

	}
	
	@Test
	public void testInsertELAKOutputObjectDokumentAusgang() throws Exception {
					
		URL xmlResource = getClass().getResource("BeispielMessage_GESAMTUEBERNAHME.xml");
		assertNotNull(xmlResource);
		
		StreamSource streamSoruce = dispatch.invoke(new StreamSource(xmlResource.openStream()));

	} 
	
}
