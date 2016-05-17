package com.feynmanm.rhythmforge.music;

import com.feynmanm.rhythmforge.*;

public interface SongSubject {

	public void registerMeasureObserver( MeasureObserver observer );
	public void notifyMeasureObservers( Measure measure );
	public void notifyMeasureObservers( Measure measure, int finalBeatRemoved );
	
	public void registerSoundEventObserver( SoundEventObserver observer );
	public void notifySoundEventObservers( SoundEvent event );

}
