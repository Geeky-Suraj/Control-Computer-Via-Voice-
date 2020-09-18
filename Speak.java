/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcomputerviavoice;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

/**
 *
 * @author rahulchauhan
 */
public class Speak {
    public static Synthesizer synthesizer;
    static{
        try{
            // Set property as Kevin Dictionary 
            System.setProperty( "freetts.voices", 
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"); 
  
            // Register Engine 
            Central.registerEngineCentral( 
                "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral"); 
  
            // Create a Synthesizer 
            synthesizer= Central.createSynthesizer(new SynthesizerModeDesc(Locale.US)); 
  
            // Allocate synthesizer 
            synthesizer.allocate(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void startSpeak(String text){
        Runnable r=new Runnable(){
            public void run(){
                try{
                    // Speaks the given text, until the queue is empty.
                    Speak.synthesizer.speak(text, null);
                    //Speak.synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY); 
                }catch (Exception e) { 
                    e.printStackTrace();
                } 
            }
        };
        new Thread(r).start();
    }
}
