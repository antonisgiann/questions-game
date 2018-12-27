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
public class CorrectAnswerWithImage implements EventHandler<ActionEvent>{
    CorrectAnswerGame game;
    MyVBox vbox;
    public CorrectAnswerWithImage(CorrectAnswerGame game,MyVBox vbox){
        this.game=game;
        this.vbox=vbox;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //add the points to the player for answering correct the question
        game.getPlayer().addPoints(game.getPointsPerAnswer());
        //check if the round has ended
        if( game.getQuestionsNumb()==CorrectAnswerGame.NUMBER_OF_QUESTIONS )
        {
            //check if the game has ended
            if( game.getRoundsNumb()==CorrectAnswerGame.NUMBER_OF_ROUNDS )
            {
                //inform the player that the game has ended
                Text endOfTheGame = new Text("END OF THE GAME");
                endOfTheGame.setFont(Font.font("my",FontWeight.BOLD, 23));
                vbox.getChildren().add(endOfTheGame);
            }
            else
            {
                //increase the number of the rounds
                game.incRoundsNumb();
                //reset the number of the questions that has being asked for the new round
                game.setQuestionsNumb(0);
                //create a button for the next round
                Button nextRound = new Button("NEXT ROUND");
                nextRound.setOnAction(new NextRound(game,vbox));
            }
        }
        else
           //show a new question to the player
           CorrectAnswerGame.displayAquestionWithImage(game,vbox);
        
    }
}
