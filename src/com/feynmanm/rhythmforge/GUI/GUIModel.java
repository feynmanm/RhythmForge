package com.feynmanm.rhythmforge.GUI;

import java.util.*;
import com.feynmanm.rhythmforge.instruments.*;
import com.feynmanm.rhythmforge.music.*;
import com.feynmanm.rhythmforge.*;

public class GUIModel implements VisibleInstSubject {
	
	private ArrayList<VisibleInstrument> instrumentList;
	private ArrayList<Measure> measureList;
	private HashSet<VisibleInstObserver> observerSet;
	
	public GUIModel()  {
		instrumentList = new ArrayList<VisibleInstrument>();
		measureList = new ArrayList<Measure>();
		observerSet = new HashSet<VisibleInstObserver>();
	}
	
	public ArrayList<VisibleInstrument> getInstrumentList() {
		return instrumentList;
	}
	
	public int getInstrumentIndex( Instrument instrument ) {
		return instrumentList.indexOf( getVisibleInstrument( instrument ) );
	}
	
	public void addInstrument( Instrument instrument ) {
		VisibleInstrument visibleInstrument = new VisibleInstrument( instrument ); 
		instrumentList.add( visibleInstrument );
		int index = instrumentList.indexOf( visibleInstrument );
		notifyVisibleInstObservers( instrument, index );
	}
	
	public void removeInstrument( Instrument instrument ) {

	}
	
	public void addSound( DistinctSound sound, Instrument instrument ) {
		for( VisibleInstrument visibleInstrument: instrumentList ) {
			if ( visibleInstrument.isInstrument( instrument ) ) {
				visibleInstrument.addSound( sound );
				int instrumentIndex = instrumentList.indexOf( visibleInstrument );
				int soundIndex = visibleInstrument.getSoundIndex( sound );
				notifyVisibleInstObservers( sound, instrumentIndex, soundIndex );
			}
		}
	}
	
	public int getSoundIndex( SoundEvent event ) {
		VisibleInstrument visibleInstrument = getVisibleInstrument( event.getInstrument() );
		return visibleInstrument.getSoundIndex( event.getSound() );
	}
	
	private VisibleInstrument getVisibleInstrument( Instrument instrument ) {
		for( VisibleInstrument visibleInstrument: instrumentList ) {
			if ( visibleInstrument.getInstrument().equals( instrument ) ) return visibleInstrument;
		}
		return null;
	}
	
	public void removeSound( DistinctSound sound, Instrument instrument  ) {
		
	}
	
	public void addMeasure( Measure measure ) {
		measureList.add( measure );
	}
	
	public void deleteMeasure( Measure measure ) {
		measureList.remove( measure );
	}
	
	public int getMeasureIndex( Measure measure ) {
		return measureList.indexOf( measure );
	}

	public void notifyVisibleInstObservers( Instrument instrument, int index ) {
		for( VisibleInstObserver observer: observerSet ) {
			observer.onInstrumentAdded( instrument, index );
		}
	}
	
	public void notifyVisibleInstObservers( DistinctSound sound, int instrumentIndex, int soundIndex ) {
		for( VisibleInstObserver observer: observerSet ) {
			observer.onSoundAdded( sound, instrumentIndex, soundIndex );
		}
	}

	public void registerVisibleInstObserver( VisibleInstObserver observer ) {
		observerSet.add( observer );
	}
	
}
