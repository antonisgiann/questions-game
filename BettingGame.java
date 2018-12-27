/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.*;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author cataclysm
 */
public class BettingGame extends CorrectAnswerGame{
     public BettingGame(Player player,boolean withImage,File inputFile) throws FileNotFoundException{
         super(player,withImage,inputFile);
         super.setOutputFile("betting game stats");
     }
     
     public void setPointsPerAnswer(int points){
         POINTS_PER_ANSWER=points;
     }
     
     public static void showQuestionForBet(BettingGame game,MyVBox vbox){
//get a question without image and then display the category and the betting options
         QuestionClass q = game.getARandomQuestion();
         game.incQuestionsNumb();
         Text category = new Text(q.getCategory());
         Text howMuch = new Text("how much do you want to bet?");
         Button p250 = new Button("250");
         Button p500 = new Button("500");
         Button p750 = new Button("750");
         Button p1000 = new Button("1000");
//assign handlers to the buttons
         p250.setOnAction(new P250Handler(game,vbox,q));
         p500.setOnAction(new P500Handler(game,vbox,q));
         p750.setOnAction(new P750Handler(game,vbox,q));
         p1000.setOnAction(new P1000Handler(game,vbox,q));
//create a button so the player can see his points
         Button showPlayer = new Button("show player's points");                         
         showPlayer.setOnAction(e1 -> {
         Stage stage = new Stage();
         Text pPoints = new Text("player 1 points are "+game.getPlayer().getPoints());
         HBox hbox = new HBox();
         hbox.setAlignment(Pos.CENTER);
         hbox.getChildren().add(pPoints);
         stage.setScene(new Scene(hbox,300,300));
         stage.show();
         });
//create an HBox to display the possible betting choices
         HBox hbox = new HBox(10);
         hbox.setAlignment(Pos.CENTER);
         hbox.getChildren().addAll(p250,p500,p750,p1000);
//add everything to the vbox
         vbox.getChildren().addAll(category,howMuch,hbox,showPlayer);
     }
     
     
     public static void showQuestionForBetWithImage(BettingGame game,MyVBox vbox){
//get a question with image and then display the category and the betting options
         QuestionClassWithImage q = game.getARandomQuestionWithImage();
         game.incQuestionsNumb();
         Text category = new Text(q.getCategory());
         Text howMuch = new Text("how much do you want to bet?");
         Button p250 = new Button("250");
         Button p500 = new Button("500");
         Button p750 = new Button("750");
         Button p1000 = new Button("1000");
//assign the handlers to the buttons
         p250.setOnAction( new P250HandlerWithImage(game,vbox,q));
         p500.setOnAction( new P500HandlerWithImage(game,vbox,q));
         p750.setOnAction( new P750HandlerWithImage(game,vbox,q));
         p1000.setOnAction( new P1000HandlerWithImage(game,vbox,q));
//create a button so the player can see his points
         Button showPlayer = new Button("show player's points");                         
         showPlayer.setOnAction(e1 -> {
         Stage stage = new Stage();
         Text pPoints = new Text("player 1 points are "+game.getPlayer().getPoints());
         HBox hbox = new HBox();
         hbox.setAlignment(Pos.CENTER);
         hbox.getChildren().add(pPoints);
         stage.setScene(new Scene(hbox,300,300));
         stage.show();
         });
//create an HBox to display the possible betting choices 
         HBox hbox = new HBox(10);
         hbox.setAlignment(Pos.CENTER);
         hbox.getChildren().addAll(p250,p500,p750,p1000);
//add everything to the vbox 
         vbox.getChildren().addAll(category,howMuch,hbox,showPlayer);
     }
     
}
