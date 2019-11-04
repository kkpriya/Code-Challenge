package com.newrelic.codingchallengetest;

import org.junit.Test;

/**
 * Testing to see if client can connect to the server and pass an input.
 * 
 * @author priya
 *
 */

public class MainTest {

	private static final Integer PORT = 4000;
	private static final String IP = "127.0.0.1";

    @Test
    public void clientOne() {
        Client client = new Client();
        client.startConnection(IP, PORT);
        client.sendNumbers();
        client.stopConnection();
    }

    @Test
    public void clientTwo() {
        Client client = new Client();
        client.startConnection(IP, PORT);
        client.sendNumbers();
        client.stopConnection();
    }
}
