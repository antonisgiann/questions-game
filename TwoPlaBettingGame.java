/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
/**
 *
 * @author cataclysm
 */
public class TwoPlaBettingGame extends BettingGame {
    private final Player player2;
    private int control=0;
    private int pointsPerAnswer2=0;
    public TwoPlaBettingGame(Player player,Player player2,boolean withImage,File inputFile) throws FileNotFoundException{
        super(player,withImage,inputFile);
        this.player2=player2;
        super.setOutputFile("two players betting game stats");
    }
    
    public static void showBetOptionsTwoPlayers(TwoPlaBettingGame game,MyVBox vbox){
        QuestionClass q = game.getARandomQuestion();
        game.incQuestionsNumb();
        Text category = new Text(q.getCategory());
//show the betting options for player 1
        Text player1Choice = new Text("For the player 1");
        player1Choice.setFont(Font.font("my", FontWeight.BOLD , 20));
        Text howMuch1 = new Text("how much do you want to bet");
//create the buttons for the options and assign the handlers for player 1
        Button p1250 = new Button("250");
        p1250.setOnAction( new TwoPlaBet250Handler(game,vbox,q,1));
        Button p1500 = new Button("500");
        p1500.setOnAction( new TwoPlaBet500Handler(game,vbox,q,1));
        Button p1750 = new Button("750");
        p1750.setOnAction( new TwoPlaBet750Handler(game,vbox,q,1));
        Button p11000 = new Button("1000");
        p11000.setOnAction( new TwoPlaBet1000Handler(game,vbox,q,1));
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(p1250,p1500,p1750,p11000);
//show the betting options for player 2
        Text player2Choice = new Text("For the player 2");
        player2Choice.setFont(Font.font("my", FontWeight.BOLD , 20));
        Text howMuch2 = new Text("how much do you want to bet");
//create the buttons for the options and assign the handlers for player 2
        Button p2250 = new Button("250");
        p2250.setOnAction( new TwoPlaBet250Handler(game,vbox,q,2));
        Button p2500 = new Button("500");
        p2500.setOnAction( new TwoPlaBet500Handler(game,vbox,q,2));
        Button p2750 = new Button("750");
        p2750.setOnAction( new TwoPlaBet750Handler(game,vbox,q,2));
        Button p21000 = new Button("1000");
        p21000.setOnAction( new TwoPlaBet1000Handler(game,vbox,q,2));
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(p2250,p2500,p2750,p21000);
//create two buttons so the players can see their points before they bet
        Button showPlayer1 = new Button("show player's 1 points");
        showPlayer1.setOnAction(e1 -> {
            Stage stage = new Stage();
            Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(p1Points);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
        Button showPlayer2 = new Button("show player's 2 points");
        showPlayer2.setOnAction(e2 -> {
            Stage stage = new Stage();
            Text p2Points = new Text("player 2 points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(p2Points);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
//add everything on the vbox for the options to be displayed
        vbox.getChildren().addAll(category,player1Choice,howMuch1,hbox1,player2Choice,howMuch2,hbox2,showPlayer1,showPlayer2);
    }
    
    public static void showBetOptionsWithImageTwoPlayers(TwoPlaBettingGame game,MyVBox vbox){
        QuestionClassWithImage q = game.getARandomQuestionWithImage();
        game.incQuestionsNumb();
        ImageView image = new ImageView(new Image(q.getPath()));
        Text category = new Text(q.getCategory());
//show the betting options for player 1
        Text player1Choice = new Text("For the player 1");
        player1Choice.setFont(Font.font("my", FontWeight.BOLD , 20));
        Text howMuch1 = new Text("how much do you want to bet");
//create the buttons for the options and assign the handlers for player 1
        Button p1250 = new Button("250");
        p1250.setOnAction( new TwoPlaBet250WithImage(game,vbox,q,1));
        Button p1500 = new Button("500");
        p1500.setOnAction( new TwoPlaBet500WithImage(game,vbox,q,1));
        Button p1750 = new Button("750");
        p1750.setOnAction( new TwoPlaBet750WithImage(game,vbox,q,1));
        Button p11000 = new Button("1000");
        p11000.setOnAction( new TwoPlaBet1000WithImage(game,vbox,q,1));
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(p1250,p1500,p1750,p11000);
//show the betting options for player 2
        Text player2Choice = new Text("For the player 2");
        player2Choice.setFont(Font.font("my", FontWeight.BOLD , 20));
        Text howMuch2 = new Text("how much do you want to bet");
//create the buttons for the options and assign the handlers for player 2
        Button p2250 = new Button("250");
        p2250.setOnAction( new TwoPlaBet250WithImage(game,vbox,q,2));
        Button p2500 = new Button("500");
        p2500.setOnAction( new TwoPlaBet500WithImage(game,vbox,q,2));
        Button p2750 = new Button("750");
        p2750.setOnAction( new TwoPlaBet750WithImage(game,vbox,q,2));
        Button p21000 = new Button("1000");
        p21000.setOnAction( new TwoPlaBet1000WithImage(game,vbox,q,2));
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(p2250,p2500,p2750,p21000);
//create two buttons so the players can see their points before they bet
        Button showPlayer1 = new Button("show player's 1 points");
        showPlayer1.setOnAction(e1 -> {
            Stage stage = new Stage();
            Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(p1Points);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
        Button showPlayer2 = new Button("show player's 2 points");
        showPlayer2.setOnAction(e2 -> {
            Stage stage = new Stage();
            Text p2Points = new Text("player 2 points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(p2Points);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
//add everything on the vbox for the options to be displayed
        vbox.getChildren().addAll(image,category,player1Choice,howMuch1,hbox1,player2Choice,howMuch2,hbox2,showPlayer1,showPlayer2);
    }
    
    public static void betShowAquestionTwoPlayers(TwoPlaBettingGame game,MyVBox vbox,QuestionClass q){
        Text question = new Text(q.getQuestion());
// show the answers for the player 1
        Text player1 = new Text("For player number 1");
        player1.setFont(Font.font("my", FontWeight.BOLD , 20));
        Button answer11 = new Button(q.getPossibleAnswers()[0]);
        Button answer12 = new Button(q.getPossibleAnswers()[1]);
        Button answer13 = new Button(q.getPossibleAnswers()[2]);
        Button answer14 = new Button(q.getPossibleAnswers()[3]);
        Button[] p1Answers = {answer11,answer12,answer13,answer14};
//assign the handlers to the buttons for player 1
        for(int i=0; i<4; i++){
            if(p1Answers[i].getText().equals(q.getAnswer()))
                p1Answers[i].setOnAction( new TwoPlaBetCorrectAnswer(game,vbox,1));
            else
                p1Answers[i].setOnAction( new TwoPlaBetWrongAnswer(game,vbox,1));
        }
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
        Button showPlayer1 = new Button("show player's 1 points");
        showPlayer1.setOnAction(e -> {
            Stage stage = new Stage();
            Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(p1Points);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
//show the answers for the player 2
        Text player2 = new Text("For player number 2");
        player2.setFont(Font.font("my", FontWeight.BOLD , 20));
        Button answer21 = new Button(q.getPossibleAnswers()[0]);
        Button answer22 = new Button(q.getPossibleAnswers()[1]);
        Button answer23 = new Button(q.getPossibleAnswers()[2]);
        Button answer24 = new Button(q.getPossibleAnswers()[3]);
        Button[] p2Answers = {answer21,answer22,answer23,answer24};
//assign the handlers to the buttons for player 2
        for(int i=0; i<4; i++){
            if(p2Answers[i].getText().equals(q.getAnswer()))
                p2Answers[i].setOnAction( new TwoPlaBetCorrectAnswer(game,vbox,2));
            else
                p2Answers[i].setOnAction( new TwoPlaBetWrongAnswer(game,vbox,2));
        }
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
        Button showPlayer2 = new Button("show player's 2 points");
        showPlayer2.setOnAction(e -> {
            Stage stage = new Stage();
            Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(p1Points);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();    
        });  
//add everything to the vbox
        vbox.getChildren().addAll(question,player1,hbox1,player2,hbox2,showPlayer1,showPlayer2);
                
    }
    
    
    public static void betShowAquestionWithImageTwoPlayers(TwoPlaBettingGame game,MyVBox vbox,QuestionClassWithImage q){
        ImageView image = new ImageView(new Image(q.getPath()));
        Text question = new Text(q.getQuestion());
// show the answers for the player 1
        Text player1 = new Text("For player number 1");
        player1.setFont(Font.font("my", FontWeight.BOLD , 20));
        Button answer11 = new Button(q.getPossibleAnswers()[0]);
        Button answer12 = new Button(q.getPossibleAnswers()[1]);
        Button answer13 = new Button(q.getPossibleAnswers()[2]);
        Button answer14 = new Button(q.getPossibleAnswers()[3]);
        Button[] p1Answers = {answer11,answer12,answer13,answer14};
//assign the handlers to the buttons for player 1
        for(int i=0; i<4; i++){
            if(p1Answers[i].getText().equals(q.getAnswer()))
                p1Answers[i].setOnAction( new TwoPlaBetCorrectAnswerWithImage(game,vbox,1));
            else
                p1Answers[i].setOnAction( new TwoPlaBetWrongAnswerWithImage(game,vbox,1));
        }
        HBox hbox1 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(answer11,answer12,answer13,answer14);
        Button showPlayer1 = new Button("show player's 1 points");
        showPlayer1.setOnAction(e -> {
            Stage stage = new Stage();
            Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(p1Points);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();
        });
//show the answers for the player 2
        Text player2 = new Text("For player number 2");
        player2.setFont(Font.font("my", FontWeight.BOLD , 20));
        Button answer21 = new Button(q.getPossibleAnswers()[0]);
        Button answer22 = new Button(q.getPossibleAnswers()[1]);
        Button answer23 = new Button(q.getPossibleAnswers()[2]);
        Button answer24 = new Button(q.getPossibleAnswers()[3]);
        Button[] p2Answers = {answer21,answer22,answer23,answer24};
//assign the handlers to the buttons for player 2
        for(int i=0; i<4; i++){
            if(p2Answers[i].getText().equals(q.getAnswer()))
                p2Answers[i].setOnAction( new TwoPlaBetCorrectAnswerWithImage(game,vbox,2));
            else
                p2Answers[i].setOnAction( new TwoPlaBetWrongAnswerWithImage(game,vbox,2));
        }
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(answer21,answer22,answer23,answer24);
        Button showPlayer2 = new Button("show player's 2 points");
        showPlayer2.setOnAction(e -> {
            Stage stage = new Stage();
            Text p1Points = new Text("player 1 points are "+game.getPlayer().getPoints());
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(p1Points);
            stage.setScene(new Scene(hbox,300,300));
            stage.show();    
        });  
//add everything to the vbox
        vbox.getChildren().addAll(image,question,player1,hbox1,player2,hbox2,showPlayer1,showPlayer2);
                
    }
    
    public Player getPlayer2(){
        return player2;
    }
    public int getPointsPerAnswer2(){
        return pointsPerAnswer2;
    }
    public void setPointsPerAnswer2(int points){
        pointsPerAnswer2=points;
    }
    public int getControl(){
        return control;
    }
    public void incControl(){
        control++;
    }
    public void resetControl(){
        control=0;
    }
}
