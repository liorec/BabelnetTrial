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
public class createARFFData { 
	
	

    public static void main(String args[]) throws FileNotFoundException {
      
    	Twokenize twokenize = new Twokenize();
    	int repeat, att;
    	String emoCount;
    	
        //reading file line by line in Java using BufferedReader       
        FileInputStream fis=null, emolex=null, hashtagEmolex = null;
        //FileOutputStream out = null;
       // PrintWriter out = new PrintWriter( "testData1.arff" );
     //   PrintWriter out2 = new PrintWriter( "testDataEmoCount1.arff" );
        PrintWriter out3 = new PrintWriter( "newFormat.arff" );
        PrintWriter out4 = new PrintWriter( "newEmoCount.arff" );
        
        //PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
        BufferedReader reader = null, readerEmolex = null;
        BabelNet bn = BabelNet.getInstance();
        int check, numberOfWords=0, discardedWords=0, l=0;
        try {
        	//out = new FileOutputStream("output.txt");
            fis = new FileInputStream("output.txt");
            emolex = new FileInputStream("longEmolex.txt");
            hashtagEmolex = new FileInputStream("hashtagemolex.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
            readerEmolex = new BufferedReader(new InputStreamReader(emolex));
          
             System.out.println("Reading File line by line using BufferedReader");
          
            
            String lineEmolex;
            //lineEmolex = readerEmolex.readLine();
            String lineOutput;
            lineOutput = reader.readLine();
            
            
            while (lineOutput != null)
            {
            	emoCount="";
            	//out3.print("{");
            	att=11;
            	List<String> result = twokenize.tokenizeRawTweetText(lineOutput);
            	emolex = new FileInputStream("longEmolex.txt");
            	readerEmolex = new BufferedReader(new InputStreamReader(emolex));
            	lineEmolex = readerEmolex.readLine();
            	int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0;
            	/*if (lineOutput.length()==0)
            	{
            		out.print("\n");
            	    out2.print("\n");
            	}
            	else
            	{ */	
            	while (lineEmolex != null)
            	{
            		att++;
            		repeat = 0;
            		for (int x=0; x < result.size(); x++)
            		{
            			
            			System.out.println("a");
            		List<String> result2 = twokenize.tokenizeRawTweetText(lineEmolex);
            		
            		if (result2.get(0).equalsIgnoreCase(result.get(x)) && result2.get(2).equals("1"))
            		{
            			if (repeat == 0)
            			{
            	//		out.print("1");
            			out3.print(Integer.toString(att)+" 1, ");
            			repeat = 1;
            			}
            			switch (result2.get(1))
            			{
            			case "anger": a++;
            				break;
            			case "anticipation": b++;
        					break;
            			case "disgust": c++;
        					break;
            			case "fear": d++;
    						break;	
            			case "joy": e++;
    						break;
            			case "negative": f++;
    						break;	
            			case "positive": g++;
    						break;
            			case "sadness": h++;
    						break;	
            			case "surprise": i++;
    						break;
            			case "trust": j++;
    						break;	
            			}
            		}
            		}
            		
            		lineEmolex = readerEmolex.readLine();
            		if (lineEmolex != null)
            		{
   //         			out.print(",");
            			//out3.print(", ");
            		}
            	}
            	
        //    	out.print("\n");
            	out3.print("\n");
            	if (a != 0)
            	{
            		emoCount = emoCount + "2 " + Integer.toString(a) +", ";
            	}
            	if (b != 0)
            	{
            		emoCount = emoCount + "3 " + Integer.toString(b) +", ";
            	}
            	if (c != 0)
            	{
            		emoCount = emoCount + "4 " + Integer.toString(c) +", ";
            	}
            	if (d != 0)
            	{
            		emoCount = emoCount + "5 " + Integer.toString(d) +", ";
            	}
            	if (e != 0)
            	{
            		emoCount = emoCount + "6 " + Integer.toString(e) +", ";
            	}
            	if (f != 0)
            	{
            		emoCount = emoCount + "7 " + Integer.toString(f) +", ";
            	}
            	if (g != 0)
            	{
            		emoCount = emoCount + "8 " + Integer.toString(g) +", ";
            	}
            	if (h != 0)
            	{
            		emoCount = emoCount + "9 " + Integer.toString(h) +", ";
            	}
            	if (i != 0)
            	{
            		emoCount = emoCount + "10 " + Integer.toString(i) +", ";
            	}
            	if (j != 0)
            	{
            		emoCount = emoCount + "11 " + Integer.toString(j) +", ";
            	}
        //    	out2.println(Integer.toString(a)+"," + Integer.toString(b)+","+ Integer.toString(c)+","+ Integer.toString(d)+","+ Integer.toString(e)+","+ Integer.toString(f)+","+ Integer.toString(g)+","+ Integer.toString(h)+","+ Integer.toString(i)+","+ Integer.toString(j));
            	out4.println(emoCount);
            	//}
            	lineOutput = reader.readLine();
            }
                	
                	
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                emolex.close();
                fis.close();
                //emolex.close();
                hashtagEmolex.close();
         //      out.flush();
         //       out.close();
         //       out2.close();
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