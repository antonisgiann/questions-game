/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.FileNotFoundException;
import javafx.event.*;

/**
 *
 * @author cataclysm
 */
public class NextRoundWithImage implements EventHandler<ActionEvent>{
    CorrectAnswerGame game;
    MyVBox vbox;
    public NextRoundWithImage(CorrectAnswerGame game,MyVBox vbox){
        this.game=game;
        this.vbox=vbox;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        try{
            //reset the questions that is available for the game
            game.getAllTheQuestionsWithImage();
        }
        catch(FileNotFoundException exc){
            exc.toString();
        }
        //start the next round and show a question to the player
        CorrectAnswerGame.displayAquestionWithImage(game, vbox);
    }
    
}
