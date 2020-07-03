package com.example.characterguesser;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

//I found this code on github and I slightly modified it to randomize my background music and let it work with the main activity options menu
//For eg, I added a public ArrayList<> of musics and a public int named currentMusicIndex
//I also wrote a addMusic() function to populate the ArrayList<> and I called it inside OnCreate()
public class MusicService extends Service implements MediaPlayer.OnErrorListener {
    public int currentMusicIndex;

    public int musicToPlay; //Without this int, the music will not loop for an unknown reason
    public ArrayList<Integer> backgroundMusic=new ArrayList<>();
    private void addMusic(){
        //Now let's load the different anime openings (instrumental) into the array
        backgroundMusic.add(R.raw.onepiecememories);
        backgroundMusic.add(R.raw.narutoshippuudensilhouette);
        backgroundMusic.add(R.raw.onepieceweareinstrumental);
        backgroundMusic.add(R.raw.tokyoghoulunravel);
        backgroundMusic.add(R.raw.attackontitanmusical);

    }

    private final IBinder mBinder = new ServiceBinder();
    public MediaPlayer mPlayer;
    private int length = 0;

    public MusicService() {
    }

    public class ServiceBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        addMusic();
        Random ranMusicBackground=new Random();
        currentMusicIndex=ranMusicBackground.nextInt(backgroundMusic.size()-1);
        musicToPlay= backgroundMusic.get(currentMusicIndex);

        mPlayer = MediaPlayer.create(this,musicToPlay);

        mPlayer.setOnErrorListener(this);

        if (mPlayer != null) {
            mPlayer.setLooping(true);
            mPlayer.setVolume(50, 50);
        }


        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            public boolean onError(MediaPlayer mp, int what, int
                    extra) {

                onError(mPlayer, what, extra);
                return true;
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mPlayer != null) {
            mPlayer.start();
        }
        return START_NOT_STICKY;
    }

    public void pauseMusic() {
        if (mPlayer != null) {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                length = mPlayer.getCurrentPosition();
            }
        }
    }

    public void resumeMusic() {
        if (mPlayer != null) {
            if (!mPlayer.isPlaying()) {
                mPlayer.seekTo(length);
                mPlayer.start();
            }
        }
    }

    public void startMusic() {

        mPlayer = MediaPlayer.create(this, musicToPlay);

        mPlayer.setOnErrorListener(this);

        if (mPlayer != null) {
            mPlayer.setLooping(true);
            mPlayer.setVolume(50, 50);
            mPlayer.start();
        }

    }

    public void stopMusic() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            try {
                mPlayer.stop();
                mPlayer.release();
            } finally {
                mPlayer = null;
            }
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {

        Toast.makeText(this, "Music player failed", Toast.LENGTH_SHORT).show();
        if (mPlayer != null) {
            try {
                mPlayer.stop();
                mPlayer.release();
            } finally {
                mPlayer = null;
            }
        }
        return false;
    }
}