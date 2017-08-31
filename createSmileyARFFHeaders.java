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
public class createSmileyARFFHeaders { 
	
	

    public static void main(String args[]) throws FileNotFoundException {
      
    	 
    	
        
        PrintWriter out = new PrintWriter( "smileyHeader.arff" );
        int c = 1;
        
        while (c < 131) {
        	if (c < 10 ) { 
        	out.println("@attribute smiley00"+ c +" {0,1}" );
        	c++;
        }
        	else if (c < 100 ) { 
            	out.println("@attribute smiley0"+ c +" {0,1}" );
            	c++;
            }
        	else { 
            	out.println("@attribute smiley"+ c +" {0,1}" );
            	c++;
            }
        	
        }            
    out.close();
}
}
