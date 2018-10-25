package com.example.ty.javaap_c196;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {
    Button saveNoteB;
    Button  sendNoteB;
    Button deleteNoteB;
    Button  closeNoteB;

    EditText noteTxt;

    LinearLayout noteLL;

    static Course theCourse;

    static boolean update = false;

    static boolean loaded = false;


    DataHandler theFile;

    static Notes theNote = new Notes();

    static boolean updating = false;

    static ArrayList<Button> allNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        noteLL = findViewById( R.id.noteLL );
//        update = getIntent().getExtras().getBoolean("update");
        saveNoteB = findViewById( R.id.saveNoteB );
        sendNoteB= findViewById( R.id.sendNoteB );
        deleteNoteB= findViewById( R.id.deleteNoteB );
        closeNoteB=findViewById( R.id.closeNoteB );
        theFile=new DataHandler(this);
        noteTxt= findViewById( R.id.noteTxt1 );
        theCourse = UIData.getSlectedCourse();
        saveNoteB.setText("Add");
        int size =0;
        closeNoteB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        closeNoteB();

                    }
                }
        );

        deleteNoteB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        deleteNote();

                    }
                }
        );

        saveNoteB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        saveNote( false );
                    }
                }
        );

        sendNoteB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        sendNote();
                    }
                }
        );


        Log.d( "willI","loading.."+loaded );
        if(!loaded){
            Log.d( "loading data","loading.." );
            int total = MainActivity.noteAr.size();
            int counter =0;
            boolean found =false;
            for(int i=0; i<total;i++){
                Log.d( "Course",""+theCourse.getcID() );
                Log.d( "Note",""+MainActivity.noteAr.get( i ).getCourse() );
                if(theCourse.getcID()==MainActivity.noteAr.get( i ).getCourse()){
                    theNote=MainActivity.noteAr.get( i );
                  update = true;
                    Log.d( "noteIS","found" );
                    found =true;
                }
                counter++;
            }
            if(counter==total&&found == false){
                theNote=new Notes();
                theNote.setCourse( UIData.getSlectedCourse().getcID() );
                update =false;
                Log.d( "noteNot","found" );
            }




//            if(update){
//                for(int i = 0 ; i<
//                        MainActivity.noteAr.size()
//                        ;i++){
//                    if(theCourse.getcID()==MainActivity.noteAr.get( i ).getCourse()){
//                        theNote=MainActivity.noteAr.get( i );
//
//                    }
//
//
//                }
//            }

            loaded=true;
        }

//        int size = 0;

        loadNotes();


    }

    public void saveNote(boolean updateN){



        if(updating){
            theNote.getNotes().remove( UIData.getNoteTrack() );
            theNote.getNotes().add(noteTxt.getText().toString());
        }else {
            theNote.getNotes().add(noteTxt.getText().toString());
        }
        loadNotes();
        updating = false;
    }

    public void sendNote(){
        Intent send = new Intent( Intent.ACTION_SENDTO );
        send.setType( "text/plain" );
        send.setAction( Intent.ACTION_SEND) ;
//        send.setData( Uri.parse("Mailto:") );
//        send.putExtra( Intent.EXTRA_EMAIL )
        send.putExtra( Intent.EXTRA_TEXT,noteTxt.getText().toString() );
        startActivity( Intent.createChooser( send,"Send note: " ) );


    }

    public void deleteNote(){
        noteTxt.setText( "" );
        theNote.getNotes().remove( UIData.getNoteTrack()  );
        theFile.open();

    }

    public void closeNoteB(){
        try {


            if (theNote.getnID() > 0) {
                Log.d( "SaveNoteas", "update" );
                Log.d( "sendCourse", "" + theNote.getCourse() );
                theFile.saveNote( theNote, true );
            } else {
                Log.d( "SaveNoteas", "new" );
                theNote.setCourse( UIData.getSlectedCourse().getcID() );
                Log.d( "sendCourse", "" + theNote.getCourse() );
                theFile.saveNote( theNote, false );
            }
            MainActivity.noteAr.clear();
            theFile.createNote();

            theFile.close();
        }catch (Exception e){

        }
        update=false;
        updating=false;
        loaded=false;
        assmentScreen();

    }

    public void loadNotes(){
        noteLL.removeAllViews();
        int size =theNote.getNotes().size();
        Button[] theButtons = new Button[size];
        for(int n=0;n<size;n++){
            theButtons[n]= new Button( this );
            final int tracker = n;
            final String noteIn= theNote.getNotes().get( n );
            theButtons[n].setText( noteIn );
            theButtons[n].setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            UIData.setNoteTrack( tracker );
                            noteTxt.setText( noteIn );
                            updating= true;

                        }
                    }
            );
            allNotes.add(theButtons[n]);
            noteLL.addView( theButtons[n] );
            noteTxt.setText("");
        }
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


}


