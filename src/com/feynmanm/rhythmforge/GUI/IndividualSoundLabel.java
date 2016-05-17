package com.feynmanm.rhythmforge.GUI;

import javax.swing.*;
import javax.swing.border.*;
import com.feynmanm.rhythmforge.instruments.*;

@SuppressWarnings("serial")
public class IndividualSoundLabel extends JLabel {
	
	private DistinctSound sound;
	
	public IndividualSoundLabel( DistinctSound sound ) {
		this.sound = sound;
		this.setText( sound.getName() );
		this.setBorder( new EmptyBorder( 2, 10, 3, 10 ) );
		this.setAlignmentX( RIGHT_ALIGNMENT );
	}
	
	public DistinctSound getSound() {
		return sound;
	}

}
