
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
public class getSmileysForARFF { 
	
	

    public static void main(String args[]) throws FileNotFoundException {
      
    	Twokenize twokenize = new Twokenize(); 
    	
        //reading file line by line in Java using BufferedReader       
        FileInputStream fis=null, smileys=null, hashtagEmolex = null;
        //FileOutputStream out = null;
        PrintWriter out = new PrintWriter( "outputARFFSmileys.txt" );
        PrintWriter out2 = new PrintWriter( "discarded.txt" );
        PrintWriter out3 = new PrintWriter( "untranslated.txt" );
        PrintWriter out4 = new PrintWriter( "diffLang.txt" );
        //PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
        BufferedReader reader = null, readerSmileys = null, readerHashtagEmolex=null;
        int check, numberOfWords=0, discardedWords=0;
        try {
        	//out = new FileOutputStream("output.txt");
            fis = new FileInputStream("newInput.txt");
            //emolex = new FileInputStream("emolex.txt");
            hashtagEmolex = new FileInputStream("hashtagemolex_with_smiley.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
            //readerEmolex = new BufferedReader(new InputStreamReader(emolex));
            readerHashtagEmolex = new BufferedReader(new InputStreamReader(hashtagEmolex));
          
            System.out.println("Reading File line by line using BufferedReader");
          
            String line = reader.readLine();
            String lineSmiley;
            String lineHashtagEmolex;
            
            while(line != null){
            	
            	
                System.out.println("\n" + line);
                smileys = new FileInputStream("smileys.txt");
                readerSmileys = new BufferedReader(new InputStreamReader(smileys));
            	lineSmiley = readerSmileys.readLine();
            	int c = 141832;
            	while (lineSmiley != null) {
            	if (line.contains(lineSmiley)) {
                out.print(c+" 1, ");
            	}
            	
                c++;
            	
            	lineSmiley = readerSmileys.readLine();
            	}
            	out.print("\n");
                out2.print("\n");
                out3.print("\n");
                line = reader.readLine(); 
            
                
            }      
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                //readerEmolex.close();
                readerHashtagEmolex.close();
                fis.close();
                //emolex.close();
                hashtagEmolex.close();
               out.flush();
                out.close();
                out2.close();
                out3.close();
                out4.close();
            } catch (IOException ex) {
                Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Number of words = " + numberOfWords);
        System.out.println("Discarded words = " + discardedWords);
    } 
    
    
}