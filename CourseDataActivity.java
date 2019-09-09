package com.example.ty.javaap_c196;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.ty.javaap_c196.R.layout.activity_course_data;

public class CourseDataActivity extends AppCompatActivity {

    Button saveCourseB;
    Button courseStartB;
    Button courseEndB;
    Button cancel;
    EditText courseTitleTxt;
    CheckBox courseAlertStart;
    CheckBox courseAlertend;
    TextView cStartView;
    TextView cEndView;

    DataHandler theFile;


    static String startD =  "00/00/00";

    static String endD =  "00/00/00";

    static boolean loaded= false;
    static boolean update=false;

    static Course theCourse;
//    ArrayList<RadioButton> statusButtons = new ArrayList<>(  );

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
//        setContentView( activity_course_data);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        ScrollView mainView = new ScrollView( this );
        TableLayout lv = new TableLayout( this );
        Toolbar tool = new Toolbar( this );
        tool.setPopupTheme(R.style.AppTheme);
        tool.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tool.setTitle("Add/Edit Course: ");
        tool.setVisibility( View.VISIBLE);
//        tool.setLayoutParams(layoutParams);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        theFile=new DataHandler(this);
        theCourse = new Course();

//        toolbar.setTitle( "Add/Edit Course: " );

//        lv.addView( toolbar );

        mainView.addView( lv );

        lv.addView( tool );

        LinearLayout hz = new LinearLayout( this );

        hz.setOrientation( LinearLayout.HORIZONTAL );
        courseTitleTxt = new EditText( this );
        courseTitleTxt.setHint( "Course Title" );
        courseTitleTxt.setWidth( 500 );

        hz.addView( courseTitleTxt );
        saveCourseB = new Button( this );
        saveCourseB.setText( "Save" );
        saveCourseB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        saveCourse();

                    }

                }

        );
        hz.addView( saveCourseB );
        lv.addView( hz );

        LinearLayout hz1 = new LinearLayout( this );
        cStartView = new TextView( this );
        cStartView.setText(startD);
        cStartView.setPadding( 10,10,10,10 );

        courseStartB= new Button( this );
        courseStartB.setText( "Start" );

        courseAlertStart = new CheckBox( this );
        courseAlertStart.setText( "Alert Start" );
        courseAlertStart.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                        setAstart(courseAlertStart.isChecked());
                    }

                }

        );

        cancel = new Button( this );
        cancel.setText( "Cancel" );


        hz1.addView( cStartView );
        hz1.addView( courseStartB );
        hz1.addView( courseAlertStart );
        hz1.addView( cancel );

        lv.addView( hz1 );

        LinearLayout hz2 = new LinearLayout( this );

        cEndView= new TextView( this );
        cEndView.setText( endD );
        cEndView.setPadding( 10,10,10,10 );

        courseEndB = new Button( this );
        courseEndB.setText( "End" );

        courseAlertend = new CheckBox( this );
        courseAlertend.setText( "Alert End" );
        courseAlertend.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                        setAend(courseAlertend.isChecked());
                    }

                }
        );


        hz2.addView( cEndView );
        hz2.addView( courseEndB );
        hz2.addView( courseAlertend );

        lv.addView( hz2 );


        NestedScrollView.LayoutParams scroll = new NestedScrollView.LayoutParams(
                NestedScrollView.LayoutParams.MATCH_PARENT,
//                NestedScrollView.LayoutParams.WRAP_CONTENT
                200
        );
        NestedScrollView theScroll = new NestedScrollView( this );
        theScroll.setLayoutParams( scroll );
//        theScroll.setScrollbarFadingEnabled( false );

        String[] status = new String[]{"Good", "Bad","Indiferent"};

        RadioGroup statusR = new RadioGroup( this );


        RadioButton[] statusB = new RadioButton[status.length];

        for(int i = 0; i<status.length;i++){
            final String send  = status[i];
            statusB[i] = new RadioButton( this );
            statusB[i].setText( status[i] );
            statusB[i].setOnClickListener(
                    new Button.OnClickListener(){
                        public void onClick(View v){
                            setStatus( send  );

                        }

                    }
            );
            statusR.addView( statusB[i] );

        }

        theScroll.addView( statusR );
//        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,android.R.id.text1,statusB);
//        ListView statusView=  new ListView( this );
//        statusView.inflate( this,R.layout.activity_course_data,null );
//
//
//        statusView.setAdapter( statusAdapter );
        lv.addView( theScroll );


        if(loaded==false){
            if(update==true){

            theCourse=UIData.getSlectedCourse();
            courseTitleTxt.setText( theCourse.getcTitle() );


            }else{
                theCourse=new Course();
            }



                loaded= true;
        }
        NestedScrollView theScrol2 = new NestedScrollView( this );
        TableLayout mentView = new TableLayout( this );
        TableLayout.LayoutParams tabPa = new  TableLayout.LayoutParams(
               TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        );
        mentView.setLayoutParams( tabPa );
        theScrol2.setLayoutParams( scroll );
        ArrayList<Mentor> ments = MainActivity.mentors;
        CheckBox[] mentorCheck = new CheckBox[ments.size()];
        for(int i = 0 ; i<ments.size();i++){
            mentorCheck[i]= new CheckBox( this );
            final CheckBox selected =  mentorCheck[i];
            final int menID = ments.get( i ).getmID();
            mentorCheck[i].setText( ments.get( i ).getName() );
            mentorCheck[i].setOnClickListener(
                    new Button.OnClickListener(){
                        public void onClick(View v){
                            if(selected.isChecked()){
                                addMent( menID );
                            }else{
                                removeMent( menID );
                            }

                        }
                    }

            );


//            lv.addView( mentorCheck[i] );
            mentView.addView( mentorCheck[i] );

        }
        theScrol2.addView( mentView );
        lv.addView( theScrol2 );
        TableLayout asView = new TableLayout( this );
        asView.setLayoutParams( tabPa );
        NestedScrollView theScro3 = new NestedScrollView( this );
        theScro3.setLayoutParams( scroll );
        ArrayList<Assessment> assessmentCheck = MainActivity.assesmentAr;
        final CheckBox[] asCheck = new CheckBox[assessmentCheck.size()];
        for(int i = 0; i<assessmentCheck.size(); i++){
            asCheck[i]= new CheckBox( this );
            asCheck[i].setText( assessmentCheck.get( i ).getATitle() );
            final CheckBox selected = asCheck[i];
            final int asID=asCheck[i].getId();
            asCheck[i].setOnClickListener(
                    new Button.OnClickListener(){
                public void onClick(View v){
                if(selected.isChecked()){
                    addAs( asID );

                }else{
                    remAs( asID );
                }

                }

            } );

           asView.addView(  asCheck[i] );

        }
        theScro3.addView( asView );
        lv.addView( theScro3 );






        setContentView(mainView );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add( Menu.NONE,0, Menu.NONE,"Notes" );
        menu.add( Menu.NONE,1, Menu.NONE,"Add Assessment" );
        menu.add( Menu.NONE,2, Menu.NONE,"Delete Course" );

        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        String check = item.getItemId();

        switch(item.getItemId()){
//            case R.id.deleteMentorI:
//                deleteMent();
//                return true;
//            case R.id.goCourseI:
//                courseScreen();
//                return true;

//            case R.id.goHomeI:
//                mainScreen();
//                return true;

        }
//        Toast.makeText(this,item.getItemId(),Toast.LENGTH_LONG).show();
        return true;
    }

    //buttons and functions

    public void setAend(boolean b){
        if(b){
            theCourse.setAlertE( "true" );
        }else{
            theCourse.setAlertE( "false" );
        }
    }

    public void setAstart(boolean b){
        if(b){
            theCourse.setAlertS( "true" );
        }else {
            theCourse.setAlertS( "false" );
        }

    }

    public void remAs(int as){
        ArrayList<Assessment> asCourse= theCourse.getAssesssment();
        for(int i = 0; i<asCourse.size();i++){
            if(asCourse.get( i ).getaID()==as){
                asCourse.remove( i );
            }
        }

    }

    public void addAs(int as){
        ArrayList<Assessment> asCourse= theCourse.getAssesssment();
        ArrayList<Assessment>db = MainActivity.assesmentAr;
        for(int i =0; i<db.size();i++){
            if(db.get( i ).getaID()==as){
                asCourse.add( db.get( i ) );
            }
        }

    }

    public void removeMent(int ment){
        ArrayList<Mentor> mentA = theCourse.getMentors();

        for(int i =0; i<mentA.size();i++){
            if(mentA.get( i ).getmID()==ment){
                mentA.remove( i );
            }
        }

    }

    public void addMent(int ment){
       ArrayList<Mentor> mentA = theCourse.getMentors();
       ArrayList<Mentor> db = MainActivity.mentors;

       for(int i=0; i<db.size();i++){
           if(db.get( i ).getmID()==ment){
               mentA.add( db.get( i ) );
           }

       }

    }

    public void setStatus(String stat){

        theCourse.setStatus( stat );
        return;
    }

    public void saveCourse(){
     try {
         theFile.open();
         if (courseTitleTxt.getText().toString().equals( "" )) {
             return;
         }
         theCourse.setcTitle( courseTitleTxt.getText().toString() );

         ArrayList<Assessment> th = theCourse.getAssesssment();
         Assessment add = new Assessment();
         add.setaID( 1 );
         add.setcIDa( 0 );
         th.add( add );

         theCourse.setcStart(cStartView.getText().toString()  );
         theCourse.setcAEnd( cEndView.getText().toString() );
         if(theCourse.getAlertS()==null){
             theCourse.setAlertS( "false" );
         }else{
//             theCourse.setAlertS( "true" );
         }

         if(theCourse.getAlertE()==null){
             theCourse.setAlertE( "false" );
         }else{
//             theCourse.setAlertE( "true" );
         }



         if (update == false) {
//            theFile.open();
             theFile.saveCourse( theCourse, false );

         }

         if (update == true) {
//            theFile.open();
             theFile.saveCourse( theCourse, true );

         }
         MainActivity.courses.clear();
         theFile.createCourse();

         theFile.close();





     }catch (Exception e){

         Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
     }

    }

}
