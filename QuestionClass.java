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
public class QuestionClass {
    private String category;
    private String question;
    private String[] possibleAnswers = new String[4];
    private String answer;
//default constructor of the class
    public QuestionClass(){}
//constructor of the class
    public QuestionClass(String category,String question,String[] possibleAnswers,String answer){
        this.category=category;
        this.question=question;
        this.possibleAnswers=possibleAnswers;
        this.answer=answer;
    }
//getters and setters methods for the class
    public String getCategory(){
        return category;
    }
    public String getQuestion(){
        return question;
    }
    public String[] getPossibleAnswers(){
        return possibleAnswers;
    }
    public String getAnswer(){
        return answer;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setQuestion(String question){
        this.question=question;
    }
    public void setPossibleAnswers(String[] possibleAnswers){
        this.possibleAnswers=possibleAnswers;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }
//override the toString method from the Object class
    @Override
    public String toString(){
        String s="";
        for(String k : possibleAnswers)
            s+=" "+k;
        return s;
    }
//shuffle array of the possibleAnswers
    public void shufflePossibleAnswers(){
        for(int i = 0 ; i<(int)(30+(Math.random()*70)); i++)
        {
            int x = (int)(Math.random()*4);
            int y = (int)(Math.random()*4);
            String temp=possibleAnswers[x];
            possibleAnswers[x]=possibleAnswers[y];
            possibleAnswers[y]=temp;
        }
    }
    
}
