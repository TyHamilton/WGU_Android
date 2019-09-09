package com.example.ty.javaap_c196;

import android.content.ContentValues;

import static com.example.ty.javaap_c196.StubSQL.ASSESSMENT_ALERT;
import static com.example.ty.javaap_c196.StubSQL.ASSESSMENT_CID;
import static com.example.ty.javaap_c196.StubSQL.ASSESSMENT_GRADE;
import static com.example.ty.javaap_c196.StubSQL.ASSESSMENT_OBJECTIVE_BOOLEAN;
import static com.example.ty.javaap_c196.StubSQL.ASSESSMENT_SCHEDULE;
import static com.example.ty.javaap_c196.StubSQL.ASSESSMENT_TITLE;

/**
 * Created by Ty on 12/2/2017.
 */

public class Assessment {
    private int aID;//ID for the assessment
    private int cIDa;//class associated with this assessment
    private String ATitle;
    private String ojbectiveBoolean; // true makes this objective false string equals performance
    private String schedule;//holds schedule date for assessment
    private String alert;//boolean for alert true is when student wants alert for this assessment
    private String grade;





    public ContentValues assessmentData(Assessment asse){

        ContentValues values= new ContentValues();
        values.put(ASSESSMENT_CID,asse.getcIDa());
        values.put(ASSESSMENT_TITLE, asse.getATitle());
        values.put(ASSESSMENT_OBJECTIVE_BOOLEAN,asse.getOjbectiveBoolean());
        values.put(ASSESSMENT_SCHEDULE,asse.getSchedule());
        values.put(ASSESSMENT_ALERT,asse.getAlert());
        values.put(ASSESSMENT_GRADE, asse.getGrade());

        return values;
    }









    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public int getcIDa() {
        return cIDa;
    }

    public void setcIDa(int cIDa) {
        this.cIDa = cIDa;
    }

    public String getOjbectiveBoolean() {
        return ojbectiveBoolean;
    }

    public void setOjbectiveBoolean(String ojbectiveBoolean) {
        this.ojbectiveBoolean = ojbectiveBoolean;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }


    public String getATitle() {
        return ATitle;
    }

    public void setATitle(String ATitle) {
        this.ATitle = ATitle;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
