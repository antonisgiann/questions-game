/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.util.Locale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;


/**
 *
 * @author cataclysm
 */
public class QuizGame extends Application {
    MyVBox vbox = new MyVBox(10);
    Player player1;
    Player player2;
    String language;
    
    @Override
    public void start(Stage stage) {
       vbox.setAlignment(Pos.CENTER);
//check for the language of the system
       if(Locale.getDefault().toString().equals("en_US"))
           language="English";
       else
           language="Greek";
//show how many players the user want for the game
       showThePlayersOption(vbox,language);
       Scene scene = new Scene(vbox,600,400);
       stage.setTitle("QUIZ GAME");
       stage.setScene(scene);
       stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

//show how many players the user want for the game
    public static void showThePlayersOption(MyVBox vbox,String language){
    Text howManyPlayers = new Text("how many players?");
    Button onePlayer = new Button("One Player");
    onePlayer.setOnAction(new OnePlayer(vbox,language));
    Button twoPlayers = new Button("Two Players");
    twoPlayers.setOnAction(new TwoPlayers(vbox,language));
    vbox.getChildren().addAll(howManyPlayers,onePlayer,twoPlayers);
    
}
    
    
    
}
