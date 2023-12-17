package com.pubsub;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Subscriber {
	
	private final String subscribedTime;
	private int number;
	
	public Subscriber(int number) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String subscribedTime = sdf.format(new Date()); 
		this.subscribedTime= subscribedTime;
		this.number=number;
		ContentServer.getInstance().registerSubscriber(this);
		
		
	}
	
	public void receivedMessage(Message message) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String subTime = sdf.format(new Date()); 
		
		System.out.println("Subscriber "+this.number+" subscribed at: "+subTime);
		System.out.println("Recieved Message: "+ message);
		
		
		
	}

}
