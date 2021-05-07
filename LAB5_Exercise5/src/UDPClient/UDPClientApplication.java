package UDPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientApplication {

	public static void main(String[] args) {

		System.out.println("UDPClientApplication: Demonstration of Client-Side Application.");

		try {

			// Create the socket object to transmit the data.
			DatagramSocket clientSocket = new DatagramSocket();

			// Get IP address & portNo
			InetAddress ipAddress = InetAddress.getLocalHost();
			int portNo = 5678;

			// Buffer data to be sent
			int bufferSize = 1024;
			byte sendData[] = new byte[bufferSize];

			// Get input from user and store in byte[]
			System.out.print("Enter text to send the server:  ");
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			String text = userInput.readLine();
			sendData = text.getBytes();

			// Wrap data in datagram packet
			DatagramPacket outPacket = new DatagramPacket(sendData, sendData.length, ipAddress, portNo);

			// Send Datagram packet
			clientSocket.send(outPacket);
			System.out.println("\nSending '" + text + "' out on network.\n");

			
			
			
			// Receive data from network
			byte receiveData[] = new byte[bufferSize];

			// Wrap data in Datagram packet
			DatagramPacket inPacket = new DatagramPacket(receiveData, receiveData.length);

			// Receive network's Datagram packet
			clientSocket.receive(inPacket);

			String wordCount = new String(inPacket.getData());
			System.out.println("Word Count:" + wordCount);

			System.out.println("UDPClientApplication: end of program.");
			clientSocket.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
