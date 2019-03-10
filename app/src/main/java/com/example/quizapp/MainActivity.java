package com.example.quizapp;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int NR_OF_SIMULTANOUS = 2;
    private final int  NO_LOOP = 0;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int RATE = 1;
    private final int PRIORITY =0;
    private int Sound1ID;
    private int Sound2ID;
    private SoundPool mSoundPool;
    Button mTrue_Button;
    Button mFalse_Button;
    int index;
    int mQuestion;
    int score;
    TextView question_TextView;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    private TrueFalse [] questionBank = new TrueFalse[] {
            new TrueFalse(R.string.inital_question,  false),
            new TrueFalse(R.string.question1, true),
            new TrueFalse(R.string.question2, false),
            new TrueFalse(R.string.question3, true),
            new TrueFalse (R.string.question4, true),
            new TrueFalse(R.string.question5, false),
            new TrueFalse(R.string.question6, true),
            new TrueFalse(R.string.question7, true),
            new TrueFalse(R.string.question8, false),
            new TrueFalse(R.string.question9, false),
            new TrueFalse(R.string.question10, false),
            new TrueFalse(R.string.question11, false),
            new TrueFalse(R.string.question12, false),
            new TrueFalse(R.string.question13, false)

    };
    final int PROGRESS_BAR_CONSITANT=(int) Math.ceil(100.0/questionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     mQuestion=questionBank[index].getMQuestionİd();
     mScoreTextView = (TextView)  findViewById(R.id.Score);
     mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    question_TextView = (TextView)  findViewById(R.id.question_textView);
     mTrue_Button = (Button)  findViewById(R.id.true_button);
     mFalse_Button = (Button) findViewById(R.id.false_button);
      mSoundPool = new SoundPool(NR_OF_SIMULTANOUS, AudioManager.STREAM_MUSIC, 0);
      Sound1ID = mSoundPool.load(getApplicationContext(), R.raw.note1_c, PRIORITY);
      Sound2ID = mSoundPool.load(getApplicationContext(), R.raw.note2_d, PRIORITY);
     mTrue_Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             checkAnswer(true);
           updateQuestion();
           Play1();

         }
     });
     mFalse_Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             checkAnswer(false);
             updateQuestion();
             Play2();
         }
     });
    }
    public void updateQuestion(){
        index = (index+1) % questionBank.length;
        if(index == 0){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Oyun Bitti");
            alert.setCancelable(false);
            alert.setMessage("Sizin xalınız "+score+ "!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestion = questionBank[index].getMQuestionİd();
        question_TextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_CONSITANT);
        mScoreTextView.setText("Score: "+score+"/" + questionBank.length);

    }
    public void checkAnswer(boolean UserSelection){
        boolean correctanswer = questionBank[index].ismAnswer();
        if(UserSelection==correctanswer) {
            Toast.makeText(getApplicationContext(), R.string.rightToast, Toast.LENGTH_SHORT).show();
            score++;

        }
        else{
            Toast.makeText(getApplicationContext(), R.string.wrongToast, Toast.LENGTH_SHORT).show();
        }
    }
    public void Play1(){
        mSoundPool.play(Sound1ID, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, RATE);

    }
    public void Play2(){
        mSoundPool.play(Sound2ID, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, RATE);
    }


}
