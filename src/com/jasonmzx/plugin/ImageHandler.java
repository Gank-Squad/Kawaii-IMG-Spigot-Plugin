package com.jasonmzx.plugin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;




public abstract class ImageHandler {
	
	public static ArrayList<BlockSample> sampleFileRead(String sampleFilename){
		
		ArrayList<String> lines = new ArrayList();
		 try {
		      File myObj = new File(Utility.relDirectoryPath + sampleFilename);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        lines.add(data);
		      }
		      myReader.close();
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred whilst reading the sampleFilename file.");
		      e.printStackTrace();
		      return null;
		      
		    }
		 
		 ArrayList<BlockSample> ret = new ArrayList();
		 
		 for(String line : lines) {
			 try {
			 BlockSample sample = new BlockSample(line);
			 ret.add(sample);
			 } catch(Exception e) {
				 e.printStackTrace();
			 } 
		 }		
		return ret;
	}
	
	public static BufferedImage loadImg(String imageName) {
	     
		try {
	     File fileImg = new File(Utility.relDirectoryPath + imageName);
	     
   	  	BufferedImage img = ImageIO.read(fileImg);
   	  
	     return img;
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void loadImage(Player player, String imageName, String sampleName) throws Exception {
		      
			  //Reading the image
			  String username = player.getName();
	          World world 	  = player.getWorld();
		      
		      //Read given user's selection coords and generate image based on this:
		      
		      int[] playerSelection = JsonHandle.getPlayerSelection(username);
		      
		      //x y z
		      Location loc = new Location(world,playerSelection[0],playerSelection[1],playerSelection[2]);
		      
		     //
		     File fileImg = null; 
		      
		     fileImg = new File(Utility.relDirectoryPath + imageName);
		     
		     if(fileImg != null) {
		    	 
		    	  BufferedImage img = ImageIO.read(fileImg);
			      for (int y = 0; y < img.getHeight(); y++) {
			         for (int x = 0; x < img.getWidth(); x++) {
			        	 
			            //Retrieving contents of a pixel
			            int pixel = img.getRGB(x,y);
			            
			            //Creating a Color object from pixel value
			            Color color = new Color(pixel, true);
			            
			            //Retrieving the R G B values
			            int red = color.getRed();
			            int green = color.getGreen();
			            int blue = color.getBlue();
			            
					    Location loc1 = new Location(world,playerSelection[0]+x,playerSelection[1]+y,playerSelection[2]);
			            
			            //Now I got My RGB Values:
					    
					    ArrayList<BlockSample> loadedSample = sampleFileRead(Utility.relDirectoryPath + "sample.txt"); 
					      
					    String bestBlock = Utility.matchBlock(loadedSample,red,green,blue);
					    loc1.getBlock().setType(Material.getMaterial(bestBlock));
					    
			         }
			      }
		     } else {
		    	 player.sendMessage("File not found...");
		     }
		     
		      System.out.println("RGB values at each pixel are stored in the specified file");
		   }
}
