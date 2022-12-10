/*
 * File: WordCount.java
 * Creates an WordCount object that stores a String from the text and int that is the number of occurences.
 */

public class WordCount {
	private String word;
	private int count;

	/*
	 * WordCount constructor
	 * @param String w
	 * Take in a word and setup its count at 1
	 */
	public WordCount(String w) {
		word = w;
		count = 1;
	}

	/*
	 * getWord()
	 * @param none
	 * @return String
	 * getter for instance variable 'word'
	 */
	public String getWord() {
		return word;
	}

	/*
	 * getCount()
	 * @param none
	 * @return int - count value
	 * getter for instance variable 'count'
	 */
	public int getCount() {
		return count;
	}

	/*
	 * increment count value
	 * @param none
	 * @return void
	 */
	public void increment() {
		count++;
	}

	/*
	 * set count value to param c
	 * @param int c
	 * @return void
	 */
	public void setCount(int c) {
		count = c;
	}

	/*
	 * toString for WordCount object
	 * 'word: count' format
	 * @param none
	 * @return String - string format for the WordCount object
	 */
	public String toString() {
		return word + " : " + count;
	}
}