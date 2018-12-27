/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author cataclysm
 */
public class ThermometerKeyPressed implements EventHandler<KeyEvent>{
    private final ThermometerGame game1;
    private final ThermometerGame game2;
    private final MyVBox vbox;
    private final ArrayList<Button> answers;
    private final QuestionClass q1;
    private final QuestionClass q2;
    public ThermometerKeyPressed(ThermometerGame game1,ThermometerGame game2,MyVBox vbox,ArrayList<Button> answers,QuestionClass q1,QuestionClass q2){
        this.game1=game1;
        this.game2=game2;
        this.vbox=vbox;
        this.answers=answers;
        this.q1=q1;
        this.q2=q2;
    }
    @Override
    public void handle(KeyEvent e){
                 
                    switch(e.getCode()){
//check if player 1 gave as an answer the first possible answer
                     case Q:   
//if the answer of the player 1 is correct do the above
                         if( answers.get(0).getText().equals(q1.getAnswer()) || answers.get(0).getText().equals(q2.getAnswer())){
                         game1.getPlayer().incCorrectAnswersNumb();
                         if( game1.getPlayer().getCorrectAnswersNumb() == 5 )
                         {
//add 5000 points to the player 1 for answering correct 5 questions
                             game1.getPlayer().addPoints(5000);
//display a message to inform the players that the game has ended if this was the last round
                             if( ThermometerGame.getRounds() == CorrectAnswerGame.NUMBER_OF_ROUNDS ){
//save the statistics of the game to a file
                               File file = new File("thermometerGame statistics");
                               try(
                                 FileWriter fw = new FileWriter(file,true);
                                 BufferedWriter bw = new BufferedWriter(fw);
                                 PrintWriter pw = new PrintWriter(bw)){
                                 java.util.Date date = new java.util.Date();
                                 pw.println(date);
                                 pw.println("Player 1 has accumulated "+game1.getPlayer().getPoints()+" points");
                                 pw.println("Player 2 has accumulated "+game2.getPlayer().getPoints()+" points");
                                 if(game1.getPlayer().getPoints() > game2.getPlayer().getPoints())
                                     pw.println("player 1 has won the game");
                                 else
                                     pw.println("player 2 has won the game");
                               }
                               catch(IOException exc){
                                   System.out.println(exc.toString());
                               }
                                 MyVBox.clear(vbox);
                                 Text endOfTheGame = new Text("END OF THE GAME");
                                 endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
//create a button so player 1 can see his points at the end of the game
                                 Button showPoints1 = new Button("show player's 1 points");
                                 showPoints1.setOnAction(e1 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 1 has "+game1.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
//create a button so player 2 can se his points at the end of the game
                                 Button showPoints2 = new Button("show player's 2 points");
                                 showPoints2.setOnAction(e2 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 2 has "+game2.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
                                 vbox.getChildren().addAll(endOfTheGame,showPoints1,showPoints2);
                             }
                             else
                             {
                                  MyVBox.clear(vbox);
                                  ThermometerGame.incThermometerRounds();
                                  Text newRound = new Text("NEW ROUND\nRound number "+ThermometerGame.getRounds());
                                  newRound.setFont(Font.font("my",FontWeight.BOLD,20));
                                  vbox.getChildren().add(newRound);
//reset the questions and start the next round
                                  try{
                                      game1.getAllTheQuestions();
                                      game2.getAllTheQuestions();
                                  }
                                   catch(FileNotFoundException exc){
                                      System.out.println(exc.toString());
                                  }
                                  ThermometerGame.showQuestionsFirstTime(game1, game2, vbox, ThermometerGame.getBp(), answers);
                             }
                         }
                         else
                         {
//display a new question for player 1
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                         }
                     }
//if the answer of the player 1 was wrong do the above
                     else{
//display a  new question to the player 1
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                     }
                         break;
//check if player 1 gave as an answer the second possible answer                         
                     case W:   
//if answer of the player 1 is correct do the above 
                         if( answers.get(1).getText().equals(q1.getAnswer()) || answers.get(1).getText().equals(q2.getAnswer())){
                         game1.getPlayer().incCorrectAnswersNumb();
                         if( game1.getPlayer().getCorrectAnswersNumb() == 5 )
                         {
//add 5000 points to the player 1 for answering correct 5 questions
                            game1.getPlayer().addPoints(5000); 
//display a message to inform the players that the game has ended if this was the last round
                             if( ThermometerGame.getRounds() == CorrectAnswerGame.NUMBER_OF_ROUNDS ){
//save the statistics of the game to a file
                                 File file = new File("thermometerGame statistics");
                               try(
                                 FileWriter fw = new FileWriter(file,true);
                                 BufferedWriter bw = new BufferedWriter(fw);
                                 PrintWriter pw = new PrintWriter(bw)){
                                 java.util.Date date = new java.util.Date();
                                 pw.println(date);
                                 pw.println("Player 1 has accumulated "+game1.getPlayer().getPoints()+" points");
                                 pw.println("Player 2 has accumulated "+game2.getPlayer().getPoints()+" points");
                                 if(game1.getPlayer().getPoints() > game2.getPlayer().getPoints())
                                     pw.println("player 1 has won the game");
                                 else
                                     pw.println("player 2 has won the game");
                               }
                               catch(IOException exc){
                                   System.out.println(exc.toString());
                               }
                               
                                 MyVBox.clear(vbox);
                                 Text endOfTheGame = new Text("END OF THE GAME");
                                 endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
//create a button so player 1 can see his points at the end of the game
                                 Button showPoints1 = new Button("show player's 1 points");
                                 showPoints1.setOnAction(e1 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 1 has "+game1.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
//create a button so player 2 can se his points at the end of the game
                                 Button showPoints2 = new Button("show player's 2 points");
                                 showPoints2.setOnAction(e2 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 2 has "+game2.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
                                 vbox.getChildren().addAll(endOfTheGame,showPoints1,showPoints2);
                             }
                             else
                             {
                                  MyVBox.clear(vbox);
                                  ThermometerGame.incThermometerRounds();
                                  Text newRound = new Text("NEW ROUND\nRound number "+ThermometerGame.getRounds());
                                  newRound.setFont(Font.font("my",FontWeight.BOLD,20));
                                  vbox.getChildren().add(newRound);
//reset the questions for the next round
                                  try{
                                      game1.getAllTheQuestions();
                                      game2.getAllTheQuestions();
                                  }
                                   catch(FileNotFoundException exc){
                                      System.out.println(exc.toString());
                                  }
                                  ThermometerGame.showQuestionsFirstTime(game1, game2, vbox, ThermometerGame.getBp(), answers);
                             }
                         }
                         else
                         {
//display a new question for player 1
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                         }
                     }
//if the answer of the player 1 was wrong do the above
                     else{
//display a  new question to the player 1
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                     }
                
                         break;
//check if player 1 gave as answer the third possible answer
                     case E:   
                         if( answers.get(2).getText().equals(q1.getAnswer()) || answers.get(2).getText().equals(q2.getAnswer())){
                         game1.getPlayer().incCorrectAnswersNumb();
                         if( game1.getPlayer().getCorrectAnswersNumb() == 5 )
                         {
//add 5000 points to the player 1 for answering correct 5 questions
                             game1.getPlayer().addPoints(5000);
//display a message to inform the players that the game has ended
                             if( ThermometerGame.getRounds() == CorrectAnswerGame.NUMBER_OF_ROUNDS ){
//save the statistics of the game to a file
                                 File file = new File("thermometerGame statistics");
                               try(
                                 FileWriter fw = new FileWriter(file,true);
                                 BufferedWriter bw = new BufferedWriter(fw);
                                 PrintWriter pw = new PrintWriter(bw)){
                                 java.util.Date date = new java.util.Date();
                                 pw.println(date);
                                 pw.println("Player 1 has accumulated "+game1.getPlayer().getPoints()+" points");
                                 pw.println("Player 2 has accumulated "+game2.getPlayer().getPoints()+" points");
                                 if(game1.getPlayer().getPoints() > game2.getPlayer().getPoints())
                                     pw.println("player 1 has won the game");
                                 else
                                     pw.println("player 2 has won the game");
                               }
                               catch(IOException exc){
                                   System.out.println(exc.toString());
                               }
                               
                                 MyVBox.clear(vbox);
                                 Text endOfTheGame = new Text("END OF THE GAME");
                                 endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
//create a button so player 1 can see his points at the end of the game
                                 Button showPoints1 = new Button("show player's 1 points");
                                 showPoints1.setOnAction(e1 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 1 has "+game1.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
//create a button so player 2 can se his points at the end of the game
                                 Button showPoints2 = new Button("show player's 2 points");
                                 showPoints2.setOnAction(e2 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 2 has "+game2.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
                                 vbox.getChildren().addAll(endOfTheGame,showPoints1,showPoints2);
                             }
                             else
                             {
//add the points to the player 1 for asnwering correct five answers
                                  game1.getPlayer().addPoints(5000);
                                  MyVBox.clear(vbox);
                                  ThermometerGame.incThermometerRounds();
                                  Text newRound = new Text("NEW ROUND\nRound number "+ThermometerGame.getRounds());
                                  newRound.setFont(Font.font("my",FontWeight.BOLD,20));
                                  vbox.getChildren().add(newRound);
//reset the questions and start the next round
                                  try{
                                      game1.getAllTheQuestions();
                                      game2.getAllTheQuestions();
                                  }
                                   catch(FileNotFoundException exc){
                                      System.out.println(exc.toString());
                                  }
                                  ThermometerGame.showQuestionsFirstTime(game1, game2, vbox, ThermometerGame.getBp(), answers);
                             }
                         }
                         else
                         {
//display a new question to the player 1  
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                         }
                     }
//if the answer of the player 1 was wrong do the above
                     else{
//display a  new question to the player 1
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                     }
             
                         break;
//check if player 1 gave as answer the fourth possible answer
                     case R:   if( answers.get(3).getText().equals(q1.getAnswer()) || answers.get(3).getText().equals(q2.getAnswer())){
                         game1.getPlayer().incCorrectAnswersNumb();
                         if( game1.getPlayer().getCorrectAnswersNumb() == 5 )
                         {
//add 5000 points to the player 1 for answering correct 5 questions
                             game1.getPlayer().addPoints(5000);
//display a message to inform the players that the game has ended if this was the last round
                             if( ThermometerGame.getRounds() == CorrectAnswerGame.NUMBER_OF_ROUNDS ){
//save the statistics of the game to a file
                                 File file = new File("thermometerGame statistics");
                               try(
                                 FileWriter fw = new FileWriter(file,true);
                                 BufferedWriter bw = new BufferedWriter(fw);
                                 PrintWriter pw = new PrintWriter(bw)){
                                 java.util.Date date = new java.util.Date();
                                 pw.println(date);
                                 pw.println("Player 1 has accumulated "+game1.getPlayer().getPoints()+" points");
                                 pw.println("Player 2 has accumulated "+game2.getPlayer().getPoints()+" points");
                                 if(game1.getPlayer().getPoints() > game2.getPlayer().getPoints())
                                     pw.println("player 1 has won the game");
                                 else
                                     pw.println("player 2 has won the game");
                               }
                               catch(IOException exc){
                                   System.out.println(exc.toString());
                               }
                               
                                 MyVBox.clear(vbox);
                                 Text endOfTheGame = new Text("END OF THE GAME");
                                 endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
//create a button so player 1 can see his points at the end of the game
                                 Button showPoints1 = new Button("show player's 1 points");
                                 showPoints1.setOnAction(e1 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 1 has "+game1.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
//create a button so player 2 can se his points at the end of the game
                                 Button showPoints2 = new Button("show player's 2 points");
                                 showPoints2.setOnAction(e2 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 2 has "+game2.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
                                 vbox.getChildren().addAll(endOfTheGame,showPoints1,showPoints2);
                             }
                             else
                             {
                                  game1.getPlayer().addPoints(5000);
                                  MyVBox.clear(vbox);
                                  ThermometerGame.incThermometerRounds();
                                  Text newRound = new Text("NEW ROUND\nRound number "+ThermometerGame.getRounds());
                                  newRound.setFont(Font.font("my",FontWeight.BOLD,20));
                                  vbox.getChildren().add(newRound);
//reset the questions and start the new round
                                  try{
                                      game1.getAllTheQuestions();
                                      game2.getAllTheQuestions();
                                  }
                                   catch(FileNotFoundException exc){
                                      System.out.println(exc.toString());
                                  }
                                  ThermometerGame.showQuestionsFirstTime(game1, game2, vbox, ThermometerGame.getBp(), answers);
                             }
                         }
                         else
                         {
//display a new question to the player 1  
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                         }
                     }
//if the answer of the player 1 was wrong 
                     else{
//display a  new question to the player 1
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                     }
             
                         break;
//in case player 2 choose the first answer                         
                     case U:   if( answers.get(4).getText().equals(q1.getAnswer()) || answers.get(4).getText().equals(q2.getAnswer())){
                         game2.getPlayer().incCorrectAnswersNumb();
                         if( game2.getPlayer().getCorrectAnswersNumb() == 5 )
                         {
//add 5000 points to the player 2 for answering correct 5 questions
                             game2.getPlayer().addPoints(5000);
//display a message to inform the players that the game has ended if this was the last round
                             if( ThermometerGame.getRounds() == CorrectAnswerGame.NUMBER_OF_ROUNDS ){
//save the statistics of the game to a file
                                 File file = new File("thermometerGame statistics");
                               try(
                                 FileWriter fw = new FileWriter(file,true);
                                 BufferedWriter bw = new BufferedWriter(fw);
                                 PrintWriter pw = new PrintWriter(bw)){
                                 java.util.Date date = new java.util.Date();
                                 pw.println(date);
                                 pw.println("Player 1 has accumulated "+game1.getPlayer().getPoints()+" points");
                                 pw.println("Player 2 has accumulated "+game2.getPlayer().getPoints()+" points");
                                 if(game1.getPlayer().getPoints() > game2.getPlayer().getPoints())
                                     pw.println("player 1 has won the game");
                                 else
                                     pw.println("player 2 has won the game");
                               }
                               catch(IOException exc){
                                   System.out.println(exc.toString());
                               }
                                 MyVBox.clear(vbox);
                                 Text endOfTheGame = new Text("END OF THE GAME");
                                 endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
//create a button so player 1 can see his points at the end of the game
                                 Button showPoints1 = new Button("show player's 1 points");
                                 showPoints1.setOnAction(e1 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 1 has "+game1.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
//create a button so player 2 can se his points at the end of the game
                                 Button showPoints2 = new Button("show player's 2 points");
                                 showPoints2.setOnAction(e2 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 2 has "+game2.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
                                 vbox.getChildren().addAll(endOfTheGame,showPoints1,showPoints2);
                             }
                             else
                             {
                                  MyVBox.clear(vbox);
                                  ThermometerGame.incThermometerRounds();
                                  Text newRound = new Text("NEW ROUND\nRound number "+ThermometerGame.getRounds());
                                  newRound.setFont(Font.font("my",FontWeight.BOLD,20));
                                  vbox.getChildren().add(newRound);
//reset the questions and start the next round
                                  try{
                                      game1.getAllTheQuestions();
                                      game2.getAllTheQuestions();
                                  }
                                   catch(FileNotFoundException exc){
                                      System.out.println(exc.toString());
                                  }
                                  ThermometerGame.showQuestionsFirstTime(game1, game2, vbox, ThermometerGame.getBp(), answers);
                             }
                         }
                         else
                         {
//display a question for the player 2
                             ThermometerGame.questionForPlayer2(game1, game2, vbox, answers, q1);
                         }
                     }

                     else{
//display a  new question to the player 2
                           ThermometerGame.questionForPlayer2(game1, game2, vbox, answers, q1);
                     }
                
                         break;
//in case player 2 choose the second answer                        
                     case I:  
                         if( answers.get(5).getText().equals(q1.getAnswer()) || answers.get(5).getText().equals(q2.getAnswer())){
                         game2.getPlayer().incCorrectAnswersNumb();
                         if( game2.getPlayer().getCorrectAnswersNumb() == 5 )
                         {
//add 5000 points to the player 2 for answering correct 5 questions
                             game2.getPlayer().addPoints(5000);
//display a message to inform the players that the game has ended if this was the last round
                             if( ThermometerGame.getRounds() == CorrectAnswerGame.NUMBER_OF_ROUNDS ){
//save the statistics of the game to a file
                                 File file = new File("thermometerGame statistics");
                               try(
                                 FileWriter fw = new FileWriter(file,true);
                                 BufferedWriter bw = new BufferedWriter(fw);
                                 PrintWriter pw = new PrintWriter(bw)){
                                 java.util.Date date = new java.util.Date();
                                 pw.println(date);
                                 pw.println("Player 1 has accumulated "+game1.getPlayer().getPoints()+" points");
                                 pw.println("Player 2 has accumulated "+game2.getPlayer().getPoints()+" points");
                                 if(game1.getPlayer().getPoints() > game2.getPlayer().getPoints())
                                     pw.println("player 1 has won the game");
                                 else
                                     pw.println("player 2 has won the game");
                               }
                               catch(IOException exc){
                                   System.out.println(exc.toString());
                               }
                                 MyVBox.clear(vbox);
                                 Text endOfTheGame = new Text("END OF THE GAME");
                                 endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
//create a button so player 1 can see his points at the end of the game
                                 Button showPoints1 = new Button("show player's 1 points");
                                 showPoints1.setOnAction(e1 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 1 has "+game1.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
//create a button so player 2 can se his points at the end of the game
                                 Button showPoints2 = new Button("show player's 2 points");
                                 showPoints2.setOnAction(e2 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 2 has "+game2.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
                                 vbox.getChildren().addAll(endOfTheGame,showPoints1,showPoints2);
                             }
                             else
                             {
                                  MyVBox.clear(vbox);
                                  ThermometerGame.incThermometerRounds();
                                  Text newRound = new Text("NEW ROUND\nRound number "+ThermometerGame.getRounds());
                                  newRound.setFont(Font.font("my",FontWeight.BOLD,20));
                                  vbox.getChildren().add(newRound);
//reset the questions for the next round
                                  try{
                                      game1.getAllTheQuestions();
                                      game2.getAllTheQuestions();
                                  }
                                   catch(FileNotFoundException exc){
                                      System.out.println(exc.toString());
                                  }
                                  ThermometerGame.showQuestionsFirstTime(game1, game2, vbox, ThermometerGame.getBp(), answers);
                             }
                         }
                         else
                         {
                             ThermometerGame.questionForPlayer2(game1, game2, vbox, answers, q1);
                         }
                     }

                     else{
//display a  new question to the player 2
                             ThermometerGame.questionForPlayer2(game1, game2, vbox, answers, q1);
                     }
                
                         break;
//in case player 2 choose the third answer                          
                     case O:   
                         if( answers.get(6).getText().equals(q1.getAnswer()) || answers.get(6).getText().equals(q2.getAnswer())){
                         game2.getPlayer().incCorrectAnswersNumb();
                         if( game2.getPlayer().getCorrectAnswersNumb() == 5 )
                         {
//add 5000 points to the player 2 for answering correct 5 questions
                             game2.getPlayer().addPoints(5000);
//display a message to inform the players that the game has ended if this was the last round
                             if( ThermometerGame.getRounds() == CorrectAnswerGame.NUMBER_OF_ROUNDS ){
//save the statistics of the game to a file
                                 File file = new File("thermometerGame statistics");
                               try(
                                 FileWriter fw = new FileWriter(file,true);
                                 BufferedWriter bw = new BufferedWriter(fw);
                                 PrintWriter pw = new PrintWriter(bw)){
                                 java.util.Date date = new java.util.Date();
                                 pw.println(date);
                                 pw.println("Player 1 has accumulated "+game1.getPlayer().getPoints()+" points");
                                 pw.println("Player 2 has accumulated "+game2.getPlayer().getPoints()+" points");
                                 if(game1.getPlayer().getPoints() > game2.getPlayer().getPoints())
                                     pw.println("player 1 has won the game");
                                 else
                                     pw.println("player 2 has won the game");
                               }
                               catch(IOException exc){
                                   System.out.println(exc.toString());
                               }
                                 MyVBox.clear(vbox);
                                 Text endOfTheGame = new Text("END OF THE GAME");
                                 endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
//create a button so player 1 can see his points at the end of the game
                                 Button showPoints1 = new Button("show player's 1 points");
                                 showPoints1.setOnAction(e1 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 1 has "+game1.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
//create a button so player 2 can se his points at the end of the game
                                 Button showPoints2 = new Button("show player's 2 points");
                                 showPoints2.setOnAction(e2 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 2 has "+game2.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
                                 vbox.getChildren().addAll(endOfTheGame,showPoints1,showPoints2);
                             }
                             else
                             {
                                  MyVBox.clear(vbox);
                                  ThermometerGame.incThermometerRounds();
                                  Text newRound = new Text("NEW ROUND\nRound number "+ThermometerGame.getRounds());
                                  newRound.setFont(Font.font("my",FontWeight.BOLD,20));
                                  vbox.getChildren().add(newRound);
//reset the questions and start the next round
                                  try{
                                      game1.getAllTheQuestions();
                                      game2.getAllTheQuestions();
                                  }
                                   catch(FileNotFoundException exc){
                                      System.out.println(exc.toString());
                                  }
                                  ThermometerGame.showQuestionsFirstTime(game1, game2, vbox, ThermometerGame.getBp(), answers);
                             }
                         }
                         else
                         {
//display a new question for player 2
                             ThermometerGame.questionForPlayer2(game1, game2, vbox, answers, q1);
                         }
                     }

                     else{
//display a  new question to the player 2
                             ThermometerGame.questionForPlayer1(game1, game2, vbox, answers, q2);
                     }
                
                         break;
//in case player 2 choose the forth answer                          
                     case P:   
                         if( answers.get(7).getText().equals(q1.getAnswer()) || answers.get(7).getText().equals(q2.getAnswer())){
                         game2.getPlayer().incCorrectAnswersNumb();
                         if( game2.getPlayer().getCorrectAnswersNumb() == 5 )
                         {
//add 5000 points to the player 2 for answering correct 5 questions
                             game2.getPlayer().addPoints(5000);
//display a message to inform the players that the game has ended if this was the last round
                             if( ThermometerGame.getRounds() == CorrectAnswerGame.NUMBER_OF_ROUNDS ){
//save the statistics of the game to a file
                                 File file = new File("thermometerGame statistics");
                               try(
                                 FileWriter fw = new FileWriter(file,true);
                                 BufferedWriter bw = new BufferedWriter(fw);
                                 PrintWriter pw = new PrintWriter(bw)){
                                 java.util.Date date = new java.util.Date();
                                 pw.println(date);
                                 pw.println("Player 1 has accumulated "+game1.getPlayer().getPoints()+" points");
                                 pw.println("Player 2 has accumulated "+game2.getPlayer().getPoints()+" points");
                                 if(game1.getPlayer().getPoints() > game2.getPlayer().getPoints())
                                     pw.println("player 1 has won the game");
                                 else
                                     pw.println("player 2 has won the game");
                               }
                               catch(IOException exc){
                                   System.out.println(exc.toString());
                               }
                                 MyVBox.clear(vbox);
                                 Text endOfTheGame = new Text("END OF THE GAME");
                                 endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 25));
//create a button so player 1 can see his points at the end of the game
                                 Button showPoints1 = new Button("show player's 1 points");
                                 showPoints1.setOnAction(e1 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 1 has "+game1.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
//create a button so player 2 can se his points at the end of the game
                                 Button showPoints2 = new Button("show player's 2 points");
                                 showPoints2.setOnAction(e2 -> {
                                    Stage stage = new Stage();
                                    Text showPoints = new Text("the player 2 has "+game2.getPlayer().getPoints()+" points");
                                    HBox hbox = new HBox();
                                    hbox.setAlignment(Pos.CENTER);
                                    hbox.getChildren().add(showPoints);
                                    stage.setScene(new Scene(hbox,300,300));
                                    stage.show();
                                 });
                                 vbox.getChildren().addAll(endOfTheGame,showPoints1,showPoints2);
                             }
                             else
                             {
                                  MyVBox.clear(vbox);
                                  ThermometerGame.incThermometerRounds();
                                  Text newRound = new Text("NEW ROUND\nRound number "+ThermometerGame.getRounds());
                                  newRound.setFont(Font.font("my",FontWeight.BOLD,20));
                                  vbox.getChildren().add(newRound);
//reset the questions and start the next round
                                  try{
                                      game1.getAllTheQuestions();
                                      game2.getAllTheQuestions();
                                  }
                                   catch(FileNotFoundException exc){
                                      System.out.println(exc.toString());
                                  }
                                  ThermometerGame.showQuestionsFirstTime(game1, game2, vbox, ThermometerGame.getBp(), answers);
                             }
                         }
                         else
                         {
//display a new question for player 2
                             ThermometerGame.questionForPlayer2(game1, game2, vbox, answers, q1);
                         }
                     }

                     else{
//display a  new question to the player 2
                             ThermometerGame.questionForPlayer2(game1, game2, vbox, answers, q1);
                     }
                         
                         break;
                    }
                 
    }
 
    
    
}
