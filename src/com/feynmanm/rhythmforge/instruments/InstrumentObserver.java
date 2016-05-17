package com.feynmanm.rhythmforge.instruments;

public interface InstrumentObserver {
	
	public void onInstrumentAdded( Instrument instrument );
	public void onInstrumentRemoved( Instrument instrument );
	
	public void onSoundAdded( DistinctSound sound, Instrument instrument );
	public void onSoundRemoved( DistinctSound sound, Instrument instrument );

}
