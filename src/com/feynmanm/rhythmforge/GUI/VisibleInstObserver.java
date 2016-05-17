package com.feynmanm.rhythmforge.GUI;

import com.feynmanm.rhythmforge.instruments.DistinctSound;
import com.feynmanm.rhythmforge.instruments.Instrument;

public interface VisibleInstObserver {

	public void onInstrumentAdded( Instrument instrument, int index );
	public void onInstrumentRemoved( Instrument instrument );
	
	public void onSoundAdded( DistinctSound sound, int instrumentIndex, int soundIndex );
	public void onSoundRemoved( DistinctSound sound, Instrument instrument );

}
