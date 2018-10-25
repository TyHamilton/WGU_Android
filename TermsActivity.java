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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import java.util.ArrayList;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 168);
        Toolbar tool = new Toolbar(this);
        tool.setPopupTheme(R.style.AppTheme);
        tool.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tool.setTitle("Terms: ");
        tool.setVisibility(View.VISIBLE);
        tool.setLayoutParams(layoutParams);

        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button addTermB = new Button(this);
        addTermB.setText(" Add Term");
        addTermB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        termAdd();

                    }

                }

        );


        Button sep = new Button(this);
        sep.setText("[------Your Terms------]");
        sep.setBackgroundColor(Color.parseColor("#FAB01C"));


        ScrollView sc = new ScrollView(this);
        TableLayout tb = new TableLayout(this);

        sc.addView(tb);
        tb.addView(tool);
        tb.addView(addTermB);
        tb.addView(sep);

        ArrayList<Term> tm = MainActivity.terms;
        Button[] but = new Button[tm.size()];
        for(int i =0; i<tm.size();i++){
           final Term target = tm.get(i);
            String tText = "Term: "+target.getTitle()+"\n"+" Start : "+ target.getStart()+ " End: "+ target.getEnd();

        but[i]= new Button(this);
        but[i].setText(tText);
        but[i].setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                       UIData.setSelectedTerm( target );
                        termEdit();

                    }

                }
        );
        tb.addView(but[i]);
        }


        setContentView(sc);
    }


    public void termAdd(){
        Intent terMov = new Intent(this,TermDataActivity.class);
        terMov.putExtra( "update",false );
        startActivity(terMov);
    }
    public void termEdit(){
        Intent terMov = new Intent(this,TermDataActivity.class);
        terMov.putExtra( "update",true );
        startActivity(terMov);
    }



}
