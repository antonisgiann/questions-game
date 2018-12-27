/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

/**
 *
 * @author cataclysm
 */
public class TwoPlaBetWrongAnswer implements EventHandler<ActionEvent>{
    TwoPlaBettingGame game;
    MyVBox vbox;
    boolean allow=true;
    int x;
    public TwoPlaBetWrongAnswer(TwoPlaBettingGame game,MyVBox vbox,int x){
        this.game=game;
        this.vbox=vbox;
        this.x=x;
    }
    @Override
    public void handle(ActionEvent e){
//check if the button was pressed again
        if(allow)
        {
//make sure the button will be pressed only once
            allow=false;
//check who of the players is using this handler and add the points to him
            if(x==1)
                game.getPlayer().removePoints(game.getPointsPerAnswer());
            else 
                game.getPlayer2().removePoints(game.getPointsPerAnswer2());
            game.incControl();
//check if both of the players has answered their question
            if( game.getControl()==2 )
            {
                MyVBox.clear(vbox);
                game.resetControl();
//check if the round has ended
                if( game.getQuestionsNumb()==TwoPlaBettingGame.NUMBER_OF_QUESTIONS )
                {
//check if the game has ended
                    if( game.getRoundsNumb()==TwoPlaBettingGame.NUMBER_OF_ROUNDS )
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
                         Text endOfTheGame = new Text("END OF THE GAME");
                         endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
                         Button showPlayer1 = new Button("show player's 1 points");
                         showPlayer1.setOnAction(e1 -> {
                            Stage stage = new Stage();
                            Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
                            HBox hbox = new HBox();
                            hbox.setAlignment(Pos.CENTER);
                            hbox.getChildren().add(p1Points);
                            stage.setScene(new Scene(hbox,300,300));
                            stage.show();
                        });
                         Button showPlayer2 = new Button("show player's 2 points");
                         showPlayer2.setOnAction(e2 -> {
                            Stage stage = new Stage();
                            Text p1Points = new Text("player 2 points are "+game.getPlayer2().getPoints());
                            HBox hbox = new HBox();
                            hbox.setAlignment(Pos.CENTER);
                            hbox.getChildren().add(p1Points);
                            stage.setScene(new Scene(hbox,300,300));
                            stage.show();
                        });
                         vbox.getChildren().addAll(endOfTheGame,showPlayer1,showPlayer2);
                    }
//start the next round                   
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
//increase the number of rounds                         
                         game.incRoundsNumb();
//reset the points of the players
                         game.getPlayer().setPoints(0);
                         game.getPlayer2().setPoints(0);
//reset the number of the questions
                         game.setQuestionsNumb(0);
                         try{
                             game.getAllTheQuestions();
                         }
                         catch(FileNotFoundException exc){
                             System.out.println(exc.toString());
                         }
//show the number of the round
                         Text roundNumber = new Text("Round Number "+game.getRoundsNumb());
                         roundNumber.setFont(Font.font("my",FontWeight.EXTRA_BOLD,25));
                         vbox.getChildren().add(roundNumber);
                         TwoPlaBettingGame.showBetOptionsTwoPlayers(game,vbox);
                    }       
                }
//show again the betting options in order for the next question to be displayed                
                else
                    TwoPlaBettingGame.showBetOptionsTwoPlayers(game, vbox);
                    
                
            }
        }
    }
}
