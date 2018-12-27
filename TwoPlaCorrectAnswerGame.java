/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.image.*;

/**
 *
 * @author cataclysm
 */
public class TwoPlaCorrectAnswerGame extends CorrectAnswerGame {
    private final Player player2;
    private int control=0;
    public TwoPlaCorrectAnswerGame(Player player,Player player2,boolean withImage,File inputFile) throws FileNotFoundException{
        super(player,withImage,inputFile);
        this.player2=player2;
        super.setOutputFile("two players correct answer game stats");
    }
       
    
    
//show a question for both players   
    public static void displayAquestionTwoPlayers(TwoPlaCorrectAnswerGame game,MyVBox vbox){
       //get a question from all the questions
       QuestionClass q = game.getARandomQuestion();
       game.incQuestionsNumb();
       //create the texts and the buttons for the question and the possible answers
       Text category = new Text(q.getCategory());
       Text question = new Text(q.getQuestion());
       Text player1 = new Text("PLAYER 1");
       Button answer11 = new Button(q.getPossibleAnswers()[0]);
       Button answer12 = new Button(q.getPossibleAnswers()[1]);
       Button answer13 = new Button(q.getPossibleAnswers()[2]);
       Button answer14 = new Button(q.getPossibleAnswers()[3]);
       Button[] p1Answers = {answer11,answer12,answer13,answer14};
       Text player2 = new Text("PLAYER 2");
       Button answer21 = new Button(q.getPossibleAnswers()[0]);
       Button answer22 = new Button(q.getPossibleAnswers()[1]);
       Button answer23 = new Button(q.getPossibleAnswers()[2]);
       Button answer24 = new Button(q.getPossibleAnswers()[3]);
       Button[] p2Answers = {answer21,answer22,answer23,answer24};
       //create the button to show the players points
       Button showPlayer1 = new Button("show player's 1 points");
       Button showPlayer2 = new Button("show player's 2 points");
       showPlayer1.setOnAction(e1 -> {
           Stage stage = new Stage();
           Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
           HBox hbox = new HBox();
           hbox.setAlignment(Pos.CENTER);
           hbox.getChildren().add(p1Points);
           stage.setScene(new Scene(hbox,300,300));
           stage.show();
       });
       showPlayer2.setOnAction(e2 -> {
           Stage stage = new Stage();
           Text p2Points = new Text("player 2 points are "+game.getPlayer2().getPoints());
           HBox hbox = new HBox();
           hbox.setAlignment(Pos.CENTER);
           hbox.getChildren().add(p2Points);
           stage.setScene(new Scene(hbox,300,300));
           stage.show();
       });
       //assign the right handlers to each button
       for(int i=0; i<4; i++)
       {
           if(q.getAnswer().equals(p1Answers[i].getText()))
               p1Answers[i].setOnAction(new TwoPlaCorrectAnswer(game,vbox,game.getPlayer()));
           else
               p1Answers[i].setOnAction(new TwoPlaWrongAnswer(game,vbox));
       }
       for(int i=0; i<4; i++)
       {
           if(q.getAnswer().equals(p2Answers[i].getText()))
               p2Answers[i].setOnAction(new TwoPlaCorrectAnswer(game,vbox,game.getPlayer2()));
           else
               p2Answers[i].setOnAction(new TwoPlaWrongAnswer(game,vbox));
       }
       //create two hboxs for the answers to be displayed horizontal
       HBox hbox1 = new HBox(10);
       hbox1.setAlignment(Pos.CENTER);
       HBox hbox2 = new HBox(10);
       hbox2.setAlignment(Pos.CENTER);
       hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
       hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
       //add all of the above to the vbox
       vbox.getChildren().addAll(category,question,player1,hbox1,player2,hbox2,showPlayer1,showPlayer2);
    }
    
    
    public static void displayAquestionWithImageTwoPlayers(TwoPlaCorrectAnswerGame game,MyVBox vbox){
       //get a question from all the questions
       QuestionClassWithImage q = game.getARandomQuestionWithImage();
       game.incQuestionsNumb();
       //create the image node,the texts and the buttons for the question and the possible answers
       ImageView image = new ImageView(new Image(q.getPath()));
       Text category = new Text(q.getCategory());
       Text question = new Text(q.getQuestion());
       Text player1 = new Text("PLAYER 1");
       Button answer11 = new Button(q.getPossibleAnswers()[0]);
       Button answer12 = new Button(q.getPossibleAnswers()[1]);
       Button answer13 = new Button(q.getPossibleAnswers()[2]);
       Button answer14 = new Button(q.getPossibleAnswers()[3]);
       Button[] p1Answers = {answer11,answer12,answer13,answer14};
       Text player2 = new Text("PLAYER 2");
       Button answer21 = new Button(q.getPossibleAnswers()[0]);
       Button answer22 = new Button(q.getPossibleAnswers()[1]);
       Button answer23 = new Button(q.getPossibleAnswers()[2]);
       Button answer24 = new Button(q.getPossibleAnswers()[3]);
       Button[] p2Answers = {answer21,answer22,answer23,answer24};
       //create the button to show the players points
       Button showPlayer1 = new Button("show player's 1 points");
       Button showPlayer2 = new Button("show player's 2 points");
       showPlayer1.setOnAction(e1 -> {
           Stage stage = new Stage();
           Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
           HBox hbox = new HBox();
           hbox.setAlignment(Pos.CENTER);
           hbox.getChildren().add(p1Points);
           stage.setScene(new Scene(hbox,300,300));
           stage.show();
       });
       showPlayer2.setOnAction(e2 -> {
           Stage stage = new Stage();
           Text p2Points = new Text("player 2 points are "+game.getPlayer2().getPoints());
           HBox hbox = new HBox();
           hbox.setAlignment(Pos.CENTER);
           hbox.getChildren().add(p2Points);
           stage.setScene(new Scene(hbox,300,300));
           stage.show();
       });
       //assign the right handlers to each button
       for(int i=0; i<4; i++)
       {
           if(q.getAnswer().equals(p1Answers[i].getText()))
               p1Answers[i].setOnAction(new TwoPlaCorrectAnswerWithImage(game,vbox,game.getPlayer()));
           else
               p1Answers[i].setOnAction(new TwoPlaWrongAnswerWithImage(game,vbox));
       }
       for(int i=0; i<4; i++)
       {
           if(q.getAnswer().equals(p2Answers[i].getText()))
               p2Answers[i].setOnAction(new TwoPlaCorrectAnswerWithImage(game,vbox,game.getPlayer2()));
           else
               p2Answers[i].setOnAction(new TwoPlaWrongAnswerWithImage(game,vbox));
       }       
       //create the hboxs for the possible answers to be displayed horizontal
       HBox hbox1 = new HBox(10);
       HBox hbox2 = new HBox(10);
       hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
       hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
       //add all of the above to the vbox
       vbox.getChildren().addAll(image,category,question,player1,hbox1,player2,hbox2,showPlayer1,showPlayer2); 
    }
    //settesrs and getters
    public int getControl(){
        return control;
    }
    public void resetControl(){
        control=0;
    }
    public void incControl(){
        control++;
    }   
    public Player getPlayer2(){
        return player2;
    }
}
