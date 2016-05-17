package com.feynmanm.rhythmforge.instruments;

import java.util.*;

@SuppressWarnings("serial")
public class Store extends HashSet<Instrument> implements InstrumentSubject {
	
	private HashSet<InstrumentObserver> observerSet;

	public Store() {
		observerSet = new HashSet<InstrumentObserver>();
	}

	public void addInstrument( Instrument instrument ) {
		if( this.add( instrument ) ) notifyInstrumentObservers( instrument );
	}
	
	public void addSound( DistinctSound sound, Instrument instrument ){
		if( instrument.add( sound ) ) notifyInstrumentObservers( sound, instrument );
	}
	
	public void notifyInstrumentObservers( Instrument instrument ) {
		for( InstrumentObserver observer: observerSet ) {
			observer.onInstrumentAdded( instrument );
		}
	}
	
	public void notifyInstrumentObservers( DistinctSound sound, Instrument instrument ) {
		for( InstrumentObserver observer: observerSet ) {
			observer.onSoundAdded( sound, instrument );
		}
	}
	
	public void registerInstrumentObserver( InstrumentObserver observer ) {
		observerSet.add( observer );
	}

}
