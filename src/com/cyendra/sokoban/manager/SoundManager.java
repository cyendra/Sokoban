package com.cyendra.sokoban.manager;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class SoundManager {
	String path = new String("audio\\");
	String file = new String("bgm.mid");
	Sequence seq;
	Sequencer midi;
	boolean sign;
	public void loadSound(){
		try{
			seq = MidiSystem.getSequence(new File(path+file));
			midi = MidiSystem.getSequencer();
			midi.open();midi.setSequence(seq);
			midi.start();
			midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		}
		catch (Exception e){System.out.println(e.toString());}
		sign = true;
	}
	public void bgmStop(){
		midi.stop();
		midi.close();
		sign = false;
	}
	public boolean isplay(){
		return sign;
	}
	void setMusic(String e){
		file = e;
	}
	public SoundManager() {}

}
