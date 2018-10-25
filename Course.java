package com.example.ty.javaap_c196;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

import static com.example.ty.javaap_c196.StubSQL.COURSE_AENDATE;
import static com.example.ty.javaap_c196.StubSQL.COURSE_ALERT_END;
import static com.example.ty.javaap_c196.StubSQL.COURSE_ALERT_START;
import static com.example.ty.javaap_c196.StubSQL.COURSE_ASSESSMENT;
import static com.example.ty.javaap_c196.StubSQL.COURSE_MENTORS;
import static com.example.ty.javaap_c196.StubSQL.COURSE_START;
import static com.example.ty.javaap_c196.StubSQL.COURSE_STATUS;
import static com.example.ty.javaap_c196.StubSQL.COURSE_TITLE;

/**
 * Created by Ty on 11/12/2017.
 */

public class Course {

    private int cID;
    private String cTitle;
    private String cStart;
    private String cAEnd;
    private String status;
    private String alertS;// true false only
    private String alertE;//true false only

    public String getAlertS() {
        return alertS;
    }

    public void setAlertS(String alertS) {
        this.alertS = alertS;
    }

    public String getAlertE() {
        return alertE;
    }

    public void setAlertE(String alertE) {
        this.alertE = alertE;
    }

    private ArrayList<Assessment> assesssment = new ArrayList<>();
    private ArrayList<Mentor> mentors = new ArrayList<>();

    public ContentValues courseData(Course cor){
        String buff= "";
        ArrayList<Mentor> buffMent= cor.getMentors();
        Log.d("courseSaveMent", ""+buffMent.size());
        for(int i = 0 ; i<buffMent.size();i++){
            String ment = "%"+buffMent.get(i).getmID()+"!";
            Log.d("courSData: ", ment);
            buff +=ment ;

        }

        String buff2 ="";
        ArrayList<Assessment> buffMen2= cor.getAssesssment();
        for(int i = 0 ; i<cor.getAssesssment().size();i++){
            String asses = "%"+buffMen2.get(i).getaID()+"!";
            buff2 +=asses ;

        }

//        if(buff.length()>0){
//            buff = "%0!";
//
//        }
//        if(buff2.length()>0){
//            buff2="%0!";
//        }
        Log.d("courFinSave", buff);
        ContentValues values= new ContentValues();
        values.put(COURSE_TITLE,cor.getcTitle());
        values.put(COURSE_START,cor.getcStart());
        values.put(COURSE_AENDATE,cor.getcAEnd());
        values.put(COURSE_STATUS,cor.getStatus());
        values.put(COURSE_MENTORS,buff);
        values.put(COURSE_ASSESSMENT, buff2);
        values.put(COURSE_ALERT_START,cor.getAlertS());
        values.put( COURSE_ALERT_END, cor.getAlertE());



        return values;
    }



    public Course(){


    }

    public String getcTitle() {
        return cTitle;
    }
    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }
    public String getcStart() {
        return cStart;
    }
    public void setcStart(String cStart) {
        this.cStart = cStart;
    }
    public String getcAEnd() {
        return cAEnd;
    }
    public void setcAEnd(String cAend) {
        this.cAEnd = cAend;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ArrayList<Mentor> getMentors() {
        return mentors;
    }
    public void setMentors(ArrayList<Mentor> mentorsN) {
        this.mentors = mentorsN;
    }


    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public ArrayList<Assessment> getAssesssment() {
        return assesssment;
    }

    public void setAssesssment(ArrayList<Assessment> assesssment) {
        this.assesssment = assesssment;
    }
}
