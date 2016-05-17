package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	
	public static final String BUTTON_STOP_TEXT = "Stop";
	public static final String BUTTON_START_TEXT = "Start Again";
	public static final String ADD_MEASURE = "Add Measure";
	public static final String SAVE_SONG = "Save Song";
	public static final String LOAD_SONG = "Load Song";
	// public static final String REMOVE_INST_TEXT = "Remove Selected Instruments";

	private JButton addMeasureButton;
	private JButton stopStartButton;
	private JButton saveSongButton;
	private JButton loadSongButton;
	// private JButton removeButton;
	
	public ButtonPanel( ActionListener stopStartListener, ActionListener addMeasureListener, ActionListener removeInstListener, 
			ActionListener saveSongListener, ActionListener loadSongListener ) {
		this.setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );
		this.setBorder( new EmptyBorder( 0, 0, 10, 0 ) );
		stopStartButton = new JButton( BUTTON_STOP_TEXT );
		addMeasureButton = new JButton( ADD_MEASURE );
		saveSongButton = new JButton( SAVE_SONG );
		loadSongButton = new JButton( LOAD_SONG );
		// removeButton = new JButton( REMOVE_INST_TEXT );
		stopStartButton.addActionListener( stopStartListener );
		addMeasureButton.addActionListener( addMeasureListener );
		saveSongButton.addActionListener( saveSongListener );
		loadSongButton.addActionListener( loadSongListener );
		// removeButton.addActionListener( removeInstListener );
		this.add( stopStartButton );
		this.add( addMeasureButton );
		this.add( saveSongButton );
		this.add( loadSongButton );
		// this.add( removeButton );
	}
	
}
