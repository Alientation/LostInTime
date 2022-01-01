package com.alientation.game.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONLoader {
	/*
	 * Helper class to load a JSON
	 * TODO: Combine all helper classes and methods into one class called Utilities
	 * 
	 */
	public static JSONObject loadJson(String path) {
		try {
			Object obj = new JSONParser().parse(new FileReader(path));
			return (JSONObject) obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
