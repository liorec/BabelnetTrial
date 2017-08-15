import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSynsetID;
import it.uniroma1.lcl.babelnet.InvalidBabelSynsetIDException;
import it.uniroma1.lcl.babelnet.data.BabelAudio;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.data.BabelSensePhonetics;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InvalidBabelSynsetIDException {
        BabelNet bn = BabelNet.getInstance();
        for (BabelSense sense : bn.getSynset(new BabelSynsetID("bn:00000356n"))) {
            System.out.println("Sense: " + sense.getLemma()
                            + "\tLanguage: " + sense.getLanguage().toString()
                            + "\tSource: " + sense.getSource().toString());
            BabelSensePhonetics phonetic = sense.getPronunciations();
            for (BabelAudio audio : phonetic.getAudioItems()) {
                System.out.println("Audio URL " + audio.getValidatedUrl());
            }
        }
    }
}