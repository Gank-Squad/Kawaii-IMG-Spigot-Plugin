package com.jasonmzx.plugin;

import java.util.ArrayList;

public abstract class Utility {
	
	final static String relDirectoryPath = "plugins/Kawaii_IMG_Folder/"; 
	
	public static String matchBlock(ArrayList<BlockSample> samples, int red, int green, int blue) {
		
		ArrayList<BlockSample> sortedSamples = new ArrayList();
		
		for(int i = 0; i < samples.size(); i++) {
			
			BlockSample sample = samples.get(i);
			
			//Get all color differences:
			int redDiff   = Math.abs(sample.red - red);
			int greenDiff = Math.abs(sample.green - green);
			int blueDiff  = Math.abs(sample.blue - blue);
			
			double mean = (redDiff+greenDiff+blueDiff)/3;
			sample.comparsionValue = mean;
			
			//First element being appended doesn't need to be checked...
			if(sortedSamples.size() == 0) {
				
				sortedSamples.add(sample);
				
			} else if(sortedSamples.size() == 1) { //if 1
				
				if(sample.comparsionValue > sortedSamples.get(0).comparsionValue ) {
					sortedSamples.add(sample);
				} else {
					sortedSamples.add(0, sample);
				}
			}
			
			else {
				
			
			for(int j = sortedSamples.size() - 1; j >= 0; j--) {
				
				if( (sortedSamples.size() -1) == j) { //If last element
					
					if(sample.comparsionValue > sortedSamples.get(j).comparsionValue) {
						sortedSamples.add(sample);
						continue;
					}
				}
				
				if( j == 0) { //If first element
					
					if(sample.comparsionValue < sortedSamples.get(0).comparsionValue) {
						sortedSamples.add(0, sample);
						continue;
					}
				}
				
				if(sortedSamples.get(j).comparsionValue < sample.comparsionValue && 
				   sortedSamples.get(j+1).comparsionValue > sample.comparsionValue) {
					
					sortedSamples.add(j+1, sample);
				}
					
				
				
			} //end of nested for loop	
		} // end of else
	} //end of main for loop
		
	return sortedSamples.get(0).blockId;	
	
	} //END OF matchBlock
	
	
} // END OF Utility Class
