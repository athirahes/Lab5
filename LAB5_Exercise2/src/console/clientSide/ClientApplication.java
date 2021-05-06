package console.clientSide;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.ItemProduct;

public class ClientApplication {

	public ClientApplication() {

	}

	public static void main(String[] args) {
		/*
		 * Receive information from server 
		 * Display all information in console
		 */

		System.out.println("\nClientSideApplication: Start Demo of application.\n");

		ItemProduct item1 = new ItemProduct();
		item1.setName("Spritzer Water Bottle");
		item1.setPrice(43.45f);

		ItemProduct item2 = new ItemProduct();
		item2.setName("Cactus Water Bottle");
		item2.setPrice(76.89f);

		ItemProduct item3 = new ItemProduct();
		item3.setName("Dasani Water Bottle");
		item3.setPrice(33.13f);

		List<ItemProduct> itemProduct = new ArrayList<ItemProduct>();
		itemProduct.add(item1);
		itemProduct.add(item2);
		itemProduct.add(item3);
		
		
		try {
			// sends itemProduct List to server
			// Data to establish connection to server; portNo & serverAddress
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());

			// Send request to server
			System.out.println("Send object to server: " + itemProduct);
			objectOS.writeObject(itemProduct);
			objectOS.flush();
			
			//receive itemProduct id from server

			// Close all closable objects
			objectOS.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
