package com.example.ilyad.testvk;


import java.util.ArrayList;
import java.util.Random;

class TextForPlay {
    private ArrayList<String> text = new ArrayList<>();

    TextForPlay() {
        text.add("Come on");
        text.add("Dance with me");
        text.add("Winter is coming");
        text.add("Already frozen");
        text.add("Tap to play");
        text.add("Feel the cold?");
        text.add("Keep moving!");
        text.add("Happy curving");
        text.add("New Game");
    }
    String getText(){
        return text.get(new Random().nextInt(text.size()-1));
    }
}
