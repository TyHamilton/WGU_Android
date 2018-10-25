package com.example.ty.javaap_c196;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import java.util.ArrayList;

public class AssesmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_assesment);
//        Toolbar toolbar = (Toolbar) new Toolbar( this );
//        setSupportActionBar(toolbar);
//        toolbar.setTitle( "All Assessments: " );
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 100);
        Toolbar tool = new Toolbar(this);
        tool.setPopupTheme(R.style.AppTheme);
        tool.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tool.setTitle("All Assessments: ");
        tool.setVisibility(View.VISIBLE);
        tool.setLayoutParams(layoutParams);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
//        tb.addView(temp1);
//        tb.addView( toolbar );
        tb.addView( tool );
        tb.addView(nav);


        ArrayList<Assessment> asBuff = MainActivity.assesmentAr;
        Button[] buttonB = new Button[asBuff.size()];
        for(int i =0; i<asBuff.size(); i++){
            buttonB[i]= new Button(this);
            final Assessment targ = asBuff.get(i);
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
            tb.addView( buttonB[i]);
        }



    sc.addView(tb);


    setContentView(sc);


    }
    public void temp(){
        Intent menMov = new Intent(this,AssesmentDataActivity.class);
        menMov.putExtra( "update", false );
        startActivity(menMov);

    }

    public void edAssesment(Assessment targ){
        Intent menMov = new Intent(this,AssesmentDataActivity.class);
        UIData.setSelectedAs( targ );
        menMov.putExtra( "update", true );
        Log.d("Going to assesment", UIData.getSelectedAs().getATitle());
        startActivity(menMov);

    }


}
