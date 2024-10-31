package second_exchange;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Exchange {
  public static double[][] exchangeRate = {{1.0, 13.619896, 96.913486, 105.337452, 125.821642},
      {0.073414, 1.0, 7.115366, 7.733735, 9.237771},
      {0.010318, 0.140544, 1.0, 1.086995, 1.298487},
      {0.009492, 0.129298, 0.919981, 1.0, 1.194589},
      {0.007947, 0.108246, 0.770197, 0.837121, 1.0}};
  public static String[] currencyCode = {"RUB", "CNY", "USD", "EUR", "GBP"};
  public static HashMap<String, Integer> hm;
  public static void main(String[] args) throws IOException {
    hm = new HashMap<>();
    hm.put("RUB", 0);
    hm.put("CNY", 1);
    hm.put("USD", 2);
    hm.put("EUR", 3);
    hm.put("GBP", 4);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String answer = "Y";
    while(answer.charAt(0) == 'Y') {
      System.out.println("What currency do you want to convert? \nAvailable currency:");
      System.out.println("RUB - Russian Ruble\nCNY - Chinese Yuan Renminbi\nUSD - US Dollar\nEUR - Euro\nGBP - British Pound");
      System.out.println("Enter currency code");
      answer = br.readLine().toUpperCase();
      while(answer.length() != 3 || !hm.containsKey(answer)) {
        System.out.println("Enter correct currency code");
        answer = br.readLine().toUpperCase();
      }
      String currencyCodeFrom = answer;
      int indexFrom = hm.get(answer);
      System.out.println("What currency would you like to convert your amount to?");
      System.out.println("Enter currency code");
      answer = br.readLine().toUpperCase();
      while(answer.length() != 3 || !hm.containsKey(answer)) {
        System.out.println("Enter correct currency code");
        answer = br.readLine().toUpperCase();
      }
      String currencyCodeTo = answer;
      int indexTo = hm.get(answer);
      System.out.println("Current exchange rate: 1 " + currencyCodeFrom + " equals " + exchangeRate[indexTo][indexFrom] + " " + currencyCodeTo
        + " or 1 " + currencyCodeTo + " equals " + exchangeRate[indexFrom][indexTo] + " " + currencyCodeFrom);
      System.out.println("Do you want to change this exchange rate? Press Y to change or press N to continue the operation with the already set rate");
      answer = br.readLine().toUpperCase();
      while(answer.length() != 1 || (answer.charAt(0) != 'Y' && answer.charAt(0) != 'N')) {
        System.out.println("press Y or press N");
        answer = br.readLine().toUpperCase();
      }
      if(answer.charAt(0) == 'Y') {
        System.out.println("Enter what 1 " + currencyCodeFrom + " is equal to " + currencyCodeTo);
        answer = br.readLine();
        while(isDouble(answer) == false || (isDouble(answer) == true && Double.parseDouble(answer) < 0)) {
          System.out.println("The amount must consist of positive numbers and with/without a dot or the number entered was too large");
          answer = br.readLine();
        }
        exchangeRate[indexTo][indexFrom] = Double.parseDouble(answer);
      }
      System.out.println("Enter the amount you want to convert");
      answer = br.readLine();
      double amount;
      while(isDouble(answer) == false || (isDouble(answer) == true && Double.parseDouble(answer) < 0)) {
        System.out.println("The amount must consist of positive numbers and with/without a dot or the number entered was too large");
        answer = br.readLine();
      }
      amount = Double.parseDouble(answer);
      double result = amount * exchangeRate[indexTo][indexFrom];
      System.out.println(amount + " " + currencyCodeFrom + " equals " + new DecimalFormat("#0.000000").format(result) + " " + currencyCodeTo);
      System.out.println("Same amount in other currencies:");
      for(int i = 0; i < exchangeRate.length; i++) {
        if(i != indexTo && i != indexFrom) {
          System.out.println(new DecimalFormat("#0.000000").format(amount * exchangeRate[i][indexFrom]) + " " + currencyCode[i]);
        }
      }
      System.out.println("Press the Y key to continue converting currency or any other key on the keyboard to finish the program execution");
      answer = br.readLine().toUpperCase();
    }
  }

  public static boolean isDouble(String s) {
    try {
      Double.parseDouble(s);
    } catch(Exception e) {
      return false;
    }
    return true;
  }
}
