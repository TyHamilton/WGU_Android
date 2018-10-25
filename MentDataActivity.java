package com.example.ty.javaap_c196;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;


public class MentDataActivity extends AppCompatActivity {
    static Mentor theMentor = new Mentor();
    static boolean update = false;
    static boolean loaded = false;

    static ArrayList<String> phoneTemp = new ArrayList<>();

    Button saveAction ;

    Button addPhone;

    Button deletePhone;

    Button addEmail ;

    Button deleteEmail;

    EditText  nameMentor;


    EditText mentorPhone;

    EditText mentorEmail ;

    TextView selectPhoneTXT;

    TextView selectEmailTXT;

    DataHandler theFile ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ment_data );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar3 );
        toolbar.setTitle( "Add/Edit Mentor: " );

        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        update = getIntent().getExtras().getBoolean("update");

        theFile=new DataHandler(this);

        saveAction =  (Button) findViewById(R.id.saveB);

        addPhone= (Button) findViewById(R.id.phoneAddB );

        deletePhone =(Button) findViewById(R.id.dPhoneB);

        addEmail =(Button) findViewById(R.id.emailAddB);

        deleteEmail =(Button) findViewById(R.id.dMailB);

        nameMentor = (EditText) findViewById(R.id.mNameTxt);

        mentorPhone= (EditText) findViewById(R.id.mPhoneTxt);

        mentorEmail = (EditText)findViewById(R.id.mEmailTxt);

        selectPhoneTXT= (TextView)findViewById( R.id.phoneSelectTxt );

        selectEmailTXT= (TextView)findViewById( R.id.selectEmailTxt );

        Button cancelB= (Button)findViewById( R.id.mCancelB );

        if(loaded==false) {
            if (update == true) {
                theMentor = UIData.getSelectedMentor();
                nameMentor.setText(theMentor.getName());
                phoneTemp = theMentor.getPhone();


            } else {
                theMentor = new Mentor();


            }

            loaded=true;
        }

        ArrayAdapter<String> phList = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,theMentor.getPhone());
        ArrayAdapter<String> emList = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,theMentor.getEmail());

        ListView phView = findViewById(R.id.phoneView);
        phView.setAdapter(phList);

        ListView emView = findViewById(R.id.emailView);
        emView.setAdapter(emList);


        addPhone.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        savePhoneTo();
                    }
                }
        );

        addEmail.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        saveEmailTo();
                    }
                }

        );

        phView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                          @Override
                                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                              String  selecPhone= String.valueOf(parent.getItemAtPosition( position ));
                                              UIData.setSelectedPhone( selecPhone);
                                              selectPhoneTXT.setText( selecPhone );

                                          }

                                      }

        );


        emView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selecEmail= String.valueOf( parent.getItemAtPosition( position ) );
                        UIData.setSelectedEmail( selecEmail );
                        selectEmailTXT.setText( selecEmail );

                    }
                }


        );

        deletePhone.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        deletePhoneM();
                    }
                }
        );

        deleteEmail.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        deleteEmailM();
                    }
                }
        );


        saveAction.setOnClickListener(

                new Button.OnClickListener() {
                    public void onClick(View v) {
                        saveMentor();
                    }
                }
        );

        cancelB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        cancelM();
                    }
                }
        );

    }

    private void deleteMent(){
//        try{
            theFile.open();

            if(theMentor.getmID()==-1) {

                theMentor= new Mentor(  );
            }else{
                String menID= Integer.toString( theMentor.getmID() );


                theFile.deleteMentor(theMentor.getmID());
                Toast.makeText(this,"Deleted".toString(),Toast.LENGTH_LONG).show();
            }

//        }catch(Exception e){
//            Toast.makeText(this,"Not able to delete!",Toast.LENGTH_LONG).show();
//        }
        MainActivity.mentors.clear();
        theFile.createMentors();

        theFile.close();

        loaded=false;
        mentorScreen();
    }

    private void savePhoneTo(){
        String toAdd = mentorPhone.getText().toString();
        if(toAdd.length()==0){return;}
        ArrayList<String>tempPH= theMentor.getPhone();
        tempPH.add(toAdd);

        mentorPhone.setText(" ");

    }
    private void saveEmailTo(){
        String toAdd= mentorEmail.getText().toString();
        if(toAdd.length()==0){return;}
        ArrayList<String>tempEM= theMentor.getEmail();
        tempEM.add(toAdd);
        mentorEmail.setText("");


    }


    private void deletePhoneM(){
        String check = UIData.getSelectedPhone();
        ArrayList<String> phoneD= theMentor.getPhone();
        for(int i =0; i<phoneD.size(); i++){
            if(check.equals( phoneD.get( i ) )){
                phoneD.remove( i );

            }

            selectPhoneTXT.setText( " " );

        }


    }


    private void deleteEmailM(){
        String check = UIData.getSelectedEmail();
        ArrayList<String> emailD= theMentor.getEmail();
        for(int i=0; i<emailD.size();i++){
            emailD.remove( i );

        }
        selectEmailTXT.setText( " " );

    }

    private void saveMentor(){
        try{
            String nm=nameMentor.getText().toString();

            if(nm.equals( "" )){throw new Exception(  );}
            theMentor.setName(nm );

        }catch (Exception e){

            Toast.makeText(this,"Need mentor name!",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            theFile.open();
            if (update == true) {


                theFile.saveMentor( theMentor, true );


            } else {

                theFile.saveMentor( theMentor, false );

            }

            MainActivity.mentors.clear();
            theFile.createMentors();
            theFile.close();

            UIData.setSelectedMentor( null );
            UIData.setSelectedEmail( "" );
            UIData.setSelectedPhone( "" );
            loaded= false;
            update=false;
            mainScreen();


        }catch (Exception b){
            Toast.makeText(this,"Not able to save!",Toast.LENGTH_LONG).show();
            String er =b.toString();
            Toast.makeText(this,er,Toast.LENGTH_LONG).show();
            return;
        }

    }

    public void mainScreen(){
        Intent terMov = new Intent(this,MainActivity.class);

        startActivity(terMov);
    }

    public void courseScreen(){
        Intent terMov = new Intent(this,CoursesActivity.class);

        startActivity(terMov);
    }

    public void mentorScreen(){
        Intent terMov = new Intent(this,MentorMActivity.class);

        startActivity(terMov);
    }


    public void cancelM(){

        loaded= false;
        update=false;

        mainScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater( this ).inflate( R.menu.menu_main,menu );

        return super.onCreateOptionsMenu( menu );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        String check = item.getItemId();
        switch(item.getItemId()){
            case R.id.deleteMentorI:
                deleteMent();
            return true;
            case R.id.goCourseI:
                courseScreen();
                return true;

            case R.id.goHomeI:
                mainScreen();
                return true;

            default:
                mentorScreen();
        }
//        Toast.makeText(this,item.getItemId(),Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        update= false;
        loaded=false;
    }
}
