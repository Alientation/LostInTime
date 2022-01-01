package com.alientation.game.util;

import java.sql.Timestamp;

public class Log {
	private String title;
	private String description;
	private Timestamp timestamp;
	
	public Log(String title, String description, Timestamp timestamp) {
		this.title = title;
		this.description = description;
		this.timestamp = timestamp;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
}