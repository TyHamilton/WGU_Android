package com.example.ty.javaap_c196;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dh = new DataHandler(this);
        dh.open();
        Toast.makeText(this,"Database Loaded",Toast.LENGTH_LONG).show();
        this.gd = new GestureDetectorCompat(this,this);

//        DataStub.dataLoader();
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

           dh.createAssessment();
           dh.createNote();
           dh.createMentors();
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
        ly.addView(note);





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
                for(int j=0; j<terms.get(i).getCourses().size();j++){
                    ArrayList<Course> buff = terms.get(i).getCourses();
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
}



