/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;
import java.io.FileNotFoundException;

/**
 *
 * @author cataclysm
 */
public class NextRound implements EventHandler<ActionEvent>{
    CorrectAnswerGame game;
    MyVBox vbox;
    public NextRound(CorrectAnswerGame game,MyVBox vbox){
        this.game=game;
        this.vbox=vbox;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        try{
            //reset the questions that is available for the game
            game.getAllTheQuestions();
        }
        catch(FileNotFoundException exc){
            exc.toString();
        }
        //start the new round and show a new question to the player
        CorrectAnswerGame.displayAquestion(game, vbox);
    }
    
}
