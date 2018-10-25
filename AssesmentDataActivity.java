package com.example.ty.javaap_c196;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class AssesmentDataActivity extends AppCompatActivity {

    EditText titleText ;
    EditText assesText;

    RadioButton perf;
    RadioButton obje;

    CheckBox alertCheck;

    TextView dateTxt;

    Button saveB;
    Button schedB;
    Button addScB;
    Button delB;

    LinearLayout noteAsse;

    private DatePickerDialog.OnDateSetListener theDateStart;
    private DatePickerDialog.OnDateSetListener theDateEnd;


    static Course theCourse;

    static boolean update = false;

    static boolean loaded = false;

    static Assessment theAssess = new Assessment();

    DataHandler theFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_assesment_data );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolAss );
        setSupportActionBar( toolbar );


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle( "Assessment: " );
        update = getIntent().getExtras().getBoolean("update");

        titleText = findViewById(R.id.titleTxt);

        assesText = findViewById(R.id.assessTxt);

        perf = findViewById(R.id.perfRad);

        obje = findViewById(R.id.objRad);

        alertCheck  = findViewById(R.id.cAlert);

        dateTxt = findViewById(R.id.tSchedule);

        saveB =  findViewById(R.id.bSave);

        schedB=  findViewById(R.id.bSchedule);

        addScB=  findViewById(R.id.bScore);

        delB=  findViewById(R.id.bDelete);

        noteAsse= findViewById( R.id.asseNote );

        theFile=new DataHandler(this);
        try {
            theCourse = UIData.getSlectedCourse();
        }catch(Exception e){
            Log.d("ErrorLCourse", e.toString());
        }

        if(loaded==false) {
            if (update == true) {
//try{

                theAssess = UIData.getSelectedAs();
//           if(MainActivity.noteAr.size()==0){Notes theNew = new Notes();}
              try {
                  for (int i = 0; i < MainActivity.noteAr.size(); i++) {
                      if (MainActivity.courses.get( i ).getcID() == theAssess.getcIDa()) {
                          UIData.setSlectedCourse( MainActivity.courses.get( i ) );
                      }
                  }
              }catch(Exception d){
                  Log.d("Error",d.toString());
              }
                titleText.setText( theAssess.getATitle() );
                if(theAssess.getGrade()!= null){
                    assesText.setText( theAssess.getGrade() );
                }
                if(theAssess.getOjbectiveBoolean().equals( "true" )){
                    obje.isChecked();
                }else{
                    perf.isChecked();
                }

                dateTxt.setText( theAssess.getSchedule() );

                if(theAssess.getAlert().equals( "true" )){
                    alertCheck.setChecked( true );
                }

//       }catch(Exception d){
//    Log.d( "ErrorUpdate", d.toString() );
//}
            }

            loaded= true;

        }
        saveB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                        saveAssess();
                    }
                }
        );


        schedB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
//                    setAstart(courseAlertStart.isChecked());
                    }
                }
        );

        addScB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        theAssess.setGrade( assesText.getText().toString() );
                    }
                }
        );

        delB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        deleteAssess();
                    }
                }
        );

        schedB.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar theCal = Calendar.getInstance();
                int y= theCal.get( Calendar.YEAR );
                int m = theCal.get( Calendar.MONTH );
                int d = theCal.get( Calendar.DAY_OF_MONTH );

                DatePickerDialog picks = new DatePickerDialog( AssesmentDataActivity.this, android.R.style.Theme_Black,theDateStart,y,m,d );
                picks.show();
            }
        } );


    theDateStart = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month += 1;
            dateTxt.setText( month+"/"+dayOfMonth+"/"+year );
        }
    };

        ArrayList<Notes> note = MainActivity.noteAr;

        Button noteB = new Button( this );
        noteB.setText( "Course Notes:" );
        noteB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        noteScreen();
                    }
                }
        );

        noteAsse.addView( noteB );

    for(int n = 0; n<note.size();n++){
        Notes terget = note.get( n );
        Log.d("The course is",""+terget.getCourse() );
        Log.d( "The asses C is", ""+ theAssess.getcIDa() );
        if(terget.getCourse() == UIData.getSelectedAs().getcIDa()) {


            for (int z = 0; z < terget.getNotes().size(); z++) {
                Button[] theNote = new Button[terget.getNotes().size()];
                theNote[z] = new Button( this );
                theNote[z].setText( terget.getNotes().get( z ) );
                theNote[z].setOnClickListener(
                        new Button.OnClickListener(){
                            public void onClick(View v){
                               noteScreen();
                            }
                        }
                );


                noteAsse.addView( theNote[z] );
            }
        }
    }


    }

    public void saveAssess(){
        theFile.open();
        if(titleText.getText().toString().length()<=0){return;}
        Log.d( "Told to save", "Starting..." );
        boolean perf1;
        if(  obje.isChecked()){
            perf1= true;
        }else{
            perf1=false;
        }
        String change = Boolean.toString( perf1 );
        theAssess.setATitle( titleText.getText().toString() );
        theAssess.setOjbectiveBoolean( change );
        theAssess.setSchedule( dateTxt.getText().toString() );
        theAssess.setcIDa( UIData.getSlectedCourse().getcID() );
        if(assesText.getText().toString().length()>0){
            theAssess.setGrade( assesText.getText().toString() );
        }
        boolean alert1 = alertCheck.isChecked();
        String change2 = Boolean.toString( alert1 );
        theAssess.setAlert( change2 );
//        theAssess.setcIDa( UIData.getSlectedCourse().getcID() );
        if(theCourse.getcTitle()!=null){

        }else {
            theAssess.setcIDa( theCourse.getcID() );

        }

        if(theAssess.getaID()>0){
            Log.d( "Assesss update ","Starting..." );
            theFile.saveAsse( theAssess, true );


        }else{
            Log.d( "Assesss new ","Starting..." );
            theFile.saveAsse( theAssess,false );
        }

        MainActivity.assesmentAr.clear();
        theFile.createAssessment();




        theFile.close();
        mainScreen();

    }

    public void mainScreen(){
        Intent terMov = new Intent(this,MainActivity.class);
        loaded= false;
        update=false;
        startActivity(terMov);
    }

    public void deleteAssess(){
        theFile.open();
        if(theAssess.getcIDa()>0){

            theFile.deleteAssessment( theAssess.getaID() );

        }else{
            theAssess = new Assessment();
        }

        MainActivity.assesmentAr.clear();
        theFile.createAssessment();
        theFile.close();
        assmentScreen();
    }

    public void assmentScreen(){
        update =false;
        loaded=false;
        UIData.setSlectedCourse( UIData.getSlectedCourse() );
        Intent terMov = new Intent(this,CourseDataActivity.class);
        terMov.putExtra( "update", true);
        UIData.setCourseUpdate( true );
        startActivity(terMov);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loaded= false;
        update=false;
    }

    public void noteScreen(){
        Intent terMov = new Intent(this,NoteActivity.class);
        loaded= false;
        update=false;
//        terMov.putExtra( "course", (Parcelable) theCourse );
        terMov.putExtra( "update", true );
        for(int i=0; i<MainActivity.courses.size(); i++){
            if(MainActivity.courses.get( i ).getcID()==theAssess.getcIDa()){
                UIData.setSlectedCourse( MainActivity.courses.get( i ) );
            }
        }
//        UIData.setSlectedCourse( theCourse );
        Log.d( "courseLoadedtoUI",""+ theCourse.getcID() );
        startActivity(terMov);
    }

}
