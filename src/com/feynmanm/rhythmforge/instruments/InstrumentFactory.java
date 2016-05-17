package com.feynmanm.rhythmforge.instruments;


public abstract class InstrumentFactory {
	
	public abstract Instrument createInstrument( String name );
	public abstract DistinctSound createSound( String name );

}
