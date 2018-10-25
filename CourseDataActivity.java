package com.example.ty.javaap_c196;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.ty.javaap_c196.R.layout.activity_course_data;

public class CourseDataActivity extends AppCompatActivity {

    Button saveCourseB;
    Button courseStartB;
    Button courseEndB;
    Button cancel;
    Button cDelete;
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


    private DatePickerDialog.OnDateSetListener theDateStart;
    private DatePickerDialog.OnDateSetListener theDateEnd;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String[] status = new String[]{"In Progress", "Completed","Dropped","Plan To Take"};
        super.onCreate(savedInstanceState);
//        if(getIntent().getExtras().getBoolean( "update" )){
//            update = true;
//        }
        update= getIntent().getExtras().getBoolean( "update" );

        ScrollView mainView = new ScrollView( this );
        TableLayout lv = new TableLayout( this );
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT, 168);
        Toolbar tool = new Toolbar(this);
//        tool.setPopupTheme(R.style.AppTheme);
        tool.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tool.setTitle("Courses: ");
        tool.setVisibility(View.VISIBLE);
//        tool.setLayoutParams(layoutParams);
        setSupportActionBar(tool);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled( true );
        theFile=new DataHandler(this);
        theCourse = new Course();




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
        cancel.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        mainScreen();
                    }

                }
        );


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
        cDelete = new Button( this );

        cDelete.setText( "Delete" );
        cDelete.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        deleteCourse();
                    }

                }
        );


        hz2.addView( cEndView );
        hz2.addView( courseEndB );
        hz2.addView( courseAlertend );
//        hz2.addView( cDelete );

        lv.addView( hz2 );


        NestedScrollView.LayoutParams scroll = new NestedScrollView.LayoutParams(
                NestedScrollView.LayoutParams.MATCH_PARENT,
//                NestedScrollView.LayoutParams.WRAP_CONTENT
                250
        );
        NestedScrollView theScroll = new NestedScrollView( this );
        theScroll.setLayoutParams( scroll );
//        theScroll.setScrollbarFadingEnabled( false );



        RadioGroup statusR = new RadioGroup( this );
        RadioButton[] statusB = new RadioButton[status.length];

        for(int i = 0; i<status.length;i++){
            final String send  = status[i];
            statusB[i] = new RadioButton( this );
            statusB[i].setText( status[i] );
            statusB[i].setId( 4000+i );
            statusB[i].setOnClickListener(
                    new Button.OnClickListener(){
                        public void onClick(View v){
                            setStatus( send  );

                        }

                    }
            );
            statusR.addView( statusB[i] );

        }
        if(loaded==false){
            if(update==true){

                theCourse=UIData.getSlectedCourse();
                courseTitleTxt.setText( theCourse.getcTitle() );
                for(int i = 0; i<status.length;i++){
                    if(status[i].equals( theCourse.getStatus() )){
                        statusR.check( 4000+i );
                    }
                }

                if(theCourse.getAlertS().equals( "true" )){
                    courseAlertStart.setChecked( true );
                }
                if(theCourse.getAlertE().equals( "true" )){
                    courseAlertend.setChecked( true );
                }

                cStartView.setText( theCourse.getcStart() );
                cEndView.setText( theCourse.getcAEnd() );
                Log.d( "load has mentors: ", ""+theCourse.getMentors().size() );
            }else{
                theCourse=new Course();
            }



            loaded= true;
        }

        courseStartB.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar theCal = Calendar.getInstance();
                int y= theCal.get( Calendar.YEAR );
                int m = theCal.get( Calendar.MONTH );
                int d = theCal.get( Calendar.DAY_OF_MONTH );

                DatePickerDialog picks = new DatePickerDialog( CourseDataActivity.this, android.R.style.Theme_Black,theDateStart,y,m,d );
                picks.show();
            }
        } );

        courseEndB.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar theCal = Calendar.getInstance();
                int y= theCal.get( Calendar.YEAR );
                int m = theCal.get( Calendar.MONTH );
                int d = theCal.get( Calendar.DAY_OF_MONTH );

                DatePickerDialog picks = new DatePickerDialog( CourseDataActivity.this, android.R.style.Theme_Black,theDateEnd,y,m,d );
                picks.show();
            }
        } );

        theDateStart =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                cStartView.setText( month+"/"+dayOfMonth+"/"+year );
            }
        };

        theDateEnd =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                cEndView.setText( month+"/"+dayOfMonth+"/"+year );
            }
        };


        theScroll.addView( statusR );
//        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,android.R.id.text1,statusB);
//        ListView statusView=  new ListView( this );
//        statusView.inflate( this,R.layout.activity_course_data,null );
//
//
//        statusView.setAdapter( statusAdapter );
        lv.addView( theScroll );



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

            final Mentor target = ments.get( i );
            final CheckBox selected =  mentorCheck[i];
            Log.d( "Checkbox for:", target.getName() );
            final int menID = ments.get( i ).getmID();

            Log.d( "Size of mentors: ", "" +theCourse.getMentors().size() );
            for(int n =0; n<theCourse.getMentors().size();n++){
                Log.d( "Searching: ", target.getName() );
                if(theCourse.getMentors().get( n ).getmID()==target.getmID()){
                    mentorCheck[i].setChecked( true );
                    Log.d( "Mentor found", target.getName() );
                }else{
//                    mentorCheck[i].setChecked( false );
                    Log.d( "Mentor not found", target.getName() );
                }
            }


            mentorCheck[i].setText( ments.get( i ).getName() );
            mentorCheck[i].setOnClickListener(
                    new Button.OnClickListener(){
                        public void onClick(View v){
                            if(selected.isChecked()){
                                mentData( target,true );
                            }else{
                                mentData( target,false );
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
//        for(int i = 0; i<assessmentCheck.size(); i++){
//            asCheck[i]= new CheckBox( this );
//            asCheck[i].setText( assessmentCheck.get( i ).getATitle() );
//            final CheckBox selected = asCheck[i];
//            final int asID=asCheck[i].getId();
//            asCheck[i].setOnClickListener(
//                    new Button.OnClickListener(){
//                public void onClick(View v){
//                if(selected.isChecked()){
//                    addAs( asID );
//
//                }else{
//                    remAs( asID );
//                }
//
//                }
//
//            } );
//
//           asView.addView(  asCheck[i] );
//
//        }
//        theScro3.addView( asView );
//        lv.addView( theScro3 );

        Button nav = new Button(this);
        nav.setBackgroundColor( Color.parseColor("#FAB01C"));
        nav.setText("[------Your Assessments------]");

        lv.addView( nav );

        ArrayList<Assessment> asBuff = MainActivity.assesmentAr;
        Button[] buttonB = new Button[asBuff.size()];
        for(int i =0; i<asBuff.size(); i++){
            final Course theC= UIData.getSlectedCourse();
            buttonB[i]= new Button(this);
            final Assessment targ = asBuff.get(i);
            if(targ.getcIDa()==theC.getcID()) {
                String butTxt= "Assessment: "+ targ.getATitle()+"\n Type: ";

                if(targ.getOjbectiveBoolean().equals("true")){
                    butTxt+= "Objective ";
                }else{
                    butTxt+="Performance ";
                }

                butTxt+="\n Scheduled: "+targ.getSchedule()+"\n Score: ";

                if(targ.getGrade()==null){

                }else{
                    butTxt+=targ.getGrade();
                }

                buttonB[i].setText(butTxt);

                buttonB[i].setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                edAssesment( targ );
                            }
                        }
                );

                lv.addView( buttonB[i] );
            }
        }






        setContentView(mainView );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add( Menu.NONE,0, Menu.NONE,"Notes" );
        menu.add( Menu.NONE,1, Menu.NONE,"Assessment" );
        menu.add( Menu.NONE,2, Menu.NONE,"Delete" );

        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        String check = item.getItemId();

        switch(item.getItemId()){
            case 0:
                noteScreen();

                return true;
            case 1:
                assmentScreen();
                return true;

            case 2:
                deleteCourse();
                return true;

            default:
                courseScreen();

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

    public void mentData(Mentor m, boolean add){
        if(add){
            theCourse.getMentors().add( m );
            Log.d("addM", "size of Ment:"+theCourse.getMentors().size());
        }else{
            theCourse.getMentors().remove( m );
        }
    }


//    public void removeMent(int ment){
//        ArrayList<Mentor> mentA = theCourse.getMentors();
//
//        for(int i =0; i<mentA.size();i++){
//            if(mentA.get( i ).getmID()==ment){
//                mentA.remove( i );
//            }
//        }
//
//    }
//
//    public void addMent(int ment){
//       ArrayList<Mentor> mentA = theCourse.getMentors();
//       ArrayList<Mentor> db = MainActivity.mentors;
//
//       for(int i=0; i<db.size();i++){
//           if(db.get( i ).getmID()==ment){
//               mentA.add( db.get( i ) );
//           }
//
//       }
//
//    }

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

//         ArrayList<Assessment> th = theCourse.getAssesssment();
//         Assessment add = new Assessment();
//         add.setaID( 1 );
//         add.setcIDa( 0 );
//         th.add( add );

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





            if (UIData.isCourseUpdate()) {
                Log.d( "save course =", "update" );
//            theFile.open();
                theFile.saveCourse( theCourse, true );

            }else{
//             theFile.open();
                theFile.saveCourse( theCourse, false );
                Log.d( "save course =", "new" );
            }
            MainActivity.courses.clear();
            theFile.createCourse();

            theFile.close();
            UIData.setSlectedCourse( new Course() );
            theCourse = UIData.getSlectedCourse();

            courseScreen();



        }catch (Exception e){

            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        loaded= false;
        update=false;
    }

    public void deleteCourse(){
        try {
            theFile.open();
            if (theCourse.getcID() >-1) {
                Log.i( "Delete existing", "Started" );
                theFile.deleteCourse(theCourse.getcID());
//              Toast.makeText(this,UIData.getSlectedCourse().getcID(),Toast.LENGTH_LONG).show();
                UIData.setSlectedCourse( new Course() );
            }else{
                UIData.setSlectedCourse( new Course() );
                theCourse = UIData.getSlectedCourse();
            }
            Log.i( "Generic finish", "Started" );
            for(int i = 0; i<MainActivity.assesmentAr.size();i++){
                if(MainActivity.assesmentAr.get( i ).getcIDa()==theCourse.getcID()){
                    Log.d("FindingClassDelete",""+MainActivity.assesmentAr.get( i ).getcIDa()+" vs "+theCourse.getcID());
                    Assessment target =MainActivity.assesmentAr.get( i );
                    theFile.deleteAssessment( target.getaID() );
                }
            }



            MainActivity.courses.clear();
            theFile.createCourse();
            theFile.close();

            loaded= false;
            update=false;
            Log.i( "Delete Course", "Deleted" );




            courseScreen();
        }catch (Exception e){
//          theFile.close();
//          Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void mainScreen(){
        Intent terMov = new Intent(this,MainActivity.class);
        loaded= false;
        update=false;
        startActivity(terMov);
    }

    public void courseScreen(){
        Intent terMov = new Intent(this,CoursesActivity.class);
        loaded= false;
        update=false;
        startActivity(terMov);
    }

    public void noteScreen(){
        Intent terMov = new Intent(this,NoteActivity.class);
        loaded= false;
        update=false;
//        terMov.putExtra( "course", (Parcelable) theCourse );
        terMov.putExtra( "update", true );
        UIData.setSlectedCourse( theCourse );
        Log.d( "courseLoadedtoUI",""+ theCourse.getcID() );
        startActivity(terMov);
    }

    public void assmentScreen(){
//        edAssesment( UIData.getSlectedCourse(),false );

        Intent menMov = new Intent(this,AssesmentDataActivity.class);
        UIData.setSlectedCourse( theCourse );
        loaded= false;
        update=false;

        menMov.putExtra( "update", false );
//        Log.d("Going to assesment", UIData.getSelectedAs().getATitle());
        startActivity(menMov);

    }


    public void edAssesment(Assessment targ){
        Intent menMov = new Intent(this,AssesmentDataActivity.class);
        UIData.setSlectedCourse( theCourse  );
        UIData.setSelectedAs( targ );
        loaded= false;
        update=false;

        menMov.putExtra( "update", true );
//        Log.d("Going to assesment", UIData.getSelectedAs().getATitle());
        startActivity(menMov);

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        courseScreen();
//        return true;
//    }


}
