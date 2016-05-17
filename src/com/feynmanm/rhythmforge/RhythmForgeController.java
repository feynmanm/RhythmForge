package com.feynmanm.rhythmforge;

import java.io.*;
import com.feynmanm.rhythmforge.music.*;
import com.feynmanm.rhythmforge.instruments.*;
import com.feynmanm.rhythmforge.GUI.*;
import com.feynmanm.rhythmforge.MIDI.*;

public class RhythmForgeController {

	public final static String APPLICATION_NAME = "Rhythm Forge";
	public final static String SAVED_SONG_DIR = System.getProperty( "user.dir") + File.separator + "SavedSongs" + File.separator;
	private Store store; // model of the instruments available for making the song
	private SongController songController;
	private InterfaceController interfaceController; // controller of the user interface
	private MusicPlayer musicPlayer; // audio player
	private Dispatcher dispatcher;

	public RhythmForgeController(String instrumentsFileName) {

		// set up models
		store = new Store();
		songController = new SongController();
		interfaceController = new GUIController( this, APPLICATION_NAME );
		musicPlayer = new MidiPlayer( Song.DEFAULT_TEMPO );

		// set up controllers
		store.registerInstrumentObserver( interfaceController );
		songController.registerMeasureObserver( interfaceController );
		songController.registerMeasureObserver( musicPlayer );
		songController.registerSoundEventObserver( musicPlayer );
		songController.registerSoundEventObserver( interfaceController );

		// stock store
		dispatcher = new Dispatcher( Dispatcher.MIDI_OUTPUTTYPE );
		File instrumentsFile = new File( instrumentsFileName );
		stockStore( Dispatcher.XML_INPUTFILETYPE, instrumentsFile );

	}

	public void stockStore(int fileType, File instrumentsFile) {
		DeliveryTruck truck = dispatcher.createTruck( fileType );
		truck.stockStore( store, instrumentsFile );
	}
	
	public void playSong() {
		musicPlayer.playSong();
	}
	
	public void stopSong() {
		musicPlayer.stopSong();
	}
	
	public void addMeasure() {
		songController.addMeasure();
	}
	
	public void deleteMeasure( Measure measure ) {
		songController.deleteMeasure( measure );
	}
	
	public void addSoundEvent( SoundEvent event ) {
		songController.addSoundEvent( event );
	}
	
	public void saveSong() {
		songController.saveSong();
	}

	public void loadSong( File savedSongFile ) {
		songController.loadSong( savedSongFile );
	}

}
