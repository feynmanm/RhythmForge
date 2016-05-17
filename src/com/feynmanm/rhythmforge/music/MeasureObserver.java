package com.feynmanm.rhythmforge.music;

public interface MeasureObserver {

	public void onMeasureAdded( Measure measure );
	public void onMeasureDeleted( Measure measure, int finalBeatRemoved );

}
