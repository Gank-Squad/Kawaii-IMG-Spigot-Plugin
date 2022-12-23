package com.jasonmzx.plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

public class WallGeneratorPlugin extends JavaPlugin {
    
	
	public void generateJSON() {
		
		JSONObject obj = new JSONObject();

		String JSONpath = "plugins/wallGenData.json";
		
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
		
		generateJSON();
		
		
		getServer().getPluginManager().registerEvents(new JoinHandle(), this);
		getServer().getPluginManager().registerEvents(new BreakHandle(), this);
		this.getCommand("image").setExecutor(new CommandImage());
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
	
}
