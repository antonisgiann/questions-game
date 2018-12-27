/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.scene.control.Button;
import javafx.event.*;
/**
 *
 * @author cataclysm
 */
public class ThermometerChooseQuestions implements EventHandler<ActionEvent>{
    MyVBox vbox;
    String language;
    public ThermometerChooseQuestions(MyVBox vbox,String language){
        this.vbox=vbox;
        this.language=language;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //create two buttons,one for game with image and one for without
        Button queWithoutImage = new Button("questions without images");
        Button queWithImage = new Button("questions with images");
        queWithoutImage.setOnAction(new ThermometerGameHandler(vbox,language,false));
        queWithImage.setOnAction(new ThermometerGameHandler(vbox,language,true));
        //add the buttons to the vbox
        vbox.getChildren().addAll(queWithoutImage,queWithImage);
    }
}
