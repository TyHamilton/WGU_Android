package com.example.ty.javaap_c196;

import android.content.ContentValues;

import java.util.ArrayList;

import static com.example.ty.javaap_c196.StubSQL.NOTES_COURSE;
import static com.example.ty.javaap_c196.StubSQL.NOTES_ID;
import static com.example.ty.javaap_c196.StubSQL.NOTES_NOTE;

/**
 * Created by Ty on 12/2/2017.
 */

public class Notes {
    private ArrayList<String> notes ;
    private int nID;//id for note
    private  int course;//id for course

    Notes(){}

    public ContentValues notesData(Notes no){
        ContentValues values= new ContentValues();
        String noter = "";
        for(int i = 0; i<no.getNotes().size(); i++){
            noter+= "%"+no.getNotes().get(i)+"!";

        }


        values.put(NOTES_ID,no.getnID() );
        values.put(NOTES_NOTE,noter);
        values.put(NOTES_COURSE,no.getCourse());

        return values;
    }


    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public int getnID() {
        return nID;
    }

    public void setnID(int courseID) {
        this.nID = courseID;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
