package com.example.quizapp;

public class TrueFalse {
    private  int MQuestionİd;
    private boolean mAnswer;

    public TrueFalse(int MQuestionİd, boolean mAnswer) {
        this.MQuestionİd = MQuestionİd;
        this.mAnswer = mAnswer;
    }

    public int getMQuestionİd() {
        return MQuestionİd;
    }

    public void setMQuestionİd(int MQuestionİd) {
        this.MQuestionİd = MQuestionİd;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }

}
