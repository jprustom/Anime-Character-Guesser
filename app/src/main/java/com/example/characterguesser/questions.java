package com.example.characterguesser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.StrictMath.max;

public class questions extends AppCompatActivity implements View.OnClickListener  {
    ImageView imageBackgroud;
    int eren=0,luffy=0,naruto=0,kaneki=0,randomIndex;
    TextView questionTextView,answerTextView;
    CheckBox answer1,answer2,answer3,answer4;
    ArrayList<String> questionsList;
    ArrayList<String> answer1List;
    ArrayList<String> answer2List;
    ArrayList<String> answer3List;
    ArrayList<String> answer4List;
    ArrayList<Integer> randomizedIndex;
    LinearLayout answersLayout;
    int totalNumberOfQuestions;
    Button returnToMenu;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Return To Menu?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        questions.super.onBackPressed();
                    }
                }).create().show();
    }

    void removeUsedQuestion(int indexToRemove){
        questionsList.remove(indexToRemove);
        answer1List.remove(indexToRemove);
        answer2List.remove(indexToRemove);
        answer3List.remove(indexToRemove);
        answer4List.remove(indexToRemove);
    }

    @SuppressLint("SetTextI18n")
    void chooseRandomQuestion(){

            //Only continue asking if there is at least one question remaining
            if (!questionsList.isEmpty()){

                //Choose a random question out of 10
                Random ran=new Random();
                randomIndex=ran.nextInt(questionsList.size()); //number between 0 and 9 inclusive

                //Set question and answers accordingly
                questionTextView.setText(questionsList.get(randomIndex));
                answer1.setText(answer1List.get(randomIndex));
                answer2.setText(answer2List.get(randomIndex));
                answer3.setText(answer3List.get(randomIndex));
                answer4.setText(answer4List.get(randomIndex));
            }
            else{
                answersLayout.setVisibility(View.INVISIBLE);
                questionTextView.setText("You are.....");
                returnToMenu.setVisibility(View.VISIBLE);

                int max=max(max(eren,luffy),max(kaneki,naruto));

                if (max==eren) {
                    imageBackgroud.setImageResource(R.drawable.eren);
                    answerTextView.setText("Eren Yaegar! A hardheaded, strong-willed, passionate, and impulsive person. Like him , strong determination describes you. You also get angry easily which is a big default, however deep inside you care about your family even if you lose your mind sometimes and become widely aggressive. Maybe you saw the bad side of the world but you will not lose yourself and eventually you will find your way home.");
                }
                if (max==luffy){
                    answerTextView.setText("Monkey D.Luffy! You like adventures, gathering friends and is extremely humble! Having an innocent child's kindness, you like to discover the world and take risks. You also love eating and care a lot about your friends.");
                    imageBackgroud.setImageResource(R.drawable.luffy);}
                if (max==kaneki){
                    answerTextView.setText("Ken Kaneki! You might have been innocent and weak at some point in your life, but now you are a great badass that is nothing like the old you. However in reality you can be an attention seeker sometimes and you unconsciously fear being alone. You may like to spend your free time reading books and may also seek a soulmate to fill a gap. ");
                    imageBackgroud.setImageResource(R.drawable.kaneki);}
                if (max==naruto){
                    answerTextView.setText("Uzumaki Naruto. Hmmm what a character...An attention seeker, someone who hates being ignored or rejected, someone who always tries his best at everything and unconsciously an inspiration for your entourage . You follow your dream/goals and is a hard worker, you believe hard work is the key for success.");
                    imageBackgroud.setImageResource(R.drawable.naruto);
            }



        }}

    public void returnToMenu(View view){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        randomizedIndex=new ArrayList<>();
        questionsList = new ArrayList<>();
        answer1List = new ArrayList<>();
        answer2List = new ArrayList<>();
        answer3List = new ArrayList<>();
        answer4List = new ArrayList<>();


        //BINDING VARIABLES WITH XML VALUES
        returnToMenu=(Button)findViewById(R.id.returnToMenu);
        questionTextView=(TextView)findViewById(R.id.question);
        //FOR THE CHECKBOXES WE HAVE TO ADD A SET ON CLICK LISTENER FUNCTION
        answer1=(CheckBox) findViewById(R.id.answer1);
        answer1.setOnClickListener(this);
        answer2=(CheckBox) findViewById(R.id.answer2);
        answer2.setOnClickListener(this);
        answer3=(CheckBox) findViewById(R.id.answer3);
        answer3.setOnClickListener(this);
        answer4=(CheckBox) findViewById(R.id.answer4);
        answer4.setOnClickListener(this);
        imageBackgroud=(ImageView)findViewById(R.id.backgroundImage);
        answersLayout=(LinearLayout)findViewById(R.id.answersLayout);
        answerTextView=(TextView)findViewById(R.id.answerTextView);



        //Loading same background image as the home screen
        Intent intent=getIntent();
        imageBackgroud.setImageResource(intent.getIntExtra("Image Value",R.drawable.naruto));

        //Each time we open app we delete old database to not recreate another copy
        this.deleteDatabase("Table Of Questions");

        //Creating Database For Each Question and its answers
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Table Of Questions", MODE_PRIVATE, null);

        //Creating Database


        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS myQuestionsDatabase (question VARCHAR, answer1 VARCHAR, answer2 VARCHAR, answer3 VARCHAR, answer4 VARCHAR,id INTEGER PRIMARY KEY)");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase (question, answer1, answer2,answer3,answer4) VALUES ('Which motto suits you best?','I will achieve my ambitions no matter the method used','Say whatever you want about me, however if you lay your hand on my friends I will kill you','All things happening in my life are the consequences of my own decisions','I will never go back on my words.')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase (question,answer1,answer2,answer3,answer4) VALUES ('Which one is more important for you?','Revenge','Friends','Strength','Being acknowledged by all')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase(question,answer1,answer2,answer3,answer4)VALUES('Those who oppose you...','Are in my way and deserve no mercy','Are simply not my friends, nothing personal','Are probably just misguided','Maybe I will try to understand them.')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase(question,answer1,answer2,answer3,answer4)VALUES('Which one of them do you fear?','Losing In a duel','Losing family/friends','Not being powerful','Loneliness')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase(question,answer1,answer2,answer3,answer4)VALUES('Which pill would you choose?','Unlimited power','Unlimited Food','A happy life','Trustful friends')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase(question,answer1,answer2,answer3,answer4)VALUES('One thing you strive for?','Being strong enough for this world, a cruel tragedic place','Achieving my childhood dream','Acceptance of who I am','Acceptance from others')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase(question,answer1,answer2,answer3,answer4)VALUES('Most of the time you are:','Angry','Smiling And Happy','Desperate','Training')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase(question,answer1,answer2,answer3,answer4)VALUES('Hobbie?','I do not care about hobbies','Traveling','Reading','Gym/Training')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase(question,answer1,answer2,answer3,answer4)VALUES('Weapon Of Choice?','Maybe a knife','Just Fists','Nothing, I am against violence','Basic old fashioned body training is the only ultimate weapon')");
        myDatabase.execSQL("INSERT INTO myQuestionsDatabase(question,answer1,answer2,answer3,answer4)VALUES('Assume you can only save one, you would save your:','Mother','Friends','Yourself','Best Friend')");

        Log.i("Database", "Created");

        //I want to know to number of questions for testing purposes
        Cursor count=myDatabase.rawQuery("SELECT * FROM myQuestionsDatabase",null);
        totalNumberOfQuestions=count.getCount();
        Log.i("NUMBER OF ROWS",""+totalNumberOfQuestions);


        //We will use the cursor to go through all values of the table
        Cursor c = myDatabase.rawQuery("SELECT * FROM myQuestionsDatabase", null);
        int questionIndex = c.getColumnIndex("question");
        int answer1Index = c.getColumnIndex("answer1");
        int answer2Index = c.getColumnIndex("answer2");
        int answer3Index = c.getColumnIndex("answer3");
        int answer4Index = c.getColumnIndex("answer4");
        c.moveToFirst();

        do{
           questionsList.add(c.getString(questionIndex));
            answer1List.add(c.getString(answer1Index));
            answer2List.add(c.getString(answer2Index));
            answer3List.add(c.getString(answer3Index));
            answer4List.add(c.getString(answer4Index));
        }while(c.moveToNext());
        for (int i=0;i<questionsList.size();i++)
            Log.i("QUESTION LIST: ","questionsList["+i+"] is "+questionsList.get(i));



        //The function below will display a random question
        chooseRandomQuestion();





    }

    //I learned online how to manipulate checkboxes and I found out that we should overwrite a public function called onClick
    @Override
    public void onClick(View view) {

        //On each answer selected we will increment the appropriate counter then display another question
        switch (view.getId()) {
            case R.id.answer1:
                if (answer1.isChecked())
                eren++;
                removeUsedQuestion(randomIndex);
                Log.i("COUNTER","Eren's Counter is "+eren);
                answer1.setChecked(false);
                break;
            case R.id.answer2:
                if (answer2.isChecked())
                luffy++;
                removeUsedQuestion(randomIndex);
                Log.i("COUNTER","Luffy's Counter is "+luffy);
                answer2.setChecked(false);
                break;
            case R.id.answer3:
                if (answer3.isChecked())
                kaneki++;
                removeUsedQuestion(randomIndex);
                Log.i("COUNTER","Kaneki's Counter is "+kaneki);
                answer3.setChecked(false);
                break;
            case R.id.answer4:
                if (answer4.isChecked())
                naruto++;
                removeUsedQuestion(randomIndex);
                Log.i("COUNTER","Naruto's Counter is "+naruto);
                answer4.setChecked(false);
                break;
            default:
                Toast.makeText(this,"Your App Is Haunted By Demons",Toast.LENGTH_LONG);
        }

        chooseRandomQuestion();


    }





    }



