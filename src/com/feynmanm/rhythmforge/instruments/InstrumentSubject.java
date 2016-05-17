package com.feynmanm.rhythmforge.instruments;

public interface InstrumentSubject {
	
	public void registerInstrumentObserver( InstrumentObserver observer );
	public void notifyInstrumentObservers( Instrument instrument );
	public void notifyInstrumentObservers( DistinctSound sound, Instrument instrument );

}
