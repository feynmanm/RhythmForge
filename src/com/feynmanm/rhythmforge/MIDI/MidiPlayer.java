package com.feynmanm.rhythmforge.MIDI;

import javax.sound.midi.*;
import java.util.*;
import com.feynmanm.rhythmforge.*;
import com.feynmanm.rhythmforge.music.*;

public class MidiPlayer implements MusicPlayer {

	public static final int TICKS_PER_BEAT = 4;

	private Sequencer sequencer;
	private Sequence sequence;
	private Track track;
	private MidiEvent endingEvent;
	private int tempo;
	private int totalBeats;

	public MidiPlayer( int tempo ){
		this.tempo = tempo;
		totalBeats = 0;
		setUpSequencer();
	}
	
	private void setUpSequencer() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setTempoInBPM( tempo );
			sequencer.setLoopCount( Sequencer.LOOP_CONTINUOUSLY );
			sequence = new Sequence( Sequence.PPQ, TICKS_PER_BEAT );
			track = sequence.createTrack();
			endingEvent = createEvent( ShortMessage.PROGRAM_CHANGE, 9, 1, 0, 0 );
			track.add( endingEvent );
			sequencer.setSequence( sequence );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public void playSong() {
		sequencer.start();
		sequencer.stop();
		sequencer.start();
	}

	public void stopSong() {
		sequencer.stop();
	}
	
	public void onMeasureAdded( Measure measure ) {
		totalBeats += measure.getBeats();
		try {
			track.remove( endingEvent );
			endingEvent = createEvent( ShortMessage.PROGRAM_CHANGE, 9, 1, 0, totalBeats );
			track.add( endingEvent );
			sequencer.setSequence( sequence );
			playSong();
		} catch ( Exception exception ) {
			exception.printStackTrace();
		}
	}
	
	public void onMeasureDeleted( Measure measure, int finalBeatRemoved ) {
		totalBeats -= measure.getBeats();
		try {
			MidiEvent event = null;
			ArrayList<MidiEvent> eventList = new ArrayList<MidiEvent>();
			for( int i = 0; i < track.size(); i++ ) {
				event = track.get( i );
				int eventTick = (int) event.getTick();
				if( eventTick > (finalBeatRemoved -1) ) event.setTick( eventTick - measure.getBeats() );
				eventList.add( event );
			}
			for( MidiEvent midiEvent: eventList ) {
				track.remove( midiEvent );
				track.add( midiEvent );
			}
			sequencer.setSequence( sequence );
			playSong();
		} catch ( Exception exception ) {
			exception.printStackTrace();
		}
	}

	public void onSoundEvent( SoundEvent event, int totalBeats ) {
		
		MidiSound sound = (MidiSound) event.getSound();
		int channel = Integer.parseInt( event.getInstrumentData() );
		
		if( event.getActionTaken() == SoundEvent.SOUND_ADDED ) {
			try {
				if ( channel == 2 ) track.add(  createEvent( ShortMessage.PROGRAM_CHANGE, channel, 33, 0, 0 ) );
				else track.add(  createEvent( ShortMessage.PROGRAM_CHANGE, channel, 33, 0, 0 ) );
				track.add( createEvent( ShortMessage.NOTE_ON, channel, sound.getNote(), sound.getVelocity(), totalBeats ) );
				track.add( createEvent( ShortMessage.NOTE_OFF, channel, sound.getNote(), sound.getVelocity(), totalBeats + 1 ) );
				sequencer.setSequence( sequence );
				playSong();
			} catch ( Exception exception ) {
				exception.printStackTrace();
			}
		}
		
		if( event.getActionTaken() == SoundEvent.SOUND_REMOVED ) {
			boolean isNoteOn = false;
			boolean isNoteOff = false;
			MidiEvent midiEvent = null;
			ShortMessage sm = null;
			for ( int i = 0; i < ( track.size() -1 ); i++ ) {
				midiEvent = track.get( i );
				sm = (ShortMessage) midiEvent.getMessage();
				isNoteOn = midiEvent.getTick() == totalBeats && sm.getData1() == sound.getNote() && sm.getCommand() == ShortMessage.NOTE_ON;
				isNoteOff = midiEvent.getTick() == ( totalBeats + 1 ) && sm.getData1() == sound.getNote() && sm.getCommand() == ShortMessage.NOTE_OFF;
				if ( isNoteOn || isNoteOff ) {
					track.remove( midiEvent );
				}
			}
		}
		
	}
	
	private MidiEvent createEvent( int comd, int chan, int one, int two, int tick ) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage( comd, chan, one, two );
			event = new MidiEvent( a, tick );
		} catch ( Exception e ) { e.printStackTrace(); }
		return event;
	}

}
