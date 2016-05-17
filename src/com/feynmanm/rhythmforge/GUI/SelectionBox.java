package com.feynmanm.rhythmforge.GUI;

import javax.swing.JCheckBox;
import com.feynmanm.rhythmforge.music.*;
import com.feynmanm.rhythmforge.instruments.*;

@SuppressWarnings("serial")
public class SelectionBox extends JCheckBox {
	
	private DistinctSound sound;
	private Instrument instrument;
	private Measure measure;
	private int beatIndex;
	
	public SelectionBox ( DistinctSound sound, Instrument instrument, Measure measure, int beatIndex ) {
		this.sound = sound;
		this.instrument = instrument;
		this.measure = measure;
		this.beatIndex = beatIndex;
	}
	
	public DistinctSound getSound() {
		return sound;
	}
	
	public Measure getMeasure() {
		return measure;
	}
	
	public int getBeatIndex() {
		return beatIndex;
	}
	
	public Instrument getInstrument() {
		return instrument;
	}

}
