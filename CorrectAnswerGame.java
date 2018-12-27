/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.image.*;

/**
 *
 * @author cataclysm
 */
public class CorrectAnswerGame{
    private final Player player;
    protected int POINTS_PER_ANSWER=1000;
    private File inputFile;
    private File inputFileWithImage;
    private File STATISTICS_OF_THE_GAME;
    public static final int NUMBER_OF_ROUNDS=6;
    public static final int NUMBER_OF_QUESTIONS=5;
    private int questionsNumb,roundsNumb;
    private ArrayList<QuestionClass> questions = new ArrayList<>();
    private ArrayList<QuestionClassWithImage> imageQuestions = new ArrayList<>();
    Boolean withImage;
//constructor of the class    
    public CorrectAnswerGame(Player player,boolean withImage,File inputFile) throws FileNotFoundException{
      this.player=player;
      this.withImage=withImage;
      STATISTICS_OF_THE_GAME=new File("correct answer game stats");
      questionsNumb=0;
      roundsNumb=1;
      if(withImage) {
          this.inputFileWithImage=inputFile;
          getAllTheQuestionsWithImage();
      }
      else {
          this.inputFile=inputFile;
          getAllTheQuestions();
      }
      
    }
    
//get all the questions with images from the input file and add them to the imageQuestions ArrayList
    public void getAllTheQuestionsWithImage() throws FileNotFoundException{
        
    java.util.Scanner input = new java.util.Scanner(inputFileWithImage);
    while(input.hasNext()){
      QuestionClassWithImage question = new QuestionClassWithImage();
      question.setPath(input.nextLine());
      question.setCategory(input.nextLine());
      question.setQuestion(input.nextLine());
      question.setPossibleAnswers(input.nextLine().split(" "));
      question.setAnswer(input.nextLine());
      imageQuestions.add(question);
    }
    }
    
//get all the questions from the input file and add them to the questions ArrayList
    public void getAllTheQuestions() throws FileNotFoundException{
    
    java.util.Scanner input = new java.util.Scanner(inputFile);
    while(input.hasNext()){
    QuestionClass question = new QuestionClass();
    question.setCategory(input.nextLine());
    question.setQuestion(input.nextLine());
    question.setPossibleAnswers(input.nextLine().split(" "));
    question.setAnswer(input.nextLine());
    questions.add(question);
    }
    }
//fetch a random question from the questions ArrayList and remove that question from the questions
    public QuestionClass getARandomQuestion(){
    int x = new java.util.Random().nextInt(questions.size());
    QuestionClass question = questions.remove(x);
    question.shufflePossibleAnswers();
    return question;
    }
//fetch a random question with image from the imageQuestions ArrayList and remove that imageQuestion from the imageQuestions  
    public QuestionClassWithImage getARandomQuestionWithImage(){
    int x = new java.util.Random().nextInt(imageQuestions.size());
    QuestionClassWithImage question = imageQuestions.remove(x);
    question.shufflePossibleAnswers();
    return question;
    }
//show a question    
    public static void displayAquestion(CorrectAnswerGame game,MyVBox vbox){
//fetch a random question
        QuestionClass q=game.getARandomQuestion();
        game.incQuestionsNumb();
//create the necessary nodes for the question to be displayed
        Text category = new Text(q.getCategory());
        Text question = new Text(q.getQuestion());
        Button answer1 = new Button(q.getPossibleAnswers()[0]);
        Button answer2 = new Button(q.getPossibleAnswers()[1]);
        Button answer3 = new Button(q.getPossibleAnswers()[2]);
        Button answer4 = new Button(q.getPossibleAnswers()[3]);
        Button[] answers = {answer1,answer2,answer3,answer4};
//create a button for the player so he can see his points
        Button playerPoints = new Button("show the player's points!");
        playerPoints.setOnAction(e -> {
           Text show = new Text("the player has "+game.getPlayer().getPoints()+" points"); 
           Stage stage = new Stage();
           HBox hbox = new HBox();
           hbox.getChildren().add(show);
           stage.setScene(new Scene(hbox,300,300));
           stage.show();
        });
//assign the right handlers to the possible answers
        for(int i=0; i<4; i++){
            if(q.getAnswer().equals(answers[i].getText()))
                answers[i].setOnAction(new CorrectAnswer(game,vbox));
            else
                answers[i].setOnAction(new WrongAnswer(game,vbox));
        }
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(answer1,answer2,answer3,answer4);
//add all of the above to the vbox
        vbox.getChildren().addAll(category,question,hbox,playerPoints);
    }
    
//show a question with image    
    public static void displayAquestionWithImage(CorrectAnswerGame game,MyVBox vbox){
//get a random question with image
        QuestionClassWithImage q=game.getARandomQuestionWithImage();
        game.incQuestionsNumb();
//create all the necessary nodes for question to be displayed
        ImageView image = new ImageView(new Image(q.getPath()));
        Text category = new Text(q.getCategory());
        Text question = new Text(q.getQuestion());
        Button answer1 = new Button(q.getPossibleAnswers()[0]);
        Button answer2 = new Button(q.getPossibleAnswers()[1]);
        Button answer3 = new Button(q.getPossibleAnswers()[2]);
        Button answer4 = new Button(q.getPossibleAnswers()[3]);
        Button[] answers = {answer1,answer2,answer3,answer4};
//create a button for the player so he can see his points
        Button playerPoints = new Button("show the player's points!");
        playerPoints.setOnAction(e -> {
           Text show = new Text("the player has "+game.getPlayer().getPoints()+" points"); 
           Stage stage = new Stage();
           HBox hbox = new HBox();
           hbox.getChildren().add(show);
           stage.setScene(new Scene(hbox,300,300));
           stage.show();
        });
//assign the right handlers to the possible answers
        for(int i=0; i<4; i++){
            if(q.getAnswer().equals(answers[i].getText()))
                answers[i].setOnAction(new CorrectAnswerWithImage(game,vbox));
            else
                answers[i].setOnAction(new WrongAnswerWithImage(game,vbox));
        }
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(answer1,answer2,answer3,answer4);
//add all of the above to the vbox
        vbox.getChildren().addAll(image,category,question,hbox,playerPoints);
    }
       
    
//getters and setters methods   
    public ArrayList<QuestionClass> getQuestions(){
        return questions;
    }
    
    public ArrayList<QuestionClassWithImage> getQuestionsWithImage(){
        return imageQuestions;
    }
    
    public Player getPlayer(){
        return player;
    }
    public int getPointsPerAnswer(){
        return POINTS_PER_ANSWER;
    }
    public File getInputFile(){
        return inputFile;
    }
    public File getInputFileWithImage(){
        return inputFileWithImage;
    }
    public void incQuestionsNumb(){
        questionsNumb++;
    }
    public void incRoundsNumb(){
        roundsNumb++;
    }
    public int getQuestionsNumb(){
        return questionsNumb;
    }
    public int getRoundsNumb(){
        return roundsNumb;
    }
    public void setQuestionsNumb(int x){
        questionsNumb=x;
    }
    public File getOutputFile(){
        return STATISTICS_OF_THE_GAME;
    }
    public void setOutputFile(String s){
        STATISTICS_OF_THE_GAME=new File(s);
    }
}

