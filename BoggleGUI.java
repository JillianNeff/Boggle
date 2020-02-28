/** Boggle Game GUI
Creates interactive display for boggle game. 
CS 110
Created by: Jillian Neff
*/



import javafx.application.Application;
import javafx.application.Platform; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.scene.control.TextField;


public class BoggleGUI extends Application 
{
   private Game game; // The game
   private GridPane grid; // the board of squares
   private BorderPane main; // main layout  
   private VBox textArea, wordsFound; // Words found 
   private Text status,points;
   
   private HBox buttonPane; // Pane that holds the buttons 
   private Button end, newGame, test; // end game, new game and test Buttons
   private VBox extra;
   private HBox timer;
   private Arc timeArc;
   private Timeline timelineDisplay;
   private double arcLength = 2;
   private TextField search;
   
   @Override
   public void start(Stage primaryStage)throws Exception
   {
      // Set up panes.
      main = new BorderPane();
      extra = new VBox(30);
      extra.setStyle("-fx-background-color: palevioletred");
      extra.setMinWidth(120);
      main.setLeft(extra);
      
      grid = new GridPane();
      grid.setStyle("-fx-background-color: lightgrey");
      grid.setPadding(new Insets(10,10,10,10));
      
      // textArea set up
      textArea = new VBox(10);
      textArea.setStyle("-fx-background-color: lavenderblush");
      
      wordsFound = new VBox(2);
      wordsFound.setPadding(new Insets(20,20,20,20));
      
      buttonPane= new HBox(20);
      buttonPane.setStyle("-fx-background-color: wheat");
      
      //Initialize game and draw Board
      game = new Game();
      drawBoard();
      main.setCenter(grid);
      
      points = new Text("Points: "+ game.getScore());
      status = new Text("");
      
      
      Text t= new Text("Words");
      t.setFont(new Font(15));
      t.setUnderline(true);
      wordsFound.getChildren().add(t);
      
      textArea.getChildren().add(wordsFound);
      textArea.getChildren().add(points);
      textArea.setMargin(points, (new Insets(30,30,30,30)));
               
      // end Button
      end = new Button("End Game");
      end.setOnAction(this::handleEnd);
           
      buttonPane.getChildren().add(end);
      
      // New Game Button
      newGame= new Button("New Game");
      newGame.setOnAction( new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e)
         { 
            game = new Game();
            drawBoard(); 
            wordsFound.getChildren().clear();
            wordsFound.getChildren().add(t);
            timeArc.setLength(0); 
            arcLength=2;
            status.setText("");
            timelineDisplay.play(); 
            timerStart();  
         }
       });
      buttonPane.getChildren().add(newGame);
      
      // Test Button 
      Button test = new Button("Test Word");
      test.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e)
         {
            if(game.testSelected())
            {
               wordsFound.getChildren().add(new Text(game.getWords().get(game.getWords().size()-1).toString()));
               points.setText("Points: "+ game.getScore());
            }
            else 
               status.setText("Invalid Selection");
            
            game.clearSelected();
            drawBoard();    
         }
       });
       
      buttonPane.getChildren().add(test);
      buttonPane.getChildren().add(status);
      
      //Set up timer/clock
      timer = new HBox();
      timer.setPadding(new Insets(30,30,30,30));
      timeArc = new Arc(50,50,15,15,0,0);
      timeArc.setType(ArcType.ROUND);
      timeArc.setFill(Color.SEASHELL);
      timeArc.setStrokeWidth(20);
      timer.getChildren().add(timeArc);
      extra.getChildren().add(timer);
      
      // update Timer image
      class TimeHandler implements EventHandler<ActionEvent>
      {    
         @Override
         public void handle(ActionEvent e)
         {
            timeArc.setLength(arcLength+=2);   
         }
      }
      
      // create Timeline
      KeyFrame frameDisplay = new KeyFrame(Duration.seconds(1),new TimeHandler());
      timelineDisplay = new Timeline(frameDisplay);
      timelineDisplay.setCycleCount(Animation.INDEFINITE);
      timelineDisplay.play();
      this.timerStart();
      
      //Search bar
      search=new TextField("Search");
      search.setStyle("-fx-background-color: white");
      search.setOnAction(this::handleSearch);
      extra.getChildren().add(search);
                  
      // Complete set up
      main.setRight(textArea);
      main.setBottom(buttonPane);
      primaryStage.setTitle("Boggle");
      Scene scene= new Scene(main);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   
   // Event Handling for user clicking on a Tile
   public void handleClick(MouseEvent e)
   {
      status.setText("");
      TilePane tp = (TilePane)(e.getSource());
      
      if(game.isValidSelection(tp.getRow(),tp.getCol()))
      {
         game.addToSelected(tp.getRow(),tp.getCol());
         tp.setSelected(); 
      }
      else if(tp.getTile()== game.getSelectedTiles().get(game.getSelectedTiles().size()-1))
      {
          game.removeFromSelected(tp.getRow(),tp.getCol());
          tp.setUnselected();
      }
      else
         status.setText("Not a valid tile.");
   }
   
   // searchWord method used in hnadle search, determines if word is a valid play on board. 
   public boolean searchWord(String word,int row, int column)
   {
      if (word.isEmpty())
         return true;
      if(game.hasTile(word.charAt(0),row,column))
         return searchWord(word.substring(1),row,column);
           
      if ((row<3)&&game.hasTile(word.charAt(0),row+1,column)&& game.isValidSelection(row+1,column))
         return searchWord(word.substring(1),row+1,column);
      else if((row<3)&&(column<3)&&game.hasTile(word.charAt(0),row+1,column+1)&& game.isValidSelection(row+1,column+1))
         return searchWord(word.substring(1),row+1,column+1);
      else if((row>0) && (column>0)&& game.hasTile(word.charAt(0),row-1,column-1)&&game.isValidSelection(row-1,column-1))
         return searchWord(word.substring(1),row-1,column-1);
      else if((row<3)&&(column>0)&&game.hasTile(word.charAt(0),row+1,column-1)&& game.isValidSelection(row+1,column-1))
         return searchWord(word.substring(1),row+1,column-1);
      else if((row>0)&&(column<3)&&game.hasTile(word.charAt(0),row-1,column+1)&& game.isValidSelection(row-1,column+1))
         return searchWord(word.substring(1),row-1,column+1);
      else if ((row>0)&&game.hasTile(word.charAt(0),row-1,column)&& game.isValidSelection(row-1,column))
         return searchWord(word.substring(1),row-1,column);
      else if ((column<3)&&game.hasTile(word.charAt(0),row,column+1)&& game.isValidSelection(row,column+1))
         return searchWord(word.substring(1),row,column+1);
      else if ((column>0)&&game.hasTile(word.charAt(0),row,column-1)&& game.isValidSelection(row,column-1))
         return searchWord(word.substring(1),row,column-1);
      else 
         return false;
   } 
   //Sets the status if user inputs a valid play
   public void handleSearch(ActionEvent e)
   {
      status.setText("");
      String input = search.getText();
      for(int r=0;r<4;r++)
         for(int c=0;c<4;c++)
         {
            if (searchWord(input,r,c))
            {
               status.setText("is a valid play");     
            } 
         }
      if(status.getText().equals(""))
         status.setText("Not a valid play");
   }
   // Event Handling for user clicking end button or timer running out
   public void handleEnd(ActionEvent e)
   {
      for (Node pane: grid.getChildren())
         pane.setOnMouseClicked(null);
      timelineDisplay.pause();
      status.setText("Game Over!    You Scored: "+ game.getScore());    
   }
   
   //Method starts the timer for three minutes
   public void timerStart()
   {
     KeyFrame frame = new KeyFrame(Duration.minutes(3.0),(this::handleEnd));
     Timeline timeline = new Timeline(frame);
     timeline.setCycleCount(1);
     timeline.play();
   }
   
   //Creates the board
   public void drawBoard()
   {
      grid.getChildren().clear();
      for(int r=0;r<4;r++)
         for(int c=0;c<4;c++)
         {
            TilePane tp = new TilePane(game.getBoard(),r,c);
            tp.setOnMouseClicked(this::handleClick);
            grid.add(tp,c,r);
         }
      grid.setHgap(10);
      grid.setVgap(10);
   }
   
   public static void main(String [] args)
   {
      launch(args);
   }
}
      