package com.example.ty.javaap_c196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ty on 11/17/2017.
 */

public class StubSQL extends SQLiteOpenHelper{

    //name of database and version
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "student.db";

    //Constants for Course
    public static final String TABLE_COURSE = "course";
    public static final String COURSE_ID= "_cID";
    public static final String COURSE_TITLE="cTitle";
    public static final String COURSE_START = "startDate";
    public static final String COURSE_AENDATE = "antDate";
    public static final String COURSE_STATUS = "status";
    public static final String COURSE_MENTORS = "mentors";
    public static final String COURSE_ASSESSMENT= "cAssessment";
    public static final String COURSE_ALERT_START="aStart";//bool alerts start end
    public static final String COURSE_ALERT_END="aEnd";





    public static final String[] ALL_COURSE = {COURSE_ID,COURSE_TITLE,COURSE_START,COURSE_AENDATE,COURSE_STATUS,COURSE_MENTORS};

    //SQL to create table
    private static final String TABLE_CREATE_COURSE =
            "CREATE TABLE " + TABLE_COURSE + " (" +
                    COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_TITLE+" TEXT ,"+
                    COURSE_START  + "  TEXT," +
                    COURSE_AENDATE + "  TEXT," +
                    COURSE_STATUS + " TEXT, "+
                    COURSE_MENTORS + " TEXT, "+
                    COURSE_ASSESSMENT+ " TEXT, "+
                    COURSE_ALERT_START+ " TEXT, "+
                    COURSE_ALERT_END+ " TEXT "+
                    ")";


//constants for assessment
    public static final String TABLE_ASSESSMENT="assessment";
    public static final String ASSESSMENT_ID="_aID";
    public static final String ASSESSMENT_CID="_cIDa";
    public static final String ASSESSMENT_TITLE="aTitle";
    public static final String ASSESSMENT_OBJECTIVE_BOOLEAN="objBool";
    public static final String ASSESSMENT_SCHEDULE= "scheduleA";
    public static final String ASSESSMENT_ALERT= "assessAlert";
    public static final String ASSESSMENT_GRADE= "grade";



    //code to create table for assessment
    private static final String TABLE_CREATE_ASSESSMENT=
            "CREATE TABLE " + TABLE_ASSESSMENT + " (" +
                    ASSESSMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_CID+ " INTEGER, "+
                    ASSESSMENT_TITLE+" TEXT, "+
                    ASSESSMENT_OBJECTIVE_BOOLEAN+" TEXT, "+
                    ASSESSMENT_SCHEDULE+" TEXT, "+
                    ASSESSMENT_ALERT+" TEXT, "+
                    ASSESSMENT_GRADE+" TEXT"+
                    ")";


//constants created for mentor data
    public static final String TABLE_MENTOR="mentor";
    public static final String MENTOR_ID="_mID";
    public static final String MENTOR_NAME="mName";
    public static final String MENTOR_PHONE="mPhone";
    public static final String MENTOR_EMAIL="mEmail";

//code to create the table for mentors
    public static final String MENTOR_TABLE_CREATE =
            "CREATE TABLE "+ TABLE_MENTOR +" ("+
                    MENTOR_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MENTOR_NAME + " TEXT, "+
                    MENTOR_PHONE+  " TEXT, "+
                    MENTOR_EMAIL+ " TEXT "+

                    ")";


    //constants for term data
    public static final String TABLE_TERM="term";
    public static final String TERM_ID="_tID";
    public static final String TERM_TITLE = "tTitle";
    public static final String TERM_START= "tStart";
    public static final String TERM_END="tEnd";
    public static final String TERM_COURSES="tCourses";

    //code to create table for courses
    public static final String TERM_TABLE_CREATE=
            "CREATE TABLE "+  TABLE_TERM +" ("+
                    TERM_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TERM_TITLE + " TEXT, "+
                    TERM_START + "  TEXT, " +
                    TERM_END + "  TEXT, " +
                    TERM_COURSES + " TEXT "+


            ")";

    //constants for note table
    public static final String TABLE_NOTES="note";
    public static final String NOTES_ID = "_cNID";
    public static final String NOTES_NOTE="notes";
    public static final String NOTES_COURSE= "_cIDn";

    public static final String NOTES_TABLE_CREATE=
            "CREATE TABLE "+  TABLE_NOTES +" ("+
                    NOTES_ID+   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTES_NOTE+ " TEXT, "+
                    NOTES_COURSE+ " INTEGER "+

                    ")";




    public StubSQL(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        SQLiteDatabase db= this.getWritableDatabase();
    }


    String a="CREATE TABLE `mentor` (`_mID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`mName`	TEXT,`mPhone`	TEXT,`mEmail`	TEXT);";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_COURSE);
        db.execSQL(MENTOR_TABLE_CREATE);
        db.execSQL(TERM_TABLE_CREATE);
        db.execSQL(NOTES_TABLE_CREATE);
        db.execSQL(TABLE_CREATE_ASSESSMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MENTOR);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TERM);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ASSESSMENT);
        onCreate(db);

    }







}
