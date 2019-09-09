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
      ArrayList<String> menT = new ArrayList<>();
        for(int i = 0; i<cTemp.size(); i++){
            Course target = cTemp.get(i);

            but[i]=new Button(this);
            for(int a = 0; a<target.getMentors().size();a++){
                Mentor men = target.getMentors().get(i);
                String ment="";

                String name = men.getName();

                ment += " Mentor: "+ name+"\n";;
                for(int p = 0; p<target.getMentors().size();p++){

                    ArrayList<String> ph= men.getPhone();
                    String phone = ph.get(p);
                    ment += " Phone: "+ phone + "\n";
                    for(int e = 0; e<target.getMentors().size();e++){

                        ArrayList<String> em = men.getEmail();
                        String email = em.get(e);
                        ment+= " Email: "+ email+"\n";


                    }

                }
                menT.add(ment);

            }

        String cText = "Course: " + target.getcTitle()+"\n\n"+ " Start: "+ target.getcStart()+ " Anticipated End: "+target.getcAEnd()+"\n";
//            if(target.getPerformanceBool().equals("true")){
//                cText+= "\n"+ "Performance: " + target.getPerformance()+ "\n";
//            }
//            if(target.getOjbectiveBool().equals("true")){
//                cText+= "\n"+ "Objective: "+ target.getOjbective()+ "\n";
//            }

            cText+= "\n"+" Status: "+ target.getStatus()+"\n";

            for(int j = 0; j<menT.size(); j++){
                cText+= "\n"+ menT.get(j);
            }





            but[i].setText(cText);
            tb.addView( but[i]);


        }



        setContentView(sc);





    }
    public void courseAdd(){
        Intent terMov = new Intent(this,CourseDataActivity.class);

        startActivity(terMov);
    }

}
