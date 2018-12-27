/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.FileNotFoundException;
import javafx.event.*;
import javafx.scene.control.Button;
/**
 *
 * @author cataclysm
 */
public class TwoPlaChooseQuestions implements EventHandler<ActionEvent>{
    MyVBox vbox;
    String language;
    public TwoPlaChooseQuestions(MyVBox vbox,String language){
        this.vbox=vbox;
        this.language=language;
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //create two buttons,one for questions with images and one for without
        Button queWithImage = new Button("questions with images");
        Button queWithoutImage = new Button("questions without images");
        try{
            queWithoutImage.setOnAction(new TwoPlaCrctAnsGameBtnHandler(vbox,language,false));
        }
        catch(FileNotFoundException exc){
            System.out.println(exc.toString());
        }
        try{
            queWithImage.setOnAction(new TwoPlaCrctAnsGameBtnHandler(vbox,language,true)); 
        }
        catch(FileNotFoundException exc){
            System.out.println(exc.toString());
        }
        //add the buttons to the vbox
        vbox.getChildren().addAll(queWithImage,queWithoutImage);        
    }
}
