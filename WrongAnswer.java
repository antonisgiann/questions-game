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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author cataclysm
 */
public class WrongAnswer implements EventHandler<ActionEvent>{
    CorrectAnswerGame game;
    MyVBox vbox;
    public WrongAnswer(CorrectAnswerGame game,MyVBox vbox){
        this.game=game;
        this.vbox=vbox;
    }
    @Override
    public void handle(ActionEvent e){
        MyVBox.clear(vbox);
//check if the round has ended
           if( game.getQuestionsNumb()==CorrectAnswerGame.NUMBER_OF_QUESTIONS )
           {
               //check if this was the last round
               if( game.getRoundsNumb()==CorrectAnswerGame.NUMBER_OF_ROUNDS )
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
                 Button showPlayer = new Button("show player's points");
                 showPlayer.setOnAction(e1 -> {
                    Stage stage = new Stage();
                    Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
                    HBox hbox = new HBox();
                    hbox.setAlignment(Pos.CENTER);
                    hbox.getChildren().add(p1Points);
                    stage.setScene(new Scene(hbox,300,300));
                    stage.show();
                 });
                 endOfTheGame.setFont(Font.font("my",FontWeight.BOLD,23));
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
//reset the player's points and start the new round
               game.getPlayer().setPoints(0);
               game.setQuestionsNumb(0);
               game.incRoundsNumb();
               Text endOfTheRound = new Text("end of the round "+(game.getRoundsNumb()-1));
               Button nextRound = new Button("NEXT ROUND");
               nextRound.setOnAction(new NextRound(game,vbox));
               vbox.getChildren().addAll(endOfTheRound,nextRound);
               }
           }
//show a new question to the player
           else         
               CorrectAnswerGame.displayAquestion(game, vbox);
           
    }
}
