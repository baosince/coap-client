/**
 * 
 */
package com.clc.coap.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.clc.coap.client.coap.CoapClientImpl;

/**
 * @author pnichols
 *
 */
public class MainClass {
  private static final Logger LOG=Logger.getLogger(MainClass.class.getName());
  private final Scanner CMD=new Scanner(System.in);
  private static MainClass mainClass;
  private boolean isStarted;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  if(mainClass==null) {
		  mainClass=new MainClass();
	      mainClass.isStarted=true;
	  }
	  while(mainClass.isStarted) {
		try {
			Thread.sleep(5000);
		      mainClass.showMenu();
		}
		catch(InterruptedException ie) {
			LOG.error("Error in sleep thread.");
		}
	  }	
	}
	
	
	
	private void showMenu() {
		StringBuilder sb= new StringBuilder();
		sb.append("\n\t=============================================================");
		sb.append("\n\t=          COTIVITY SAMPLE COAP CLIENT                      =");
		sb.append("\n\t=          ---------------------------                      =");
		sb.append("\n\t= 1. Send Data Segment to Cotivity Coap Server port 5683    =");
		sb.append("\n\t= 2. Send Data Segment to Cotivity Coap Server port 5684    =");
		sb.append("\n\t= 3. Send Data Segment to Cotivity Coap Server port 5685    =");
		sb.append("\n\t= 4. Quit                                                   =");
		sb.append("\n\t=                                                           =");
		sb.append("\n\t=============================================================");
		sb.append("\n\tSelect an option above to execute:");
		System.out.println(sb.toString());
		String selected=CMD.nextLine();
           nextAction(selected);		
		
		
	}
	
	private void nextAction(String selected) {
	  int cmd=0;
	  boolean cont=true;
	    try{
	    	cmd=Integer.parseInt(selected);	
	    }
	    catch(NumberFormatException nfe) {
	    	 cont=false;
	    	System.err.println("You must type in a valid menu option (1-4)");
	    }
	    if (cont) {
	       executeCommand(cmd);
	    }
	    else {
	       System.err.println("You must type in a test message.");
	    	 showMenu();
	    }
	    
	    
	}
	
	private void executeCommand(int cmd) {
		switch(cmd) {
		case 1:
			sendMessage(5683);
			break;
			
		case 2:
			sendMessage(5684);
		    break;
		case 3:
			sendMessage(5685);
		    break;
		    
		case 4:
			System.exit(0);
			break;
		}
		
   }
   private void sendMessage(int port) {
	   System.out.println("\tType  in a simple message to send to the Coap Server");
	   String message=CMD.nextLine();
	    if( (message!=null) &&(message.length()>5)) {
	    	//We will send message
	    	CoapClientImpl coapClient= new CoapClientImpl();
	    	 boolean mesgSent= coapClient.sendMessage(port, message);
	    	  if(mesgSent) {
	    		  System.out.println("Message was sent successfully");
	    		  
	    	  }
	    	  else {
	    		  System.out.println("Error in sending message. Check log files.");
	    	  }
	    		 
	    }
	    else {
	    	System.out.println("You have to type in a message to send.");
	    	
	    }
   }
	

}
