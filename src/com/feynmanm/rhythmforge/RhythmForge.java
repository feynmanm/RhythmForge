package com.feynmanm.rhythmforge;

public class RhythmForge {
	
	public static RhythmForgeController mainController;

	public static void main(String[] args) {
		if ( args[0] == null ) {
	        System.out.println("Proper Usage is: instrument source filename");
	        System.exit(0);
		} else mainController = new RhythmForgeController( args[0] );
	}
}