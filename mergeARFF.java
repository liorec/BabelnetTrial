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
public class mergeARFF { 
	
	

    public static void main(String args[]) throws FileNotFoundException {
      
    	
    	
        //reading file line by line in Java using BufferedReader       
        FileInputStream first=null, second=null, third=null;
        //FileOutputStream out = null;
        PrintWriter out = new PrintWriter( "features.arff" );
        
        
        //PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
        BufferedReader reader1 = null, reader2 = null, reader3=null;
        
           try {
        	//out = new FileOutputStream("output.txt");
            first = new FileInputStream("newEmoCount.arff");
            second = new FileInputStream("newFormat.arff");
            third = new FileInputStream("GoldStandardEmotions.txt");
            reader1 = new BufferedReader(new InputStreamReader(first));
            reader2 = new BufferedReader(new InputStreamReader(second));
            reader3 = new BufferedReader(new InputStreamReader(third));
          
             System.out.println("Reading File line by line using BufferedReader");
          
            
            String line1;
            String line2;
            String line3;
            line1 = reader1.readLine();
            line2 = reader2.readLine();
            line3 = reader3.readLine();
            
            while (line1 != null)
            {
            	out.println("{"+line1+line2+"141832 "+line3+"}");
            	line1 = reader1.readLine();
            	line2 = reader2.readLine();
            	line3 = reader3.readLine();
            	System.out.println("Still running...");
            }
                	
                	
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader1.close();
                reader2.close();
                first.close();
                //emolex.close();
                second.close();
               out.flush();
                out.close();
                
                
            } catch (IOException ex) {
                Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
           System.out.println("FIN");
}
    
}