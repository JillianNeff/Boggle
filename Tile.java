
/** 
Represents one tile on the boggle board. Stores the letter showing, the row and the column
     where it is located on the board, and a flag to indicate if the tile has been selected by the user.
*/

public class Tile
{
   private char letter;
   private int row;
   private int column;
   private boolean selected;
   
   
   /** Creates a tile object with a letter, row and column.
   @param char letter - the letter facing up on the tile
   @param int row - the tiles' row number
   @param int column - the tiles' colummn number
   */
   public Tile(char letter,int row, int column)
   {
      this.letter=Character.toUpperCase(letter);
      this.row=row;
      this.column=column;
   }
   
    /** Creates a tile object with a letter, row and column.
   @param String letter - the letter facing up on the tile
   @param int row - the tiles' row number
   @param int column - the tiles' colummn number
   */

   public Tile(String letter,int row, int column)
   {
      if(letter.equalsIgnoreCase("qu"))
         this.letter='Q';
      else 
         this.letter=letter.toUpperCase().charAt(0);
      this.row=row;
      this.column=column;
   }
   
   public int getRow()
   {
      return row;
   }
   
   public int getColumn()
   {
      return column;
   }
   
   /** method toString creates and returns a String representation of the letter on the tile.
   @return a String representation of the letter on the tile object.
   */
   @Override
   public String toString()
   {
      if (this.letter=='Q')
         return "Qu";
      else
         return String.valueOf(letter);
   }
   
   /** method equals overides the default and 
   @param an object tested for equality.
   @return boolean value true if the Tile objects have the same letter, row and column,
            false otherwise.
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
      
      Tile otherTile = (Tile)other;
      
      if((this.row==otherTile.row)&& (this.column==otherTile.column) && (this.letter==otherTile.letter))
         return true;
      else
         return false;
    }

   

}   