package com.jasonmzx.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class BreakHandle implements Listener {
    
	
	public void WriteToPlayer(String username, String parameter, int[] coords) {
		
	}
	
	@EventHandler
    public void OnBreak(BlockBreakEvent event) {
    	Player playerObject = event.getPlayer();
    	
    	ItemStack mainhand = playerObject.getInventory().getItemInMainHand();
    	
    	if(mainhand.getType() == Material.GOLDEN_AXE ) {
    		
    		Bukkit.broadcastMessage(playerObject.getName() );
    		
    		Block blockObject = event.getBlock();
    		
    		event.setCancelled(true);
    		
    		int[] newPositions = {
    				blockObject.getX(),
    				blockObject.getY(),
    				blockObject.getZ(),
    		};
    		
    		
    		//Set the first & second entry of JSON array to break:
    		
    		JsonHandle.setPlayerCoords(playerObject.getName(), newPositions, false);
    		
    	}
    	
    	 Bukkit.broadcastMessage("Broke");
    }
}
