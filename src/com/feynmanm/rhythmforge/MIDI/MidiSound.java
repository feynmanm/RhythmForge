package com.feynmanm.rhythmforge.MIDI;

import com.feynmanm.rhythmforge.instruments.DistinctSound;

public class MidiSound extends DistinctSound {
	
	private static final long serialVersionUID = 1L;
	private int note;
	private int velocity;
	
	public MidiSound( String name ) {
		this.name = name;
	}
	
	public int getNote() {
		return note;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public void setData1( String data ) {
		this.note = Integer.parseInt( data );  
	}
	
	public void setData2( String data ) {
		this.velocity = Integer.parseInt( data );
	}
	
	public void setData3( String data ) {
	}
	
	public void setData4( String data ) {
	}

}
