package com.example.ty.javaap_c196;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import java.util.ArrayList;

public class MentorMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(android.R.style.Theme);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 168);
        Toolbar tool = new Toolbar(this);
        tool.setPopupTheme(R.style.AppTheme);
        tool.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tool.setTitle("Mentors: ");
        tool.setVisibility(View.VISIBLE);
        tool.setLayoutParams(layoutParams);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayList<Mentor> ment= MainActivity.mentors;

        Button addMent = new Button(this);
        addMent.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        addMentor();

                    }

                }

        );
        addMent.setText("Add Mentor");


        Button first = new Button(this);
        first.setBackgroundColor(Color.parseColor("#FAB01C"));

        String size = Integer.toString(ment.size());
        first.setText("[------Your Current Mentors-----]");
        ScrollView sc = new ScrollView(this);
        TableLayout tb = new TableLayout(this);
        Button[] mentZ = new Button[ment.size()];




        tb.addView(tool);
        tb.addView(addMent);
        tb.addView(first);
        sc.addView(tb);
        for(int i = 0; i<ment.size();i++){
            Button added = new Button(this);
            mentZ[i]= added;

            final Mentor select = ment.get(i);

            String bText= select.getmID()+" "+select.getName();
            mentZ[i].setText(bText);
            mentZ[i].setId(i);
            mentZ[i].setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            UIData.setSelectedMentor( select );
                            editMentor();
                        }
                    }
            );

            tb.addView(mentZ[i]);

        }



        setContentView(sc);





    }//end of onCreate

    public void addMentor(){
        Intent terMov = new Intent(this,MentDataActivity.class);
        terMov.putExtra( "update", false );
        startActivity(terMov);
    }

    public void editMentor(){

        Intent terMov = new Intent(this,MentDataActivity.class);
        terMov.putExtra( "update", true );

        startActivity(terMov);

    }



}
