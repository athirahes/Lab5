package console.serverSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.ItemProduct;

public class ServerApplication {

	public ServerApplication() {

	}

	public static void validateProductName(String pName) {
		
		String s = "VALID";
		for(int i=0; i<pName.length(); i++) {
			char c = pName.charAt(i);
			boolean alphanumeric = Character.isLetterOrDigit(c);
			boolean space = c == ' ';
			
			if (!alphanumeric) {
				if (!space) {
				s = "INVALID (name should contain only alphanumeric characters and spaces)";
				}
			}
			
		}
		System.out.println(s);

	}

	public static void main(String[] args) {

		try {
			// Port to receive and respond to request
			int portNo = 4228;
			ServerSocket serverSocket = new ServerSocket(portNo);

			System.out.println("Ready for request");

			// Server need to be alive forever thus the while(true)
			while (true) {
				// Accept client request for connection
				Socket socket = serverSocket.accept();

				// receive itemProduct List from client
				// Create input stream to read object
				ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());

				// Get object from stream and display details
				List<ItemProduct> itemProduct = (ArrayList<ItemProduct>) objectIS.readObject();
				System.out.println("\nTotal item requested by client: " + itemProduct.size());
				int i = 1;
				for (int index = 0; index < itemProduct.size(); index++) {
					
					ItemProduct currentItem = itemProduct.get(index);

					// display item requested from client
					System.out.print("Item #" + i++ + ": " + currentItem.getName());
					System.out.println(" (RM" + currentItem.getPrice() + ")");

					// validate item name
					System.out.print("\t Name Validation: ");
					validateProductName(currentItem.getName());
					
					// assign id to the product
					currentItem.setItemProductId((index + 5)*275);
					System.out.print("\t New ID Assigned: " + currentItem.getItemProductId() + "\n\n");
		
				}
				
				
				
											
				// sends itemProduct List to client
				ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream()); // Open stream to send object
				
				objectOS.writeObject(itemProduct); // sends data to client
				
				System.out.println("Ready for next request");
				
				
				// Close all closable objects
				objectIS.close();
				objectOS.close();
				
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
