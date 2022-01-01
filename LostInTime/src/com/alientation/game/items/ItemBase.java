package com.alientation.game.items;

import java.awt.image.BufferedImage;
import java.util.Date;

public class ItemBase {
	private String name;
	private ItemID itemID;
	private int quantity;
	private Date dateAcquired;
	private boolean stackable;
	public ItemBase(String name, ItemID itemID, int quantity, Date dateAcquired, boolean stackable) {
		this.name = name;
		this.itemID = itemID;
		this.quantity = quantity;
		this.dateAcquired = dateAcquired;
		this.stackable = stackable;
	}
	
	
	
	
}
