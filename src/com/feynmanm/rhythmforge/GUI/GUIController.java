package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.feynmanm.rhythmforge.*;
import com.feynmanm.rhythmforge.instruments.*;
import com.feynmanm.rhythmforge.music.Measure;

public class GUIController extends InterfaceController {
	
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 1000;

	private RhythmForgeController masterController;
	private GUIModel guiModel;
	private JFrame guiFrame;
	private JPanel mainPanel;
	private ButtonPanel buttonPanel;
	private InstrumentPanel instrumentPanel;
	private MeasurePanel measurePanel;
	
	public GUIController( RhythmForgeController masterController, String name ) {
		this.masterController = masterController;
		guiModel = new GUIModel();
		guiFrame = new JFrame( name );
		mainPanel = new JPanel( new BorderLayout() );
		buttonPanel = new ButtonPanel( new StopStartListener(), new AddMeasureListener(), new RemoveInstListener(), new SaveSongListener(), 
				new LoadSongListener() );
		instrumentPanel = new InstrumentPanel();
		measurePanel = new MeasurePanel( new SelectionBoxListener(), new DeleteMeasureListener() );
		buildGUI();
	}
	
	private void buildGUI() {
		guiModel.registerVisibleInstObserver( instrumentPanel );
		guiModel.registerVisibleInstObserver( measurePanel );
		mainPanel.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		mainPanel.add( buttonPanel, BorderLayout.NORTH );
		mainPanel.add( instrumentPanel, BorderLayout.WEST );
		mainPanel.add( measurePanel, BorderLayout.CENTER );
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		guiFrame.setLayout( new BorderLayout() );
		guiFrame.setSize( WIDTH, HEIGHT );
		guiFrame.add( mainPanel, BorderLayout.NORTH );
		guiFrame.setVisible( true );
	}
	
	public void onInstrumentAdded( Instrument instrument ) {
		guiModel.addInstrument( instrument );
	}
	
	public void onInstrumentRemoved( Instrument instrument ) {
		guiModel.removeInstrument( instrument );
	}
	
	public void onSoundAdded( DistinctSound sound, Instrument instrument ) {
		guiModel.addSound( sound, instrument );
	}
	
	public void onSoundRemoved( DistinctSound sound, Instrument instrument ) {
		guiModel.removeSound( sound, instrument );
	}
	
	public void onMeasureAdded( Measure measure ) {
		guiModel.addMeasure( measure );
		measurePanel.addMeasure( measure, guiModel.getMeasureIndex( measure ), guiModel.getInstrumentList() );
	}
	
	public void onMeasureDeleted( Measure measure, int finalBeatRemoved_NOTUSED ) {
		int index = guiModel.getMeasureIndex( measure );
		guiModel.deleteMeasure( measure );
		measurePanel.deleteMeasure( measure, index );
	}

	public void onSoundEvent( SoundEvent event, int totalBeats_NOTUSED ) {
		if( event.getActionTaken() == SoundEvent.SOUND_ADDED ) {
			int measureIndex = guiModel.getMeasureIndex( event.getMeasure() );
			int instrumentIndex = guiModel.getInstrumentIndex( event.getInstrument() );
			int soundIndex = guiModel.getSoundIndex( event ); 
			measurePanel.addSoundEvent( event, measureIndex, instrumentIndex, soundIndex );
		}
	}
	
	public class SelectionBoxListener implements ItemListener {
		public void itemStateChanged( ItemEvent e ) {
			SelectionBox box = (SelectionBox) e.getItemSelectable();
			int actionType = 0;
			if( e.getStateChange() == ItemEvent.SELECTED ) {
				actionType = SoundEvent.SOUND_ADDED;
			} else if ( e.getStateChange() == ItemEvent.DESELECTED ) {
				actionType = SoundEvent.SOUND_REMOVED;
			}
			masterController.addSoundEvent( new SoundEvent( box.getSound(), box.getInstrument(), 
					box.getMeasure(), box.getBeatIndex(), actionType ) );
		}
	}
	
	public class StopStartListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			JButton button = (JButton) e.getSource();
			if ( button.getText() == ButtonPanel.BUTTON_STOP_TEXT ) {
				masterController.stopSong();
				button.setText( ButtonPanel.BUTTON_START_TEXT );
			} else if ( button.getText() == ButtonPanel.BUTTON_START_TEXT ) {
				masterController.playSong();
				button.setText( ButtonPanel.BUTTON_STOP_TEXT );
			}
		}
	}
	
	public class AddMeasureListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			masterController.addMeasure();
		}
	}
	
	public class DeleteMeasureListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			JButton button = (JButton) e.getSource();
			IndividualMeasurePanel panel = (IndividualMeasurePanel) button.getParent().getParent();
			masterController.deleteMeasure( panel.getMeasure() );
		}
	}
	
	public class RemoveInstListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			System.out.println( "instrument removed");
		}
	}
	
	public class SaveSongListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			masterController.saveSong();
		}
	}
	
	public class LoadSongListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			JFileChooser fileOpen = new JFileChooser( RhythmForgeController.SAVED_SONG_DIR );
			fileOpen.showOpenDialog( guiFrame );
			File savedSongFile = fileOpen.getSelectedFile();
			masterController.loadSong( savedSongFile );
		}
	}
	
}
