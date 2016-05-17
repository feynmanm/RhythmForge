package com.feynmanm.rhythmforge.instruments;

import java.io.*;

public abstract class DistinctSound implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String name;
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void setData1( String data );
	public abstract void setData2( String data );
	public abstract void setData3( String data );
	public abstract void setData4( String data );
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals( Object objectToTest ) {
		DistinctSound snd = (DistinctSound) objectToTest;
		return getName().equals( snd.getName() );
	}
	
}
