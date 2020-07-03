package com.example.characterguesser;



public class characters {

    private String characterName;
    private String anime;
    private int characterIcon;

    public characters(String name, String animeName, int imageId) {
        characterName = name;
        anime = animeName;
        characterIcon = imageId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getAnime() {
        return anime;
    }

    public int getCharacterIcon() {
        return characterIcon;
    }
}

