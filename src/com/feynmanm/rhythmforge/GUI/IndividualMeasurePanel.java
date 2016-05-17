package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import com.feynmanm.rhythmforge.music.*;
import com.feynmanm.rhythmforge.instruments.*;
import com.feynmanm.rhythmforge.*;

@SuppressWarnings("serial")
public class IndividualMeasurePanel extends JPanel {
	
	public final static String DELETE_MEASURE = "delete";
	
	private Measure measure;
	private ItemListener selectionBoxListener;
	private Box buttonBox;
	private JButton deleteButton;
	
	public IndividualMeasurePanel( Measure measure, ItemListener selectionBoxListener, ActionListener deleteMeasureListener ) {
		this.measure = measure;
		this.selectionBoxListener = selectionBoxListener;
		deleteButton = new JButton( DELETE_MEASURE );
		deleteButton.addActionListener( deleteMeasureListener );
		deleteButton.setAlignmentX( Component.LEFT_ALIGNMENT );
		// Set the following to false if you only want to show the delete button when the measure is moused over
		deleteButton.setVisible( true );
		buttonBox = new Box( BoxLayout.X_AXIS );
		//buttonBox.add( Box.createHorizontalGlue() );
		buttonBox.add( deleteButton );
		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		this.setBorder( new EmptyBorder( 2, 2, 2, 2 ) );
		this.add( buttonBox );
		this.setAlignmentY( TOP_ALIGNMENT );
		// Use the following if you only want to show the delete button when the measure is moused over
		// this.addMouseListener( new MeasureMouseListener() );
	}
	
	public Measure getMeasure() {
		return measure;
	}
	
	public void addInstrument( Instrument instrument, int index ) {
		this.add( new MeasureInstrumentPanel( instrument, selectionBoxListener, measure ), index );
	}
	
	public void addSound( DistinctSound sound, int instrumentIndex, int soundIndex ) {
		MeasureInstrumentPanel panel = (MeasureInstrumentPanel) this.getComponent( instrumentIndex );
		panel.addSound( sound, soundIndex );
	}
	
	public void addSoundEvent( SoundEvent event, int instrumentIndex, int soundIndex ) { 
		MeasureInstrumentPanel panel = (MeasureInstrumentPanel) this.getComponent( instrumentIndex );
		panel.addSoundEvent( event, soundIndex );
	}
	
	//  The following inner class can be hooked up to only show the delete button when the measure is moused over
	public class MeasureMouseListener implements MouseListener {
		public void mouseEntered( MouseEvent event ) {
			Component[] instrumentPanels = IndividualMeasurePanel.this.getComponents();
			for( int i = 0; i < instrumentPanels.length; i++ ) {
				if( instrumentPanels[i] instanceof MeasureInstrumentPanel ) {
					MeasureInstrumentPanel panel = (MeasureInstrumentPanel) instrumentPanels[i];
					panel.highlightMeasurePanel( Color.CYAN );
					deleteButton.setVisible( true );
				}
			}
			updateUI();
		}
		public void mouseExited( MouseEvent event ) {
			if( IndividualMeasurePanel.this.getMousePosition() == null ) {
				Component[] instrumentPanels = IndividualMeasurePanel.this.getComponents();
				for( int i = 0; i < instrumentPanels.length; i++ ) {
					if( instrumentPanels[i] instanceof MeasureInstrumentPanel ) {
						MeasureInstrumentPanel panel = (MeasureInstrumentPanel) instrumentPanels[i];
						panel.removeMeasurePanelHighlight();
						deleteButton.setVisible( false );
					}
				}
				updateUI();
			}
		}
		public void mouseClicked( MouseEvent event ) {}
		public void mousePressed( MouseEvent event ) {}
		public void mouseReleased( MouseEvent event ) {}
	}
	
}
