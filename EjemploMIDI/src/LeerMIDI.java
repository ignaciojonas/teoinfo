import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
public class LeerMIDI {

		public static HashMap<Integer, String> a = new HashMap<Integer, String>();
	    public static final int NOTE_ON = 0x90;
	    public static final int NOTE_OFF = 0x80;
	    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

	    public static Vector<Integer> getNotes(String filename, int track) throws Exception
	    {
	    	int size = 0;
	    	Vector<Integer> v = new Vector<Integer>();
	    	Sequence sequence = MidiSystem.getSequence(new File(filename));
	    	Track t = sequence.getTracks()[track];
	        for (int i=0; i < t.size(); i++) { 
	                MidiEvent event = t.get(i);
	                MidiMessage message = event.getMessage();
	                if (message instanceof ShortMessage) {
	                    ShortMessage sm = (ShortMessage) message;
	                    if (sm.getCommand() == NOTE_ON) {
	                        v.add((sm.getData1()/10));
	                        size++;
	                    }
	                }
	                if(size == 250)
	                	return v;
	                	
	        }

			return v;
	   
	    }
	    
	    public static void getHash(Vector<Integer> simbolos, Vector<Integer> cancion, String nombre)
	    {
	    	for (Integer integer : simbolos) {
				if(cancion.contains(integer))
					if(a.containsKey(integer))
					{
						String canciones = a.get(integer);
						canciones += "-" + nombre;
						a.put(integer, canciones);
					}
					else
						a.put(integer,nombre);
			}
	    	
	    }
	    
	    public static Vector<Integer> CountSimbolos(Vector<Integer> v)
	    {
	    	Vector<Integer> simb = new Vector<Integer>();
	    	for (Integer i : v) {
				if(!simb.contains(i))
				{
					simb.add(i);
				}
			}
	    	return simb;
	    }
	    
	    public static void main(String[] args) throws Exception {
	    	
	    	
	    	Vector<Integer> v = getNotes("bigbang.mid", 3);
	    	Vector<Integer> simb = CountSimbolos(v);
	    	Vector<Integer> v1 = getNotes("damasgratis.mid", 0);
	    	Vector<Integer> simb1 = CountSimbolos(v1);
	    	Vector<Integer> v2 = getNotes("gameofthrones.mid", 2);
	    	Vector<Integer> simb2 = CountSimbolos(v2);
	    	Vector<Integer> v3 = getNotes("juegodecapos.mid", 0);
	    	Vector<Integer> simb3 = CountSimbolos(v2);
	    	Vector<Integer> v4 = getNotes("thesimpsons.mid", 3);
	    	Vector<Integer> simb4 = CountSimbolos(v4);
	    	Vector<Integer> v5 = getNotes("yerbabrava.mid", 0);
	    	Vector<Integer> simb5 = CountSimbolos(v5);
	    	
	    	Vector<Integer> simbolos = new Vector<Integer>();
	    	for (Integer integer : simb) {
				if(!simbolos.contains(integer))
					simbolos.add(integer);
			}
	    	for (Integer integer : simb1) {
				if(!simbolos.contains(integer))
					simbolos.add(integer);
			}
	    	for (Integer integer : simb2) {
				if(!simbolos.contains(integer))
					simbolos.add(integer);
			}
//	    	for (Integer integer : simb3) {
//				if(!simbolos.contains(integer))
//					simbolos.add(integer);
//			}
	    	for (Integer integer : simb4) {
				if(!simbolos.contains(integer))
					simbolos.add(integer);
			}
	    	for (Integer integer : simb5) {
				if(!simbolos.contains(integer))
					simbolos.add(integer);
			}
	    	Collections.sort(simb);
	    	Collections.sort(simb1);
	    	Collections.sort(simb2);
//	    	Collections.sort(simb3);
	    	Collections.sort(simb4);
	    	
	    	getHash(simbolos, v,"TBBT");
	    	getHash(simbolos, v1,"DG");
	    	getHash(simbolos, v2,"GOT");
//	    	getHash(simbolos, v3,"GOT3");
	    	getHash(simbolos, v4,"SIM");
	    	getHash(simbolos, v5,"YB");
	    	
	    	for (Integer integer : a.keySet()) {
				System.out.println(integer + "-" + a.get(integer));
			}
	    	
	    	System.out.println("TBBT"+ v.size()+"Simb:"+simb.size());
	    	for (Integer integer : v) {
				System.out.print(integer);
				System.out.print(", ");
			}
	    	System.out.println();
	    	System.out.println("Damas Gratis"+v1.size()+"Simb:"+simb1.size());
	    	for (Integer integer : v1) {
	    		System.out.print(integer);
				System.out.print(", ");
			}
	    	System.out.println();
	    	System.out.println("GOT"+v2.size()+"Simb:"+simb2.size());
	    	for (Integer integer : v2) {
	    		System.out.print(integer);
				System.out.print(", ");
			}
	    	
	    	System.out.println();
	    	System.out.println("Juego De Capos"+v3.size()+"Simb:"+simb3.size());
	    	for (Integer integer : v3) {
	    		System.out.print(integer);
				System.out.print(", ");
			}
	    	
	    	System.out.println();
	    	System.out.println("SIM"+v4.size()+"Simb:"+simb4.size());
	    	for (Integer integer : v4) {
	    		System.out.print(integer);
				System.out.print(", ");
			}
	    	System.out.println();
	    	System.out.println("YB"+v5.size()+"Simb:"+simb5.size());
	    	for (Integer integer : v5) {
	    		System.out.print(integer);
				System.out.print(", ");
			}
	    	int i = simb.size() + simb2.size()+ simb1.size()+ simb4.size()+ simb5.size();
	    	System.out.println("Simbolos" + simbolos.size()+"Total:"+ i);
//	    	for (Integer integer : simbolos) {
//				System.out.println(integer);
//			}
//	        Sequence sequence = MidiSystem.getSequence(new File("big bang.mid"));
//
//	        int trackNumber = 0;
//	        for (Track track :  sequence.getTracks()) {
//	            trackNumber++;
//	            System.out.println("Track " + trackNumber + ": size = " + track.size());
//	            System.out.println();
//	            for (int i=0; i < track.size(); i++) { 
//	                MidiEvent event = track.get(i);
//	                System.out.print("@" + event.getTick() + " ");
//	                MidiMessage message = event.getMessage();
//	                if (message instanceof ShortMessage) {
//	                    ShortMessage sm = (ShortMessage) message;
//	                    System.out.print("Channel: " + sm.getChannel() + " ");
//	                    if (sm.getCommand() == NOTE_ON) {
//	                        int key = sm.getData1();
//	                        int octave = (key / 12)-1;
//	                        int note = key % 12;
//	                        String noteName = NOTE_NAMES[note];
//	                        int velocity = sm.getData2();
//	                        System.out.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);
//	                    } else if (sm.getCommand() == NOTE_OFF) {
//	                        int key = sm.getData1();
//	                        int octave = (key / 12)-1;
//	                        int note = key % 12;
//	                        String noteName = NOTE_NAMES[note];
//	                        int velocity = sm.getData2();
//	                        System.out.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);
//	                    } else {
//	                        System.out.println("Command:" + sm.getCommand());
//	                    }
//	                } else {
//	                    System.out.println("Other message: " + message.getClass());
//	                }
//	            }
//
//	            System.out.println();
//	        }

	    }
}
