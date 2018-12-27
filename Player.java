/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.geometry.Pos;

/**
 *
 * @author cataclysm
 */
class Player{
    private int points=0;
    private int control=0;
    private int correctAnswersNumb=0;
    private final MyVBox vbox=new MyVBox(10);
    public Player(int points){
        this.points=points;
        vbox.setAlignment(Pos.CENTER);
    }
    public double getPoints(){
        return points;
    }
    public void addPoints(int points){
        this.points+=points;
    }
    public void removePoints(int points){
        this.points-=points;
    }
    public void setPoints(int points){
        this.points=points;
    }
    public void incControl(){
        control++;
    }
    public int getControl(){
        return control;
    }
    public void resetControl(){
        control=0;
    }
    public MyVBox getVBox(){
        return vbox;
    }  
    public int getCorrectAnswersNumb(){
        return correctAnswersNumb;
    }
    public void setCorrectAnswersNumb(int numb){
        correctAnswersNumb=numb;
    }
    public void incCorrectAnswersNumb(){
        correctAnswersNumb++;
    }
    @Override
    public String toString(){
        return "the players points are "+getPoints();
    }
}
