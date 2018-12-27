/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

/**
 *
 * @author cataclysm
 */
public class QuestionClassWithImage extends QuestionClass {
    private String path;
    public QuestionClassWithImage(){}
    public QuestionClassWithImage(String path,String category,String question,String[] possibleAnswers,String answer){
        super(category,question,possibleAnswers,answer);
        this.path=path;
    }
    
    public void setPath(String path){
        this.path=path;
    }
    public String getPath(){
        return path;
    }
}
