import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
An ArrayList of Strings containing the words in a dictionary.
*/

public class Dictionary
{ 
   private ArrayList<String> dictionary;
   
   /** 
   Constructor creates an ArrayList of String objects from the file.
   @param A String filename holding the name of the file of words.
   */
   public Dictionary(String filename)
   {
      Scanner infile = null;      
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("Error opening file");
      }
      finally
      {
      }
      
      
      
      dictionary = new ArrayList<String>();
   
      for(int i=0;infile.hasNext();i++)
      {
         String line = infile.nextLine();
         dictionary.add(i,line.toUpperCase());
      }
      
      infile.close();
   }
   
   /**
   isValidWord checks if the letter from tiles in order form a word.
   @param an ArrayList composed of Tile objects in order selected.
   @return boolean returns true if the tiles form a word, returns false otherwise.
   */
   public boolean isValidWord(ArrayList<Tile> tiles)
   { 
      int s = tiles.size();
      if(s < 3)
         return false;
         
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<tiles.size(); i++)
      {
         sb.append(tiles.get(i).toString().toUpperCase());
      }
      
      String word=sb.toString();
      
      if (dictionary.contains(word))
         return true;
      else
         return false;
   }   
         
}