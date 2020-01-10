package com.mentalsegment.triviaapp;

public class UserScoreBoardModel {

    private int Image;
    private String name;
    private String score;

    public UserScoreBoardModel(int image, String name, String score) {
        Image = image;
        this.name = name;
        this.score = score;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
