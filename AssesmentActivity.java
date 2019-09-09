package com.example.ty.javaap_c196;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;

import java.util.ArrayList;

public class AssesmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_assesment);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Button temp1 = new Button(this);
        temp1.setText("Add Assessment");
        temp1.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        temp();

                    }

                }

        );

        Button nav = new Button(this);
        nav.setBackgroundColor(Color.parseColor("#FAB01C"));
        nav.setText("[------Your Assessments------]");

        ScrollView sc = new ScrollView(this);
        TableLayout tb = new TableLayout(this);
        tb.addView(temp1);
        tb.addView(nav);


        ArrayList<Assessment> asBuff = MainActivity.assesmentAr;
        Button[] buttonB = new Button[asBuff.size()];
        for(int i =0; i<asBuff.size(); i++){
            buttonB[i]= new Button(this);
           Assessment targ = asBuff.get(i);
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
            tb.addView( buttonB[i]);
        }



    sc.addView(tb);


    setContentView(sc);


    }
    public void temp(){
        Intent menMov = new Intent(this,MentorData2Activity.class);

        startActivity(menMov);

    }
}
