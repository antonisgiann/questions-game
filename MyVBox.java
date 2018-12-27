/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import javafx.scene.layout.VBox;
/**
 *
 * @author cataclysm
 */
public class MyVBox extends VBox {
    public MyVBox(int x){
        super(x);
    }
    public static void clear(MyVBox vbox){
        vbox.getChildren().removeAll(vbox.getChildren());
    }
    
}
