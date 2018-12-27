/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.BufferedWriter;
import javafx.event.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author cataclysm
 */
public class BetGameHandler implements EventHandler<ActionEvent>{
    BettingGame game;
    MyVBox vbox;
    Player player;
    String language;
    boolean withImage;
    public BetGameHandler(MyVBox vbox,String language,boolean withImage) throws FileNotFoundException{
        this.vbox=vbox;
        this.language=language;
        player=new Player(0);
        this.withImage=withImage;
//check for the language of the system and create the appropriate game
        if(language.equals("English"))
            game=new BettingGame(player,false,new File("/home/cataclysm/EnglishNoImage"));  
        else
            game=new BettingGame(player,false,new File("/home/cataclysm/GreekNoImage"));
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
        //check if the questions are with image or without and display a question
        if(withImage)
            BettingGame.showQuestionForBetWithImage(game, vbox);
        else
            BettingGame.showQuestionForBet(game, vbox);
    }
}
