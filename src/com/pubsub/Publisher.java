package com.pubsub;

public class Publisher {
	
	public void publish(Message message) {
		ContentServer.getInstance().sendMessage(message);
	}
	
	

}
