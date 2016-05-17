package com.feynmanm.rhythmforge.instruments;

import com.feynmanm.rhythmforge.MIDI.*;

public class Dispatcher {
	
	public static final int MIDI_OUTPUTTYPE = 1;
	public final static int XML_INPUTFILETYPE = 1;

	private int audioOutputType;
	private InstrumentFactory factory;
	
	// singleton Trucks
	private XMLTruck xmlTruck;
	
	public Dispatcher( int audioOutputType ) {
		this.audioOutputType = audioOutputType;
		createInstrumentFactory();
	}
	
	private void createInstrumentFactory() {
		if( audioOutputType == MIDI_OUTPUTTYPE ) {
			factory = new MidiInstrumentFactory();
		}
	}
	
	public DeliveryTruck createTruck( int fileType ) {		
		if ( fileType == Dispatcher.XML_INPUTFILETYPE ) {
			if ( xmlTruck == null && factory != null ) xmlTruck = new XMLTruck( audioOutputType, factory );
			return xmlTruck;
		} else return null;
	}

}
