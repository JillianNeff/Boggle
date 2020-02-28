import java.util.ArrayList;
/** 
Represents a word that was entered by the player. Stores the characters in the word and the points the word is worth.
*/

public class Word
{

   private String word;
   private int points;
   
   /**
   Constructor Creates a String object of the letters on the tiles selected and 
               determines the amount of points for the word.
   */
   public Word(ArrayList<Tile> tiles)
   {
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<tiles.size(); i++)
      {
         sb.append(tiles.get(i).toString().toUpperCase());
      }
      
      word=sb.toString();
      int l=word.length();
      
      if (l==3 || l==4)
         points = 1;
      else if (l==5)
         points = 2;
      else if (l==6)
         points = 3;
      else if (l==7)
         points = 5;
      else if (l>=8)
         points = 11;
   
   }
   
   /** getPoints returns the points for the word
   @return int value with number of points for the word. 
   */
   public int getPoints()
   {
      return points;
   }
   
   /** getWord returns the word from the tiles
   @return a String representation of the word formed by the tiles.
   */
   public String getWord()
   {
      return word;
   }
   
   @Override
   public String toString()
   {
      return word;
   }

   /** method equals tests if two words formed by tiles are equal.
   @return boolean true if the words are the same, false otherwise
   @param Object other an object being tested for equality with a Word object.
   */
   @Override
   public boolean equals(Object other)
   {
      if (other==null)
         return false;
      if (other==this)
         return true;
      if (this.getClass()!=other.getClass())
         return false;
      
      Word otherWord = (Word)other;
      
      return this.word.equals(otherWord.word);
    }
      
}
