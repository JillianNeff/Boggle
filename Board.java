import java.util.ArrayList;
import java.util.Random;

/** 
Represents the playing board and the letters showing on the board.
*/

public class Board
{
   private ArrayList<ArrayList<Tile>> board;

   public Board()
   {
      ArrayList<String> dice = new ArrayList<String>(16);
      dice.add(0,"RIFOBX");
      dice.add(1,"IFEHEY");
      dice.add(2,"DENOWS"); 
      dice.add(3,"UTOKND");
      dice.add(4,"HMSRAO");
      dice.add(5,"LUPETS");
      dice.add(6,"ACITOA");
      dice.add(7,"YLGKUE");
      dice.add(8,"QBMJOA");
      dice.add(9,"EHISPN");
      dice.add(10,"VETIGN");
      dice.add(11,"BALIYT");
      dice.add(12,"EZAVND");
      dice.add(13,"RALESC");
      dice.add(14,"UWILRG");
      dice.add(15,"PACEMD");
      
      Random r = new Random();
            
      ArrayList<Tile> row0 = new ArrayList<>(4);
      ArrayList<Tile> row1 = new ArrayList<>(4);
      ArrayList<Tile> row2 = new ArrayList<>(4);
      ArrayList<Tile> row3 = new ArrayList<>(4);
      
      for(int i=0;i<4;i++)
      {
         int dienum=r.nextInt(dice.size());
         int sidenum=r.nextInt(6);
      
         row0.add(new Tile(dice.get(dienum).charAt(sidenum),0,i));
         
         dice.remove(dienum);
      }
      
      for(int i=0;i<4;i++)
      {
         int dienum=r.nextInt(dice.size());
         int sidenum=r.nextInt(6);
      
         row1.add(new Tile(dice.get(dienum).charAt(sidenum),1,i));
         
         dice.remove(dienum);
      }
      
      for(int i=0;i<4;i++)
      {
         int dienum=r.nextInt(dice.size());
         int sidenum=r.nextInt(6);
      
         row2.add(new Tile(dice.get(dienum).charAt(sidenum),2,i));
         
         dice.remove(dienum);
      }
      
      for(int i=0;i<4;i++)
      {
         int dienum=r.nextInt(dice.size());
         int sidenum=r.nextInt(6);
      
         row3.add(new Tile(dice.get(dienum).charAt(sidenum),3,i));
         
         dice.remove(dienum);
      }

      board = new ArrayList<ArrayList<Tile>>(4);
      board.add(0,row0);
      board.add(1,row1);
      board.add(2,row2);
      board.add(3,row3);

   }
   /** method getBoard()
   @return an ArrayList of ArrayLists of tile objecvts representing the board.
   */
   public ArrayList<ArrayList<Tile>> getBoard()
   {
      return board;
   }
   /** method getTile returns
   @param int row the row of the tile on the board
   @param int col the column of the tile on the board
   @return Tile object representing the tile given the row and column
   */
   public Tile getTile(int row, int col)
   {
         return board.get(row).get(col);
   }
   
   /** method toString returns a String representation of a Board.
   @return String representation of a Board object.
   */
   @Override
   public String toString()
   {
      String s0,s1,s2,s3;
      
      s0=String.format("%-3s%-3s%-3s%-3s",board.get(0).get(0).toString(),board.get(0).get(1).toString(),board.get(0).get(2).toString(),board.get(0).get(3).toString());
      s1=String.format("%-3s%-3s%-3s%-3s",board.get(1).get(0).toString(),board.get(1).get(1).toString(),board.get(1).get(2).toString(),board.get(1).get(3).toString());
      s2=String.format("%-3s%-3s%-3s%-3s",board.get(2).get(0).toString(),board.get(2).get(1).toString(),board.get(2).get(2).toString(),board.get(2).get(3).toString());
      s3=String.format("%-3s%-3s%-3s%-3s",board.get(3).get(0).toString(),board.get(3).get(1).toString(),board.get(3).get(2).toString(),board.get(3).get(3).toString());
      
      String s= (s0+"\n"+ s1+"\n"+s2+"\n"+s3);
      return s;
    }  
      
}   
    