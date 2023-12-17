package com.pubsub;

public class Test {

	public static void main(String[] args) {
		Message message = new Message("Hello subscribers!");
		Publisher publisher = new Publisher();
		
		ContentServer server =  ContentServer.getInstance();
		Subscriber sub1=new Subscriber(1);
		Subscriber sub2=new Subscriber(2);
		Subscriber sub3=new Subscriber(3);
		Subscriber sub4=new Subscriber(4);
		
		publisher.publish(message);
		
		server.registerSubscriber(sub1);
		server.registerSubscriber(sub2);
		server.registerSubscriber(sub3);
		server.registerSubscriber(sub4);
		
		
		
		server.shutdownThreadPool();

	}

}
