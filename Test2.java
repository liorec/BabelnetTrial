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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.lang.*;
/**
 * BufferedReader and Scanner can be used to read line by line from any File or
 * console in Java.
 * This Java program demonstrate line by line reading using BufferedReader in Java
 *
 * @author Javin Paul
 */
public class Test2 {   

    public static void main(String args[]) {
      
        //reading file line by line in Java using BufferedReader       
        FileInputStream fis = null;
        BufferedReader reader = null;
        BabelNet bn = BabelNet.getInstance();
        try {
            fis = new FileInputStream("input.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
            System.out.println("Reading File line by line using BufferedReader");
          
            String line = reader.readLine();
            while(line != null){
                System.out.println("\n" + line);
                for (BabelSynset synset : bn.getSynsets(line, Language.EN, Arrays.asList( Language.TL))) {
                	BabelSense bs = synset.getMainSense(Language.TL);
                    String lang = bs.getLanguage().toString();
                    if (lang == "TL") {
                    String lemma = bs.getLemma();
                    System.out.println("Lemma: " + lemma);
                    BabelSenseTranslationInfo info =bs.getSenseTranslationInfo();
                    System.out.println("Translation Info: " + info);
                    }                    
                }
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  } 
}