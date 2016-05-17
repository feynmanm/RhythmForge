package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.feynmanm.rhythmforge.music.*;
import com.feynmanm.rhythmforge.*;

import com.feynmanm.rhythmforge.instruments.*;

@SuppressWarnings("serial")
public class MeasurePanel extends JPanel implements VisibleInstObserver {
	
	private ItemListener selectionBoxListener;
	private ActionListener deleteMeasureListener;
	
	public MeasurePanel( ItemListener selectionBoxListener, ActionListener deleteMeasureListener ) {
		this.selectionBoxListener = selectionBoxListener;
		this.deleteMeasureListener = deleteMeasureListener;
		this.setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );
		this.setAlignmentY( TOP_ALIGNMENT );
	}
	
	public void onInstrumentAdded( Instrument instrument, int index ) {
		Component[] measurePanelArray = this.getComponents();
		for( int i = 0; i < measurePanelArray.length; i++ ) {
			IndividualMeasurePanel panel = (IndividualMeasurePanel) measurePanelArray[i];
			panel.addInstrument( instrument, index );
		}
		updateUI();
	}
	
	public void onSoundAdded( DistinctSound sound, int instrumentIndex, int soundIndex ) {
		Component[] measurePanelArray = this.getComponents();
		for( int i = 0; i < measurePanelArray.length; i++ ) {
			IndividualMeasurePanel panel = (IndividualMeasurePanel) measurePanelArray[i];
			panel.addSound( sound, instrumentIndex, soundIndex );
		}
		updateUI();
	}
	
	public void onInstrumentRemoved( Instrument instrument ) {
		
	}
	
	public void onSoundRemoved( DistinctSound sound, Instrument instrument ) {
		
	}
	
	public void addMeasure( Measure measure, int index, ArrayList<VisibleInstrument> instrumentList ) {
		IndividualMeasurePanel panel = new IndividualMeasurePanel( measure, selectionBoxListener, deleteMeasureListener ); 
		this.add( panel, index );
		for( VisibleInstrument visibleInstrument: instrumentList ) {
			int instrumentIndex = instrumentList.indexOf( visibleInstrument );
			panel.addInstrument( visibleInstrument.getInstrument(), instrumentIndex );
			ArrayList<DistinctSound> soundList = visibleInstrument.getSoundList();
			for( DistinctSound sound: soundList ) {
				int soundIndex = soundList.indexOf( sound );
				panel.addSound( sound, instrumentIndex, soundIndex );
			}
		}
		updateUI();
	}
	
	public void deleteMeasure( Measure measure, int index ) {
		this.remove( index );
		updateUI();
	}
	
	public void addSoundEvent( SoundEvent event, int measureIndex, int instrumentIndex, int soundIndex ) {
		IndividualMeasurePanel indMeasurePanel = (IndividualMeasurePanel) this.getComponent( measureIndex ); 
		indMeasurePanel.addSoundEvent(  event, instrumentIndex, soundIndex );
	}
	
}
