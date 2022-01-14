/**
 * This code aims to get a simple analysis of the DNA strand and find what
 * bases are on the other strand of DNA.
 * We want to count the number of times the 'T' base appears on the DNA.
 * This program takes the input DNA and outputs the reversed DNA genome
 * as well as counts the number of times the 'T' base appears ont he DNA
 */

import java.util.Scanner;
public class CovidGenomeAnalysis {
  public static void main(String[] args){
    // Part 1 genome analysis
    Scanner input = new Scanner(System.in);
    String genome = input.nextLine();
    // get genome sequence input
    int numOfT=0;
    char[] chars = genome.toCharArray();
    // change the string input to an array of chars
    for (int i=0; i<chars.length ;i++) {
    // loop through chars changing each char to its opposite
      if(chars[i] == 'T'){
        chars[i] = 'A';
      }else if(chars[i] == 'A'){
        chars[i] = 'T';
      }else if(chars[i] == 'G'){
        chars[i] = 'C';
      }else if(chars[i] == 'C'){
        chars[i] = 'G';
      }else{
        return;
      }
    }
    for (int i=0; i<chars.length ;i++) {
    // loop through new sequence counting 'T'
      if(chars[i] == 'T'){
        numOfT=numOfT+1;
      }
    }
    String newGenome = new String(chars);
    // change char array to string
    System.out.println(numOfT+" "+newGenome);
    //print output
  }
}
