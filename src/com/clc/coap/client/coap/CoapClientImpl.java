/**
 * 
 */
package com.clc.coap.client.coap;

import org.apache.log4j.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

/**
 * @author pnichols
 *
 */
public class CoapClientImpl {
  private static final Logger LOG=Logger.getLogger(CoapClientImpl.class.getName());
  private final String HOST_IP="coap://127.0.0.1:";
  private final String END_POINT="/Coap-Sample-Res";	
	public boolean sendMessage(int port, String message) {
		boolean result=true; //assume true
		StringBuilder sb= new StringBuilder();
		sb.append(HOST_IP);
		sb.append(port);
		sb.append(END_POINT);
		LOG.info("ENDPOINT "+sb.toString());
		 CoapClient client = new CoapClient(sb.toString());
	     CoapResponse response = client.post(message,MediaTypeRegistry.TEXT_PLAIN);             
	        System.out.println(response.isSuccess());                   
	        if (response!=null) {
	            byte[] b=response.getPayload();
	            String serverResp = new String(b);
	            System.out.println(serverResp);
	       }
	    return result;    
	}
}
