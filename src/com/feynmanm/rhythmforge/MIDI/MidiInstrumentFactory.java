package com.feynmanm.rhythmforge.MIDI;

import com.feynmanm.rhythmforge.instruments.*;

public class MidiInstrumentFactory extends InstrumentFactory {
	
	// instruments
	public static final String DRUMKIT_NAME = "Drum Kit";
	public static final String FINGEREDBASS_NAME = "Fingered Bass";
	
	// sound components
	public static final String BASSDRUM_NAME = "Bass Drum";
	public static final String SNARE_NAME = "Snare";
	public static final String OPENHIHAT_NAME = "Open Hi-Hat";
	public static final String CLOSEDHIHAT_NAME = "Closed Hi-Hat";
	
	public Instrument createInstrument( String name ) {
		switch( name ) {
		case DRUMKIT_NAME: return new DrumKit( DRUMKIT_NAME );
		case FINGEREDBASS_NAME: return new Bass( FINGEREDBASS_NAME );
		default: return null;
		}
	}
	
	public DistinctSound createSound( String name ) {
		return new MidiSound( name );
	}

}
