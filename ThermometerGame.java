/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import javafx.scene.image.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 *
 * @author cataclysm
 */
public class ThermometerGame extends CorrectAnswerGame {
    private int control=0;
    private static ArrayList<Button> ANSWERS=new ArrayList<>();
    private static BorderPane BP=new BorderPane();
    private static int thermometerRounds;
    public ThermometerGame(Player player,boolean withImage,File inputFile) throws FileNotFoundException{
        super(player,withImage,inputFile);
        ANSWERS=new ArrayList<>();
        BP=new BorderPane();
        thermometerRounds=0;
    }
//show both players a question    
    public static void showQuestionsFirstTime(ThermometerGame game1,ThermometerGame game2,MyVBox vbox,BorderPane bp,ArrayList<Button> answers){
//question for the player 1
        QuestionClass q1 = game1.getARandomQuestion();
        Text player1 = new Text("Question for player 1");
        player1.setFont(Font.font("my", FontWeight.LIGHT, 15));
        Text category1 = new Text(q1.getCategory());
        Text question1 = new Text(q1.getQuestion());
        Button answer11 = new Button(q1.getPossibleAnswers()[0]);
        Button answer12 = new Button(q1.getPossibleAnswers()[1]);
        Button answer13 = new Button(q1.getPossibleAnswers()[2]);
        Button answer14 = new Button(q1.getPossibleAnswers()[3]);
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
        game1.getPlayer().getVBox().getChildren().addAll(player1,category1,question1,hbox1);
        bp.setCenter(game1.getPlayer().getVBox());
//question for the player 2
        QuestionClass q2 = game2.getARandomQuestion();
        Text player2 = new Text("Question for player 2");
        player1.setFont(Font.font("my", FontWeight.LIGHT, 15));
        Text category2 = new Text(q2.getCategory());
        Text question2 = new Text(q2.getQuestion());
        Button answer21 = new Button(q2.getPossibleAnswers()[0]);
        Button answer22 = new Button(q2.getPossibleAnswers()[1]);
        Button answer23 = new Button(q2.getPossibleAnswers()[2]);
        Button answer24 = new Button(q2.getPossibleAnswers()[3]);
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
        game2.getPlayer().getVBox().getChildren().addAll(player2,category2,question2,hbox2);
        bp.setBottom(game2.getPlayer().getVBox());
//assign the handlers to the buttons
        answer11.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q1,q2));
        answer12.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q1,q2));
        answer13.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q1,q2));
        answer14.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q1,q2));
        answer21.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q1,q2));
        answer22.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q1,q2));
        answer23.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q1,q2));
        answer24.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q1,q2));
//put all the answers in an array
        answers.add(answer11);
        answers.add(answer12);
        answers.add(answer13);
        answers.add(answer14);
        answers.add(answer21);
        answers.add(answer22);
        answers.add(answer23);
        answers.add(answer24);
//display the questions to the players
        vbox.getChildren().addAll(bp);
        
    }
//show both players a question with image    
    public static void showQuestionsFirstTimeWithImage(ThermometerGame game1,ThermometerGame game2,MyVBox vbox,BorderPane bp,ArrayList<Button> answers){
//question for the player 1
        QuestionClassWithImage q1 = game1.getARandomQuestionWithImage();
        Text player1 = new Text("Question for player 1");
        player1.setFont(Font.font("my", FontWeight.LIGHT, 15));
        ImageView image1 = new ImageView(new Image(q1.getPath()));
        Text category1 = new Text(q1.getCategory());
        Text question1 = new Text(q1.getQuestion());
        Button answer11 = new Button(q1.getPossibleAnswers()[0]);
        Button answer12 = new Button(q1.getPossibleAnswers()[1]);
        Button answer13 = new Button(q1.getPossibleAnswers()[2]);
        Button answer14 = new Button(q1.getPossibleAnswers()[3]);
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
        game1.getPlayer().getVBox().getChildren().addAll(player1,image1,category1,question1,hbox1);
        bp.setCenter(game1.getPlayer().getVBox());
//question for the player 2
        QuestionClassWithImage q2 = game2.getARandomQuestionWithImage();
        Text player2 = new Text("Question for player 2");
        player1.setFont(Font.font("my", FontWeight.LIGHT, 15));
        ImageView image2 = new ImageView(new Image(q2.getPath()));
        Text category2 = new Text(q2.getCategory());
        Text question2 = new Text(q2.getQuestion());
        Button answer21 = new Button(q2.getPossibleAnswers()[0]);
        Button answer22 = new Button(q2.getPossibleAnswers()[1]);
        Button answer23 = new Button(q2.getPossibleAnswers()[2]);
        Button answer24 = new Button(q2.getPossibleAnswers()[3]);
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
        game2.getPlayer().getVBox().getChildren().addAll(player2,image2,category2,question2,hbox2);
        bp.setBottom(game2.getPlayer().getVBox());
//assign the handlers to the buttons
        answer11.setOnKeyPressed(new ThermometerKeyPressedWithImage(game1,game2,vbox,answers,q1,q2));
        answer12.setOnKeyPressed(new ThermometerKeyPressedWithImage(game1,game2,vbox,answers,q1,q2));
        answer13.setOnKeyPressed(new ThermometerKeyPressedWithImage(game1,game2,vbox,answers,q1,q2));
        answer14.setOnKeyPressed(new ThermometerKeyPressedWithImage(game1,game2,vbox,answers,q1,q2));
        answer21.setOnKeyPressed(new ThermometerKeyPressedWithImage(game1,game2,vbox,answers,q1,q2));
        answer22.setOnKeyPressed(new ThermometerKeyPressedWithImage(game1,game2,vbox,answers,q1,q2));
        answer23.setOnKeyPressed(new ThermometerKeyPressedWithImage(game1,game2,vbox,answers,q1,q2));
        answer24.setOnKeyPressed(new ThermometerKeyPressedWithImage(game1,game2,vbox,answers,q1,q2));
//put all the answers in an array
        answers.add(answer11);
        answers.add(answer12);
        answers.add(answer13);
        answers.add(answer14);
        answers.add(answer21);
        answers.add(answer22);
        answers.add(answer23);
        answers.add(answer24);
//display the questions to the players
        vbox.getChildren().addAll(bp);
        
    }
    
    
//show player 1 a question    
    public static void questionForPlayer1(ThermometerGame game1,ThermometerGame game2,MyVBox vbox,ArrayList<Button> answers,QuestionClass q2){
//remove the old answers from the array
        for(int i=0; i<4; i++)
            answers.remove(0);
//clear the vbox of player 1 from the previous contents
        MyVBox.clear(game1.getPlayer().getVBox());
//create a new question
        QuestionClass q = game1.getARandomQuestion();
        Text player1 = new Text("Question for player 1");
        player1.setFont(Font.font("my", FontWeight.LIGHT, 15));
        Text category1 = new Text(q.getCategory());
        Text question1 = new Text(q.getQuestion());
        Button answer11 = new Button(q.getPossibleAnswers()[0]);
        Button answer12 = new Button(q.getPossibleAnswers()[1]);
        Button answer13 = new Button(q.getPossibleAnswers()[2]);
        Button answer14 = new Button(q.getPossibleAnswers()[3]);
//add the new answers to the array
        answers.add(0,answer11);
        answers.add(1,answer12);
        answers.add(2,answer13);
        answers.add(3,answer14);
//assign the handlers to the buttons-answers
        answer11.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q2));
        answer12.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q2));
        answer13.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q2));
        answer14.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q2));
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
//add the question and the answers to the vbox of the player
        game1.getPlayer().getVBox().getChildren().addAll(player1,category1,question1,hbox1);
    }
    
//show player 2 a question    
    public static void questionForPlayer2(ThermometerGame game1,ThermometerGame game2,MyVBox vbox,ArrayList<Button> answers,QuestionClass q1){
//remove the old answers from the array       
        for(int i=0; i<4; i++)
            answers.remove(4); 
//clear the vbox from the old contents
        MyVBox.clear(game2.getPlayer().getVBox());
//create a new question
        QuestionClass q = game2.getARandomQuestion();
        Text player2 = new Text("Question for player 1");
        player2.setFont(Font.font("my", FontWeight.LIGHT, 15));
        Text category2 = new Text(q.getCategory());
        Text question2 = new Text(q.getQuestion());
        Button answer21 = new Button(q.getPossibleAnswers()[0]);
        Button answer22 = new Button(q.getPossibleAnswers()[1]);
        Button answer23 = new Button(q.getPossibleAnswers()[2]);
        Button answer24 = new Button(q.getPossibleAnswers()[3]);
//add the new answers to the array
        answers.add(answer21);
        answers.add(answer22);
        answers.add(answer23);
        answers.add(answer24);
//assign the handlers to the buttons-answers
        answer21.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q1));
        answer22.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q1));
        answer23.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q1));
        answer24.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q1));
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
//add the question and the answers to the vbox of the player
        game2.getPlayer().getVBox().getChildren().addAll(player2,category2,question2,hbox2);
    }
    
//show a question with image to player 1   
    public static void questionWithImageForPlayer1(ThermometerGame game1,ThermometerGame game2,MyVBox vbox,ArrayList<Button> answers,QuestionClassWithImage q2){
//remove the old answers from the array        
        for(int i=0; i<4; i++)
            answers.remove(0);
//clear the vbox of the player from its contents
        MyVBox.clear(game1.getPlayer().getVBox());
//create a new question with image
        QuestionClassWithImage q = game1.getARandomQuestionWithImage();
        Text player1 = new Text("Question for player 1");
        player1.setFont(Font.font("my", FontWeight.LIGHT, 15));
        ImageView image = new ImageView(new Image(q.getPath()));
        Text category1 = new Text(q.getCategory());
        Text question1 = new Text(q.getQuestion());
        Button answer11 = new Button(q.getPossibleAnswers()[0]);
        Button answer12 = new Button(q.getPossibleAnswers()[1]);
        Button answer13 = new Button(q.getPossibleAnswers()[2]);
        Button answer14 = new Button(q.getPossibleAnswers()[3]);
//add the new answers to the array
        answers.add(0,answer11);
        answers.add(1,answer12);
        answers.add(2,answer13);
        answers.add(3,answer14);
//assign the handlers to the buttons-answers
        answer11.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q2));
        answer12.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q2));
        answer13.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q2));
        answer14.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q2));
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
//add the question and the answers to the vbox of the player
        game1.getPlayer().getVBox().getChildren().addAll(player1,image,category1,question1,hbox1);
    }
    
//show a question with image to player 2   
    public static void questionWithImageForPlayer2(ThermometerGame game1,ThermometerGame game2,MyVBox vbox,ArrayList<Button> answers,QuestionClassWithImage q1){
//remove the old answers from the array       
        for(int i=0; i<4; i++)
            answers.remove(4);
//clear the player's vbox from its old contenets
        MyVBox.clear(game1.getPlayer().getVBox());
//create a new question with image
        QuestionClassWithImage q = game1.getARandomQuestionWithImage();
        Text player2 = new Text("Question for player 1");
        player2.setFont(Font.font("my", FontWeight.LIGHT, 15));
        ImageView image = new ImageView(new Image(q.getPath()));
        Text category2 = new Text(q.getCategory());
        Text question2 = new Text(q.getQuestion());
        Button answer21 = new Button(q.getPossibleAnswers()[0]);
        Button answer22 = new Button(q.getPossibleAnswers()[1]);
        Button answer23 = new Button(q.getPossibleAnswers()[2]);
        Button answer24 = new Button(q.getPossibleAnswers()[3]);
//add the new answers to the array
        answers.add(answer21);
        answers.add(answer22);
        answers.add(answer23);
        answers.add(answer24);
//assign the handlers to the buttons-answers
        answer21.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q1));
        answer22.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q1));
        answer23.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q1));
        answer24.setOnKeyPressed(new ThermometerKeyPressed(game1,game2,vbox,answers,q,q1));
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
//add the question and the answers to the vbox of the player
        game1.getPlayer().getVBox().getChildren().addAll(player2,image,category2,question2,hbox2);
    }
    
    
    
    
    
//setters and getters for the class
    public void incControl(){
        control++;
    }
    public int getControl(){
        return control;
    }
    public void resetControl(){
        control=0;
    }
    public static BorderPane getBp(){
        return BP;
    }
    public static ArrayList<Button> getAnswers(){
        return ANSWERS;
    }
    public static int getRounds(){
        return thermometerRounds;
    }
    public static void setThermometerRounds(int x){
        thermometerRounds=x;
    }
    public static void incThermometerRounds(){
        thermometerRounds++;
    }
    
    
}
