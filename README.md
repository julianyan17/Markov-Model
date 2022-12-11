Author: Julian Yan  
Date: 1/26/2019  
Title: Markov Model  
Summary: This program uses the Markov Model to create text that is made of predictions based off an analysis of the original text. In order to make a prediction, it reads in a text file and stores every unique word or character and the number of occurences followed by a certain word in the file. The program will then attempt to reproduce the original text from a given word based off the data that was stored.  

Contents:

* MarkovModel.java
  * This file creates a class that will generate text based on the given text. It includes helper methods that come together to train the machine to predict words that should come after words or characters.
    * MarkovModel (int degree, boolean isWordModel)
    * void trainWordModel(String content)
    * void trainCharModel(String content)
    * trainFromText(String filename)
    * ArrayList<String> getFlattenedList(String prefix)
    * String generateNext(String prefix)
    * String generate(int count)
    * String toString()

* WordCount.java
  * This file includes a class that creates an WordCount object that stores a String from the text and int that is the number of occurences.
    * WordCount(String w)
    * String getWord()
    * int getCount()
    * void increment()
    * void setCount(int c)
    * String toString()

* WordCountList.java
  * This file creates a class that handles the list of WordCount objects. It includes methods to create a list, to add words into the list and to print out the list.
    * WordCountList()
    * ArrayList<WordCount> getList()
    * void add(String word)
    * String toString()

* Generator.java
  * This file includes a class that contains a main method that constructs, trains, and uses a Markov model.
  

