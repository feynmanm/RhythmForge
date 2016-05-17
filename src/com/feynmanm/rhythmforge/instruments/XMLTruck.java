package com.feynmanm.rhythmforge.instruments;

import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import com.feynmanm.rhythmforge.MIDI.*;

public class XMLTruck extends DefaultHandler implements DeliveryTruck {
	
	private InstrumentFactory factory;
	private XMLConverter converter;
	
	private String instrumentElementTitle;
	private String instrumentElementNameTitle;
	private String instrumentDataTitle;
	private String soundElementTitle;
	private String soundElementNameTitle;
	private String data1Title;
	private String data2Title;
	private String data3Title;
	private String data4Title;
	
	private boolean bInstrumentElementTitle;
	private boolean bInstrumentElementNameTitle;
	private boolean bInstrumentDataTitle;
	private boolean bSoundElementTitle;
	private boolean bSoundElementNameTitle;
	private boolean bData1Title;
	private boolean bData2Title;
	private boolean bData3Title;
	private boolean bData4Title;
	
	private SAXParser parser;
	private Store store;
	
	Instrument tempInstrument;
	DistinctSound tempSound;
	
	public XMLTruck( int outputType, InstrumentFactory factory ) {
		this.factory= factory; 
		if ( outputType == Dispatcher.MIDI_OUTPUTTYPE ) converter = new MidiXMLConverter();
		setElements();
		resetXMLGates();
	}
	
	private void setElements() {
		instrumentElementTitle = converter.getInstrumentElementTitle();
		instrumentElementNameTitle = converter.getInstrumentElementNameTitle();
		instrumentDataTitle = converter.getInstrumentDataTitle();
		soundElementTitle = converter.getSoundElementTitle();
		soundElementNameTitle = converter.getSoundElementNameTitle();
		data1Title = converter.getData1Title();
		data2Title = converter.getData2Title();
		data3Title = converter.getData3Title();
		data4Title = converter.getData4Title();
	}
	
	private void resetXMLGates() {
		bInstrumentElementTitle = false;
		bInstrumentElementNameTitle = false;
		bInstrumentDataTitle = false;
		bSoundElementTitle = false;
		bSoundElementNameTitle = false;
		bData1Title = false;
		bData2Title = false;
		bData3Title = false;
		bData4Title = false;
	}
	
	public void stockStore( Store store, File sourceFile ) {
		this.store = store;
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parser = parserFactory.newSAXParser();
			parser.parse( sourceFile, this );
		} catch ( Exception e ) { e.printStackTrace(); }
	}
	
	// SAX parser callbacks
	@Override
	public void startElement(String uri, String localName,String qName, Attributes attributes) {
		try{
			if (qName.equalsIgnoreCase( instrumentElementTitle ) ) { bInstrumentElementTitle = true; }
			if (qName.equalsIgnoreCase( instrumentElementNameTitle ) ) { bInstrumentElementNameTitle = true; }
			if (qName.equalsIgnoreCase( instrumentDataTitle ) ) { bInstrumentDataTitle = true; }
			if (qName.equalsIgnoreCase( soundElementTitle ) ) { bSoundElementTitle = true; }
			if (qName.equalsIgnoreCase( soundElementNameTitle ) ) { bSoundElementNameTitle = true; }
			if (qName.equalsIgnoreCase( data1Title ) ) { bData1Title = true; }
			if (qName.equalsIgnoreCase( data2Title ) ) { bData2Title = true; }
			if (qName.equalsIgnoreCase( data3Title ) ) { bData3Title = true; }
			if (qName.equalsIgnoreCase( data4Title ) ) { bData4Title = true; }
		} catch ( Exception e ) { e.printStackTrace(); }
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		if ( bInstrumentElementTitle ) {
			resetXMLGates();
		}

		if ( bInstrumentElementNameTitle ) {
			String instrumentName = new String( ch, start, length );
			tempInstrument = factory.createInstrument( instrumentName );
			store.addInstrument( tempInstrument );
			resetXMLGates();
		}
		
		if( bInstrumentDataTitle ) {
			String instrumentData = new String( ch, start, length );
			tempInstrument.setData( instrumentData );
			resetXMLGates();
		}
		
		if ( bSoundElementTitle ) {
			resetXMLGates();
		}
		
		if ( bSoundElementNameTitle ) {
			String soundName = new String( ch, start, length );
			tempSound = factory.createSound( soundName );
			store.addSound( tempSound, tempInstrument );
			resetXMLGates();
		}

		if ( bData1Title ) {
			String data = new String( ch, start, length );
			tempSound.setData1( data );
			resetXMLGates();
		}

		if ( bData2Title ) {
			String data = new String( ch, start, length );
			tempSound.setData2( data );
			resetXMLGates();
		}

		if ( bData3Title ) {
			String data = new String( ch, start, length );
			tempSound.setData3( data );
			resetXMLGates();
		}

		if ( bData4Title ) {
			String data = new String( ch, start, length );
			tempSound.setData4( data );
			resetXMLGates();
		}
	}
}
