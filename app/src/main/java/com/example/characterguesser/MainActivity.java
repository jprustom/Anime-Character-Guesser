package com.example.characterguesser;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Process;
import java.util.Random;

//I ADDED LINES TO THE MANIFEST FILE FIRST TO BE ABLE TO USE A CODE I FOUND ONLINE AND SECONDLY THE APP WAS CRASHING BECAUSE OF IMAGES SO I FOUND A LITTLE TWEAK TO GET OVER IT

public class MainActivity extends AppCompatActivity {
    int currentImageresourceIndex;
    Button quit,normalMode,characterInfos, guessMode;
    String userName;
    Intent intent;
    LinearLayout mainMenuLayout;
    boolean male=false,female=false;
    ArrayList<Integer> backgroundImages = new ArrayList<>();
    ImageView backgroundImage;

    //This function randomizes the background
    void loadImageBackground(){
        //Let's load the different images into the arraylist
        backgroundImages.add(R.drawable.eren);
        backgroundImages.add(R.drawable.kaneki);
        backgroundImages.add(R.drawable.luffy);
        backgroundImages.add(R.drawable.naruto);
        backgroundImages.add(R.drawable.sasuke);
        backgroundImages.add(R.drawable.usopp);
        backgroundImages.add(R.drawable.zoro);
        backgroundImages.add(R.drawable.narutosmile);

        //Let's set a random image
        Random ranImageBackground= new Random();
        int randomizedIndex=ranImageBackground.nextInt(backgroundImages.size()-1);
        backgroundImage.setImageResource(backgroundImages.get(randomizedIndex));
        currentImageresourceIndex =randomizedIndex;

    }

    //Customizing the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.myoptions, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.changeBackground:
                if (++currentImageresourceIndex==backgroundImages.size())
                    currentImageresourceIndex=0;
                    backgroundImage.setImageResource(backgroundImages.get(currentImageresourceIndex));
                break;

            case R.id.changeMusic:
                if (++mServ.currentMusicIndex==mServ.backgroundMusic.size())
                    mServ.currentMusicIndex=0;
                mServ.stopMusic();
                mServ.musicToPlay= mServ.backgroundMusic.get(mServ.currentMusicIndex);
                mServ.startMusic();
                /*mServ.mPlayer=MediaPlayer.create(this,mServ.backgroundMusic.get(mServ.currentMusicIndex));
                mServ.mPlayer.start();*/
                break;
            case R.id.muteMusic:
                if (mServ.mPlayer.isPlaying()){
                    Toast.makeText(this, "Sound Off.", Toast.LENGTH_SHORT).show();
                    mServ.mPlayer.pause();
                }
                else {
                    Toast.makeText(this, "Sound On", Toast.LENGTH_SHORT).show();
                    mServ.mPlayer.start();}
                break;
            case R.id.helpMe:
                intent=new Intent(getApplicationContext(),HelpMeActivity.class);
                startActivity(intent);

                break;
            default:
                Toast.makeText(this, "If this appears, then your app is haunted :)", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    //BELOW ARE THE MAIN THREE OPTIONS AND THE QUIT BUTTON

    //Normal mode where user finds which character he is
    public void startNormalMode(View view){
        intent=new Intent(getApplicationContext(),questions.class);
        intent.putExtra("Image Value",backgroundImages.get(currentImageresourceIndex));
        startActivity(intent);

    }

    //Guess mode where character gets asked questions and he has to find out the appropriate character
    public void startGuessMode (View view){
        intent=new Intent(getApplicationContext(),guessModeActivity.class);
        startActivity(intent);

    }

    //this button let users read info about anime characters
    public void displayCharacters(View view){
        intent=new Intent(getApplicationContext(),displayCharacters.class);
        startActivity(intent);
    }

    //Exit From App
    public void quit(View view){
        finish();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding variables with XML variables
        mainMenuLayout=(LinearLayout)findViewById(R.id.mainMenu);
        quit = (Button) findViewById(R.id.quit);
        normalMode = (Button) findViewById(R.id.normalMode);
        characterInfos = (Button) findViewById(R.id.characterInfos);
        guessMode=(Button)findViewById(R.id.guessMode);
        backgroundImage=(ImageView)findViewById(R.id.backgroundImage);

        Toast.makeText(this,"Change/Mute Music in the options above",Toast.LENGTH_LONG).show();

        //When we first open the app we want a background image to be displayed
        loadImageBackground();

        //Below is a code I found on github that allows the music to loop through all activities
        //This code comes with two additional classes named HomeWatcher and MusicService, and it added couple lines to the manifest file
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        //This piece of code comes with the one I mentioned above, this one stops music when user presses home button or is seeing used apps
        HomeWatcher mHomeWatcher;
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();


    }


    //All of these below are also part of the code I found online
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };
    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }
    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }
    }


