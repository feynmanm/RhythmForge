package com.feynmanm.rhythmforge.music;

import java.util.*;
import java.io.*;
import java.text.*;
import com.feynmanm.rhythmforge.*;

public class SongSaver {
	
	public void saveSong( Song song ) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		ArrayList<Measure> measureList = song.getMeasureList();
		
		try {
			FileOutputStream fileStream = new FileOutputStream( RhythmForgeController.SAVED_SONG_DIR + "SavedSong_" + timeStamp + ".ser");
			ObjectOutputStream objectStream = new ObjectOutputStream( fileStream );
			objectStream.writeObject( measureList );
			objectStream.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
}
