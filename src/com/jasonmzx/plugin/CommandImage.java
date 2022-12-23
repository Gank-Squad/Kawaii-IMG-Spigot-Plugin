package com.jasonmzx.plugin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandImage implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	//Make sure it's a player sending the command:
    	
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            //Assert Argument length:
            if(3 > args.length) {
            	sender.sendMessage("§4Error: Illegal formatting, try something like: ");
            	sender.sendMessage("§c/image <img_name> <sample_txt> <orientation>");
            	return false;
            }
            
            //Arguments from command:
            String fileName = args[0];
            String sampleName = args[1];
            String orientation = args[2];
            
            BufferedImage requestedImage = ImageHandler.loadImg(fileName);
            
            ArrayList<BlockSample> loadedSample = ImageHandler.sampleFileRead(sampleName);
           
            //Assert the possibility that the image or sample is null:
            if(requestedImage == null) {
            	player.sendMessage(
            	"§4Error, the file: §f "+ fileName +"§4 wasn't found in the plugin directory..."
            	);
            	return false;
            }
            
            if(loadedSample == null) {
            	player.sendMessage(
                "§4Error, the §6sample §4file: §f "+ sampleName +"§4 wasn't found in the plugin directory..."
                );
            	return false;
            }
            
            
            player.sendMessage("§a> `"+fileName+"` found! ");
            player.sendMessage("§a> `"+sampleName+"` found! ");
              
            //Generate the blocks into the player's selection
            
            int[] playerSelection = JsonHandle.getPlayerSelection(player.getName());
	        World world = player.getWorld();
	        
		      for (int y = 0; y < requestedImage.getHeight(); y++) {
			         for (int x = 0; x < requestedImage.getWidth(); x++) {
			        	 
			            //Retrieving contents of a pixel
			            int pixel = requestedImage.getRGB(x,y);
			            
			            //Creating a Color object from pixel value
			            Color color = new Color(pixel, true);
			            
			            //Retrieving the R G B values
			            int red = color.getRed();
			            int green = color.getGreen();
			            int blue = color.getBlue();
			            
					    Location loc1 = new Location(world,playerSelection[0]+x,playerSelection[1]+y,playerSelection[2]);
			            
			           
					    String bestBlock = Utility.matchBlock(loadedSample,red,green,blue);
					    loc1.getBlock().setType(Material.getMaterial(bestBlock));
					    
			         }
			      }
              
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
