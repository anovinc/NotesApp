package com.example.notes;

import io.realm.RealmObject;

public class Note extends RealmObject {
    private String Text;


    public Note() {

    }



    public Note(String text, int id) {
        Text = text;

    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
