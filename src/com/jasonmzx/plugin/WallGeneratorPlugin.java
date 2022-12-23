package com.jasonmzx.plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  



public class WallGeneratorPlugin extends JavaPlugin {
    
	
	public void initialize() {
		
		JSONObject obj = new JSONObject();

		String JSONpath = Utility.relDirectoryPath +"wallGenData.json";
		
		File f = new File(JSONpath);
		
		if(f.exists()) {
			Bukkit.getLogger().severe("[WGP] JSON File already exists! ");
		} 
		else {
			try {

				FileWriter file = new FileWriter(JSONpath);
				file.write(obj.toJSONString());
				file.flush();
				file.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}
	
	//Fired when plugin is enabled
	@Override
    public void onEnable() {
		
		initialize(); //This JSON file is the cursor reference for every player
		
		getServer().getPluginManager().registerEvents(new JoinHandle(), this);
		getServer().getPluginManager().registerEvents(new BreakHandle(), this);
		this.getCommand("image").setExecutor(new CommandImage());
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
	
}
