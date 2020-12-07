package com.henridev;

/**
 * Model/Data class
 */
public class Question {
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;
    // Constructor
    public Question(String question, String a, String b, String c, String d, String answer){
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }
    public String getAnswer(){
        return answer;
    }
    // Setters
    public void setQuestion(String question) {
        this.question = question;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setD(String d) {
        this.d = d;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    @Override
    public String toString(){
        return String.format("%s\n%s\n%s\n%s\n%s",question, a,b,c,d);
    }
}
