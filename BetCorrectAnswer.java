/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.BufferedWriter;
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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author cataclysm
 */
public class BetCorrectAnswer implements EventHandler<ActionEvent>{
    BettingGame game;
    MyVBox vbox;
    public BetCorrectAnswer(BettingGame game,MyVBox vbox){
        this.game=game;
        this.vbox=vbox;
    }
    @Override
    public void handle(ActionEvent e){
//clear the vbox from everything
        MyVBox.clear(vbox);
//add the points to the player for answering right the question
        game.getPlayer().addPoints(game.getPointsPerAnswer());
//check if the round has ended
        if( game.getQuestionsNumb()==BettingGame.NUMBER_OF_QUESTIONS)
        {
//check if the game has ended
            if( game.getRoundsNumb()==BettingGame.NUMBER_OF_ROUNDS )
            {
//save the statistics of the last round to the output file
                try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw))
                    {
                        pw.println("the player has accumulated "+game.getPlayer().getPoints()+" points at the round number "+game.getRoundsNumb());
                    }
                 catch(IOException exc){
                       System.out.println(exc.toString());
                    }  
//show that the game has ended                         
                         Text endOfTheGame = new Text("END OF THE GAME");
                         endOfTheGame.setFont(Font.font("my",FontWeight.BOLD,25));
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
                         vbox.getChildren().addAll(endOfTheGame,showPlayer);
            }
            else
            {
//save the statistics of the last round to the output file
                try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw))
                    {
                        pw.println("the player has accumulated "+game.getPlayer().getPoints()+" points at the round number "+game.getRoundsNumb());
                    }
                 catch(IOException exc){
                       System.out.println(exc.toString());
                    }  
//reset the player's points for the next round and start the next round
                game.getPlayer().setPoints(0);
                game.incRoundsNumb();
                game.setQuestionsNumb(0);
                try{
                    game.getAllTheQuestions();
                }
                catch(FileNotFoundException exc){
                    System.out.println(exc.toString());
                }
                Text roundNumber = new Text("ROUND NUMBER "+game.getRoundsNumb());
                roundNumber.setFont(Font.font("my",FontWeight.BOLD,20));
                vbox.getChildren().add(roundNumber);
                BettingGame.showQuestionForBet(game,vbox);
            }       
        }
//show a question to the player
        else
            BettingGame.showQuestionForBet(game,vbox);
    }
}
