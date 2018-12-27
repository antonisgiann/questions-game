/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

/**
 *
 * @author cataclysm
 */
public class WrongAnswerWithImage implements EventHandler<ActionEvent>{
    CorrectAnswerGame game;
    MyVBox vbox;
    public WrongAnswerWithImage(CorrectAnswerGame game,MyVBox vbox){
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
                //show the player that the game has ended
                Text endOfTheGame = new Text("END OF THE GAME");
                endOfTheGame.setFont(Font.font("my",FontWeight.BOLD, 23));
                vbox.getChildren().add(endOfTheGame);
            }
            else
            {
                //increase the number of the rounds for the next one to begin
                game.incRoundsNumb();
                game.setQuestionsNumb(0);
                Text endOfTheRound = new Text("end of the round "+(game.getRoundsNumb()-1));
                Button nextRound = new Button("NEXT ROUND");
                nextRound.setOnAction(new NextRound(game,vbox));
                vbox.getChildren().addAll(endOfTheRound,nextRound);
            }
        }
        else
           //show a new question to the player
           CorrectAnswerGame.displayAquestionWithImage(game,vbox);
        
    }
}

