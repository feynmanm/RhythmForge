package com.feynmanm.rhythmforge.instruments;

import java.util.*;
import java.io.*;

public abstract class Instrument extends HashSet<DistinctSound> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String data;

	public String getName() {
		return name;
	}
	
	public void setData( String dataString ) {
		this.data = dataString;
	}
	
	public String getData() {
		return data;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals( Object objectToTest ) {
		Instrument inst = (Instrument) objectToTest;
		return getName().equals( inst.getName() );
	}
	
}
