package console.clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
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
		item1.setName("Spritzer 5000ml Water Bottle ");
		item1.setPrice(19.45f);

		ItemProduct item2 = new ItemProduct();
		item2.setName("Cactus Water Bottle");
		item2.setPrice(32.89f);

		ItemProduct item3 = new ItemProduct();
		item3.setName("Dasani Water Bottle :3");
		item3.setPrice(10.13f);

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
			objectOS.writeObject(itemProduct);
			objectOS.flush();
			
			
			
			
			//receive itemProduct id from server
			
			//Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream()); 
			
			// Get object from stream, cast and display details
			itemProduct = (ArrayList<ItemProduct>) objectIS.readObject();
			
			// Display data
			for(ItemProduct itemproduct: itemProduct) {
				
				System.out.println ("\nName  :  " + itemproduct.getName());
				System.out.println ("Price : RM" + itemproduct.getPrice());
				System.out.println ("Assigned ID: " + itemproduct.getItemProductId());
				
			}	
			
			
			// Close all closable objects
			socket.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
