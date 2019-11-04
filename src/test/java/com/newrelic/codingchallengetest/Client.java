package com.newrelic.codingchallengetest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * This class starts the client connections, sends random 9 digits numbers to the server, and close the client connection.
 * 
 * @author priya
 *
 */

public class Client {
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * Start Client connection
     */
    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error while initializing connection");
        }
    }

    /**
     * Sending 100 random 9 digit numbers to the server.
     */
    public void sendNumbers() {
    	Random random = new Random();
        //for (int i = 0; i < 100; i++) {
        //    out.println(String.format("%09d", random.nextInt(1000000000)));
        //}
    	out.println("001232312");
    	out.println("001232312");
    	out.println("551232312");
    }
    
    /**
     * Sending "terminate" input to the server.
     */
    public void terminate() {
        out.println("terminate");
    }

    /*
     * Stop Client connection.
     */
    public void stopConnection() {
        try {
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error while closing");
        }
    }
}