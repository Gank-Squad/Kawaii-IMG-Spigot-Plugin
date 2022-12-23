package com.jasonmzx.plugin;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandImage implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            String playerName = player.getName();
            
            Bukkit.broadcastMessage( "Executed Image command" );
    	
            World playerInWorld = player.getWorld();
            
            ImageHandler ImgClass = new ImageHandler();
            try {
            ImgClass.loadImage(playerName, playerInWorld);	
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
