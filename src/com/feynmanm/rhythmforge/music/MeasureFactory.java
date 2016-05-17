package com.feynmanm.rhythmforge.music;

public class MeasureFactory {

	private static int lastMeasureID = 0;
	
	public Measure createMeasure( int numberOfBeats ) {
		Measure newMeasure = new Measure( getNextMeasureID(), numberOfBeats );
		return newMeasure;
	}
	
	public static int getNextMeasureID() {
		return lastMeasureID += 1;
	}
}
