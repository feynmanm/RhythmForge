package com.feynmanm.rhythmforge.music;

import java.util.*;
import java.io.*;
import com.feynmanm.rhythmforge.*;

public class Measure implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int measureID;
	private int beatCount;
	private ArrayList<SoundEvent> soundEventList;
	
	public Measure( int measureID, int beatCount ) {
		this.measureID = measureID;
		this.beatCount = beatCount;
		soundEventList = new ArrayList<SoundEvent>();
	}
	
	public Iterator<SoundEvent> getIterator() {
		return soundEventList.iterator();
	}
	
	public int getMeasureID() {
		return measureID;
	}
	
	public void setMeasureID( int measureID ) {
		this.measureID = measureID;
	}
	
	public int getBeats() {
		return beatCount;
	}
	
	public void addSoundEvent( SoundEvent event ) {
		soundEventList.add( event );
	}
	
	public ArrayList<SoundEvent> getSoundEventList() {
		return soundEventList;
	}
	
	public void clearSoundEvents() {
		for( SoundEvent event: soundEventList ) this.deleteSoundEvent( event );
	}
	
	public boolean deleteSoundEvent( SoundEvent event ) {
		return soundEventList.remove( event );
	}

}
