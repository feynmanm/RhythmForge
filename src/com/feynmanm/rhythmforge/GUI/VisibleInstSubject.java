package com.feynmanm.rhythmforge.GUI;

import com.feynmanm.rhythmforge.instruments.*;

public interface VisibleInstSubject {
	
	public void registerVisibleInstObserver( VisibleInstObserver observer );
	public void notifyVisibleInstObservers( Instrument instrument, int Index );
	public void notifyVisibleInstObservers( DistinctSound sound, int instrumentIndex, int soundIndex );

}
