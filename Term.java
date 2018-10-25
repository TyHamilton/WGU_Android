package com.example.ty.javaap_c196;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

import static com.example.ty.javaap_c196.StubSQL.TERM_COURSES;
import static com.example.ty.javaap_c196.StubSQL.TERM_END;
import static com.example.ty.javaap_c196.StubSQL.TERM_START;
import static com.example.ty.javaap_c196.StubSQL.TERM_TITLE;

/**
 * Created by Ty on 11/12/2017.
 */

public class Term {

    private int tID;
    private String title;
    private String start;
    private String end;
    private ArrayList<Course> courses = new ArrayList<>();


    public Term(){}



    public ContentValues termData(Term ter){
        String buff= "";
        ArrayList<Course> buffCourse = ter.getCourses();
        for(int i =0; i<ter.getCourses().size();i++){
            String cour = "%"+ buffCourse.get(i).getcID()+"!";
            buff+=cour;
        }
        Log.d("save courses as: ", buff);
        ContentValues values= new ContentValues();

        values.put(TERM_TITLE, ter.getTitle());
        values.put(TERM_START, ter.getStart());
        values.put(TERM_END, ter.getEnd());
        values.put(TERM_COURSES, buff);

        return values;
    }



    public Term(String tt, String st, String en ){
        this.title=tt;
        this.start=st;
        this.end=en;

    }



    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }



    public ArrayList<Course> getCourses() {
        return courses;
    }



    public void setCourses(ArrayList<Course> cBuff) {
        this.courses = cBuff;
    }
    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }




}
