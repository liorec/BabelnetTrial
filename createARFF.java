import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSynsetID;
import it.uniroma1.lcl.babelnet.InvalidBabelSynsetIDException;
import it.uniroma1.lcl.babelnet.data.BabelAudio;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.data.BabelSensePhonetics;
import it.uniroma1.lcl.babelnet.data.BabelSenseSource;
import it.uniroma1.lcl.babelnet.data.BabelSenseTranslationInfo;
import it.uniroma1.lcl.jlt.util.Language;
//import translate.translate;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import cmu.arktweetnlp.*;

import com.google.cloud.RetryParams;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.common.collect.ImmutableList;

import java.lang.*;
/**
 * BufferedReader and Scanner can be used to read line by line from any File or
 * console in Java.
 * This Java program demonstrate line by line reading using BufferedReader in Java
 *
 * @author Javin Paul
 */
public class createARFF { 
	
	

    public static void main(String args[]) throws FileNotFoundException {
      
    	 
    	
        //reading file line by line in Java using BufferedReader       
        FileInputStream fis=null, emolex=null, hashtagEmolex = null;
        //FileOutputStream out = null;
        PrintWriter out = new PrintWriter( "test.arff" );
        
        //PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
        BufferedReader reader = null, readerEmolex = null;
        BabelNet bn = BabelNet.getInstance();
        int check, numberOfWords=0, discardedWords=0;
        try {
        	//out = new FileOutputStream("output.txt");
            fis = new FileInputStream("input2.txt");
            emolex = new FileInputStream("emolex.txt");
            hashtagEmolex = new FileInputStream("hashtagemolex.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
            readerEmolex = new BufferedReader(new InputStreamReader(emolex));
          
             System.out.println("Reading File line by line using BufferedReader");
          
            
            String lineEmolex;
            lineEmolex = readerEmolex.readLine();
            
            
            
                	
                	while (lineEmolex != null)
                	{
                	out.println("@attribute " +lineEmolex+"_anger {0,1}" );
                	out.println("@attribute " +lineEmolex+"_anticipation {0,1}" );
                	out.println("@attribute " +lineEmolex+"_disgust {0,1}" );
                	out.println("@attribute " +lineEmolex+"_fear {0,1}" );
                	out.println("@attribute " +lineEmolex+"_joy {0,1}" );
                	out.println("@attribute " +lineEmolex+"_negative {0,1}" );
                	out.println("@attribute " +lineEmolex+"_positive {0,1}" );
                	out.println("@attribute " +lineEmolex+"_sadness {0,1}" );
                	out.println("@attribute " +lineEmolex+"_surprise {0,1}" );
                	out.println("@attribute " +lineEmolex+"_trust {0,1}" );
                		 
                	
                           
                lineEmolex = readerEmolex.readLine(); 
            
            }      
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                
                fis.close();
                //emolex.close();
                hashtagEmolex.close();
               out.flush();
                out.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Number of words = " + numberOfWords);
        System.out.println("Discarded words = " + discardedWords);
    } 
    
    
}