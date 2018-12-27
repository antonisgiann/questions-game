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
public class TwoPlayers implements EventHandler<ActionEvent>{
    MyVBox vbox;
    String language;
    public TwoPlayers(MyVBox vbox,String language){
        this.vbox=vbox;
        this.language=language;
    }
    @Override
    public void handle(ActionEvent e){
     //clear the vbox
      MyVBox.clear(vbox);
      //show the possible games
      Button answrGame = new Button("correct answer game");
      Button bettingGame = new Button("game with betting");
      Button thermometerGame = new Button("thermometer game");
      Button quickAnswerGame = new Button("Quick answer game");
      //assign the handlers to the buttons from the above
      answrGame.setOnAction(new TwoPlaChooseQuestions(vbox,language));
      bettingGame.setOnAction(new TwoPlaBettingChooseQuestions(vbox,language));
      thermometerGame.setOnAction(new ThermometerChooseQuestions(vbox,language));
      quickAnswerGame.setOnAction(new QuickAnswerChooseQuestion(vbox,language));
      //add the buttons to the vbox
      vbox.getChildren().addAll(answrGame,bettingGame,thermometerGame,quickAnswerGame);
    }
}
