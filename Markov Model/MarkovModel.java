/**
 * Author: Julian Wai San Yan
 * Date: 1/26/19
 * File: MarkovModel.java
 * Sources of Help: CSE 8B Piazza, PSA7 Write Up, Discussion Slides, Lecture
 *                  Slides, Tutors, String Documentation, ArrayList 
 *                  Documentation
 */

/**
 * This file fulfills the requirements stated in Page 4 of the PSA7 Write Up 
 * which is to create a class that will generate text based on the given text.
 * Included below are helper methods that come together to train the machine
 * to predict words that should come after words or characters.
 */


import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;
import java.util.Random;
import java.util.Scanner;

/**
 * This class included methods to train the machine to print out text similar
 * to the text that was inputted. In order to accurately generate text, helper
 * methods are used to break down the steps which allows for a differentiation
 * between generating text using words and chars. Important instance variables
 * is the HashMap predictionMap that stores all pairs of words and chars, the
 * degree which is the amount of words to use to predict text, a random to 
 * randomly choose a prediction and a boolean isWordModel to determine whether
 * it is a word model or char model.
 */

public class MarkovModel {

    protected HashMap<String, WordCountList> predictionMap;

    protected int degree;
    protected Random random;
    protected boolean isWordModel; 
    protected final static char DELIMITER = '\u0000';	

    private static final String SPACE_STRING = " ";
    private static final String COLON_STRING = ": ";
    private static final String NEWLINE_STRING = "\n";
        
    /**
     * Constructor that initalizes all instance variables 
     *
     * @param degree      degree of object
     * @param isWordModel whether the model will generate characters or words
     * @return            a MarkovModel object
     */

    public MarkovModel (int degree, boolean isWordModel) {
        this.degree = degree;
        this.isWordModel = isWordModel;
        this.random = new Random();
        this.predictionMap = new HashMap<String, WordCountList>();
    }
    
    /**
     * Matches prefixes and their prediction words 
     *
     * @param content file to read in to train 
     * @return        void
     */

    private void trainWordModel(String content) {
        Scanner scanner = new Scanner(content);
        ArrayList<String> contentList = new ArrayList<String>();

        // reads in content
        while(scanner.hasNext()) {
            contentList.add(scanner.next());
        }
        
        for(int i = 0; i < contentList.size() - degree; i++) {
            String prefix = new String();
            // creates prefixes depending on the degree
            for(int j = 0; j < this.degree; j++) {
                prefix += contentList.get(j+i);
                prefix += DELIMITER;
            }
            // if prefix is not in the prediction map, add it to the prediction
            // map
            if(!predictionMap.containsKey(prefix)){
                WordCountList newKey = new WordCountList();
                predictionMap.put(prefix, newKey);
                newKey.add(contentList.get(i + degree));
            }
            // adds prediction word to the corresponding prefix in the 
            // prediction map
            else {
                predictionMap.get(prefix).add(contentList.get(i + degree));
            }
        }
    }
    
    /**
     * Matches prefixes and their prediction chars
     *
     * @param content file to read in to train
     * @return        void
     */

    private void trainCharModel(String content) {
        ArrayList<String> contentList = new ArrayList<String>();

        // adds every char into array list
        for(int i = 0; i < content.length(); i++) {
            contentList.add(String.valueOf(content.charAt(i)));
        }

        for(int i = 0; i < contentList.size() - degree; i++) {
            String prefix = new String();
            // creates prefix depending on the degree
            for(int j = 0; j < this.degree; j++) {
                prefix += contentList.get(j+i);
                prefix += DELIMITER;
            }
            // if prefix is not in the prediction map, add it to the prediction
            // map            
            if(!predictionMap.containsKey(prefix)){
                WordCountList newKey = new WordCountList();
                predictionMap.put(prefix, newKey);
                newKey.add(contentList.get(i + degree));
            } 
            // adds prediction char to the corresponding prefix in the 
            // prediction map            
            else {
                predictionMap.get(prefix).add(contentList.get(i + degree));
            }
            
        }
    }

    /**
     * Trains text to predict words or chars
     *
     * @param String filename file to read
     * @return void
     */

    public void trainFromText(String filename) {

        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // `content` contains everything from the file, in one single string
        content = content.toLowerCase();
        System.out.println(content);
        Scanner scanner = new Scanner(content);
        
        if(isWordModel == true) {
            content = content.replaceAll("\n", "");
            // wrap the text
            for(int i = 0; i < this.degree; i++) {
                if(scanner.hasNext()) {
                    content += SPACE_STRING + scanner.next();
                    }
                    else {
                        scanner = new Scanner(content);
                    }
                }
            trainWordModel(content);
            System.out.println(content);
        }

        if(isWordModel == false) {
            // wrap the text
            ArrayList<String> contentList = new ArrayList<String>();            
            for(int i = 0; i < content.length(); i++) {
                contentList.add(String.valueOf(content.charAt(i)));   
            }
            String degreeWords = new String();
            for(int j = 0; j < this.degree; j++) {
                degreeWords += contentList.get(j);
            }
        content = content + degreeWords;
        trainCharModel(content);
        System.out.println(content);
        }
    }

    /**
     * Create a "flattened list" of predictions
     *
     * @param prefix prefix to associate with predictions
     * @return       list with predictions associated with prefix
     */

    public ArrayList<String> getFlattenedList(String prefix){
        ArrayList<String> flattenedList = new ArrayList<String>();
        // if prefix exists in prediction map
        if(predictionMap.containsKey(prefix)) {
            WordCountList wordCountList = predictionMap.get(prefix);
            ArrayList<WordCount> arrayList = wordCountList.getList();
            for(int i = 0; i < arrayList.size(); i++) {
                for(int j = 0; j < arrayList.get(i).getCount(); j++) {
                    // add the word to the array list depending on the count
                    // of the word
                    flattenedList.add(arrayList.get(i).getWord());
                }
            }           
        }
        return flattenedList;
    }

    /** 
     * Generate the next word depending on the prefix 
     *
     * @param prefix prefix to use to generate the next word
     * @return String      the next word after the prefix
     */

    public String generateNext(String prefix) {
        ArrayList<String> flattenedList = getFlattenedList(prefix);
        // generates a random word to follow the prefix
        System.out.println(flattenedList.size());
        int randomNum = random.nextInt(flattenedList.size());
        String nextString = flattenedList.get(randomNum);
        return nextString;
    }
    
    /**
     * Generates text randomly
     *
     * @param count number of words or chars to generate
     * @return      string of random words or chars
     */

    public String generate(int count) {
        ArrayList<String> finalText = new ArrayList<String>();
        ArrayList<String> keys = new ArrayList<String>(predictionMap.keySet());
        
        // create the initial prefix with its prediction
        int randomNum = random.nextInt(keys.size());
        String originalPrefix = keys.get(randomNum);
        originalPrefix += generateNext(originalPrefix); 
        // gets rid of all delimiters and separates each word or char
        String[] prefixArray = originalPrefix.split(String.valueOf(DELIMITER));

        // adds each word or char to string arraylist without delimiter
        for(int i = 0; i < prefixArray.length; i++) {
           finalText.add(prefixArray[i]);
        }
        
        // gets the other predictions based on the most recent words or chars
        for(int i = 1; i < count - this.degree; i++) {
            ArrayList<String> newPrefixList = new ArrayList<String>
                (finalText.subList(i, i + degree));
            String newPrefix = new String();
            for(int j = 0; j < newPrefixList.size(); j++) {
                newPrefix += newPrefixList.get(j);
                newPrefix += DELIMITER;
            }
            finalText.add(generateNext(newPrefix));
        }
        
        // puts all the generated text in a string
        String finalString = new String();
        for(int i = 0; i < finalText.size(); i++) {
            finalString += finalText.get(i);
                if(isWordModel == true) {
                    finalString += SPACE_STRING;
                }
        }

        // trims the last space for words
        if(isWordModel == true) {
            finalString = finalString.trim();
        }
        return finalString;
    }


    /**
     * Returns the String representation of a MarkovModel object
     *
     * @param none
     * @return     a String representation of a MarkovModel object
     */

    @Override 
    public String toString(){
        String newString = new String();
        // gets the key and value pairs
        for(String key : predictionMap.keySet()) {
            newString += key + COLON_STRING + predictionMap.get(key) + 
                NEWLINE_STRING;
        }
        // replaces all delimiters with spaces
        newString = newString.replaceAll(String.valueOf(DELIMITER), 
                SPACE_STRING);
        return newString;
    }
}
