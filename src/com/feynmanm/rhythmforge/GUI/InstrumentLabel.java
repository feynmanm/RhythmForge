package com.feynmanm.rhythmforge.GUI;

import java.awt.Font;
import javax.swing.*;

@SuppressWarnings("serial")
public class InstrumentLabel extends JLabel {
	
	public InstrumentLabel( String name ) {
		super( name );
		this.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 14 ) );
	}

}
