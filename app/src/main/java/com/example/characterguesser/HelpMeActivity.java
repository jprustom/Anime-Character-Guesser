package com.example.characterguesser;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HelpMeActivity extends AppCompatActivity {
    LinearLayout helpMeLayout;
    TextView whoAmIHelpMe,guessMeHelpMe,meetMeHelpMe,authorHelpMe;
    Button goBack;
    ImageView narutoSmiling;



    public void goBack(View view){
        goBack.setVisibility(View.INVISIBLE);
        helpMeLayout.setVisibility(View.VISIBLE);
        whoAmIHelpMe.setVisibility(View.INVISIBLE);
        guessMeHelpMe.setVisibility(View.INVISIBLE);
        meetMeHelpMe.setVisibility(View.INVISIBLE);
        authorHelpMe.setVisibility(View.INVISIBLE);
        narutoSmiling.setVisibility(View.INVISIBLE);
    }
    public void help(View view){
        int tag=Integer.parseInt(view.getTag().toString());
        helpMeLayout.setVisibility(View.INVISIBLE);
        narutoSmiling.setVisibility(View.VISIBLE);
        goBack.setVisibility(View.VISIBLE);
        switch (tag) {
            case 1:
                whoAmIHelpMe.setVisibility(View.VISIBLE);
                break;
            case 2:
                guessMeHelpMe.setVisibility(View.VISIBLE);
                break;
            case 3:
                meetMeHelpMe.setVisibility(View.VISIBLE);
                break;
            case 4:
                authorHelpMe.setVisibility(View.VISIBLE);
                break;
            default:
                Toast.makeText(this, "If you're reading this message, then your app is haunted by evil spirits please get rid of your phone", Toast.LENGTH_SHORT).show(); }
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_me);
        goBack=(Button)findViewById(R.id.goBack) ;
        narutoSmiling=(ImageView)findViewById(R.id.narutoSmiling);
        helpMeLayout=(LinearLayout)findViewById(R.id.helpMeLayout);
        whoAmIHelpMe=(TextView)findViewById(R.id.whoAmIHelpMe);
        guessMeHelpMe=(TextView)findViewById(R.id.guessMeHelpMe);
        meetMeHelpMe=(TextView)findViewById(R.id.meetMeHelpMe);
        authorHelpMe=(TextView)findViewById(R.id.authorHelpMe);
    }
}
