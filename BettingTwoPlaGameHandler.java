/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.BufferedWriter;
import javafx.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author cataclysm
 */
public class BettingTwoPlaGameHandler implements EventHandler<ActionEvent>{
    TwoPlaBettingGame game;
    MyVBox vbox;
    String language;
    Player player;
    Player player2;
    boolean withImage;
    public BettingTwoPlaGameHandler(MyVBox vbox,String language,boolean withImage) throws FileNotFoundException{
        this.vbox=vbox;
        this.language=language;
        this.withImage=withImage;
        player=new Player(0);
        player2=new Player(0);
        //check for the language of the system
        if(language.equals("English"))
        {
           //check if the questions are with image or without and create a game
           if(withImage)
               game=new TwoPlaBettingGame(player,player2,true,new File("/home/cataclysm/EnglishWithImage"));
           else
               game=new TwoPlaBettingGame(player,player2,false,new File("/home/cataclysm/EnglishNoImage"));
        }
        else
        {
            //check if the questions are with image or without and create a game
            if(withImage)
               game=new TwoPlaBettingGame(player,player2,true,new File("/home/cataclysm/GreekWithImage"));
           else
               game=new TwoPlaBettingGame(player,player2,false,new File("/home/cataclysm/GreekNoImage"));
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
        MyVBox.clear(vbox);
        if(withImage){
            TwoPlaBettingGame.showBetOptionsWithImageTwoPlayers(game,vbox);
        }
        else{
            TwoPlaBettingGame.showBetOptionsTwoPlayers(game, vbox);
        }
    }
}
