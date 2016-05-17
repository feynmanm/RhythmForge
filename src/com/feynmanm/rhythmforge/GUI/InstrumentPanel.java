package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import com.feynmanm.rhythmforge.instruments.*;

@SuppressWarnings("serial")
public class InstrumentPanel extends JPanel implements VisibleInstObserver {
	
	public InstrumentPanel() {
		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
	}
	
	public void onInstrumentAdded( Instrument instrument, int index ) {
		this.add( new IndividualInstrumentPanel( instrument ), index );
		updateUI();
	}
	
	public void onInstrumentRemoved( Instrument instrument ) {
		
	}
	
	public void onSoundAdded( DistinctSound sound, int instrumentIndex, int soundIndex ) {
		IndividualInstrumentPanel panel = (IndividualInstrumentPanel) getComponent( instrumentIndex );
		panel.addSound( sound, soundIndex );
		updateUI();
	}
	
	public void onSoundRemoved( DistinctSound sound, Instrument instrument ) {
		
	}

}
