package com.feynmanm.rhythmforge.music;

import java.util.*;
import com.feynmanm.rhythmforge.SoundEvent;
import com.feynmanm.rhythmforge.SoundEventObserver;

public class Song implements SongSubject {

	public final static int STANDARD_BEATS_PERMEASURE = 4;
	public final static int DEFAULT_TEMPO = 100;

	public MeasureFactory factory;
	public ArrayList<Measure> measureList;
	public ArrayList<MeasureObserver> measureObserverList;
	public ArrayList<SoundEventObserver> soundEventObserverList;

	public Song() {
		factory = new MeasureFactory();
		measureList = new ArrayList<Measure>();
		measureObserverList = new ArrayList<MeasureObserver>();
		soundEventObserverList = new ArrayList<SoundEventObserver>();
	}

	public ArrayList<Measure> getMeasureList() {
		return measureList;
	}
	
	public void addMeasure() {
		Measure newMeasure = factory.createMeasure( STANDARD_BEATS_PERMEASURE );
		measureList.add( newMeasure );
		notifyMeasureObservers( newMeasure );
	}

	public void deleteMeasure(Measure measure) {
		Iterator<SoundEvent> iterator = measure.getIterator();
		SoundEvent event = null;
		while (iterator.hasNext()) {
			event = iterator.next();
			event.setActionTaken( SoundEvent.SOUND_REMOVED );
			notifySoundEventObservers( event );
		}
		int finalBeatRemoved = getFinalBeatRemoved( measure );
		measureList.remove( measure );
		notifyMeasureObservers( measure, finalBeatRemoved );
	}

	private int getFinalBeatRemoved(Measure measureToBeDeleted) {
		int totalBeats = 0;
		for (Measure measure : measureList) {
			totalBeats += measure.getBeats();
			if (measure == measureToBeDeleted)
				return totalBeats;
		}
		return 0;
	}

	public void addSoundEvent(SoundEvent event) {
		event.getMeasure().addSoundEvent( event );
		notifySoundEventObservers( event );
	}

	public void registerMeasureObserver(MeasureObserver observer) {
		measureObserverList.add( observer );
	}

	public void notifyMeasureObservers(Measure measure) {
		for (MeasureObserver observer : measureObserverList) {
			observer.onMeasureAdded( measure );
		}
	}

	public void notifyMeasureObservers(Measure measure, int finalBeatRemoved) {
		for (MeasureObserver observer : measureObserverList) {
			observer.onMeasureDeleted( measure, finalBeatRemoved );
		}
	}

	public void registerSoundEventObserver(SoundEventObserver observer) {
		soundEventObserverList.add( observer );
	}

	public void notifySoundEventObservers(SoundEvent event) {
		int totalBeats = getTotalBeats( event );
		for (SoundEventObserver observer : soundEventObserverList) {
			observer.onSoundEvent( event, totalBeats );
		}
	}

	private int getTotalBeats(SoundEvent event) {
		int totalBeats = 0;
		for (Measure measure : measureList) {
			if (event.getMeasure() == measure) {
				totalBeats += event.getBeatInMeasure();
				return totalBeats;
			} else
				totalBeats += measure.getBeats();
		}
		return 0;
	}

}
