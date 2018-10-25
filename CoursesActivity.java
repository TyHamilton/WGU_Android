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

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 168);
        Toolbar tool = new Toolbar(this);
        tool.setPopupTheme(R.style.AppTheme);
        tool.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tool.setTitle("Courses: ");
        tool.setVisibility(View.VISIBLE);
        tool.setLayoutParams(layoutParams);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ScrollView sc = new ScrollView(this);
        TableLayout tb = new TableLayout(this);

        Button addCourseB = new Button(this);
        addCourseB.setText(" Add Course");
        addCourseB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        courseAdd();

                    }

                }

        );

        Button sep = new Button(this);
        sep.setText("[------Your Courses------]");
        sep.setBackgroundColor(Color.parseColor("#FAB01C"));

        sc.addView(tb);
        tb.addView(tool);
        tb.addView(addCourseB);
        tb.addView(sep);

        ArrayList<Course> cTemp = MainActivity.courses;
        Button[] but = new Button[cTemp.size()];
//      ArrayList<String> menT = new ArrayList<>();
        /*
        This section creates buttons to display all course data and the mentors with one phone number.
         */
        for(int i = 0; i<cTemp.size(); i++){
           final Course target = cTemp.get(i);

            but[i]=new Button(this);
            int size =0;
            try {
            size = target.getMentors().size();

            }catch (Exception e){

            }
            Log.d("ment size", ""+size);


         String cText = "Course: " + target.getcTitle()+"\n\n"+ " Start: "+ target.getcStart()+ " Anticipated End: "+target.getcAEnd()+"\n";


            cText+= "\n"+" Status: "+ target.getStatus()+"\n";

            ArrayList<Mentor> ments= target.getMentors();
            for(int m =0; m<ments.size();m++){
                Mentor men = ments.get( m );
                if(men.getPhone().get( 0 )!=null)
                cText+= "\n Mentor: "+ men.getName() +" Phone: "+ men.getPhone().get( 0 );


            }



            but[i].setText(cText);
            but[i].setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            courseAdd(target);
                        }
                    }
            );

            tb.addView( but[i]);


        }



        setContentView(sc);





    }
    public void courseAdd(){
        Intent terMov = new Intent(this,CourseDataActivity.class);
        terMov.putExtra( "update", false );
        UIData.setCourseUpdate( false );
        startActivity(terMov);
    }
    public void courseAdd(Course cor){
        UIData.setSlectedCourse( cor );
        Intent terMov = new Intent(this,CourseDataActivity.class);
        terMov.putExtra( "update", true);
        UIData.setCourseUpdate( true );
        startActivity(terMov);
    }


}
