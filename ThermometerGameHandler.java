/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;
import java.io.FileNotFoundException;
import java.io.File;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author cataclysm
 */
public class ThermometerGameHandler implements EventHandler<ActionEvent>{
    ThermometerGame game1;
    ThermometerGame game2;
    Player player1=new Player(0);
    Player player2=new Player(0);
    MyVBox vbox;
    String language;
    boolean withImage;
    public ThermometerGameHandler(MyVBox vbox,String language,boolean withImage){
        this.vbox=vbox;
        this.language=language;
        this.withImage=withImage;
        //check for the language
        if(language.equals("English"))
        {
           //check if the game is with questions with image or without
           if(withImage)
           {
               try{
                   //create games for the players 
                   game1= new ThermometerGame(player1,true,new File("/home/cataclysm/EnglishWithImage"));
                   game2= new ThermometerGame(player2,true,new File("/home/cataclysm/EnglishWithImage"));
               }
               catch(FileNotFoundException exc){
                   System.out.println(exc.toString());
               }
           }
           else
           {
               try{
                   //create games for the players
                   game1= new ThermometerGame(player1,false,new File("/home/cataclysm/EnglishNoImage"));
                   game2= new ThermometerGame(player2,false,new File("/home/cataclysm/EnglishNoImage"));
               } 
               catch(FileNotFoundException exc){
                   System.out.println(exc.toString());
               }
           }
        }
        else
            //check if the game is with questions with image or without
            if(withImage)
           {
               try{
                   //create games for the players
                   game1= new ThermometerGame(player1,true,new File("/home/cataclysm/GreekWithImage"));
                   game2= new ThermometerGame(player2,true,new File("/home/cataclysm/GreekWithImage"));
               }
               catch(FileNotFoundException exc){
                   System.out.println(exc.toString());
               }
           }
           else
           {
               try{
                   //create games for the players
                   game1= new ThermometerGame(player1,false,new File("/home/cataclysm/GreekNoImage"));
                   game2= new ThermometerGame(player2,false,new File("home/cataclysm/GreekNoImage"));
               } 
               catch(FileNotFoundException exc){
                   System.out.println(exc.toString());
               }
           }
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //show to players how they will answer each question
        Text howToAnswer = new Text("For player 1:Use the keys  (  q    w    e    r  )  to answer your questions\nFor player 2:Use the keys  (  u    i    o    p  )  to answer your questions");
        howToAnswer.setFont(Font.font("my",FontWeight.LIGHT, 15));
        vbox.getChildren().add(howToAnswer);
        //show each player a question
        ThermometerGame.showQuestionsFirstTime(game1,game2, vbox, ThermometerGame.getBp(),ThermometerGame.getAnswers());          
    }
}
