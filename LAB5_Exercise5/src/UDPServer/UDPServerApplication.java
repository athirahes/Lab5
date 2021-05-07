package UDPServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerApplication {

	public static void main(String[] args) {

		System.out.println("UDPServerApplication: Demonstration of UDP Server-Side Application.");

		try {

			// Declaration of port no to received datagram packet
			int portNo = 5678;

			// Create a socket to listen at port 5678
			DatagramSocket serverDatagramSocket = new DatagramSocket(portNo);
			int byteSize = 1024;

			while (true) {

				// Variable to received data from port 5678
				byte[] receivedData = new byte[byteSize];

				// Datagram to hold received packet
				DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);

				// Received packet datagram
				serverDatagramSocket.receive(receivedPacket);

				// Retrieve the sent text from packet
				String retreiveMessage = new String(receivedPacket.getData());
				System.out.println("\nMessage received: " + retreiveMessage + ".\n");
				
				// Retrieve client's IP Address & port
				int clientPort = receivedPacket.getPort();
				InetAddress clientIPAddress = receivedPacket.getAddress();

				// Count words from text received
				String[] wordArray = retreiveMessage.trim().split("\\s+");
				String wordCount = " " + wordArray.length;
				System.out.println("Message returned: "+wordCount);

				// Clear the buffer after every message.
				receivedData = new byte[byteSize];

				
				
				
				// Send data back to client
				byte[] sendData = new byte[byteSize];
				sendData = wordCount.getBytes();

				// Create packet to send the data back to the client
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort); 
				
				// Send the created packet to client
				serverDatagramSocket.send(sendPacket); 

				// Close the socket connection
				serverDatagramSocket.close();
			}

		} catch (IOException e) {

			e.printStackTrace();
			
		}

		System.out.println("UDPServerApplication: end of program.");

	}

}
