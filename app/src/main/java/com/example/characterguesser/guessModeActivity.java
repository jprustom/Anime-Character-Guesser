package com.example.characterguesser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class guessModeActivity extends AppCompatActivity {
    TextView scoreText,highScoreText,whatToGuess,gameOverText;
    int score=0,highScore;
    ArrayList<String> luffyQuestions;
    ArrayList<String> narutoQuestions;
    ArrayList<String> kanekiQuestions;
    ArrayList<String> erenQuestions;
    ArrayList<String> zoroQuestions;
    ArrayList<String> sanjiQuestions;
    ArrayList<String> usoppQuestions;
    ArrayList<String> sasukeQuestions;
    ArrayList<ArrayList<String>> poolOfQuestionsToChoose;
    ArrayList<String> usedQuestions;
    LinearLayout charactersLayout;
    ImageView gameOverImage,heart1,heart2,heart3;
    Button retryButton,quitButton;
    String randomizedQuestion;
    SharedPreferences saveHighscore;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        guessModeActivity.super.onBackPressed();
                    }
                }).create().show();
    }

    //The functions below initialize our 8 arrays of questions
    void initializeLuffyQuestions(){
        luffyQuestions=new ArrayList<>();
        luffyQuestions.add("My grandfather is my ennenmy...");
        luffyQuestions.add("I don't know my parents...");
        luffyQuestions.add("My father is a revolutionary....");
        luffyQuestions.add("I love meat...");
        luffyQuestions.add("I grew up with mountain bandits...");
        luffyQuestions.add("I want to realize my dream...");
        luffyQuestions.add("I love adventures....");
        luffyQuestions.add("Wherever I go I make friends...");
        luffyQuestions.add("My brother died in front of me...");
        luffyQuestions.add("I am a big wanted criminal....");
        luffyQuestions.add("I have defied the world government several times...");
        luffyQuestions.add("My anime is the name of the treasure I want to find....");
        luffyQuestions.add("My anime is the longest one of these currently here...");
        luffyQuestions.add("My anime is the oldest one out of these 4 here...");
        luffyQuestions.add("My anime is being animated by toei animation...");
        luffyQuestions.add("My anime has the most movies, specials and ovas out of the 4 anime here...");
        luffyQuestions.add("I was defeated once in a samurai country");
        luffyQuestions.add("In my anime, I have a special rare ability ...");
        luffyQuestions.add("I am smiling most of the time...");
        luffyQuestions.add("My crewmate once left me but I brought him back...");
        luffyQuestions.add("My crewmate once defied me...");
        luffyQuestions.add("My crewmate once need help and I helped him/her");
        luffyQuestions.add("I have gathered 4 companions from the area where I live");
        luffyQuestions.add("There was a two-years timeskip after a war happened in my anime");

    }
    void initializeZoroQuestions(){
        zoroQuestions=new ArrayList<>();
        zoroQuestions.add("I sleep a lot...");
        zoroQuestions.add("When I was a child, my rival was a girl...");
        zoroQuestions.add("I trained for two years under the man I want to defeat...");
        zoroQuestions.add("I was shown to be the most loyal to my captain...");
        zoroQuestions.add("I wear a bandana...");
        zoroQuestions.add("SANTORYU....");
        zoroQuestions.add("I get lost easily....");
        zoroQuestions.add("I was defeated once in East Blue...");
        zoroQuestions.add("My anime has not shown any hint about my parents...");
    }
    void initializeUsoppQuestions(){
        usoppQuestions=new ArrayList<>();
        usoppQuestions.add("My father is the crewmate of a pirate emperor...");
        usoppQuestions.add("My captain was told stories about me in his childhood...");
        usoppQuestions.add("I lie often...");
        usoppQuestions.add("On my wanted poster, I am labeled as a God...");
        usoppQuestions.add("My father left me to go on an adventure...");
        usoppQuestions.add("My dream is to become a brave warrior of the sea....");

    }
    void initializeSanjiQuestions(){
        sanjiQuestions=new ArrayList<>();
        sanjiQuestions.add("I was bullied and rejected by my family...");
        sanjiQuestions.add("When I was a child, an experiment failed on me...");
        sanjiQuestions.add("I visited my mother often before her death...");
        sanjiQuestions.add("I never use fists when fighting....");
        sanjiQuestions.add("I love to cook...");
        sanjiQuestions.add("I am a pervert...");
        sanjiQuestions.add("My marriage was a trap...");
        sanjiQuestions.add("I am a prince...");

    }
    void initializeSasukeQuestions(){
        sasukeQuestions=new ArrayList<>();
        sasukeQuestions.add("As an adult, I am the tutor of my childhood bestfriend...");
        sasukeQuestions.add("My clan was killed by my older brother...");
        sasukeQuestions.add("I killed my own brother for revenge, them regretted it...");
        sasukeQuestions.add("As a teenager, I was full of hate and lost my way...");
        sasukeQuestions.add("I defied my bestfriend twice....");
        sasukeQuestions.add("I was believed to be the only survivor of my clan...");
        sasukeQuestions.add("Someone of my clan was the best friend of my tutor...");
        sasukeQuestions.add("My best friend's father was the tutor of out tutor...");
        sasukeQuestions.add("At some point I lost my mind and decided to destroy my hometown...");
        sasukeQuestions.add("I left home to seek power...");
        sasukeQuestions.add("At some point I believed being alone makes me strong...");
        sasukeQuestions.add("I sought strength...");
    }
    void initializeNarutoQuestions() {
        narutoQuestions = new ArrayList<>();
        narutoQuestions.add("At the end of my anime, I realised my dream...");
        narutoQuestions.add("On my 17th birthday, a war ended...");
        narutoQuestions.add("My father was feared by fellow villages...");
        narutoQuestions.add("I never go back on my words...");
        narutoQuestions.add("After my anime ended, a new anime about my son started....");
        narutoQuestions.add("Inside me resides a demon...");
        narutoQuestions.add("I befriended the demon inside me...");
        narutoQuestions.add("The demon inside me killed my parents...");
        narutoQuestions.add("I was avoided by all when I was a child...");
        narutoQuestions.add("I met my parents after their death...");
    }
    void initializeErenQuestions(){
        erenQuestions=new ArrayList<>();
        erenQuestions.add("I want to kill all my ennemies...");
        erenQuestions.add("My mother was eaten in front of me...");
        erenQuestions.add("My mother was the second woman my father marries...");
        erenQuestions.add("I grew up with an adopted sister....");
        erenQuestions.add("My father was a doctor...");
        erenQuestions.add("I killed my father...");
        erenQuestions.add("I want to achieve my revenge...");
        erenQuestions.add("I can transform into a titan....");
        erenQuestions.add("I can transform into a monster...");
        erenQuestions.add("My village was attacked when I was a child...");
        erenQuestions.add("I was in the recon corps...");
        erenQuestions.add("I have a half brother that I din't know about...");

    }
    void initializeKanekiQuestions(){
        kanekiQuestions=new ArrayList<>();
        kanekiQuestions.add("My personality dramatically changed through my life....");
        kanekiQuestions.add("I have mommy issues...");
        kanekiQuestions.add("The girl I was on a date with tried to eat me...");
        kanekiQuestions.add("I got tortured for 10 days straight...");
        kanekiQuestions.add("I liked reading books...");
        kanekiQuestions.add("I had only one friend...");
        kanekiQuestions.add("I tried to eat my only friend unconsciously...");
        kanekiQuestions.add("I had my hobby of reading from my father...");
        kanekiQuestions.add("I was a literature student...");
        kanekiQuestions.add("At the age of 18 my life changed...");
        kanekiQuestions.add("I became a human eating monster....");
        kanekiQuestions.add("At some point I lost my memories for two years...");
        kanekiQuestions.add("My hair color changes a lot....");
        kanekiQuestions.add("The \"Marie Antoinette\" Syndrome hit me ...");
        kanekiQuestions.add("At the beginning of my anime I was a shy introvert teen...");
        kanekiQuestions.add("My anime deviated a lot from the manga...");
        kanekiQuestions.add("My mangaka is unknown...");
    }

    //I grouped these 8 functions into as single function that I will call on onCreate
    void initializeQuestions(){
        initializeLuffyQuestions();
        initializeZoroQuestions();
        initializeUsoppQuestions();
        initializeSanjiQuestions();
        initializeNarutoQuestions();
        initializeSasukeQuestions();
        initializeErenQuestions();
        initializeKanekiQuestions();
    }

    //I will use the function below to remove asked questions from my sets of questions
    //This way the app will not go over the same question again
    void removeFromQuestionsSet(String stringToBeRemoved){
        //The function remove will return false if an object is not in the array so it is unecessary to add conditions
        luffyQuestions.remove(stringToBeRemoved);
        usoppQuestions.remove(stringToBeRemoved);
        zoroQuestions.remove(stringToBeRemoved);
        sanjiQuestions.remove(stringToBeRemoved);
        narutoQuestions.remove(stringToBeRemoved);
        sasukeQuestions.remove(stringToBeRemoved);
        kanekiQuestions.remove(stringToBeRemoved);
        erenQuestions.remove(stringToBeRemoved);
        if (luffyQuestions.isEmpty())
            poolOfQuestionsToChoose.remove(luffyQuestions);
        if (zoroQuestions.isEmpty())
            poolOfQuestionsToChoose.remove(zoroQuestions);
        if (usoppQuestions.isEmpty())
            poolOfQuestionsToChoose.remove(usoppQuestions);
        if (sanjiQuestions.isEmpty())
            poolOfQuestionsToChoose.remove(sanjiQuestions);
        if (narutoQuestions.isEmpty())
            poolOfQuestionsToChoose.remove(narutoQuestions);
        if (sasukeQuestions.isEmpty())
            poolOfQuestionsToChoose.remove(luffyQuestions);
        if (kanekiQuestions.isEmpty())
            poolOfQuestionsToChoose.remove(kanekiQuestions);
        if (erenQuestions.isEmpty())
            poolOfQuestionsToChoose.remove(erenQuestions);
        if (poolOfQuestionsToChoose.isEmpty())
            Toast.makeText(this,"OMG! NO QUESTIONS LEFT! YOU TRULY ARE A HARDCORE FAN!",Toast.LENGTH_LONG).show();
    }

    //The function below will display a random question each time
    void chooseRandomQuestion(){

        Log.i("TEST","Entered choose random question function");

        //We want to get a random set of questions in an array containing these sets of random questions
        //Outcome will be the array list of luffy's questions or naruto's or kaneki's or zoro's or.......
        ArrayList<String> randomizedPoolOfQuestions=new ArrayList<>();
        //BUT ONLY IF THERE ARE REMAINING QUESTIONS!
        if (!poolOfQuestionsToChoose.isEmpty()){
            Random ran=new Random();
            randomizedPoolOfQuestions=poolOfQuestionsToChoose.get(ran.nextInt(poolOfQuestionsToChoose.size()));
            Log.i("TEST","We chose a random pool");

            //Now we want to get a random question inside this set
            randomizedQuestion=randomizedPoolOfQuestions.get(ran.nextInt(randomizedPoolOfQuestions.size()));


            Log.i("TEST","We got a randomized question that is "+randomizedQuestion);

            //We associate question with our text view
            whatToGuess.setText(randomizedQuestion);
        }
        else {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }


    }

    void lostLife(){
        Toast.makeText(this, "Wrong !", Toast.LENGTH_SHORT).show();
        if (heart1.getVisibility()==View.VISIBLE)
            heart1.setVisibility(View.INVISIBLE);
        else if (heart2.getVisibility()==View.VISIBLE)
            heart2.setVisibility(View.INVISIBLE);
        else {
            heart3.setVisibility(View.INVISIBLE);
            gameOver();
        }
    }

    //The function below will display the game over screen
    void gameOver(){
        whatToGuess.setVisibility(View.INVISIBLE);
        charactersLayout.setVisibility(View.INVISIBLE);
        gameOverImage.setVisibility(View.VISIBLE);
        gameOverText.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.VISIBLE);
        quitButton.setVisibility(View.VISIBLE);
        Random ran=new Random();
        int randomImageGameOverBackground=ran.nextInt(4);
        if (randomImageGameOverBackground==0)
            gameOverImage.setImageResource(R.drawable.gamover1);
        else if (randomImageGameOverBackground==1)
            gameOverImage.setImageResource(R.drawable.gameover2);
        else if (randomImageGameOverBackground==2)
            gameOverImage.setImageResource(R.drawable.gameover3);
        else
            gameOverImage.setImageResource(R.drawable.luffythinking);



    }

    //The image clicked function below will either increase the score if user guessed right or it will display to him the game over screen if he's wrong
    @SuppressLint("SetTextI18n")
    public void imageClicked(View view){
        //First of all we get the tag, we have 8 different tags of type text
        String questionAsked=whatToGuess.getText().toString();
        String tagValue=(view.getTag().toString());

        //Each time the user is right we will increment his score
        if (tagValue.equals("luffy")){
            Log.i("Clicked:",tagValue);
            if (!luffyQuestions.contains(randomizedQuestion))
                lostLife();
            else {
                score++;
                removeFromQuestionsSet(randomizedQuestion);
                chooseRandomQuestion();
            }

        }
        if (tagValue.equals("zoro")){
            Log.i("Clicked:",tagValue);
            if (!zoroQuestions.contains(randomizedQuestion))
                lostLife();
            else {
                score++;
                removeFromQuestionsSet(randomizedQuestion);
                chooseRandomQuestion();
            }
        }
        if (tagValue.equals("usopp")){
            Log.i("Clicked:",tagValue);
            if (!usoppQuestions.contains(randomizedQuestion))
                lostLife();
            else {
                score++;
                removeFromQuestionsSet(randomizedQuestion);
                chooseRandomQuestion();
            }
            }

        if (tagValue.equals("sanji")){
            Log.i("Clicked:",tagValue);
            if (!sanjiQuestions.contains(randomizedQuestion))
                lostLife();
            else {
                score++;
                removeFromQuestionsSet(randomizedQuestion);
                chooseRandomQuestion();
            }
        }
        if (tagValue.equals("naruto")){
            Log.i("Clicked:",tagValue);
            if (!narutoQuestions.contains(randomizedQuestion))
                lostLife();
            else {
                score++;
                removeFromQuestionsSet(randomizedQuestion);
                chooseRandomQuestion();
            }
        }
        if (tagValue.equals("sasuke")){
            Log.i("Clicked:",tagValue);
            if (!sasukeQuestions.contains(randomizedQuestion))
                lostLife();
            else {
                score++;
                removeFromQuestionsSet(randomizedQuestion);
                chooseRandomQuestion();
            }
            }

        if (tagValue.equals("kaneki")){
            Log.i("Clicked:",tagValue);
            if (!kanekiQuestions.contains(randomizedQuestion))
                lostLife();
            else {
                score++;
                removeFromQuestionsSet(randomizedQuestion);
                chooseRandomQuestion();
            }
        }
        if (tagValue.equals("eren")){
            Log.i("Clicked:",tagValue);
            if (!erenQuestions.contains(randomizedQuestion))
                lostLife();
            else {
                score++;
                removeFromQuestionsSet(randomizedQuestion);
                chooseRandomQuestion();
            }
        }

        //Set high score
        if (score>highScore){
            highScore=score;
            saveHighscore.edit().putInt("High Score", highScore).apply();}

        //Display Score
        scoreText.setText(score+"");

        //Display high score
        highScoreText.setText(""+highScore);

        }

    //The function below is linked to the retry button which will let the user retry the guess mode
    public void retry(View view){
        Intent intent=new Intent(getApplicationContext(),guessModeActivity.class);
        finish();
        startActivity(intent);

    }

    //The function below is for the main menu button which will let the user exit guess mode
    public void mainMenu(View view){
        guessModeActivity.super.onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_mode);

        usedQuestions =new ArrayList<>();

        //BINDING VARIABLES
        retryButton=(Button)findViewById(R.id.retryButton);
        gameOverImage=(ImageView)findViewById(R.id.gameOverImage);
        charactersLayout=(LinearLayout)findViewById(R.id.charactersLayout);
        scoreText=(TextView)findViewById(R.id.scoreText);
        highScoreText=(TextView)findViewById(R.id.highScoreText);
        whatToGuess=(TextView)findViewById(R.id.whatToGuess);
        gameOverText=(TextView)findViewById(R.id.gameOverText);
        quitButton=(Button)findViewById(R.id.quitButton);
        heart1=(ImageView)findViewById(R.id.heart1) ;
        heart2=(ImageView)findViewById(R.id.heart2) ;
        heart3=(ImageView)findViewById(R.id.heart3) ;

        //INITIALIZING ARRAYS OF QUESTIONS
        initializeQuestions();

        //INITIALIZING ARRAY OF ARRAYS OF QUESTIONS
        poolOfQuestionsToChoose=new ArrayList<>();
        poolOfQuestionsToChoose.add(luffyQuestions);
        poolOfQuestionsToChoose.add(zoroQuestions);
        poolOfQuestionsToChoose.add(usoppQuestions);
        poolOfQuestionsToChoose.add(sanjiQuestions);
        poolOfQuestionsToChoose.add(kanekiQuestions);
        poolOfQuestionsToChoose.add(erenQuestions);
        poolOfQuestionsToChoose.add(narutoQuestions);
        poolOfQuestionsToChoose.add(sasukeQuestions);

        //SETTING SCORE TO 0 AT FIRST
        scoreText.setText(score+"");

        //Setting high score, default 0
        saveHighscore = getSharedPreferences("High Score", MODE_PRIVATE);
        highScore= saveHighscore.getInt("High Score", 0);
        highScoreText.setText(""+highScore);

        //For testing purposes only
        int totalNumberOfQuestions=luffyQuestions.size()+zoroQuestions.size()+usoppQuestions.size()+sanjiQuestions.size()+kanekiQuestions.size()+erenQuestions.size()+narutoQuestions.size()+sasukeQuestions.size();
        Log.i("TOTAL","We have in total "+totalNumberOfQuestions+" questions.");

        //LOADING A RANDOM QUESTION
        chooseRandomQuestion();
    }
}
