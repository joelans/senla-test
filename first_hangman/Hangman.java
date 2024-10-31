package first_hangman;

import java.io.*;
import java.util.*;

public class Hangman {
  public static String[][] wordsForGuessing = {{"SUMMER", "It's a lovely time - sun, greenery and heat.", "******"}, 
    {"OCEAN", "I am greater than any sea.\n      I drive waves across the vastness.", "*****"},
    {"HORIZON", "You can see the edge, but you won’t get there.", "*******"}};
  public static void main(String[] args) throws IOException {
    System.out.println("To start the game press Y or press N to exit");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String answer = br.readLine().toUpperCase();
    while(answer.length() != 1 || (answer.charAt(0) != 'Y' && answer.charAt(0) != 'N')) {
      System.out.println("To start the game press Y or press N to exit");
      answer = br.readLine().toUpperCase();
    }
    int index;
    while(answer.charAt(0) == 'Y') {
      index = (int)(Math.random() * wordsForGuessing.length);
      game(br, wordsForGuessing[index]);
      System.out.println("To play the game again press Y or press N to exit");
      answer = br.readLine().toUpperCase();
      while(answer.length() != 1 || (answer.charAt(0) != 'Y' && answer.charAt(0) != 'N')) {
        System.out.println("To play the game again press Y or press N to exit");
        answer = br.readLine().toUpperCase();
      }
    }
  }

  public static void game(BufferedReader br, String[] wordToGuess) throws IOException {
    String[] outputWord = wordToGuess[2].split("");
    HashSet<Character> guessedLettersSet = new HashSet<>();
    HashMap<Character, List<Integer>> hm = new HashMap<>();
    for(int i = 0; i < wordToGuess[0].length(); i++) {
      if(hm.containsKey(wordToGuess[0].charAt(i))) {
        List<Integer> tmp = hm.get(wordToGuess[0].charAt(i));
        tmp.add(i);
        hm.put(wordToGuess[0].charAt(i), tmp);
      } else {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(i);
        hm.put(wordToGuess[0].charAt(i), tmp);
      }
    }
    System.out.println("Hint: " + wordToGuess[1] + "\n" + wordToGuess[2]);
    String answer;
    int unsuccessfulAttempts = 0;
    int guessedLetters = 0;
    System.out.println("Enter one letter of the English alphabet to guess the word");
    answer = br.readLine().toUpperCase();
    while(unsuccessfulAttempts < 6 && guessedLetters < wordToGuess[0].length()) {
      if(answer.length() != 1 || (answer.charAt(0) < 'A' && answer.charAt(0) > 'Z')) {
        System.out.println("Enter one letter of the English alphabet to guess the word");
        answer = br.readLine().toUpperCase();
      } else {
        if(hm.containsKey(answer.charAt(0))) {
          if(guessedLettersSet.contains(answer.charAt(0))) {
            System.out.println("The letter has already been entered earlier");
            answer = br.readLine().toUpperCase();
          } else {
            List<Integer> tmp = hm.get(answer.charAt(0));
            for(int i = 0; i < tmp.size(); i++) {
              outputWord[tmp.get(i)] = answer;
              guessedLetters++;
            }
            guessedLettersSet.add(answer.charAt(0));
            System.out.println("The entered letter is in the word\n" + String.join("", outputWord));
            System.out.println("Unsuccessful attempts left: " + (6 - unsuccessfulAttempts));
            if(guessedLetters < wordToGuess[0].length()) {
              answer = br.readLine().toUpperCase();
            }
          }
        } else {
          unsuccessfulAttempts++;
          System.out.println("There is no such letter in the word\n" + String.join("", outputWord));
          System.out.println("Unsuccessful attempts left: " + (6 - unsuccessfulAttempts));
          if(unsuccessfulAttempts < 6) {
            answer = br.readLine().toUpperCase();
          }
        }
      }
    }
    if(guessedLetters == wordToGuess[0].length()) {
      System.out.println("You guessed the word");
    }
    if(unsuccessfulAttempts == 6) {
      System.out.println("You didn't guess the word");
    }
  }
}