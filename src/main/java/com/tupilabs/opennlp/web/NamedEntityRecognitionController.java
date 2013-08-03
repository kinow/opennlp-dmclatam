package com.tupilabs.opennlp.web;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NamedEntityRecognitionController {

    @RequestMapping("/ner")
    public String doNlp(@RequestParam String input, Model model) {
        String output = "Error";
        InputStream modelIn = null;
        InputStream tokenizerIn = null;
        try {
            tokenizerIn = NamedEntityRecognitionController.class.getResourceAsStream("/models/pt-token.bin");
            TokenizerModel tokenizerModel = new TokenizerModel(tokenizerIn);
            Tokenizer tokenizer = new TokenizerME(tokenizerModel);
            
            modelIn = NamedEntityRecognitionController.class.getResourceAsStream("/models/pt-ner-person.bin");
            TokenNameFinderModel nerModel = new TokenNameFinderModel(modelIn);
            NameFinderME ner = new NameFinderME(nerModel);
            String[] tokens = tokenizer.tokenize(input);
            output = "Person names found:\n";
            Span nameSpans[] = ner.find(tokens);
            for (Span nameSpan : nameSpans) {
                int start = nameSpan.getStart();
                int end = nameSpan.getEnd();
                for (int i = start; i < end; ++i) {
                    output += tokens[i] + " ";
                }
            }
            ner.clearAdaptiveData();
        } catch (Exception e) {
            output = e.getMessage();
            e.printStackTrace();
        } finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (tokenizerIn != null) {
                try {
                    tokenizerIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        model.addAttribute("input", input);
        model.addAttribute("output", output);
        return "nlp";
    }
    
}
