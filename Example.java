import it.uniroma1.lcl.babelnet.*;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.jlt.util.Language;
import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException {
        BabelNet bn = BabelNet.getInstance();
        for (BabelSynset synset : bn.getSynsets("home", Language.EN)) {
            System.out.println("Synset ID: " + synset.getId());
            System.out.println("Word: " + synset);
        }
    }
}