package model;

import java.io.Serializable;

public class ItemProduct implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int itemProductId;
	private String name;
	private float price;

	public void setItemProductId(int temProductId) {
		this.itemProductId = temProductId;
	}

	public int getItemProductId() {
		return itemProductId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

}
