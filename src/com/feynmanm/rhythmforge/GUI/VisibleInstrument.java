package com.feynmanm.rhythmforge.GUI;

import java.util.*;
import com.feynmanm.rhythmforge.instruments.*;

public class VisibleInstrument {
	
	private Instrument instrument;
	private ArrayList<DistinctSound> soundList;
	
	public VisibleInstrument( Instrument instrument ) {
		this.instrument = instrument;
		soundList = new ArrayList<DistinctSound>();
	}
	
	public boolean isInstrument( Instrument instrument ) {
		return ( this.instrument == instrument );
	}
	
	public Instrument getInstrument() {
		return instrument;
	}
	
	public ArrayList<DistinctSound> getSoundList() {
		return soundList;
	}
	
	public boolean hasSound( DistinctSound sound ) {
		return soundList.contains( sound );
	}
	
	public void addSound( DistinctSound sound ) {
		soundList.add( sound );
	}
	
	public int getSoundIndex( DistinctSound sound ) {
		return soundList.indexOf( sound );
	}

}
