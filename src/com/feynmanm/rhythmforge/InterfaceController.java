package com.feynmanm.rhythmforge;

import com.feynmanm.rhythmforge.instruments.*;
import com.feynmanm.rhythmforge.music.*;

public abstract class InterfaceController implements InstrumentObserver, MeasureObserver, SoundEventObserver {

	public abstract void onInstrumentAdded( Instrument instrument );
	public abstract void onInstrumentRemoved( Instrument instrument );
	
	public abstract void onSoundAdded( DistinctSound sound, Instrument instrument );
	public abstract void onSoundRemoved( DistinctSound sound, Instrument instrument );

	public abstract void onMeasureAdded( Measure measure );
	public abstract void onMeasureDeleted( Measure measure, int finalBeatRemoved );
	
	public abstract void onSoundEvent( SoundEvent event, int totalBeats );

}
