package com.example.characterguesser;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.characterguesser.R;
import com.example.characterguesser.characters;

import java.util.ArrayList;

public class animeListViewAdapter extends ArrayAdapter<characters> {
    public animeListViewAdapter(Activity context, ArrayList<characters> characterList){
        super(context, 0, characterList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.animelistview, parent,false);
        }
        characters currentCharacter = getItem(position);

        TextView name = (TextView)listViewItem.findViewById(R.id.characterName);
        name.setText(currentCharacter.getCharacterName());

        TextView anime = (TextView)listViewItem.findViewById(R.id.animeName);
        anime.setText(currentCharacter.getAnime());

        ImageView iconView = (ImageView)listViewItem.findViewById(R.id.characterIcon);
        iconView.setImageResource(currentCharacter.getCharacterIcon());

        return listViewItem;

    }
}
