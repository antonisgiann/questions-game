/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
/**
 *
 * @author cataclysm
 */
public class P1000Handler implements EventHandler<ActionEvent>{
    BettingGame game;
    MyVBox vbox;
    QuestionClass q;
    public P1000Handler(BettingGame game,MyVBox vbox,QuestionClass q){
        this.game=game;
        this.vbox=vbox;
        this.q=q;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //set the number of points that the player is betting
        game.setPointsPerAnswer(1000);
        //create the text for the question and the buttons for the answers
        Text question = new Text(q.getQuestion());
        Button answer1 = new Button(q.getPossibleAnswers()[0]);
        Button answer2 = new Button(q.getPossibleAnswers()[1]);
        Button answer3 = new Button(q.getPossibleAnswers()[2]);
        Button answer4 = new Button(q.getPossibleAnswers()[3]);
        //create a button so the player can see his points
        Button[] answers = {answer1,answer2,answer3,answer4};
        Button showPlayer = new Button("show player's points");
        showPlayer.setOnAction(ee -> {
            Stage stage = new Stage();
            Text show = new Text("the player's points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(show);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
        //assign the handlers to the buttons
        for(int i=0; i<4; i++)
        {
            if(q.getAnswer().equals(answers[i].getText()))
                answers[i].setOnAction( new BetCorrectAnswer(game,vbox));
            else
                answers[i].setOnAction( new BetWrongAnswer(game,vbox));
        }
        //create an hbox for the answers to be displayed horizontal
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(answer1,answer2,answer3,answer4);
        //add the questions and the buttons to the vbox 
        vbox.getChildren().addAll(question,hbox,showPlayer);
    }
}
