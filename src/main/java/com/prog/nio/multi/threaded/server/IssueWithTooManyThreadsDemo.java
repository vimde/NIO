package com.prog.nio.multi.threaded.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Here we are going to create too many sockets.
 * The system will eventually run out of resources if the number of threads created is too large.
 */
public class IssueWithTooManyThreadsDemo {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket[] socket = new Socket[2000];
		for(int i = 0; i < socket.length; i++) {
			socket[i] = new Socket("localhost", 8182);
		}
		Thread.sleep(10_000);
	}
}
