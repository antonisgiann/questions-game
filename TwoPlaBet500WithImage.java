/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.event.*;

/**
 *
 * @author cataclysm
 */
public class TwoPlaBet500WithImage implements EventHandler<ActionEvent>{
    TwoPlaBettingGame game;
    MyVBox vbox;
    QuestionClassWithImage q;
    int x;
    boolean allow=true;
    public TwoPlaBet500WithImage(TwoPlaBettingGame game,MyVBox vbox,QuestionClassWithImage q,int x){
        this.game=game;
        this.vbox=vbox;
        this.q=q;
        this.x=x;
    }
    @Override
    public void handle(ActionEvent e){
        if(allow)
        {
            allow=false;
//check who of the players pressed the button
            if(x==1) 
                game.setPointsPerAnswer(500);
            else 
                game.setPointsPerAnswer2(500);
            game.incControl();
//check whether to show the question or wait for the other player to place his bet
            if( game.getControl()==2 )
            {
                MyVBox.clear(vbox);
                game.resetControl();
                TwoPlaBettingGame.betShowAquestionWithImageTwoPlayers(game,vbox,q);
            }
        }
    }
}
