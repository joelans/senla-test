package third_password_generator;

import java.io.*;
import java.util.*;

public class Generator {
  public static char[][] characters = {
    {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'},
    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
    {'!', '~', '#', '@', '&', '*', '$', '%', '_', '-', '+'},
    {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}};
  public static void main(String[] args) throws IOException {
    StringBuilder sb;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String answer = "Y";
    while(answer.charAt(0) == 'Y') {
      sb = new StringBuilder();
      System.out.println("To generate a password, enter its length. The length can be selected from 8 to 12 characters");
      answer = br.readLine();
      while(isInteger(answer) == false || (isInteger(answer) == true && (Integer.parseInt(answer) < 8 || Integer.parseInt(answer) > 12))) {
        System.out.println("The length can be selected in the range from 8 to 12 characters");
        answer = br.readLine();
      }
      int choosedLength = Integer.parseInt(answer);
      int countTypes = 0;
      HashSet<Integer> containsType = new HashSet<>(); 
      for(int i = 0; i < choosedLength; i++) {
        int index = (int)(Math.random() * characters.length);
        if(!containsType.contains(index)) {
          countTypes++;
          containsType.add(index);
        } else if(4 - countTypes == choosedLength - i) {
          while(containsType.contains(index)) {
            index = (int)(Math.random() * characters.length);
          }
          countTypes++;
          containsType.add(index);
        }
        sb.append(characters[index][(int)(Math.random() * characters[index].length)]);
      }
      System.out.println("Your new generated password " + sb.toString());
      System.out.println("Press the Y key to generate password again or any other key on the keyboard to finish the program execution");
      answer = br.readLine().toUpperCase();
    }
  }

  public static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch(Exception e) {
      return false;
    }
    return true;
  }
}
