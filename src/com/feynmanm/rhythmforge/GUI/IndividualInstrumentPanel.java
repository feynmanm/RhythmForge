package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import com.feynmanm.rhythmforge.instruments.*;

@SuppressWarnings("serial")
public class IndividualInstrumentPanel extends JPanel {

	private Instrument instrument;
	private InstrumentLabel instrumentLabel;
	private SoundBox soundBox;

	public IndividualInstrumentPanel(Instrument instrument) {
		this.instrument = instrument;
		instrumentLabel = new InstrumentLabel( instrument.getName() );
		this.setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );
		this.add( instrumentLabel );
		soundBox = new SoundBox( BoxLayout.Y_AXIS );
		this.add( soundBox );
		System.out.println( instrument.getName() + " added in IndividualInstrumentPanel");
	}
	
	public Instrument getInstrument() {
		return instrument;
	}

	public void addSound( DistinctSound sound, int index ) {
		soundBox.add( new IndividualSoundLabel( sound ), index );
		System.out.println( "IndividualInstrumentPanel add sound: " + sound.getName() );
	}

}
