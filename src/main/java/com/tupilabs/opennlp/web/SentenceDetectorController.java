package com.tupilabs.opennlp.web;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SentenceDetectorController {

    @RequestMapping("/sentenceDetector")
    public String doNlp(@RequestParam String input, Model model) {
        String output = "Error";
        InputStream modelIn = null;
        try {
            modelIn = SentenceDetectorController.class.getResourceAsStream("/models/pt-sent.bin");
            SentenceModel sentenceModel = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = 
                    new SentenceDetectorME(sentenceModel);
            String sentences[] = sentenceDetector.sentDetect(input);
            output = "Sentences:\n";
            for (String sentence : sentences) {
                output += sentence + "\n";
            }
        } catch (Exception e) {
            output = e.getMessage();
        } finally {
            if (modelIn != null)
                try {
                    modelIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        model.addAttribute("input", input);
        model.addAttribute("output", output);
        return "nlp";
    }
    
}
