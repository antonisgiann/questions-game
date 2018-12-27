/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author cataclysm
 */
public class QuickAnswerKeyPres implements EventHandler<KeyEvent>{
    private final QuickAnswerGame game;
    private final MyVBox vbox;
    private final QuestionClass q;
    private final Player player1;
    private final Player player2;
    private final ArrayList<Button> answers;
    private boolean first=true;
    public QuickAnswerKeyPres(QuickAnswerGame game,MyVBox vbox,QuestionClass q,Player player1,Player player2,ArrayList<Button> answers){
        this.game=game;
        this.vbox=vbox;
        this.q=q;
        this.player1=player1;
        this.player2=player2;
        this.answers=answers;
    }
@Override
    public void handle(KeyEvent e){
        switch(e.getCode()){
            //player 1 choose first answer
            case Q:
                //check if player 1 answer again the question
                if(player1.getControl() == 0)                
               {
                    player1.incControl();
                    //check if the answer is correct
                    if(answers.get(0).getText().equals(q.getAnswer()))
                    {
                        //check if the player was the first to answer
                        if(first)
                        {
                            first=false;
                            player1.addPoints(1000);
                        }
                        else
                            player1.addPoints(500);
                    }
                    //check if player 2 answered
                    if( player2.getControl() == 1 )
                    {
                        //check if the round has ended
                        if(game.getQuestionsNumb() == QuickAnswerGame.NUMBER_OF_QUESTIONS)
                        {
                           //check if the game has ended
                           if(game.getRoundsNumb() == QuickAnswerGame.NUMBER_OF_ROUNDS)
                           {
                               //show to the player that the game has ended
                               MyVBox.clear(vbox);
                               Text endOfTheGame = new Text("END OF THE GAME");
                               endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 20));
                               Button showP1 = new Button("show player's 1 points");
                               showP1.setOnAction(e1 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               Button showP2 = new Button("show player's 2 points");
                               showP2.setOnAction(e2 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               vbox.getChildren().addAll(endOfTheGame,showP1,showP2);
//save the statistics of the game to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       java.util.Date date = new java.util.Date();
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println(date);
                                           pw.println("player 1 has won the game");
                                       }
                                       else
                                       {
                                           pw.println(date);
                                           pw.println("player 2 has won the game");
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               
                           }
                           else
                           {
                               //save the statistics of the round to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println("player 1 has won round number "+game.getRoundsNumb());
                                       }
                                       else
                                       {
                                           pw.println("player 2 has won round number "+game.getRoundsNumb());
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               //start the new round
                               game.incRoundsNumb();
                               game.setQuestionsNumb(0);
                               player1.setPoints(0);
                               player2.setPoints(0);
                               MyVBox.clear(vbox);
                               QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                           }
                        }
                        else
                        {
                            //show a new question
                            MyVBox.clear(vbox);
                            QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                        }
                    }
                }
                break;
                //player 1 choose second answer
                case W:
                //check if the player has answered again
                if(player1.getControl() == 0)                
               {
                    player1.incControl();
                    //check if the player answered correct the question
                    if(answers.get(1).getText().equals(q.getAnswer()))
                    {
                        if(first)
                        {
                            first=false;
                            player1.addPoints(1000);
                        }
                        else
                            player1.addPoints(500);
                    }
                    //check if player2 has answered
                    if( player2.getControl() == 1 )
                    {
                        //check if the round has eneded
                        if(game.getQuestionsNumb() == QuickAnswerGame.NUMBER_OF_QUESTIONS)
                        {
                            //check if the game has ended
                           if(game.getRoundsNumb() == QuickAnswerGame.NUMBER_OF_ROUNDS)
                           {
                               MyVBox.clear(vbox);
                               Text endOfTheGame = new Text("END OF THE GAME");
                               endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 20));
                               Button showP1 = new Button("show player's 1 points");
                               showP1.setOnAction(e1 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               Button showP2 = new Button("show player's 2 points");
                               showP2.setOnAction(e2 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               vbox.getChildren().addAll(endOfTheGame,showP1,showP2);
//save the statistics of the game to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       java.util.Date date = new java.util.Date();
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println(date);
                                           pw.println("player 1 has won the game");
                                       }
                                       else
                                       {
                                           pw.println(date);
                                           pw.println("player 2 has won the game");
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               
                           }
                           else
                           {
                               //save the statistics of the round
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println("player 1 has won round number "+game.getRoundsNumb());
                                       }
                                       else
                                       {
                                           pw.println("player 2 has won round number "+game.getRoundsNumb());
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               //start the new round
                               game.incRoundsNumb();
                               game.setQuestionsNumb(0);
                               player1.setPoints(0);
                               player2.setPoints(0);
                               MyVBox.clear(vbox);
                               QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                           }
                        }
                        else
                        {
                            //show a new question
                            MyVBox.clear(vbox);
                            QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                        }
                    }
                }
                break;
                //player 1 choose third answer 
                case E:
                //check if the player has answered again
                if(player1.getControl() == 0)                
               {
                    player1.incControl();
                    //check if the answer is correct
                    if(answers.get(2).getText().equals(q.getAnswer()))
                    {
                        if(first)
                        {
                            first=false;
                            player1.addPoints(1000);
                        }
                        else
                            player1.addPoints(500);
                    }
                    //check if player 2 has answered 
                    if( player2.getControl() == 1 )
                    {
                        //check if the round has ended
                        if(game.getQuestionsNumb() == QuickAnswerGame.NUMBER_OF_QUESTIONS)
                        {
                            //check if the game has ended
                           if(game.getRoundsNumb() == QuickAnswerGame.NUMBER_OF_ROUNDS)
                           {
                               MyVBox.clear(vbox);
                               Text endOfTheGame = new Text("END OF THE GAME");
                               endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 20));
                               Button showP1 = new Button("show player's 1 points");
                               showP1.setOnAction(e1 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               Button showP2 = new Button("show player's 2 points");
                               showP2.setOnAction(e2 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               vbox.getChildren().addAll(endOfTheGame,showP1,showP2);
//save the statistics of the game to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       java.util.Date date = new java.util.Date();
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println(date);
                                           pw.println("player 1 has won the game");
                                       }
                                       else
                                       {
                                           pw.println(date);
                                           pw.println("player 2 has won the game");
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               
                           }
                           else
                           {
                               //save the statistics of the round
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println("player 1 has won round number "+game.getRoundsNumb());
                                       }
                                       else
                                       {
                                           pw.println("player 2 has won round number "+game.getRoundsNumb());
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               //start the new round
                               game.incRoundsNumb();
                               game.setQuestionsNumb(0);
                               player1.setPoints(0);
                               player2.setPoints(0);
                               MyVBox.clear(vbox);
                               QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                           }
                        }
                        else
                        {
                            //show a new question
                            MyVBox.clear(vbox);
                            QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                        }
                    }
                }
                break;
                //player 1 choose forth answer
                case R:
                //check if the player has answered again
                if(player1.getControl() == 0)                
               {
                    player1.incControl();
                    //check if the answer is correct
                    if(answers.get(3).getText().equals(q.getAnswer()))
                    {
                        if(first)
                        {
                            first=false;
                            player1.addPoints(1000);
                        }
                        else
                            player1.addPoints(500);
                    }
                    //check if player 2 has answered
                    if( player2.getControl() == 1 )
                    {
                        //check if the round has ended
                        if(game.getQuestionsNumb() == QuickAnswerGame.NUMBER_OF_QUESTIONS)
                        {
                            //check if the game has ended
                           if(game.getRoundsNumb() == QuickAnswerGame.NUMBER_OF_ROUNDS)
                           {
                               MyVBox.clear(vbox);
                               Text endOfTheGame = new Text("END OF THE GAME");
                               endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 20));
                               Button showP1 = new Button("show player's 1 points");
                               showP1.setOnAction(e1 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               Button showP2 = new Button("show player's 2 points");
                               showP2.setOnAction(e2 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               vbox.getChildren().addAll(endOfTheGame,showP1,showP2);
//save the statistics of the game to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       java.util.Date date = new java.util.Date();
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println(date);
                                           pw.println("player 1 has won the game");
                                       }
                                       else
                                       {
                                           pw.println(date);
                                           pw.println("player 2 has won the game");
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               
                           }
                           else
                           {
                               //save the statistics of the round
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println("player 1 has won round number "+game.getRoundsNumb());
                                       }
                                       else
                                       {
                                           pw.println("player 2 has won round number "+game.getRoundsNumb());
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               //start the new round
                               game.incRoundsNumb();
                               game.setQuestionsNumb(0);
                               player1.setPoints(0);
                               player2.setPoints(0);
                               MyVBox.clear(vbox);
                               QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                           }
                        }
                        else
                        {
                            //show a new question
                            MyVBox.clear(vbox);
                            QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                        }
                    }
                }
                break;
                //player 2 choose first answer
                case U:
                //check if player has answered again
                if(player2.getControl() == 0)                
               {
                    player2.incControl();
                    //check if the answer is correct
                    if(answers.get(4).getText().equals(q.getAnswer()))
                    {
                        if(first)
                        {
                            first=false;
                            player2.addPoints(1000);
                        }
                        else
                            player2.addPoints(500);
                    }
                    //check if player 1 has answered
                    if( player1.getControl() == 1 )
                    {
                        //check if the round has ended
                        if(game.getQuestionsNumb() == QuickAnswerGame.NUMBER_OF_QUESTIONS)
                        {
                            //check if the game has ended
                           if(game.getRoundsNumb() == QuickAnswerGame.NUMBER_OF_ROUNDS)
                           {
                               MyVBox.clear(vbox);
                               Text endOfTheGame = new Text("END OF THE GAME");
                               endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 20));
                               Button showP1 = new Button("show player's 1 points");
                               showP1.setOnAction(e1 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               Button showP2 = new Button("show player's 2 points");
                               showP2.setOnAction(e2 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               vbox.getChildren().addAll(endOfTheGame,showP1,showP2);
//save the statistics of the game to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       java.util.Date date = new java.util.Date();
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println(date);
                                           pw.println("player 1 has won the game");
                                       }
                                       else
                                       {
                                           pw.println(date);
                                           pw.println("player 2 has won the game");
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               
                           }
                           else
                           {
                               //save the statistics of the round
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println("player 1 has won round number "+game.getRoundsNumb());
                                       }
                                       else
                                       {
                                           pw.println("player 2 has won round number "+game.getRoundsNumb());
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               //start the new round
                               game.incRoundsNumb();
                               game.setQuestionsNumb(0);
                               player1.setPoints(0);
                               player2.setPoints(0);
                               MyVBox.clear(vbox);
                               QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                           }
                        }
                        else
                        {
                            //show a new question
                            MyVBox.clear(vbox);
                            QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                        }
                    }
                }
                break;
                //player 2 choose second answer
                case I:
                //check if player has answered again
                if(player2.getControl() == 0)                
               {
                    player2.incControl();
                    if(answers.get(5).getText().equals(q.getAnswer()))
                    {
                        if(first)
                        {
                            first=false;
                            player2.addPoints(1000);
                        }
                        else
                            player2.addPoints(500);
                    }
                    //check if player 1 has answered
                    if( player1.getControl() == 1 )
                    {
                        //check if the round has ended
                        if(game.getQuestionsNumb() == QuickAnswerGame.NUMBER_OF_QUESTIONS)
                        {
                            //check if the game has ended
                           if(game.getRoundsNumb() == QuickAnswerGame.NUMBER_OF_ROUNDS)
                           {
                               MyVBox.clear(vbox);
                               Text endOfTheGame = new Text("END OF THE GAME");
                               endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 20));
                               Button showP1 = new Button("show player's 1 points");
                               showP1.setOnAction(e1 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               Button showP2 = new Button("show player's 2 points");
                               showP2.setOnAction(e2 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               vbox.getChildren().addAll(endOfTheGame,showP1,showP2);
//save the statistics of the game to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       java.util.Date date = new java.util.Date();
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println(date);
                                           pw.println("player 1 has won the game");
                                       }
                                       else
                                       {
                                           pw.println(date);
                                           pw.println("player 2 has won the game");
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               
                           }
                           else
                           {
                               //save the statistics of the round
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println("player 1 has won round number "+game.getRoundsNumb());
                                       }
                                       else
                                       {
                                           pw.println("player 2 has won round number "+game.getRoundsNumb());
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }
                               //start the new round
                               game.incRoundsNumb();
                               game.setQuestionsNumb(0);
                               player1.setPoints(0);
                               player2.setPoints(0);
                               MyVBox.clear(vbox);
                               QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                           }
                        }
                        else
                        {
                            //show a new question
                            MyVBox.clear(vbox);
                            QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                        }
                    }
                }
                break;
                //player 2 choose the third answer
                case O:
                //check if player has anaswered again
                if(player2.getControl() == 0)                
               {
                    player2.incControl();
                    if(answers.get(6).getText().equals(q.getAnswer()))
                    {
                        if(first)
                        {
                            first=false;
                            player2.addPoints(1000);
                        }
                        else
                            player2.addPoints(500);
                    }
                    //check if player 1 has answered
                    if( player1.getControl() == 1 )
                    {
                        //check if the round has ended
                        if(game.getQuestionsNumb() == QuickAnswerGame.NUMBER_OF_QUESTIONS)
                        {
                           //check if the game has ended
                           if(game.getRoundsNumb() == QuickAnswerGame.NUMBER_OF_ROUNDS)
                           {
                               MyVBox.clear(vbox);
                               Text endOfTheGame = new Text("END OF THE GAME");
                               endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 20));
                               Button showP1 = new Button("show player's 1 points");
                               showP1.setOnAction(e1 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               Button showP2 = new Button("show player's 2 points");
                               showP2.setOnAction(e2 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               vbox.getChildren().addAll(endOfTheGame,showP1,showP2);
//save the statistics of the game to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       java.util.Date date = new java.util.Date();
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println(date);
                                           pw.println("player 1 has won the game");
                                       }
                                       else
                                       {
                                           pw.println(date);
                                           pw.println("player 2 has won the game");
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               
                           }
                           else
                           {
                               //save the statistics of the round
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println("player 1 has won round number "+game.getRoundsNumb());
                                       }
                                       else
                                       {
                                           pw.println("player 2 has won round number "+game.getRoundsNumb());
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               //start the new round
                               game.incRoundsNumb();
                               game.setQuestionsNumb(0);
                               player1.setPoints(0);
                               player2.setPoints(0);
                               MyVBox.clear(vbox);
                               QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                           }
                        }
                        else
                        {
                            //show a new question
                            MyVBox.clear(vbox);
                            QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                        }
                    }
                }
                break;
                //player 2 choose the forth answer
                case P:
                //check if player has answered again
                if(player2.getControl() == 0)                
               {
                    player2.incControl();
                    if(answers.get(7).getText().equals(q.getAnswer()))
                    {
                        if(first)
                        {
                            first=false;
                            player2.addPoints(1000);
                        }
                        else
                            player2.addPoints(500);
                    }
                    //check if player 1 has answered
                    if( player1.getControl() == 1 )
                    {
                        //check if the round has ended
                        if(game.getQuestionsNumb() == QuickAnswerGame.NUMBER_OF_QUESTIONS)
                        {
                            //check if the game has ended
                           if(game.getRoundsNumb() == QuickAnswerGame.NUMBER_OF_ROUNDS)
                           {
                               MyVBox.clear(vbox);
                               Text endOfTheGame = new Text("END OF THE GAME");
                               endOfTheGame.setFont(Font.font("my", FontWeight.BOLD, 20));
                               Button showP1 = new Button("show player's 1 points");
                               showP1.setOnAction(e1 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               Button showP2 = new Button("show player's 2 points");
                               showP2.setOnAction(e2 ->{
                               Stage stage = new Stage();
                               Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
                               HBox hbox = new HBox();
                               hbox.setAlignment(Pos.CENTER);
                               hbox.getChildren().add(showPoints);
                               stage.setScene(new Scene(hbox,300,300));
                               stage.show();
                               });
                               vbox.getChildren().addAll(endOfTheGame,showP1,showP2);
//save the statistics of the game to the output file
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       java.util.Date date = new java.util.Date();
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println(date);
                                           pw.println("player 1 has won the game");
                                       }
                                       else
                                       {
                                           pw.println(date);
                                           pw.println("player 2 has won the game");
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }  
                               
                           }
                           else
                           {
                               //save the statistics of the round
                               try(FileWriter fw = new FileWriter(game.getOutputFile(),true);
                                   BufferedWriter bw = new BufferedWriter(fw);
                                   PrintWriter pw = new PrintWriter(bw))
                                   {
                                       if( player1.getPoints() > player2.getPoints() )
                                       {
                                           pw.println("player 1 has won round number "+game.getRoundsNumb());
                                       }
                                       else
                                       {
                                           pw.println("player 2 has won round number "+game.getRoundsNumb());
                                       }
                                   }
                               catch(IOException exc){
                                       System.out.println(exc.toString());
                                   }
                               //start the new round
                               game.incRoundsNumb();
                               game.setQuestionsNumb(0);
                               player1.setPoints(0);
                               player2.setPoints(0);
                               MyVBox.clear(vbox);
                               QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                           }
                        }
                        else
                        {
                            //show a new question
                            MyVBox.clear(vbox);
                            QuickAnswerGame.questionForPlayers(game, player1, player2, vbox, answers);
                        }
                    }
                }
                break;
        }
    }
}