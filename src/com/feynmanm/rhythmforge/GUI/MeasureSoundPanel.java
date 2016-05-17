package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.feynmanm.rhythmforge.instruments.*;
import com.feynmanm.rhythmforge.music.*;
import com.feynmanm.rhythmforge.*;

@SuppressWarnings("serial")
public class MeasureSoundPanel extends JPanel {
	
	private DistinctSound sound;
	private ItemListener selectionBoxListener;
	private Instrument instrument;
	private Measure measure;
	private Color defaultBackgroundColor;

	public MeasureSoundPanel( DistinctSound sound, Instrument instrument, ItemListener selectionBoxListener, Measure measure ) {
		this.sound = sound;
		this.selectionBoxListener = selectionBoxListener;
		this.instrument = instrument;
		this.measure = measure;
		this.setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );
		this.defaultBackgroundColor = this.getBackground();
		createSelectionBoxes();
	}
	
	public void addSoundEvent( SoundEvent event ) {
		SelectionBox box = (SelectionBox) this.getComponent( event.getBeatInMeasure() );
		box.setSelected( true );
	}
	
	private void createSelectionBoxes() {
		SelectionBox box = null;
		for( int i = 0; i < measure.getBeats(); i++ ) {
			box = new SelectionBox( sound, instrument, measure, i );
			box.addItemListener( selectionBoxListener );
			box.setSelected( false );
			if ( i == ( measure.getBeats() - 1 ) ) setLastBeatHighlight( box ); 
			this.add( box );
		}
	}
	
	public void highlightSelectionBoxes( Color color ) {
		Component[] boxes = this.getComponents();
		for( int i = 0; i < boxes.length; i++ ) {
			SelectionBox box = (SelectionBox) boxes[i];
			box.setBackground( color );
		}
	}
	
	public void removeSelectionBoxHighlight() {
		Component[] boxes = this.getComponents();
		for( int i = 0; i < boxes.length; i++ ) {
			SelectionBox box = (SelectionBox) boxes[i];
			if ( i == ( measure.getBeats() - 1 ) ) setLastBeatHighlight( box );
			else box.setBackground( defaultBackgroundColor );
		}
	}
	
	private void setLastBeatHighlight( SelectionBox box ) {
		box.setBackground( Color.CYAN );
	}

}
