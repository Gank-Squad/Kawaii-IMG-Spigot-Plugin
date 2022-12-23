package com.jasonmzx.plugin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class PlayerJSONobject {
	String username; 
	int[] positionArray;
	
	//Constructor
	PlayerJSONobject(String a, int[] b){
		username = a; positionArray = b;
	}	
}

public class JsonHandle {
		
	//#########################################
	//Wrapper for reading Local JSON files
	//#########################################
	
	private static JSONObject readJSONFile(String localPath) {
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObjectReturn = new JSONObject();
		
		//Try to read JSON file, expect an IO Error 
		try {
			Reader reader = new FileReader(localPath);
			
			//Try to parse Reader, Expect Possibility of bad parse
			try {
				Object jsonObj = parser.parse(reader);
				jsonObjectReturn = (JSONObject) jsonObj;
				
			} catch (ParseException parseError) {
				parseError.printStackTrace();
			}
			
		} catch (IOException IoError) {
			IoError.printStackTrace();
		}
		
		return jsonObjectReturn; 
	}
	
	//#########################################
	//Wrapper for writing then closing Local JSON files
	//#########################################
	
	private static boolean writeAndCloseJSONFile(String localPath, JSONObject newJson) {
		
		boolean status = false;
		
		try {

			FileWriter file = new FileWriter(localPath);
				file.write(newJson.toJSONString());
				file.flush();
				file.close();
				status = true;
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return status;
		
	}
	
	//#########################################
	// Custom Array Parser from string to Array of Integers
	//#########################################
	
	public static int[] customArrayParse(String arrayString) {
		
		String[] uPositions = arrayString.split(",", -1);
		
		int[] empty = {0,0,0,0,0,0};
		
		for(int i = 0; i < uPositions.length; i++) {
			
			String pos;
			
			if(i == 0) {
				pos = uPositions[i].substring(1);
			} else if(i == 5) {
				pos = uPositions[i].substring(0, uPositions[i].length()-1);
			} else {
				pos = uPositions[i];
			}
			
			Bukkit.broadcastMessage(pos);

			int posInt = Integer.parseInt(pos);
			empty[i] = posInt;					
			
		}
		
		return empty;
	}
	
	//Actual Handling Methods
	
	public static void createUser(String username) {
		
		Bukkit.broadcastMessage("[WGP] creating user");
		
		//Reads the filePath and stores the json parsed via JSONObject class  
		JSONObject playerCoordData = readJSONFile("plugins/wallGenData.json");
				
		//Initializing Players [ x1 , y1 , z1 , x2 , y2 , z2 ] positions as 0 (default)
		JSONArray positions = new JSONArray();
				
				for(int x = 0; x < 6; x++) {
					positions.add(0);	
				}
				
		playerCoordData.put(username, positions);
				
		boolean Write = writeAndCloseJSONFile("plugins/wallGenData.json", playerCoordData);
		
	}
	
	public static void setPlayerCoords(String username, int[] newPositions, boolean back) {
		
		Bukkit.broadcastMessage("[WGP] Setting Coords");
		
		JSONParser parser = new JSONParser();

		JSONObject jsonObject = readJSONFile("plugins/wallGenData.json");	
				
				
			Object userObject = jsonObject.get(username); 
			
			//Class userObjectClass = userObject.getClass();
			String uPs = userObject.toString();
				
			JSONArray jsonPositions = new JSONArray();
				
			int[] empty = customArrayParse(uPs);
				
				
			//TODO: make this a for loop or something, looks so ugly
			if(!back) {
					empty[0] = newPositions[0];
					empty[1] = newPositions[1];
					empty[2] = newPositions[2];
			} else { 
					empty[3] = newPositions[0];
					empty[4] = newPositions[1];
					empty[5] = newPositions[2];
			}
				
				
			for(int x = 0; x < 6; x++) {
				jsonPositions.add(empty[x]);	
			}
				
			jsonObject.put(username, jsonPositions);
				
			//Now write
			boolean Write = writeAndCloseJSONFile("plugins/wallGenData.json", jsonObject);
		
	}
	
	public static int[] getPlayerSelection(String username) {
		JSONParser parser = new JSONParser();
		
		int[] empty = {};
		
		//Reading:					

		JSONObject jsonObject = readJSONFile("plugins/wallGenData.json");	
							
		Object userObject = jsonObject.get(username); 
		//Class userObjectClass = userObject.getClass();
		String uPs = userObject.toString();
									
		empty = customArrayParse(uPs);
			
		return empty;			
		
	}
}
