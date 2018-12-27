/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author cataclysm
 */
public class QuickAnswerHandler implements EventHandler<ActionEvent>{
    QuickAnswerGame game;
    MyVBox vbox;
    String language;
    boolean withImage;
    Player player1;
    Player player2;
    public QuickAnswerHandler(MyVBox vbox,String language,boolean withImage){
        this.vbox=vbox;
        this.language=language;
        this.withImage=withImage;
        player1 = new Player(0);
        player2 = new Player(0);
        //check the language of the system
        if(language.equals("English")){
            //check if the questions are with images or without
            if(withImage){
                try{
                    game = new QuickAnswerGame(player1,player2,true,new File("/home/cataclysm/EnglishWithImage"));
                }
                catch(FileNotFoundException exc){
                    System.out.println(exc.toString());
                }
            }
            else{
                try{
                    game = new QuickAnswerGame(player1,player2,false,new File("/home/cataclysm/EnglishNoImage"));
                }
                catch(FileNotFoundException exc){
                    System.out.println(exc.toString());
                }
            } 
        }
        else{
           //check if the questions are with images or without
            if(withImage){
                try{
                    game = new QuickAnswerGame(player1,player2,true,new File("/home/cataclysm/GreekWithImage"));
                }
                catch(FileNotFoundException exc){
                    System.out.println(exc.toString());
                }
            }
            else{
                try{
                    game = new QuickAnswerGame(player1,player2,false,new File("/home/cataclysm/GreekNoImage"));
                }
                catch(FileNotFoundException exc){
                    System.out.println(exc.toString());
                }
            }  
        }
    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //check if the question are with image or without and display a question
        if(withImage)
            QuickAnswerGame.questionWithImageForPlayers(game,player1,player2, vbox,game.getAnswers());
        else  
            QuickAnswerGame.questionForPlayers(game,player1,player2, vbox,game.getAnswers()); 
    }
}
