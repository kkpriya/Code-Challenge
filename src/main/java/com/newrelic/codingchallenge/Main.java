package com.newrelic.codingchallenge;

import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) {

		System.out.println("Starting up server ....");

		//creating an unbounded queue to handle concurrent server and client connections.
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		StartApp server = new StartApp(queue);
		server.start(4000); 
	}
}