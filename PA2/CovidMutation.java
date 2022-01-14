/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 * Sources Used: "None"
 * This program helps us find the output of a genome sequence if we
 * did this k-reversing.
 * This program takes any string and integer k and reverse every
 * k-sized chunk of string.
 * The program takes the user input of the genome and k-value and
 * uses cases to determine the output of the reversed string.
 */
import java.util.Scanner;
public class CovidMutation {
  public static void main(String[]args){
    // Part 2 covid mutation
    Scanner genomeScanner = new Scanner(System.in);
    String genome = genomeScanner.nextLine();
    int kValue = genomeScanner.nextInt();
    // we get the input for our sequence and k-value
    StringBuilder reverseGenome = new StringBuilder();
    // create stringbuilder to easily reverse the entire
    // sequence if kvalue is <1
    String newString = "";
    String endString = "";
    //create empty strings to concat the end strings with
    //the reversed sequence
    if(kValue>=1){
      if(kValue>genome.length()){
        reverseGenome.append(genome);
        reverseGenome.reverse();
        System.out.println(reverseGenome);
    //print the whole reversed sequence when the
    // k-value is greater than sequence length
      }else if(kValue<=genome.length()&&genome.length()%kValue==0){
    //executes when the length of sequence is divisible by kvalue
    // loop thru string creating separation between groups of
    // chars in the sequence to reverse
        for(int i=0;i<genome.length();i++){
          if(i%kValue==0){
            for(int j=kValue-1;j>=0;j--){
              newString = newString+ genome.charAt(i+j);
            }
          }
        }
        System.out.println(newString);
    // executes when sequence is divisible by kvalue and leaves
    // a remainder
      }else if(kValue<=genome.length()&&genome.length()%kValue!=0){
        for(int i=0;i<genome.length()-genome.length()%kValue;i++){
    // removes remainder characters
          if(i%kValue==0){
            for(int j=kValue-1;j>=0;j--){
              newString = newString+ genome.charAt(i+j);
            }
          }
        }
    // reverses at every k index
        for(int k=0;k<genome.length()%kValue;k++){
          endString = endString+ genome.charAt(genome.length()-k-1);
        }
        System.out.println(newString+endString);
      }
    // adds reversed remainder characters to the reversed sequence
    // at every k index
    }else{
      System.out.println(genome);
    // print input if k is <1
    }
  }
}
