package com.example.characterguesser;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class displayCharacters extends AppCompatActivity {
    ListView characterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_characters);

        //Binding ListView with ListView of XML
        ListView characterListView = (ListView)findViewById(R.id.characterListView);

        //First we populate our array list of characters
        //A character has a name, an animeName and a picture.
        ArrayList<characters> charactersList = new ArrayList<>();
        charactersList.add(new characters("Monkey D.Luffy", "One Piece", R.drawable.luffyicon));
        charactersList.add(new characters("Roronoa Zoro", "One Piece", R.drawable.zoroicon));
        charactersList.add(new characters("Usopp", "One Piece", R.drawable.usopplistview));
        charactersList.add(new characters("Vinsmoke Sanji", "One Piece", R.drawable.sanji));
        charactersList.add(new characters("Uzumaki Naruto", "Naruto", R.drawable.narutoicon));
        charactersList.add(new characters("Uchiha Sasuke", "Naruto", R.drawable.sasukeicon1));
        charactersList.add(new characters("Kaneki Ken", "Tokyo Ghoul", R.drawable.kanekiicon1));
        charactersList.add(new characters("Eren Yaegar", "Attack On Titan", R.drawable.eren));

        //After populating our array list we connect it to our adapter
        animeListViewAdapter adapter = new animeListViewAdapter(this, charactersList);

        //Then, we connect the adapter to the list view itself
        characterListView.setAdapter(adapter);


        characterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              switch (position){
                  case 0:
                      Toast.makeText(getApplicationContext(),"The main protagonist of One Piece, a captain of a pirate crew who sets sail to find a great treasure.",Toast.LENGTH_LONG).show();
                      break;
                  case 1:
                      Toast.makeText(getApplicationContext(),"The first mate of Monkey D.Luffy, a strong man whose ambition is to be the strongest swordsman in the world.",Toast.LENGTH_LONG).show();
                      break;
                  case 2:
                      Toast.makeText(getApplicationContext(),"A companion of Monkey D.Luffy, he sets sail with him and he dreams of becoming a brave warrior.",Toast.LENGTH_LONG).show();
                      break;
                  case 3:
                      Toast.makeText(getApplicationContext(),"He is the cook on the pirate ship of Monkey D.Luffy, his dream is to find a sea named All Blue containing all species of fishes worldwide.",Toast.LENGTH_LONG).show();
                      break;
                  case 4:
                      Toast.makeText(getApplicationContext(),"Uzumaki Naruto, a ninja whose dream is to become the hokage which is the highest ranked ninja in his village.",Toast.LENGTH_LONG).show();
                      break;
                  case 5:
                      Toast.makeText(getApplicationContext(),"Sasuke, the rival and best friend of Naruto. He is the only survivor of his clan and desired vengeance for his clan.",Toast.LENGTH_LONG).show();
                      break;
                  case 6:
                      Toast.makeText(getApplicationContext(),"Kaneki, a first year college whose life changes when he goes on a date with a girl who turns out to be a human-eating monster.",Toast.LENGTH_LONG).show();
                      break;
                  case 7:
                      Toast.makeText(getApplicationContext(),"Eren, a boy whose only goal in life is to kill all the titans who destroyed his village and killed his mother",Toast.LENGTH_LONG).show();
                      break;
                  default:
                      Toast.makeText(getApplicationContext(),"THIS SHOULD NEVER APPEAR !!",Toast.LENGTH_LONG).show();

              }
            }
        });


    }
}
