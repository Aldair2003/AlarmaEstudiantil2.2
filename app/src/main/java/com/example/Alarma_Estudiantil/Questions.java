package com.example.Alarma_Estudiantil;

public class Questions {
//יצירת כל השאלות האפשריות והתשובות
    //שאלה
    private String mQuestions[]={
            "Cual es el nombre de este Personaje ?",
            "De que anime es este personaje ?",
            "Cual es el nombre de este Personaje ?",
            "Cual es el nombre de este Personaje ?",
            "De que anime es este personaje ?",
            "What's the name of the princess in the picture ?",

    };
    //אפשרויות
    private String mChoice[] []={
            {"Luffy", "Zoro", "Sanji"},//Luffy
            {"SAO", "Haikyuu", "Black Clover"},//Black Clover
            {"Ichigo", "Enrique", "Luis"},//Ichigo
            {"Saitama", "Antonio", "Goku"},//Goku
            {"Naruto", "Vegetta", "Willy"},//Naruto
            {"Pikachu", "Dragonite", "Charizard"},//Charizard


    };
    //תמונה
    private String mImages[]={
            "q",
            "q1",
            "q2",
            "q3",
            "q4",
            "q5",


    };
    //סוג
    private String mQuestionsType[]={
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",

    };
    //תשובה
    private String mCorrectAnswers[]={
            "Luffy",
            "Black Clover",
            "Ichigo",
            "Goku",
            "Naruto",
            "Charizard",

    };
//יצירת Get
    public String getQuestions(int q) {
        String questions =mQuestions[q];
        return questions;
    }

    public String[] getChoice(int q) {
        String[] Choice = mChoice[q];
        return Choice;
    }

    public String getImages(int q) {
        String img = mImages[q];
        return img;
    }

    public String getType(int q){
        String type= mQuestionsType[q];
        return type;
    }
    public int getLength(){
        return mQuestions.length;
    }
    public String getCorrectAnswers(int q){
        String correct= mCorrectAnswers [q];
        return correct;
    }
}
