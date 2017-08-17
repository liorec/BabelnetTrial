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
public class Test4 { 
	
	public static String translateTextWithOptions(
		      String sourceText,
		      String sourceLang,
		      String targetLang
		      ) {

		    Translate translate = createTranslateService();
		    TranslateOption srcLang = TranslateOption.sourceLanguage(sourceLang);
		    TranslateOption tgtLang = TranslateOption.targetLanguage(targetLang);

		    Translation translation = translate.translate(sourceText, srcLang, tgtLang);
		    //out.printf("Source Text:\n\tLang: %s, Text: %s\n", sourceLang, sourceText);
		    //out.printf("Translated Text:\n\tLang: %s, Text: %s\n", targetLang,
		      return  translation.getTranslatedText();
		  }
	
	
	public static String detectLanguage(String sourceText) {
	    Translate translate = createTranslateService();
	    String lang = null;
	    List<Detection> detections = translate.detect(ImmutableList.of(sourceText));
	    //System.out.println("Language(s) detected:");
	    for (Detection detection : detections) {
	      
	      lang = detection.getLanguage();
	      
	    }
	    return lang;
	  }
	
	public static Translate createTranslateService() {
	    TranslateOptions translateOption = TranslateOptions.newBuilder()
	        .setRetryParams(retryParams())
	        .setConnectTimeout(60000)
	        .setReadTimeout(60000)
	        .build();
	    return translateOption.getService();
	  }
	
	private static RetryParams retryParams() {
	    return RetryParams.newBuilder()
	        .setRetryMaxAttempts(3)
	        .setMaxRetryDelayMillis(30000)
	        .setTotalRetryPeriodMillis(120000)
	        .setInitialRetryDelayMillis(250)
	        .build();
	  }

    public static void main(String args[]) throws FileNotFoundException {
      
    	Twokenize twokenize = new Twokenize(); 
    	
        //reading file line by line in Java using BufferedReader       
        FileInputStream fis=null, emolex=null, hashtagEmolex = null;
        //FileOutputStream out = null;
        PrintWriter out = new PrintWriter( "output_with_smileys.txt" );
        PrintWriter out2 = new PrintWriter( "discarded.txt" );
        PrintWriter out3 = new PrintWriter( "untranslated.txt" );
        PrintWriter out4 = new PrintWriter( "diffLang.txt" );
        //PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
        BufferedReader reader = null, readerEmolex = null, readerHashtagEmolex=null;
        BabelNet bn = BabelNet.getInstance();
        int check, numberOfWords=0, discardedWords=0;
        try {
        	//out = new FileOutputStream("output.txt");
            fis = new FileInputStream("input2.txt");
            //emolex = new FileInputStream("emolex.txt");
            hashtagEmolex = new FileInputStream("hashtagemolex_with_smiley.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
            //readerEmolex = new BufferedReader(new InputStreamReader(emolex));
            readerHashtagEmolex = new BufferedReader(new InputStreamReader(hashtagEmolex));
          
            System.out.println("Reading File line by line using BufferedReader");
          
            String line = reader.readLine();
            String lineEmolex;
            String lineHashtagEmolex;
            
            while(line != null){
            	
            	
                System.out.println("\n" + line);
                List<String> result = twokenize.tokenizeRawTweetText(line);
                //String[] result = line.split("\\s");
                for (int x=0; x <result.size(); x++) {
                	
                
                	
					numberOfWords++;
                	//System.out.println(translatedText);
                	String translatedText = null;
                	emolex = new FileInputStream("emolex.txt");
                	hashtagEmolex = new FileInputStream("hashtagemolex.txt");
                	readerEmolex = new BufferedReader(new InputStreamReader(emolex));
                	lineEmolex = readerEmolex.readLine();
                	readerHashtagEmolex = new BufferedReader(new InputStreamReader(hashtagEmolex));
                	lineHashtagEmolex = readerHashtagEmolex.readLine();
                	System.out.println("\n");
                	System.out.println("result = "+result.get(x));
                	System.out.println("emolex = "+lineEmolex);
                	
                	String detectedLang = Test4.detectLanguage(result.get(x));
                	System.out.println("Detected Languge = " + detectedLang);
                	
                	if (!detectedLang.equalsIgnoreCase("en") && !detectedLang.equalsIgnoreCase("tl") && !detectedLang.equalsIgnoreCase("und"))
                	{
                		out4.println("Lang: " + detectedLang + " Word: " +result.get(x));
                		System.out.println("not english nor tagalog!");
                	}
                	
                	if (!detectedLang.equalsIgnoreCase("und") && (!detectedLang.equalsIgnoreCase("en")))
                	{
                		System.out.println("translating...");
                		translatedText = Test4.translateTextWithOptions(result.get(x), detectedLang, "en");
                		if (translatedText.equalsIgnoreCase(result.get(x)))
                				{
                					out3.print(result.get(x)+" ");
                					System.out.println("untranslated");
                				}
                		System.out.println(translatedText);
                	}
                	else
                	{
                		translatedText = result.get(x);
                	}
                	
                	check = 0;
                	
                	while ((lineEmolex != null) && (check != 1))
                	{
                		
                		System.out.println("loop " + lineEmolex);
                		if (translatedText.equalsIgnoreCase(lineEmolex))
                		{
                			System.out.println("match");
                			out.print(translatedText + " ");
                			check = 1;
                			lineEmolex = readerEmolex.readLine();
                			
                			                		}
                		
                			
                		lineEmolex = readerEmolex.readLine();
                	} 
                	System.out.println(check + " " + lineHashtagEmolex);
                	
                	/* if (check == 0){
                		while ((lineHashtagEmolex != null) && (check != 1))
                    	{
                    		
                    		System.out.println("loop 2 " + lineHashtagEmolex);
                    		if (translatedText.equalsIgnoreCase(lineHashtagEmolex))
                    		{
                    			System.out.println("match");
                    			out.print(translatedText + " ");
                    			check = 1;
                    			lineHashtagEmolex = readerHashtagEmolex.readLine();
                    			
                    			                		}
                    		
                    			
                    		lineHashtagEmolex = readerHashtagEmolex.readLine();
                    	} 
                		
                	} */
                	
                	if (check == 0)
                	{
                		out2.print(translatedText + " ");
                		discardedWords++;
                	}
                	
                	
               /*   for (BabelSynset synset : bn.getSynsets(result[x], Language.EN, Arrays.asList( Language.TL))) {
                	BabelSense bs = synset.getMainSense(Language.TL);
                    String lang = bs.getLanguage().toString();
                    if (lang == "TL") {
                    String lemma = bs.getLemma();
                    System.out.println("Lemma: " + lemma);
                    BabelSenseTranslationInfo info =bs.getSenseTranslationInfo();
                    System.out.println("Translation Info: " + info);
                    }                    
                } */
                	
                	    
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