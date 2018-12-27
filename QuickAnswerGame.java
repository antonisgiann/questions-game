/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.image.*;

/**
 *
 * @author cataclysm
 */
public class QuickAnswerGame extends CorrectAnswerGame {
    private final Player player2;
    private int playersAnswered=0;
    private ArrayList<Button> answers;
    public QuickAnswerGame(Player player,Player player2,boolean withImage,File inputFile) throws FileNotFoundException{
        super(player,withImage,inputFile);
        this.player2=player2;
        super.setOutputFile("two players quick answer game stats");
        answers=new ArrayList<>();
    }
    
    
    public static void questionForPlayers(QuickAnswerGame game,Player player1,Player player2,MyVBox vbox,ArrayList<Button> answers){
    if(answers.size()==8)
    {
        for(int i=0; i<8; i++)
            answers.remove(0);
    }
        //get a random question for the palyers
        QuestionClass q = game.getARandomQuestion();
        game.incQuestionsNumb();
        //create a text for the category and one for the qeustion
        Text category = new Text(q.getCategory());
        Text question = new Text(q.getQuestion());
        //create the buttons for player 1
        Text p1 = new Text("For player 1");
        Button answer11 = new Button(q.getPossibleAnswers()[0]);
        Button answer12 = new Button(q.getPossibleAnswers()[1]);
        Button answer13 = new Button(q.getPossibleAnswers()[2]);
        Button answer14 = new Button(q.getPossibleAnswers()[3]);
        Button showP1 = new Button("show player's 1 points");
        showP1.setOnAction(e1->{
            Stage stage = new Stage();
            Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(showPoints);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
        //create the buttons for player 2
        Text p2 = new Text("For player 2");
        Button answer21 = new Button(q.getPossibleAnswers()[0]);
        Button answer22 = new Button(q.getPossibleAnswers()[1]);
        Button answer23 = new Button(q.getPossibleAnswers()[2]);
        Button answer24 = new Button(q.getPossibleAnswers()[3]);
        Button showP2 = new Button("show player'2 points");
        showP2.setOnAction(e1->{
            Stage stage = new Stage();
            Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(showPoints);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
        //create two hboxs to  hold the possible answers for each player
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
        //assing the handlers to the buttons
        answer11.setOnKeyPressed(new QuickAnswerKeyPres(game, vbox, q, player1, player2, answers));
        answer12.setOnKeyPressed(new QuickAnswerKeyPres(game, vbox, q, player1, player2, answers));
        answer13.setOnKeyPressed(new QuickAnswerKeyPres(game, vbox, q, player1, player2, answers));
        answer14.setOnKeyPressed(new QuickAnswerKeyPres(game, vbox, q, player1, player2, answers));
        answer21.setOnKeyPressed(new QuickAnswerKeyPres(game, vbox, q, player1, player2, answers));
        answer22.setOnKeyPressed(new QuickAnswerKeyPres(game, vbox, q, player1, player2, answers));
        answer23.setOnKeyPressed(new QuickAnswerKeyPres(game, vbox, q, player1, player2, answers));
        answer24.setOnKeyPressed(new QuickAnswerKeyPres(game, vbox, q, player1, player2, answers));
        //add all the buttons-answers to the array
        answers.add(answer11);
        answers.add(answer12);
        answers.add(answer13);
        answers.add(answer14);
        answers.add(answer21);
        answers.add(answer22);
        answers.add(answer23);
        answers.add(answer24);
        vbox.getChildren().addAll(category,question,p1,hbox1,p2,hbox2,showP1,showP2);
    }
    
    
    public static void questionWithImageForPlayers(QuickAnswerGame game,Player player1,Player player2,MyVBox vbox,ArrayList<Button> answers){
    if(answers.size()==8)
    {
        for(int i=0; i<8; i++)
            answers.remove(0);
    }
        //get a random question for the palyers
        QuestionClassWithImage q = game.getARandomQuestionWithImage();
        game.incQuestionsNumb();
        //create a text for the category and one for the qeustion
        ImageView image = new ImageView(new Image(q.getPath()));
        Text category = new Text(q.getCategory());
        Text question = new Text(q.getQuestion());
        //create the buttons for player 1
        Text p1 = new Text("For player 1");
        Button answer11 = new Button(q.getPossibleAnswers()[0]);
        Button answer12 = new Button(q.getPossibleAnswers()[1]);
        Button answer13 = new Button(q.getPossibleAnswers()[2]);
        Button answer14 = new Button(q.getPossibleAnswers()[3]);
        Button showP1 = new Button("show player's 1 points");
        showP1.setOnAction(e1->{
            Stage stage = new Stage();
            Text showPoints = new Text("the player 1 has "+player1.getPoints()+" points");
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(showPoints);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
        //create the buttons for player 2
        Text p2 = new Text("For player 2");
        Button answer21 = new Button(q.getPossibleAnswers()[0]);
        Button answer22 = new Button(q.getPossibleAnswers()[1]);
        Button answer23 = new Button(q.getPossibleAnswers()[2]);
        Button answer24 = new Button(q.getPossibleAnswers()[3]);
        Button showP2 = new Button("show player'2 points");
        showP2.setOnAction(e1->{
            Stage stage = new Stage();
            Text showPoints = new Text("the player 2 has "+player2.getPoints()+" points");
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(showPoints);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
        //create two hboxs to  hold the possible answers for each player
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
        //assing the handlers to the buttons
        answer11.setOnKeyPressed(new QuickAnswerKeyPresWithImage(game, vbox, q, player1, player2, answers));
        answer12.setOnKeyPressed(new QuickAnswerKeyPresWithImage(game, vbox, q, player1, player2, answers));
        answer13.setOnKeyPressed(new QuickAnswerKeyPresWithImage(game, vbox, q, player1, player2, answers));
        answer14.setOnKeyPressed(new QuickAnswerKeyPresWithImage(game, vbox, q, player1, player2, answers));
        answer21.setOnKeyPressed(new QuickAnswerKeyPresWithImage(game, vbox, q, player1, player2, answers));
        answer22.setOnKeyPressed(new QuickAnswerKeyPresWithImage(game, vbox, q, player1, player2, answers));
        answer23.setOnKeyPressed(new QuickAnswerKeyPresWithImage(game, vbox, q, player1, player2, answers));
        answer24.setOnKeyPressed(new QuickAnswerKeyPresWithImage(game, vbox, q, player1, player2, answers));
        //add all the buttons-answers to the array
        answers.add(answer11);
        answers.add(answer12);
        answers.add(answer13);
        answers.add(answer14);
        answers.add(answer21);
        answers.add(answer22);
        answers.add(answer23);
        answers.add(answer24);
        //add all to the vbox
        vbox.getChildren().addAll(image,category,question,p1,hbox1,p2,hbox2,showP1,showP2);
    }
    
    public ArrayList<Button> getAnswers(){
        return answers;
    }
    public void setPlayersAnswered(int x){
        playersAnswered=x;
    }
    public int getPlayersAnswered(){
        return playersAnswered;
    }
    public void incPlayersAnswered(){
        playersAnswered++;
    }
}
