package com.example.ty.javaap_c196;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class TermDataActivity extends AppCompatActivity {

    EditText titleTxt;
    TextView startTxt;
    TextView endTxt;

    Button termSaveB;
    Button  termStartB;
    Button termEndB;
    Button  termCancelB;

    ListView coursesView;

    static boolean update = false;

    static boolean loaded = false;

    static Term theTerm = new Term();

    LinearLayout llCourse ;

    static ArrayList<Course> nCourses = new ArrayList<>(  );
    static   ArrayList<CheckBox> theClasses = new ArrayList<>(  );

    DataHandler theFile;

    private DatePickerDialog.OnDateSetListener theDateL;
    private DatePickerDialog.OnDateSetListener theDate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        toolbar.setTitle( "Add/Edit Terms: " );
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setTitle( "" );


        theTerm = new Term(  );
        theFile=new DataHandler(this);
        update = getIntent().getExtras().getBoolean("update");

        titleTxt = findViewById(R.id.titleTxt);

        startTxt = findViewById(R.id.startTxt);

        endTxt = findViewById(R.id.endTxt);

        termSaveB = findViewById(R.id.termSaveB);

        termStartB = findViewById(R.id.termStartB);

        termEndB = findViewById(R.id.termEndB);

        termCancelB = findViewById(R.id.termCancelB);

//        coursesView =(ListView) findViewById(R.id.coursesL1 );

        llCourse = findViewById(R.id.llCourse);

        nCourses.clear();
        theClasses.clear();

//        termStartB.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        Calendar theCal = Calendar.getInstance();
//                        int y= theCal.get( Calendar.YEAR );
//                        int m = theCal.get( Calendar.MONTH );
//                        int d = theCal.get( Calendar.DAY_OF_MONTH );
//
//                        DatePickerDialog picks = new DatePickerDialog( this, android.R.style.Theme_Black,theDateL,y,m,d );
//
//                    }
//                }
//        );

        termStartB.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar theCal = Calendar.getInstance();
                        int y= theCal.get( Calendar.YEAR );
                        int m = theCal.get( Calendar.MONTH );
                        int d = theCal.get( Calendar.DAY_OF_MONTH );

                        DatePickerDialog picks = new DatePickerDialog( TermDataActivity.this, android.R.style.Theme_Black,theDateL,y,m,d );
                picks.show();
            }
        } );

        theDateL = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                startTxt.setText( month+"/"+dayOfMonth+"/"+year );
            }
        };

        termSaveB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                      saveTerm();

                    }
                }
        );
        termEndB.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar theCal = Calendar.getInstance();
                int y= theCal.get( Calendar.YEAR );
                int m = theCal.get( Calendar.MONTH );
                int d = theCal.get( Calendar.DAY_OF_MONTH );

                DatePickerDialog picks = new DatePickerDialog( TermDataActivity.this, android.R.style.Theme_Black,theDate2,y,m,d );
                picks.show();
            }
        } );

        theDate2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                endTxt.setText( month+"/"+dayOfMonth+"/"+year );
            }
        };






        termCancelB.setText( "Delete Term" );
        termCancelB.setOnClickListener( new Button.OnClickListener() {
            public void onClick(View v) {
                delTerm();

            }
        } );


        if(loaded==false) {
            if (update == true) {
            theTerm = UIData.getSelectedTerm();

            titleTxt.setText( theTerm.getTitle() );
            startTxt.setText( theTerm.getStart() );
            endTxt.setText( theTerm.getEnd() );





            }
            loaded = true;
        }
        ArrayList<Course> added = new ArrayList<>(  );

        UIData.getChecked().clear();

        for(int i = 0; i<theTerm.getCourses().size(); i++){
            final CheckBox addCourse = new CheckBox( this );
            final Course target = theTerm.getCourses().get( i );
            addCourse.setText(
                    target.getcTitle()

            );
            added.add( target );
            addCourse.setChecked( true );
            addCourse.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {

                            if(addCourse.isChecked()){
                                addCourseTo(target, true);
                            }else{
                                addCourseTo( target, false );
                            }

                        }
                    } );

            theClasses.add( addCourse );
            llCourse.addView( addCourse );
            //end for loop for building check boxes from theTerm
        }

        for(int o= 0; o < MainActivity.courses.size();o++){
            boolean found = false;
            final Course target = MainActivity.courses.get( o );
            final CheckBox addCourse = new CheckBox( this );

            for(int n = 0; n<added.size();n++){
                if(target.getcID()==added.get( n ).getcID()){
                    found = true;
                }
            }
            if(found == false){

                addCourse.setText(
                        target.getcTitle()

                );
                added.add( target );
                addCourse.setChecked( false );
                addCourse.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {

                                if(addCourse.isChecked()){
                                    addCourseTo(target, true);
                                }else{
                                    addCourseTo( target, false );
                                }

                            }
                        } );
                theClasses.add( addCourse );
                llCourse.addView( addCourse );

            }

            //end of for loop building check boxes from Main
        }








    }

    private void addCourseTo(Course c, boolean a){
        if(a){
            UIData.getChecked().add( c );
            Log.d( "added", c.getcTitle() );
        }else{
            UIData.getChecked().remove( c );

        }

    }

    private void saveTerm(){
        if(titleTxt.getText().toString().length()<0){
            Toast.makeText(this,"Need name to save",Toast.LENGTH_LONG).show();
            return;}
        theFile.open();
        theTerm.setTitle( titleTxt.getText().toString() );
        theTerm.setStart( startTxt.getText().toString() );
        theTerm.setEnd( endTxt.getText().toString() );


        for(int i =0;i<UIData.getChecked().size();i++){
            Log.d( "the target has course: ", UIData.getChecked().get( i ).getcTitle() );
        }
        ArrayList<Course> newC = UIData.getChecked();

        theTerm.setCourses( newC);

        if(theTerm.gettID()>0){
           theFile.saveTerm( theTerm, true );
        }else{
            theFile.saveTerm( theTerm,false );
        }


        for(int i =0;i<theTerm.getCourses().size();i++){
            Log.d( "the term has course: ", theTerm.getCourses().get( i ).getcTitle() );
        }
        MainActivity.terms.clear();
        theFile.createTerm();
        theFile.close();
        mainScreen();

    }

    public void delTerm(){
        if(theTerm.getCourses().size()>0){
            Toast.makeText(this,"Must remove courses and save prior to deleting term.",Toast.LENGTH_LONG).show();
            return;
        }else{
        theFile.open();
        theFile.deleteTerm( theTerm.gettID() );
        MainActivity.terms.clear();
        theFile.createTerm();
        theFile.close();
        mainScreen();
        }
    }

    public void mainScreen(){
        Intent terMov = new Intent(this,MainActivity.class);
        loaded= false;
        update=false;
        startActivity(terMov);
    }
    @Override
    protected void onResume() {
        super.onResume();

        loaded= false;
        update=false;
    }
}
