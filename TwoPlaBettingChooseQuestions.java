/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;
import javafx.scene.control.Button;
import java.io.FileNotFoundException;

/**
 *
 * @author cataclysm
 */
public class TwoPlaBettingChooseQuestions implements EventHandler<ActionEvent>{
    MyVBox vbox;
    String language;
    public TwoPlaBettingChooseQuestions(MyVBox vbox,String language){
        this.vbox=vbox;
        this.language=language;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //create buttons one for questions with image, one for without
        Button queWithoutImage = new Button("questions without images");
        Button queWithImage = new Button("questions with images");
        //assign the handlers to the buttons
        try{
            queWithoutImage.setOnAction(new BettingTwoPlaGameHandler(vbox,language,false));
        }
        catch(FileNotFoundException exc){
            System.out.println(exc.toString());
        }
        try{
            queWithImage.setOnAction(new BettingTwoPlaGameHandler(vbox,language,true));
        }
        catch(FileNotFoundException exc){
            System.out.println(exc.toString());
        }
        //add the buttons to the vbox
        vbox.getChildren().addAll(queWithoutImage,queWithImage);
    }
    
}
