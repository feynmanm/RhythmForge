package com.feynmanm.rhythmforge.MIDI;

import com.feynmanm.rhythmforge.instruments.*;

public class MidiXMLConverter extends XMLConverter {

	public MidiXMLConverter() {
		instrumentElementTitle = "INSTRUMENT";
		instrumentElementNameTitle = "INSTNAME";
		instrumentDataTitle = "CHANNEL";
		instrumentColorTitle = "COLOR";
		soundElementTitle = "MIDISOUND";
		soundElementNameTitle = "SOUNDNAME";
		data1Title = "NOTE";
		data2Title = "VELOCITY";
		data3Title = "COLOR";
		data4Title = "DATA4";
	}
	
}
