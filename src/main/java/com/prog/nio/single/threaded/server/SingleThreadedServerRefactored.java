package com.prog.nio.single.threaded.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Example of blocking Single Threaded Server
public class SingleThreadedServerRefactored {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8181);
		while (true) {
			try (Socket socket = serverSocket.accept();
					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream()) {
				System.out.println("Connected to socket at port:" + socket.getPort());
				int data;
				while ((data = inputStream.read()) != -1) {
					outputStream.write(invertCasesIfLetter(data));
				}
			} finally {
				System.out.println("Disconnected from server socket at port:" + serverSocket.getLocalPort());
			}
		}
	}

	private static int invertCasesIfLetter(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}
}
