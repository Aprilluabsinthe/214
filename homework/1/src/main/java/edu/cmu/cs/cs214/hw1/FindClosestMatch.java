package edu.cmu.cs.cs214.hw1;

import java.io.IOException;

/**
 * Takes a list of URLs on the command line and prints the two URL whose web
 * pages have the highest cosine similarity. Prints a stack trace if any of the
 * URLs are invalid, or if an exception occurs while reading data from the URLs.
 */
public class FindClosestMatch {
  public static void main(String[] args) throws IOException {
    // Replace with your implementation:
    for (int i = 0; i < args.length; i++) {
      System.out.println(args[i]);
    }
  }
}