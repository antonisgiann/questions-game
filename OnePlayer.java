/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;
import javafx.scene.control.Button;

/**
 *
 * @author cataclysm
 */
public class OnePlayer implements EventHandler<ActionEvent>{
    MyVBox vbox;
    String language;
    public OnePlayer(MyVBox vbox,String language){
        this.vbox=vbox;
        this.language=language;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //create the possible options and display them on the scene
        Button answrGame = new Button("correct answer game");
        answrGame.setOnAction(new ChooseTheQuestions(vbox,language));
        Button bettingGame = new Button("game with betting");
        bettingGame.setOnAction(new BetChooseTheQuestions(vbox,language));
        vbox.getChildren().addAll(answrGame,bettingGame);
    }
}
