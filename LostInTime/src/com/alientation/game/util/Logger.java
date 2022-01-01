package com.alientation.game.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Logger {
	private ArrayList<Log> logs;
	
	
	public Logger() {
		logs = new ArrayList<Log>();
	}
	
	public void addLog (String title, String description) {
		logs.add(new Log(title, description, new Timestamp(new Date().getTime())));
	}
	
	public void printAll() {
		for (int x = 0; x < logs.size(); x++) {
			System.out.println(logs.get(x).getTimestamp() + " >>> " + logs.get(x).getTitle() + "\n\t" + logs.get(x).getDescription());
		}
	}
}