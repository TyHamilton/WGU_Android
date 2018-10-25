package com.example.ty.javaap_c196;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    static ArrayList<Mentor> mentors = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Term> terms = new ArrayList<>();
    static ArrayList<Notes> noteAr = new ArrayList<>();
    static ArrayList<Assessment> assesmentAr = new ArrayList<>();
    static boolean load= false;

        DataHandler dh;

        private GestureDetectorCompat gd;
//    SharedPreferences courseApref = getSharedPreferences( "courseAlerts", Context.MODE_PRIVATE );

//    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UIData.alarmTerm();
//        UIData.dayPacker( "4/4/2018" );
        dh = new DataHandler(this);

        Toast.makeText(this,"Database Loaded",Toast.LENGTH_LONG).show();
        this.gd = new GestureDetectorCompat(this,this);
//         Context  contexter = this;
//         context = this;
//        SharedPreferences courseAlertP = PreferenceManager.getDefaultSharedPreferences( this );

//        DataStub.dataLoader();
        try{
            dh.close();
        }catch(Exception e){}
        dh.open();
//
//        dh.saveMentor(mentors.get(0),false);
//        dh.saveMentor(mentors.get(1),false);
//        dh.saveCourse(courses.get(0),false);
//        dh.saveCourse(courses.get(1),false);
//        dh.saveTerm(terms.get(0),false);
//        dh.saveTerm(terms.get(1),false);

       if(load==false) {
           mentors.clear();
           courses.clear();
           terms.clear();
           noteAr.clear();
           assesmentAr.clear();
//           Notes theNew = new Notes();
//           theNew.getNotes().add( "one" );
//           dh.saveNote( theNew, false );
//           noteAr.add( theNew );

           dh.createAssessment();
           dh.createNote();
           dh.createMentors();
           Log.d("Mentors added to array:", ""+mentors.size());
           dh.createCourse();
           dh.createTerm();

        load = true;
       }

//        DataStub.dataLoader();
//        DataStub.dataLoader();

        /*
        *UI code
         */

        ActionBar ab = getActionBar();
//        ab.setLogo();
//        ab.show();

        ScrollView sv = new ScrollView(this);
        TableLayout ly = new TableLayout(this);
        ly.computeScroll();

    sv.addView(ly);

        RelativeLayout.LayoutParams buttons = new  RelativeLayout.LayoutParams(

                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT


                );

        RelativeLayout.LayoutParams buttons2 = new  RelativeLayout.LayoutParams(

                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT


        );





            Button first = new Button(this);


            Button nav = new Button(this);
            nav.setText("[------Navigation------]");
            nav.setBackgroundColor(Color.parseColor("#FAB01C"));
            ly.addView(nav);


        Button note = new Button(this);
        note.setText("Note");
        note.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        noteScreen();

                    }

                }

        );
//        ly.addView(note);





        Button temp1 = new Button(this);
        temp1.setText("ASSESSMENTS");
        temp1.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        temp();

                    }

                }

        );



        ly.addView(temp1);

            Button mentors = new Button(this);
            mentors.setText(" Mentors");
            mentors.setOnClickListener(
                    new Button.OnClickListener(){
                        public void onClick(View v){
                            mentorScreen();

                        }

                    }

            );

            ly.addView(mentors);


            Button course = new Button(this);
            course.setOnClickListener(
                    new Button.OnClickListener(){
                        public void onClick(View v){
                            courseScreen();

                        }

                    }

            );
            course.setText(" Courses");

            ly.addView(course);

            Button term = new Button(this);
            term.setOnClickListener(
                    new Button.OnClickListener(){
                        public void onClick(View v){
                            termScreen();
                        }

                    }

            );
            term.setText(" Terms");

            ly.addView(term);

            Button sep = new Button(this);
            sep.setText("[------Your Terms and Courses------]");
            sep.setBackgroundColor(Color.parseColor("#FAB01C"));

            ly.addView(sep);

        if(terms.size()>0) {
            Button[] termB = new Button[terms.size()];
            Button tParent = first;
            Button cParent = new Button(this);
            Button[] cor = new Button[0];
            int idI=0;
            for (int i = 0; i < terms.size(); i++) {
                termB[i] = new Button(tParent.getContext());
                String termName = terms.get(i).getTitle();
                termB[i].setTextSize(15);

            termB[i].setBackgroundColor(Color.parseColor("#94ABC1"));
                termB[i].setPadding(10, 20, 10, 10);
                termB[i].setId(i);

                termB[i].setGravity(10);
                int termNum = i + 1;

                String stt = terms.get(i).getStart();

                String nd = terms.get(i).getEnd();

                int classSize = terms.get(i).getCourses().size();

                termB[i].setText(" " + termNum + ": " + termName + " Start: " + stt + " End: " + nd + " ");
                idI+=i;
                tParent = termB[i];

                ly.addView(termB[i], buttons);
                 cor = new Button[terms.get(i).getCourses().size()];
                Log.d( "CourseL",""+ terms.get(i).getCourses().size() );
                for(int j=0; j<terms.get(i).getCourses().size();j++){
                    ArrayList<Course> buff = terms.get(i).getCourses();
                    Log.d( "CourseL",""+ buff.size() );
                    if(j==0){
                        cor[j]= new Button(tParent.getContext());

                    }else{
                        cor[j]= new Button(cParent.getContext());
                    }
                    idI+=j;

                  String titleC= buff.get(j).getcTitle();
                  String startC= buff.get(j).getcStart();
                  String endC=buff.get(j).getcAEnd();



                    cor[j].setText("-"+titleC+" Start: "+startC+" End: "+endC);
                    cor[j].setTextSize(16);
                    cor[j].setPadding(10, 20, 10, 10);
                    cor[j].setId(idI);

                    cParent=cor[j];
                    ly.addView(cor[j],buttons);

                }


            }
        }//end of if for loading data from terms


        setContentView(sv);

/*
 Alert code in main this starts the alert check, after alerts it is saved to shared preferences
 */

       Boolean doI= alarmTerm();
//        Toast.makeText(this,"test "+doI,Toast.LENGTH_LONG).show();
       if(doI){
           AlertDialog.Builder build = new AlertDialog.Builder( this );
           build.setTitle( "Term Alert" );
           build.setMessage( "Your Term "+alertT.getTitle()+ " has days left: "+termLeft );
           build.setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.cancel();
               }
           } );
        build.show();

       }


        Boolean alertCourse =alarmCourseEnd();
       if(alertCourse){
           AlertDialog.Builder build = new AlertDialog.Builder( this );
           build.setTitle( "Course End" );
           build.setMessage( "Your Course "+alertC.getcTitle()+ " has days left: "+courseLleft);
           build.setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.cancel();
               }
           } );
           build.show();
       }


       Boolean aboutAssesment = alarmAsses();
       if(aboutAssesment){

           AlertDialog.Builder build = new AlertDialog.Builder( this );
           build.setTitle( "Assessment soon" );
           build.setMessage( "Your assessment "+alertA.getATitle()+ " in: "+assessLeft);
           build.setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.cancel();
               }
           } );
           build.show();


       }

        boolean orStart = alarmCourseStart();

        if(orStart){

            AlertDialog.Builder build = new AlertDialog.Builder( this );
            build.setTitle( "Course Start" );
            build.setMessage( "Your course "+alertCS.getcTitle()+ " starts in: "+startCourse +"days");
            build.setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            } );
            build.show();


        }

    }


    public void temp(){
        Intent menMov = new Intent(this,AssesmentActivity.class);

        startActivity(menMov);

    }

    public void mentorScreen(){
        Intent menMov = new Intent(this,MentorMActivity.class);

        startActivity(menMov);
    }

    public void courseScreen(){
        Intent couMov = new Intent(this,CoursesActivity.class);

        startActivity(couMov);
    }

    public void termScreen(){
        Intent terMov = new Intent(this,TermsActivity.class);

        startActivity(terMov);
    }

    public void noteScreen(){
        Intent terMov = new Intent(this,NoteActivity.class);

        startActivity(terMov);
    }



    @Override
    protected void onPause(){
        super.onPause();
        dh.close();
    }


    @Override
    protected void onResume(){
        super.onResume();
        dh.open();
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        Toast.makeText(this,"Scroll",Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gd.onTouchEvent(event);
//        Toast.makeText(this,"Event Detected",Toast.LENGTH_LONG).show();
        return super.onTouchEvent(event);
    }



    /*
    Alert code
     */

    //code for alerts
    private static Term alertT;
    private static int termLeft;
    private static Course alertC;
    private static int courseLleft;
    private static Assessment alertA;
    private static int assessLeft;
    private static Course alertCS;
    private static int startCourse;


    public static Course getAlertC() {
        return alertC;
    }

    public static int getCourseLleft() {
        return courseLleft;
    }

    public static Assessment getAlertA() {
        return alertA;
    }

    public static int getAssessLeft() {
        return assessLeft;
    }

    public static   Term getAlertT() {
        return alertT;
    }

    public static void setAlertT(Term alertT) {
        alertT = alertT;
    }

    public static int getTermLeft() {
        return termLeft;
    }

    public static void setTermLeft(int termLeft) {
        termLeft = termLeft;
    }

// shared pref for alerts this holds data to prevent repeating alerts

    public void sharedPAlertTerm(String id , String date){
        SharedPreferences working = this.getSharedPreferences( "termAlerts" , MODE_PRIVATE);
        SharedPreferences.Editor ed= working.edit();
        ed.putString( id,date );
        ed.apply();
       String dater = working.getString( id,"" );

        Log.d( "SharedT Date", dater );

    }

    public String sharedPAlertTerm(String id ){
        String date="";
        SharedPreferences working = this.getSharedPreferences( "termAlerts" , MODE_PRIVATE);

        date = working.getString( id,"" );

        return date;
    }

    public void sharedPAlertCStart(String id , String date){
        SharedPreferences working = this.getSharedPreferences( "cStartAlerts" , MODE_PRIVATE);
        SharedPreferences.Editor ed= working.edit();
        ed.putString( id,date );
        ed.apply();
    }

    public String sharedPAlertCStart(String id ){
        String date="";
        SharedPreferences working = this.getSharedPreferences( "cStartAlerts" , MODE_PRIVATE);

        date = working.getString( id,"" );

        return date;
    }



    public void sharedPAlertCEnd(String id , String date){
        SharedPreferences working = this.getSharedPreferences( "cEndAlerts" , MODE_PRIVATE);
        SharedPreferences.Editor ed= working.edit();
        ed.putString( id,date );
        ed.apply();
    }

    public String sharedPAlertCEnd(String id ){
        String date="";
        SharedPreferences working = this.getSharedPreferences( "cEndAlerts" , MODE_PRIVATE);

        date = working.getString( id,"" );

        return date;
    }


    public void sharedPAlertAsse(String id , String date){
        SharedPreferences working = this.getSharedPreferences( "assesAlerts" , MODE_PRIVATE);
        SharedPreferences.Editor ed= working.edit();
        ed.putString( id,date );
        ed.apply();
    }

    public String sharedPAlertAsse(String id ){
        String date="";
        SharedPreferences working = this.getSharedPreferences( "assesAlerts" , MODE_PRIVATE);

        date = working.getString( id,"" );

        return date;
    }


    //code for alerts


    public  boolean didIAlert(String name, String id){
        int[] today= UIData.dayRet();
        int month =today[0];
        int day = today[1];
        int year = today[2];

        int[] pull =new int[3];
        int cMon = pull[0];
        int cDay = pull[1];
        int cYear = pull[2];

        String dString=""+month+"/"+day+"/"+year;
        String cString="";

        switch(name) {
            case "term":
                String tCheck =sharedPAlertTerm( id );

                if(tCheck.equals( dString )){
                    Log.d("didIAlert Term", "yes");
                    return true;}

                return false;

            case "courseS":
                String csCheck =sharedPAlertCStart( id );

                if(csCheck.equals( dString )){return true;}

                return false;

            case "courseE":
                String ceCheck =sharedPAlertCEnd( id );

                if(ceCheck.equals( dString )){return true;}

                return false;

            case "alert":
                String aCheck =sharedPAlertAsse( id );

                if(aCheck.equals( dString )){return true;}
                return false;


            default:

                return false;

        }



    }

      public boolean  alarmTerm(){

        Boolean alt = false;
       int[] today= UIData.dayRet();
       int month =today[0];
       int day = today[1];
       int year = today[2];
        String dString=""+month+"/"+day+"/"+year;
//        Log.d("theDay is: ", dString);
        for(int i=0;i<MainActivity.terms.size();i++){
            String id =MainActivity.terms.get( i ).getTitle()+MainActivity.terms.get( i ).gettID();
            if (didIAlert("term", id) == false) {
                int[] pull = UIData.dayPacker( MainActivity.terms.get( i ).getEnd() );
                int cMon = pull[0];
                int cDay = pull[1];
                int cYear = pull[2];
                if (cYear == year && cMon == month) {
                    int remainder = cDay - day;
                    if (remainder < 8) {
                        alt = true;
                        alertT = MainActivity.terms.get( i );
                        termLeft = remainder;
                        sharedPAlertTerm( id,dString );
                    }
                }
            }
        }

        return alt;

    }

     public boolean alarmCourseEnd(){
        Boolean alt = false;
        int[] today= UIData.dayRet();
        int month =today[0];
        int day = today[1];
        int year = today[2];
        String dString=""+month+"/"+day+"/"+year;
        for(int i=0;i<MainActivity.courses.size();i++) {
            String id =MainActivity.courses.get( i ).getcTitle()+MainActivity.courses.get( i ).getcID();
                if(MainActivity.courses.get( i ).getAlertE().equals( "true" )){
                    if (didIAlert("courseE",id) == false) {
                int[] pull = UIData.dayPacker(MainActivity.courses.get( i ).getcAEnd());
                int cMon = pull[0];
                int cDay = pull[1];
                int cYear = pull[2];
                if (cYear == year && cMon == month) {
                    int remainder = cDay - day;
                    if (remainder < 8) {
                        alt = true;
                        alertC = MainActivity.courses.get( i );
                        courseLleft = remainder;
                        sharedPAlertCEnd( id,dString );
                    }
                }
            }
        }
        }
       return alt;
    }

     static final int[] lastDay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        public boolean alarmAsses(){

         Boolean alt = false;
        int[] today= UIData.dayRet();
        int month =today[0];
        int day = today[1];
        int year = today[2];
        String dString=""+month+"/"+day+"/"+year;
        for(int i=0;i<MainActivity.assesmentAr.size();i++) {
            String id =MainActivity.assesmentAr.get( i ).getATitle()+MainActivity.assesmentAr.get( i ).getaID();
            if(MainActivity.assesmentAr.get( i ).getAlert().equals( "true" )) {
                if (didIAlert("alert",id) == false) {
                int[] pull = UIData.dayPacker( MainActivity.assesmentAr.get( i ).getSchedule() );
                int cMon = pull[0];
                int cDay = pull[1];
                int cYear = pull[2];


                int days = lastDay[month+1];

                    if(cMon==2){
                    int che = leapCheck( cYear,cMon );
                    if(che==29){
                        days=29;
                    }
                }
                int nextMonth = days + cDay;
                    int remainder1= nextMonth-cDay;
                    if (remainder1 < 8) {
                        alt = true;
                        alertA = MainActivity.assesmentAr.get( i );
                        assessLeft = remainder1;
                        sharedPAlertAsse( id,dString );
                    }


                if (cYear == year && cMon == month) {
                    int remainder = cDay - day;
                    if (remainder < 8) {
                        alt = true;
                        alertA = MainActivity.assesmentAr.get( i );
                        assessLeft = remainder;
                        sharedPAlertAsse( id,dString );
                    }
                }
            }
        }
        }
        return alt;

    }


    public  int leapCheck(int year, int month){
        int dDay=0;
        boolean leapYear =((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
        if(leapYear){
            if(month == 2){
                return 29;
            }else {
                dDay=lastDay[month];
                return dDay;
            }
        }else{
            dDay=lastDay[month];
            return dDay;
        }

    }

     public boolean alarmCourseStart(){
        Boolean alt = false;
        int[] today= UIData.dayRet();
        int month =today[0];
        int day = today[1];
        int year = today[2];
        String dString=""+month+"/"+day+"/"+year;
        for(int i=0;i<MainActivity.courses.size();i++) {
            String id =MainActivity.courses.get( i ).getcTitle()+MainActivity.courses.get( i ).getcID();
            if(MainActivity.courses.get( i ).getAlertS().equals( "true" )){
                if (didIAlert("courseS",id) == false)  {
                    int[] pull = UIData.dayPacker( MainActivity.courses.get( i ).getcStart() );
                    int cMon = pull[0];
                    int cDay = pull[1];
                    int cYear = pull[2];



                    int days = lastDay[month+1];

                    if(cMon==2){
                        int che = leapCheck( cYear,cMon );
                        if(che==29){
                            days=29;
                        }
                    }
                    int nextMonth = days + cDay;
                    int remainder1= nextMonth-cDay;
                    if (remainder1 < 8) {
                        alt = true;
                        alertA = MainActivity.assesmentAr.get( i );
                        assessLeft = remainder1;
                        sharedPAlertCStart( id,dString );
                    }

                    if (cYear == year && cMon == month) {
                        int remainder = cDay - day;
                        if (remainder < 8) {
                            alt = true;
                            alertCS = MainActivity.courses.get( i );
                            startCourse = remainder;
                            sharedPAlertCStart( id,dString );
                        }
                    }
                }
            }
        }
        return alt;
    }
}



