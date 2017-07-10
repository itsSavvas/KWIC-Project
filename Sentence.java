import opennlp.tools.lemmatizer.Lemmatizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by timday on 7/8/17.
 */
public class Sentence {

    private String sentence;
    private String[] tokens;
    private String[] tags;
    private String[] lemmas;


    public Sentence(String sen) {
        this.sentence = sen;
        this.loadTokens();
        this.loadPOS();
        this.loadLemma();

    }

    public String getSentence() {
        return sentence;
    }
    public String[] getTokens() {
        return tokens;
    }
    public String[] getTags() {
        return tags;
    }
    public String[] getLemmas() {
        return lemmas;
    }


    private void loadTokens() {
        try {
            InputStream stream = new FileInputStream("en-token.bin");
            TokenizerModel model = new TokenizerModel(stream);
            Tokenizer tokenizer = new TokenizerME(model);
            tokens = tokenizer.tokenize(sentence); //ins var
            stream.close();

        } catch (Exception e) {
            System.out.println("Token Load Error");
        }
    }

    private void loadPOS() {
        try {
            InputStream stream = new FileInputStream("en-pos-maxent.bin");
            POSModel model = new POSModel(stream);
            POSTaggerME tagger = new POSTaggerME(model);
            tags = tagger.tag(tokens);
            stream.close();


        } catch (Exception e) {
            System.out.println("POS Tag Load Error");
        }
    }

    private void loadLemma() {
        try {
            InputStream stream = new FileInputStream("en-lemmatizer.dict");
            DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(stream);
            lemmas = lemmatizer.lemmatize(tokens, tags);
            stream.close();

        } catch (Exception e) {
            System.out.println("Lemma Load Error");
        }
    }




    public void printTokens() {
        for (String tok : tokens) {
            System.out.println(tok);
        }
    }

    public void printTags() {
        for (String tag : tags) {
            System.out.println(tag);
        }
    }

    public void printLemmas() {
        System.out.println(lemmas); //returns null
        for (String lem : lemmas) {
            System.out.println(lem);
        }
    }

    public void printTokensTagsLemmas() {
        for (int i=0; i<tokens.length; i++) {
            System.out.printf("TOKEN: %-9s TAG: %-4s LEMMA: %-8s\n", tokens[i], tags[i], lemmas[i]);
        }
    }



    public static void main(String[] args) {
        Sentence test = new Sentence("There isn't a higher mountain than Everest, that isn't submerged in the ocean.");
        test.printTokensTagsLemmas();

//        test.printTags();
//        test.printLemmas();

    }

}
