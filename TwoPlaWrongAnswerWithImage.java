/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.BufferedWriter;
import javafx.event.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 *
 * @author cataclysm
 */
public class TwoPlaWrongAnswerWithImage implements EventHandler<ActionEvent>{
    TwoPlaCorrectAnswerGame game; 
    MyVBox vbox;
    boolean allow=true;
     public TwoPlaWrongAnswerWithImage(TwoPlaCorrectAnswerGame game,MyVBox vbox){
         this.game=game;
         this.vbox=vbox;
     }
     @Override
     public void handle(ActionEvent e){
         if(allow)
         {
             allow=false;
             game.incControl();
             if(game.getControl()==2)
             {
                 MyVBox.clear(vbox);
                 game.resetControl();
                 //check if the round has ended
                 if( game.getQuestionsNumb()==TwoPlaCorrectAnswerGame.NUMBER_OF_QUESTIONS )
                 {
                     //check if this was the last round
                     if( game.getRoundsNumb()==TwoPlaCorrectAnswerGame.NUMBER_OF_ROUNDS )
                     {
//save the statistics of the last round to the output file
                         try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter pw = new PrintWriter(bw))
                         {
                            if(game.getPlayer().getPoints() > game.getPlayer2().getPoints() )
                                pw.println("player 1 has won the "+game.getRoundsNumb()+" round,with difference of "+(game.getPlayer().getPoints() - game.getPlayer2().getPoints())+" points");
                            else
                                pw.println("player 2 has won the "+game.getRoundsNumb()+" round,with difference of "+(game.getPlayer2().getPoints() - game.getPlayer().getPoints())+" points");
                         }
                         catch(IOException exc){
                             System.out.println(exc.toString());
                         }
//show that the game has ended                         
                         Text endOfTheGame = new Text("END OF THE GAME");
                         endOfTheGame.setFont(Font.font("my",FontWeight.BOLD,25));
                         Button showPlayer = new Button("show player's 1 points");
                         Button showPlayer2 = new Button("show player's 2 points");
                         showPlayer.setOnAction(e1 -> {
                               Stage stage = new Stage();
                               Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(p1Points);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                            });
                         showPlayer2.setOnAction(e2 -> {
                               Stage stage = new Stage();
                               Text p2Points = new Text("player 2 points are "+game.getPlayer2().getPoints());
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(p2Points);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                         });
                         vbox.getChildren().addAll(endOfTheGame,showPlayer,showPlayer2);
                     }
                     else
                     {
//save the statistics of the round to the output file
                         try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter pw = new PrintWriter(bw))
                         {
                            if(game.getPlayer().getPoints() > game.getPlayer2().getPoints() )
                                pw.println("player 1 has won the "+game.getRoundsNumb()+" round,with difference of "+(game.getPlayer().getPoints() - game.getPlayer2().getPoints())+" points");
                            else
                                pw.println("player 2 has won the "+game.getRoundsNumb()+" round,with difference of "+(game.getPlayer2().getPoints() - game.getPlayer().getPoints())+" points");
                         }
                         catch(IOException exc){
                             System.out.println(exc.toString());
                         }
//reset the players points and start the new round
                         game.getPlayer().setPoints(0);
                         game.getPlayer2().setPoints(0);
                         game.incRoundsNumb();
                         game.setQuestionsNumb(0);
                         try{
                             game.getAllTheQuestionsWithImage();
                         }
                         catch(FileNotFoundException exc){
                             System.out.println(exc.toString());
                         }
                         Text roundNumber = new Text("Round Number "+game.getRoundsNumb());
                         vbox.getChildren().add(roundNumber);
                         TwoPlaCorrectAnswerGame.displayAquestionWithImageTwoPlayers(game, vbox);                         
                     }
                 }
//show a new question to the players
                 else
                     TwoPlaCorrectAnswerGame.displayAquestionWithImageTwoPlayers(game, vbox);
             }
         }
     }
    
}
