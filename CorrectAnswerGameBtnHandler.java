/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;
import java.io.*;

/**
 *
 * @author cataclysm
 */
public class CorrectAnswerGameBtnHandler implements EventHandler<ActionEvent>{
    CorrectAnswerGame game;
    MyVBox vbox;
    Player player;
    String language;
    boolean withImage;
    public CorrectAnswerGameBtnHandler(MyVBox vbox,String language,boolean withImage) throws FileNotFoundException{
        this.vbox=vbox;
        this.language=language;
        player=new Player(0);
        this.withImage=withImage;
        //check the language of the system
        if(language.equals("English"))
        {
            //check if the questions are with image or without
           if(withImage)
               game=new CorrectAnswerGame(player,withImage,new File("/home/cataclysm/EnglishWithImage"));
           else
               game=new CorrectAnswerGame(player,withImage,new File("/home/cataclysm/EnglishNoImage"));
        }
        else
        {
            //check if the questions are with image or without
            if(withImage)
               game=new CorrectAnswerGame(player,withImage,new File("/home/cataclysm/GreekWithImage"));
           else
               game=new CorrectAnswerGame(player,withImage,new File("/home/cataclysm/GreekNoImage"));
        }
//save the time of the game to the output file        
        try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw))
        {
            java.util.Date date = new java.util.Date();
            pw.println(date);
        }
        catch(IOException exc){
                System.out.println(exc.toString());
        }

    }
    @Override
    public void handle(ActionEvent e){
        //clear the vbox
        MyVBox.clear(vbox);
        //check if questions are with image or without and show a question
        if(!withImage)
           CorrectAnswerGame.displayAquestion(game, vbox);
        else
           CorrectAnswerGame.displayAquestionWithImage(game, vbox);
    }
}
