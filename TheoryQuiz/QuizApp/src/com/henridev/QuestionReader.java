package com.henridev;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * QuestionReader class that will read a .JSON file and return an ArrayList of Question(s) to use during the quiz.
 */
public class QuestionReader {
    /**
     * Function to read a json file of questions and return an ArrayList of them.
     * @param fname name of file to pass.
     * @return an Arraylist of Question objects.
     */
    public ArrayList<Question> readFromJSON(String fname) {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            FileReader reader = new FileReader(new File(fname));
            JSONParser parser = new JSONParser();
            JSONObject all = (JSONObject) parser.parse(reader);
            JSONArray arr = (JSONArray) all.get("questions");
            Iterator itr = arr.iterator();
            JSONObject articleObject;
            String question, a, b, c, d,answer;
            while (itr.hasNext()) {
                articleObject = (JSONObject) itr.next();
                question = articleObject.get("question").toString();
                a = "a. " + articleObject.get("a").toString();
                b = "b. " + articleObject.get("b").toString();
                c = "c. " + articleObject.get("c").toString();
                d = "d. " + articleObject.get("d").toString();
                answer = articleObject.get("answer").toString();
                questions.add(new Question(question, a, b, c, d, answer));
            }
            reader.close(); // Close file
            return questions;
        } catch (Exception ex) {
            return null;
        }
    }
}
