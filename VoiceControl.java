/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcomputerviavoice;



import java.net.URL;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class VoiceControl {

	public static void main(String[] args) {
            Speak.startSpeak("Welcome to voice control");
		  try {
            URL url = VoiceControl.class.getResource("stt.config.xml");
            
            System.out.println("Loading...");

            ConfigurationManager cm = new ConfigurationManager(url);

	    Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
	    Microphone microphone = (Microphone) cm.lookup("microphone");


            /* allocate the resource necessary for the recognizer */
            recognizer.allocate();

            /* the microphone will keep recording until the program exits */
	    if (microphone.startRecording()) {
		while (true) {
		    Result result = recognizer.recognize();
		    if (result != null) {
			String resultText = result.getBestFinalResultNoFiller();
			System.out.println("You said: " + resultText);
                        Runtime rs = Runtime.getRuntime();
                        if(resultText.contains("note pad")){
                            Speak.startSpeak("OK Opening Note pad");
                            rs.exec("notepad");
                        }else if(resultText.contains("calculator")){
                            Speak.startSpeak("OK Opening calculator");
                            rs.exec("calc");
                        }else if(resultText.contains("stop")){
                            Speak.startSpeak("bye bye see you");
                            Thread.sleep(2000);
                            System.exit(0);
                        }else if(resultText.contains("hello")){
                            Speak.startSpeak("Hi I am voice controller");
                        }
		    } else {
			System.out.println("I can't hear you.");
		    }
		}
	    } else {
		System.out.println("Cannot start microphone.");
		recognizer.deallocate();
		System.exit(1);
	    }
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}

}
