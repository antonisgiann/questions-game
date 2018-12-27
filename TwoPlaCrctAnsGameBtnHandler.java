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
public class TwoPlaCrctAnsGameBtnHandler implements EventHandler<ActionEvent>{
    TwoPlaCorrectAnswerGame game;
    MyVBox vbox;
    Player player;
    Player player2;
    String language;
    boolean withImage;
    public TwoPlaCrctAnsGameBtnHandler(MyVBox vbox,String language,boolean withImage) throws FileNotFoundException{
        this.vbox=vbox;
        this.language=language;
        this.withImage=withImage;
        player=new Player(0);
        player2=new Player(0);
        //check what is the language of the system
        if(language.equals("English"))
        {
           //check if the questions are with image or without  and create a game
           if(withImage)
              game=new TwoPlaCorrectAnswerGame(player,player2,true,new File("/home/cataclysm/EnglishWithImage"));
           else
              game=new TwoPlaCorrectAnswerGame(player,player2,false,new File("/home/cataclysm/EnglishNoImage"));
        }
        else
        {
            //check if the questions are with image or without and create a game
            if(withImage)
              game=new TwoPlaCorrectAnswerGame(player,player2,true,new File("/home/cataclysm/GreekWithImage"));
           else
              game=new TwoPlaCorrectAnswerGame(player,player2,false,new File("/home/cataclysm/GreekNoImage"));
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
        //check if the question are with image or without and display a question
        if(withImage)
            TwoPlaCorrectAnswerGame.displayAquestionWithImageTwoPlayers(game, vbox);
        else  
            TwoPlaCorrectAnswerGame.displayAquestionTwoPlayers(game, vbox);  
    }
}
