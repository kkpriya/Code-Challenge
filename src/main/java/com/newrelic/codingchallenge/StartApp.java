package com.newrelic.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * Class starts/stops the servers, and receives connection from clients.
 * @author priya
 *
 */

public class StartApp {
	private ServerSocket serverSocket;
	private ExecutorService executorService = Executors.newFixedThreadPool(5); //Creates a thread-pool with a max of 5 threads to be run concurrently.
	private BlockingQueue<Integer> queue;

	/**
	 * BlockingQueue used to keep process thread safe.
	 */
	public StartApp(BlockingQueue<Integer> blockingQueue) {
		this.queue = blockingQueue;
	}

	/**
	 * Starts server connection, and calls the Queue class to start printing out the report.
	 */
	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			Queue queuer = new Queue(queue);
			new Thread(queuer).start();
			while (true)
				executorService.submit(new ClientHandler(serverSocket.accept(), queue));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			stop();
		}
	}

	/**
	 * Stops server connection
	 */
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This class handles client connection to the server and client's input. Checks if response is null, 9 digit number, or says "terminate."
	 * If input is a 9 digit number, converts it from a string to an integer and sends it the queue. 
	 */
	private static class ClientHandler implements Runnable {
		private Socket clientSocket;
		private BufferedReader input;
		private BlockingQueue<Integer> blockingQueue;
		private Pattern nineDigit = Pattern.compile("\\d{9}");
		private int inputInt;

		// Constructs a handler thread, stores away a socket and sets up the queue
		private ClientHandler(Socket socket, BlockingQueue<Integer> blockingQueue) {
			this.clientSocket = socket;
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			try {
				input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String inputLine;
				while (true) {
					inputLine = input.readLine();
					if (inputLine.equals("terminate"))
						System.exit(0);
					if (inputLine == null || !(nineDigit.matcher(inputLine).matches())) {
						break;
					}
					inputInt = Integer.parseInt(inputLine); 
					blockingQueue.put(inputInt);
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			} finally {
				try {
					input.close();
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
