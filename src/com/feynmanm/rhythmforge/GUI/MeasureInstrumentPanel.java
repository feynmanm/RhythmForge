package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.feynmanm.rhythmforge.instruments.*;
import com.feynmanm.rhythmforge.music.*;
import com.feynmanm.rhythmforge.*;

@SuppressWarnings("serial")
public class MeasureInstrumentPanel extends JPanel {
	
	private Instrument instrument;
	private ItemListener selectionBoxListener;
	private Measure measure;
	
	public MeasureInstrumentPanel( Instrument instrument, ItemListener selectionBoxListener, Measure measure ) {
		this.instrument = instrument;
		this.selectionBoxListener = selectionBoxListener;
		this.measure = measure;
		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
	}
	
	public void addSound( DistinctSound sound, int index ) {
		this.add( new MeasureSoundPanel( sound, instrument, selectionBoxListener, measure ), index );
	}
	
	public void addSoundEvent( SoundEvent event, int soundIndex ) { 
		MeasureSoundPanel panel = (MeasureSoundPanel) this.getComponent( soundIndex );
		panel.addSoundEvent( event );
	}
	
	public void highlightMeasurePanel( Color color ) {
		Component[] soundPanels = this.getComponents();
		for( int i = 0; i < soundPanels.length; i++ ) {
			MeasureSoundPanel soundPanel = (MeasureSoundPanel) soundPanels[i];
			soundPanel.highlightSelectionBoxes( color );
		}
	}
	
	public void removeMeasurePanelHighlight() {
		Component[] soundPanels = this.getComponents();
		for( int i = 0; i < soundPanels.length; i++ ) {
			MeasureSoundPanel soundPanel = (MeasureSoundPanel) soundPanels[i];
			soundPanel.removeSelectionBoxHighlight();
		}
	}

}
