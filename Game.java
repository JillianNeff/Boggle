import java.util.ArrayList;

/** 
Represents a game of boggle
*/ 

public class Game 
{

   private Board board;
   private ArrayList<Tile> selected;
   private ArrayList<Word> words;
   private int score;
   private Dictionary dictionary;
   
   /** 
   Default constructor
   */
   public Game() 
   {
      board= new Board();
      selected = new ArrayList<Tile>();
      words = new ArrayList<Word>();
      score=0;
      dictionary= new Dictionary("dictionary.txt");
   }
   
   /** 
   isValidSelection method checks if a tile location is a valid tile seleection
   @param row - an integer representation of the row
   @param column - integer representation of the column
   @return boolean true if the location is a valid selection false otherwise.
   */
   public boolean isValidSelection(int row,int column)
   {
      if (selected.isEmpty())
         return true;
      
      Tile last=selected.get(selected.size()-1);
      if(selected.contains(board.getTile(row,column)))
         return false;
      else if (last.getRow()== row && ((last.getColumn()==column+1)||(last.getColumn()==column-1)))
         return true;
      else if (last.getColumn()== column &&(last.getRow()==row+1||last.getRow()==row-1))
         return true;
      else if(((last.getColumn()==column-1)||(last.getColumn()==column+1))&& ((last.getRow()==row+1)||(last.getRow()==row-1)))
         return true;
      else 
         return false;
   }
   
   /** 
   addToSelected method adds the tile at the given location to the selected tiles.
   @param row - an integer representation of the row
   @param column - integer representation of the column
   */
   public void addToSelected(int row, int column)
   {
      selected.add(board.getTile(row,column));
   }
   
   /** 
   removeFromSelected method removes the tile at the given location from the selected tiles.
   @param row - an integer representation of the row
   @param column - integer representation of the column
   */
   public void removeFromSelected(int row, int column)
   {
      selected.remove(board.getTile(row,column));
   }
   
   /** 
   getSelected method returns the selected tiles.
   @return an ArrayList of Tile objects that have been selected.
   */
   public ArrayList<Tile> getSelectedTiles()
   {
      return selected;
   }
   
   /**
   clearSelected method clears all selected tiles
   */
   public void clearSelected()
   {
      selected.clear();
   }
   /**
   testSelected method tests if the selected tiles form a valid word
   @return boolean true if valid word is formed false otherwise.
   */
   public boolean testSelected()
   {  
      if (dictionary.isValidWord(selected))
      {  
         Word w = new Word(selected);
         if (words.contains(w))
            return false;
         else
         {
            words.add(w);
            score+= w.getPoints();
            return true;
         }
      }
      else
         return false;
   }
   
   /**
   hasTile tests if a tile exists in the game board
   @param letter a character representation of the letter on the tile
   @param row an int representation of the tile's row
   @param column a int representation of the column of the tile
   @return boolean value true iof the game board contatins the tile at the given location, false otherwise
   */
   public boolean hasTile(char letter,int row, int column)
   {
      if( board.getTile(row,column).equals(new Tile(letter,row,column)))
         return true;
      else 
         return false;
   }
   /** getBoard returns the board in the game
   @return a board object representing the game tiles.
   */
   public Board getBoard()
   {
      return board;
   }
   
   /** getScore returns the points earned
   @return int representation of the points earned in the game
   */
   public int getScore()
   {
      return score;
   }
   
   /** getWords returns the words found
   @return An ArrayList of Word objects
   */
   public ArrayList<Word> getWords()
   {
      return words;
   }
   
   /** 
   toString returns a string representation of a Game object
   @return String representation of a game object
   */
   @Override
   public String toString()
   {
     return String.format("%s\n\nselected%s\n\nwords: %s\n\nscore: %d\n\n",board,selected,words,score);
   }
   
}