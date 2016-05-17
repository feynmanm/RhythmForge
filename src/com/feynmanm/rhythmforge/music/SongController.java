package com.feynmanm.rhythmforge.music;

import java.io.File;

import com.feynmanm.rhythmforge.SoundEvent;
import com.feynmanm.rhythmforge.SoundEventObserver;

public class SongController {
	
	private Song song;
	private SongSaver saver;
	private SongLoader loader;
	
	public SongController() {
		song = new Song();
		saver = new SongSaver();
		loader = new SongLoader();
		song.registerMeasureObserver( loader );
	}

	public void addMeasure() {
		song.addMeasure();
	}
	
	public void deleteMeasure( Measure measure ) {
		song.deleteMeasure( measure );
	}
	
	public void addSoundEvent( SoundEvent event ) {
		song.addSoundEvent( event );
	}
	
	public void saveSong() {
		saver.saveSong( song );
	}

	public void loadSong( File savedSongFile ) {
		loader.loadSong( savedSongFile, song );
	}
	
	public void registerSoundEventObserver(SoundEventObserver observer) {
		song.registerSoundEventObserver( observer );
	}
	
	public void registerMeasureObserver(MeasureObserver observer) {
		song.registerMeasureObserver( observer );
	}
	
}
