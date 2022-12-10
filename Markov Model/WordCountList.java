/**
 * Author: Julian Wai San Yan
 * CSE8B Login: cs8bwapf
 * Email: jwyan@ucsd.edu
 * Date: 1/26/19
 * File: WordCountList.java
 * Sources of Help: CSE 8B Piazza, PSA7 Write Up, Discussion Slides, Lecture
 *                  Slides, Tutors
 */

/**
 * This file fulfills the requirements stated in Page 3 of the PSA7 Write Up 
 * which is to create a class that handles the list of WordCount objects. 
 * Included below are methods to create a list, to add words into the list and
 * to print out the list.
 */

import java.util.*;
import java.io.*;

/**
 * This class includes methods to create a WordCountList object, to add words
 * or characters to the list and to print out the words in a WordCountList
 * object. An important instance variable is an ArrayList of WordCount list 
 * objects which makes the WordCountList.
 */

public class WordCountList {

  ArrayList<WordCount> list;
   
  /**
   * Constructor that initializes the list
   *
   * @param  none
   * @return      WordCountList object created
   */

  public WordCountList() {
    this.list = new ArrayList<WordCount>();
  }

  /**
   * Getter that gets the list
   *
   * @param  none
   * @return      the list
   */

  public ArrayList<WordCount> getList(){
    return this.list;
  }
    
  /**
   * Looks for word in the list and increments the count if word exists in the
   * list, if not then adds the word into the list
   *
   * @param word the word to search for in the list
   * @return     void
   */

  public void add(String word) {
      if(word == null) {
          return;
      }
      // if list is empty, just add word
      if(list.isEmpty()) {
              WordCount newWord = new WordCount(word.toLowerCase());
              list.add(newWord);
              return;
      }
      // looks for word in list
      for(int i = 0; i < list.size(); i++) {
          if(list.get(i).getWord().equals(word.toLowerCase())) {
              list.get(i).increment();
              return;
          }
      }
      // adds word to list if not found
      WordCount newWord = new WordCount(word.toLowerCase());
      list.add(newWord);
  } 

  /**
   * Returns the String representation of a WordCountList object
   *
   * @param none
   * @return     a String representation of a WordCountList object
   */
  
  @Override
  public String toString() {
      String newString = "";
      for(int i = 0; i < this.list.size(); i++){
          newString += list.get(i).getWord() + "(" + 
              list.get(i).getCount() + ")";
          if(i != list.size() - 1) {
              newString = newString + " ";
          }
      }
      return newString;
  }
}
