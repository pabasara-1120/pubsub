package com.pubsub;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

public class ContentServer {
	
	private ArrayList<Subscriber> subscribersList;
	private ExecutorService threadPool;
	private final Lock subscribersLock;
	
	public static ContentServer serverInstance;
	
	public static ContentServer getInstance() {
		if(serverInstance==null) {
			serverInstance = new ContentServer();
		}
		return serverInstance;
	}
	
	private ContentServer() {
		this.subscribersList = new ArrayList<>();
		this.threadPool = Executors.newCachedThreadPool();
		this.subscribersLock=new ReentrantLock();
	}
	
	public void sendMessage(Message message) {
        subscribersLock.lock();
        try {
            for (Subscriber subscriber : subscribersList) {
            	
                threadPool.execute(() -> subscriber.receivedMessage(message));
            }
        } finally {
            subscribersLock.unlock();
        }
    }
	
	
	public void registerSubscriber(Subscriber s) {
		subscribersLock.lock();
        try {
            subscribersList.add(s);
        } finally {
            subscribersLock.unlock();
        }
	}
	
	public void shutdownThreadPool() {
		threadPool.shutdown();
	}

	
	

}
