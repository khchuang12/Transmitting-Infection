/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 * Sources Used: "None"
 * This program is used as an introduction to ciphers.
 * This program checks the validity of inputs if they are lower case and
 * satisfy the rest of the constraints. This program also encodes and decodes
 * characters using the caesar cipher methods, as well as encodes and decodes
 * strings using established keys in the vignere cipher methods.
 **/
import java.util.*;
/**
This cipher class contains all of the cipher and validity checking methods.
It contains the main methods and other methods to decode and encode both
characters and strings using caesar ciphers and vignere ciphers
**/
public class Cipher {
/**
main method which was used to test the methods.
**/
  public static void main(String[]args){
  }
  /**
  This isLowerCase method checks to see if the provided character is lowecase
  and not any special character
  **/
  public static boolean isLowerCase(char letter){
    int asciiLetter = (int) letter;
    int asciiSpace = (int)' ';
    int asciiA = (int)'a';
    int asciiZ = (int)'z';
  //Ensures the ascii value of the letter is between the ascii values of a &z
    if(asciiLetter>=asciiA && asciiLetter<=asciiZ){
      return true;
    }else{
      return false;
    }
  }
  /**
  This isLowerCase method checks to see if the provided string is lowercase
  and does not contain any special characters or upper case letters
  **/
  public static boolean isLowerCase(String str){
  // invalid input
    if(str==null){
      return false;
    }
  //loops through string using the isLowerCase method to check each character
  //if it is lowercase or not
    for(int i=0; i<str.length(); i++){
      if(isLowerCase(str.charAt(i))==false){
        return false;
      }
    }
    return true;
  }
  /**
  This caesarShiftEncode method takes in a character and encodes its value
  based on the key given. It is used to create secure messages.
  **/
  public static char caesarShiftEncode(char plaintext, char key){
    int asciiPlainText = (int) plaintext;
    int asciiKey = (int) key;
    int asciiA = (int)'a';
    int asciiZ = (int)'z';
  //ensures the character and key are both lowercase before executing
    if(isLowerCase(plaintext)==true&&isLowerCase(key)==true){
      int numInAlphabet = 26;
  // adds ascii value of plaintext values and key values finding the modulus
  // to get the encodingVal and using modulus to find the ascii value added to
  // the ascii value of A between a & z
      int encodingVal = (int) plaintext +(int) key;
      int remainderInt = (encodingVal%asciiA)%numInAlphabet;
      char plaintextChar = (char)(remainderInt+asciiA);
      return plaintextChar;
    }
    return plaintext;
  }
  /**
  This caesarShiftDecode method takes in a character and decodes its value
  based on the key given. It is used to decode messages that were encoded
  by the caesarShiftDecode method
  **/
  public static char caesarShiftDecode(char ciphertext, char key){
    int asciiCipherText = (int) ciphertext;
    int asciiKey = (int) key;
    int asciiA = (int)'a';
    int asciiZ = (int)'z';
    // ensures ciphertext and key characters are both lowercase
    if(isLowerCase(ciphertext)==true&&isLowerCase(key)==true){
      int numInAlphabet = 26;
      int difference = (int)ciphertext - (int)key;
    // determining if need to loop through the alphabet or introduction
    // loops through alphabet subtracting the difference
      if(difference<0){
        char cipherTextChar = (char)(numInAlphabet+difference+asciiA);
        return cipherTextChar;
      }
    // when key has a greater ascii value of ciphertext
      char cipherChar = (char)(difference + asciiA);
      return cipherChar;
    }
    return ciphertext;
  }
  /**
  This vigenereEncode method encodes a string based on a key given in the
  form of a string. It loops through each character in the string encoding
  each character value to a different one based upon the corresponding
  character in the key. If the key has less characters than the given
  plaintext, the key will repeat itself to fill the remaining indexes
  **/
  public static String vigenereEncode(String plaintext, String key){
    String ciphertext="";
  // checks to see if plaintext and key are both lowercase
  // makes sure key is not an empty string
    if(isLowerCase(plaintext)&& isLowerCase(key)&&key!=""){
  // loops through each character in the string using the caesarShiftEncode
  // to encode each character with the corresponding key value character
        for(int i=0;i<plaintext.length();i++){
          ciphertext= ciphertext +
          caesarShiftEncode(plaintext.charAt(i),key.charAt(i%key.length()));
        }
        return ciphertext;
    }
      return plaintext;
  }
  /**
  This vigenereDecode method decodes a string based on a key given in the
  form of a string. It loops through each character in the string decoding
  each character value to its plaintext char based upon the corresponding
  character in the key. If the key has less characters than the given
  ciphertext, the key will repeat itself to fill the remaining indexes
  **/
  public static String vigenereDecode(String ciphertext, String key){
    String plaintext="";
    // checks to see if ciphertext and key are both lowercase
    // makes sure key is not an empty string
    if(isLowerCase(ciphertext)&& isLowerCase(key)&&key!=""){
    // loops through each character in the string using the caesarShiftDecode
    // to encode each character with the corresponding key value character
        for(int i=0;i<ciphertext.length();i++){
          plaintext= plaintext +
          caesarShiftDecode(ciphertext.charAt(i),key.charAt(i%key.length()));
        }
        return plaintext;
    }
      return ciphertext;
  }
}
