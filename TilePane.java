import javafx.scene.layout.HBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.geometry.Pos;

/** TilePane
*/
public class TilePane extends HBox
{
   private Tile tile;
   
   /** TilePane constructor creates an instance of a TilePane
   @param Tile t- a boggle tile
   */
   public TilePane(Tile t)
   {
      super();
      tile=t;
      this.setAlignment(Pos.CENTER);
      this.setPrefSize(100,100);
      this.setStyle("-fx-background-color: mintcream;" + "-fx-bordercolor: black;");
      Text letter= new Text(t.toString());
      letter.setFont(new Font(30));
      letter.setStroke(Color.INDIGO);
      letter.setStrokeWidth(2);
      this.getChildren().add(letter);
   }
   /** TilePane constructor creates an instance of a tile pane
   @param A Board object
   */
   public TilePane(Board b, int row, int col)
   {
      this(b.getTile(row,col));
   }
      
   
   /** method setSelected changes the appearance of the pane
   */
   public void setSelected()
   {
      this.setStyle("-fx-background-color: lightblue;"+"-fx-border-width:5;"+"-fx-border-color: gold;");
   }
   
   /** method setUnselected changes the appearance to starting colors.
   */
   public void setUnselected()
   {
      this.setStyle("-fx-background-color: mintcream;" +"-fx-bordercolor: black;");
   }
   
   public Tile getTile()
   {
      return tile;
   }
   
   public int getRow()
   {
      return tile.getRow();
   }
   
   public int getCol()
   {
      return tile.getColumn();
   } 
}
   