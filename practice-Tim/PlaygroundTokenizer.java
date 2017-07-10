/**
 * Created by timday on 7/8/17.
 */

import opennlp.tools.tokenize.*;
import java.io.*;

public class PlaygroundTokenizer {

    String[] tokens;

    public void load(){

        try {
            InputStream modelIn = new FileInputStream("en-token.bin");
            TokenizerModel model = new TokenizerModel(modelIn);

            Tokenizer tokenizer = new TokenizerME(model);
            tokens = tokenizer.tokenize("An input sample sentence.");
            // "An", "input", "sample", "sentence", "."
            System.out.println(tokens[0]);

            modelIn.close();

        } catch (Exception e) {
            System.out.println("error error");
        }
    }

    public void print() {

        for (String token : tokens) {
            System.out.println(token);
        }
    }

    public static void main(String[] args) {

        PlaygroundTokenizer test = new PlaygroundTokenizer();
        test.load();
        test.print();
    }

}

