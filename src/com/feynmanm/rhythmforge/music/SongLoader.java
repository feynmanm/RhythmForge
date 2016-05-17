package com.feynmanm.rhythmforge.music;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import com.feynmanm.rhythmforge.*;

public class SongLoader implements MeasureObserver {
	
	private boolean isLoading = false;
	private ArrayList<SoundEvent> soundEventList;
	
	@SuppressWarnings("unchecked")
	public void loadSong( File savedSongFile, Song song ) {
		deleteCurrentMeasures( song );
		ArrayList<Measure> measureList = null;

		try{
			FileInputStream fileStream = new FileInputStream( savedSongFile );
			ObjectInputStream objectStream = new ObjectInputStream( fileStream );
			measureList = (ArrayList<Measure>) objectStream.readObject();
			objectStream.close();
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		for( Measure measure: measureList ) {
			soundEventList = measure.getSoundEventList();
			isLoading = true;
			song.addMeasure();
			for( SoundEvent event: soundEventList ) {
				song.addSoundEvent( event );
			}
			isLoading = false;
		}
	}
	
	private void deleteCurrentMeasures( Song song ) {
		ArrayList<Measure> measureList = song.getMeasureList();
		ArrayList<Measure> tempMeasureList = new ArrayList<Measure>();
		for( Measure measure: measureList ) {
			tempMeasureList.add( measure );
		}
		for( Measure tempMeasure: tempMeasureList) {
			song.deleteMeasure( tempMeasure );
		}
	}
	
	public void onMeasureAdded( Measure measure ) {
		if ( isLoading ) {
			for( SoundEvent event: soundEventList ) {
				event.setMeasure( measure );
			}
		}
	}
	
	public void onMeasureDeleted( Measure measure, int finalBeatRemoved ) {
		// do nothing
	}
	
}
