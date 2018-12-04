package com.prog.nio.multi.threaded.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8182);
		while (true) {
			Socket socket = serverSocket.accept();
			handleRequestUsing(socket);
		}
	}

	private static void handleRequestUsing(Socket socket) throws IOException {
		System.out.println("Connected to: "+socket);
		new Thread(() -> {
			try (InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream()) {
				int data;
				while ((data = inputStream.read()) != -1) {
					outputStream.write(ifLetterInvertCaseOf(data));
				}
			} catch (IOException ioException) {
				throw new UncheckedIOException(ioException);
			} finally {
				System.out.println("Disconnected from: "+socket);
			}
		}).start();
	}

	private static int ifLetterInvertCaseOf(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}
}
