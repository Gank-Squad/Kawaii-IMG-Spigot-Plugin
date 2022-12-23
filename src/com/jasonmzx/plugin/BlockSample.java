package com.jasonmzx.plugin;

import java.util.stream.Stream;

public class BlockSample {
	
	  String blockId;
	  int red, green, blue;
	  double comparsionValue;
	  
	  public BlockSample(String stringInput) throws Exception {
		  String[] sections = stringInput.split("-", -1); //Should be 2 elements
		  
		  if(sections.length != 2) {
			 throw new Exception(); //TODO: Make custom extensions which extends Exception class
		  }
		  
		  blockId = sections[0];
		  
		  String[] sectionRGB = sections[1].split("_",-1);
		  
		  Integer[] iRE = Stream.of(sectionRGB).map(Integer::valueOf).toArray(Integer[]::new);
		  
		  red = iRE[0]; green = iRE[1]; blue = iRE[2];
		  
		  }
}
