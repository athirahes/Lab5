package console.serverSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.ItemProduct;

public class ServerApplication {

	public ServerApplication() {

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
					
					
					// assign id to the product
					
					
				}
				
				
				// sends itemProduct List to client

				// Close all closable objects
				objectIS.close();
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
