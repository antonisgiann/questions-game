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
public class ChooseTheQuestions implements EventHandler<ActionEvent>{
    MyVBox vbox;
    String language;
    public ChooseTheQuestions(MyVBox vbox,String language){
        this.vbox=vbox;
        this.language=language;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //create one button for question with image and one for without
        Button queWithImage = new Button("questions with images");
        Button queWithoutImage = new Button("questions without images");
        try{
            //assign the handlers to the buttons
            queWithoutImage.setOnAction(new CorrectAnswerGameBtnHandler(vbox,language,false));
            queWithImage.setOnAction(new CorrectAnswerGameBtnHandler(vbox,language,true)); 
        }
        catch(FileNotFoundException exc){
            System.out.println(exc.toString());
        }
        //add the buttons to the vbox to be displayed
        vbox.getChildren().addAll(queWithImage,queWithoutImage);
    }
}
