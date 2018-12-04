package com.prog.nio.single.threaded.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Example of blocking Single Threaded Server
public class SingleThreadedServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8181);
		while (true) {
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			int data;
			while ((data = inputStream.read()) != -1) {
				outputStream.write(changeCasesIfLetter(data));
			}
			outputStream.close();
			inputStream.close();
			socket.close();
		}
	}

	private static int changeCasesIfLetter(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}
}
