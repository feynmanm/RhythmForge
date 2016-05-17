package com.feynmanm.rhythmforge;

import java.io.*;
import com.feynmanm.rhythmforge.instruments.*;
import com.feynmanm.rhythmforge.music.*;

public class SoundEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int SOUND_ADDED = 1;
	public static final int SOUND_REMOVED = 2;

	private DistinctSound sound;
	private Instrument instrument;
	private int beatInMeasure;
	private Measure measure;
	private int actionTaken;
	
	public SoundEvent( DistinctSound sound, Instrument instrument, Measure measure, int beatInMeasure, int actionTaken ) {
		this.sound = sound;
		this.instrument = instrument;
		this.beatInMeasure = beatInMeasure;
		this.measure = measure;
		this.actionTaken = actionTaken;
	}
	
	public DistinctSound getSound() {
		return sound;
	}
	
	public Instrument getInstrument() {
		return instrument;
	}
	
	public int getBeatInMeasure() {
		return beatInMeasure;
	}
	
	public Measure getMeasure() {
		return measure;
	}

	public int getActionTaken() {
		return actionTaken;
	}
	
	public void setActionTaken( int action ) {
		actionTaken = action;
	}
	
	public void setMeasure( Measure measure ) {
		this.measure = measure;
	}
	
	public String getInstrumentData() {
		return instrument.getData();
	}
	
}
