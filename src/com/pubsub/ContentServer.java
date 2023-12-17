package com.pubsub;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;

public class ContentServer {
	
	private ArrayList<Subscriber> subcribersList;
	private ExecutorService threadPool;
	
	public static ContentServer serverInstance;
	
	public static ContentServer getInstance() {
		if(serverInstance==null) {
			serverInstance = new ContentServer();
		}
		return serverInstance;
	}
	
	private ContentServer() {
		this.subcribersList = new ArrayList<>();
		this.threadPool = Executors.newCachedThreadPool();
	}
	
	public void sendMessage(Message m) {
		System.out.println("aaa");
		for(Subscriber s: subcribersList) {
			threadPool.execute(()->s.receivedMessage(m));
			
			
		}
		System.out.println("bbb");
	}
	public void registerSubscriber(Subscriber s) {
		subcribersList.add(s);
	}
	
	public void shutdownThreadPool() {
		threadPool.shutdown();
	}

	
	

}
