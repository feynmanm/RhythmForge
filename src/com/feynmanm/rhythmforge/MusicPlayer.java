package com.feynmanm.rhythmforge;

import com.feynmanm.rhythmforge.music.*;

public interface MusicPlayer extends SoundEventObserver, MeasureObserver {
	
	public void playSong();
	public void stopSong();

}
