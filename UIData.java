package com.example.ty.javaap_c196;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ty on 12/3/2017.
 */

public class UIData {
    //Data assistance code for UI and MISC for core program
    private static Assessment selectedAs = new Assessment();
    private static Mentor selectedMentor= new Mentor();
    private static Course slectedCourse = new Course();
    private static Term selectedTerm=new Term();
    private static Notes selectedNote = new Notes();

    private static int noteTrack= 0;

    public static int getNoteTrack() {
        return noteTrack;
    }

    public static void setNoteTrack(int noteTrack) {
        UIData.noteTrack = noteTrack;
    }

    private static ArrayList<Course>checked = new ArrayList<>(  );
    private static ArrayList<Course>unchecked= new ArrayList<>(  );

    public static boolean isCourseUpdate() {
        return courseUpdate;
    }

    public static void setCourseUpdate(boolean courseUpdate) {
        UIData.courseUpdate = courseUpdate;
    }

    private static boolean courseUpdate = false;


    public static ArrayList<Course> getChecked() {
        return checked;
    }

    public static void setChecked(ArrayList<Course> checked) {
        UIData.checked = checked;
    }

    public static ArrayList<Course> getUnchecked() {
        return unchecked;
    }

    public static void setUnchecked(ArrayList<Course> unchecked) {
        UIData.unchecked = unchecked;
    }

    private static String selectedPhone = new String();
    private static String selectedEmail= new String();




    public static String getSelectedPhone() {
        return selectedPhone;
    }

    public static void setSelectedPhone(String selectedPhone) {
        UIData.selectedPhone = selectedPhone;
    }

    public static String getSelectedEmail() {
        return selectedEmail;
    }

    public static void setSelectedEmail(String selectedEmail) {
        UIData.selectedEmail = selectedEmail;
    }



    public static Notes getSelectedNote() {
        return selectedNote;
    }

    public static void setSelectedNote(Notes selectedNote) {
        UIData.selectedNote = selectedNote;
    }

    public static Assessment getSelectedAs() {
        return selectedAs;
    }

    public static void setSelectedAs(Assessment selectedAs) {
        UIData.selectedAs = selectedAs;
    }

    public static Mentor getSelectedMentor() {
        return selectedMentor;
    }

    public static void setSelectedMentor(Mentor selectedMentor) {
        UIData.selectedMentor = selectedMentor;
    }

    public static Course getSlectedCourse() {
        return slectedCourse;
    }

    public static void setSlectedCourse(Course slectedCourse) {
        UIData.slectedCourse = slectedCourse;
    }

    public static Term getSelectedTerm() {
        return selectedTerm;
    }

    public static void setSelectedTerm(Term selectedTerm) {
        UIData.selectedTerm = selectedTerm;
    }



    //code for alerts
    private static Term alertT;
    private static int termLeft;
    private static Course alertC;
    private static int courseLleft;
    private static Assessment alertA;
    private static int assessLeft;

    public static Course getAlertC() {
        return alertC;
    }

    public static int getCourseLleft() {
        return courseLleft;
    }

    public static Assessment getAlertA() {
        return alertA;
    }

    public static int getAssessLeft() {
        return assessLeft;
    }

    public static   Term getAlertT() {
        return alertT;
    }

    public static void setAlertT(Term alertT) {
        alertT = alertT;
    }

    public static int getTermLeft() {
        return termLeft;
    }

    public static void setTermLeft(int termLeft) {
        termLeft = termLeft;
    }

//  public static   Context context =MainActivity.context;

   //this compairs two arrays that contain the target dates for alarms
//
//   static  public boolean  alarmTerm(){
//
//        Boolean alt = false;
//       int[] today= dayRet();
//       int month =today[0];
//       int day = today[1];
//       int year = today[2];
//        String dString=""+month+"/"+day+"/"+year;
////        Log.d("theDay is: ", dString);
//        for(int i=0;i<MainActivity.terms.size();i++){
//            String id =MainActivity.terms.get( i ).getTitle()+MainActivity.terms.get( i ).gettID();
//            if (didIAlert("term", id) == false) {
//                int[] pull = dayPacker( MainActivity.terms.get( i ).getEnd() );
//                int cMon = pull[0];
//                int cDay = pull[1];
//                int cYear = pull[2];
//                if (cYear == year && cMon == month) {
//                    int remainder = cDay - day;
//                    if (remainder < 8) {
//                        alt = true;
//                        alertT = MainActivity.terms.get( i );
//                        termLeft = remainder;
//                        MainActivity.updateCAlert(context, id,dString );
//                    }
//                }
//            }
//        }
//
//        return alt;
//
//    }

//    static public boolean alarmCourseEnd(){
//        Boolean alt = false;
//        int[] today= dayRet();
//        int month =today[0];
//        int day = today[1];
//        int year = today[2];
//        String dString=""+month+"/"+day+"/"+year;
//        for(int i=0;i<MainActivity.courses.size();i++) {
//            String id =MainActivity.courses.get( i ).getcTitle()+MainActivity.courses.get( i ).getcID();
//                if(MainActivity.courses.get( i ).getAlertE().equals( "true" )){
//                    if (didIAlert("courseE",id) == false) {
//                int[] pull = dayPacker( MainActivity.courses.get( i ).getcAEnd() );
//                int cMon = pull[0];
//                int cDay = pull[1];
//                int cYear = pull[2];
//                if (cYear == year && cMon == month) {
//                    int remainder = cDay - day;
//                    if (remainder < 8) {
//                        alt = true;
//                        alertC = MainActivity.courses.get( i );
//                        courseLleft = remainder;
//                    }
//                }
//            }
//        }
//        }
//       return alt;
//    }
//    static public boolean alarmCourseStart(){
//        Boolean alt = false;
//        int[] today= dayRet();
//        int month =today[0];
//        int day = today[1];
//        int year = today[2];
//        String dString=""+month+"/"+day+"/"+year;
//        for(int i=0;i<MainActivity.courses.size();i++) {
//            String id =MainActivity.courses.get( i ).getcTitle()+MainActivity.courses.get( i ).getcID();
//            if(MainActivity.courses.get( i ).getAlertS().equals( "true" )){
//                if (didIAlert("courseS",id) == false)  {
//                    int[] pull = dayPacker( MainActivity.courses.get( i ).getcStart() );
//                    int cMon = pull[0];
//                    int cDay = pull[1];
//                    int cYear = pull[2];
//                    if (cYear == year && cMon == month) {
//                        int remainder = cDay - day;
//                        if (remainder < 8) {
//                            alt = true;
//                            alertC = MainActivity.courses.get( i );
//                            courseLleft = remainder;
//                        }
//                    }
//                }
//            }
//        }
//        return alt;
//    }
//
//
//
//    static public boolean alarmAsses(){
//        Boolean alt = false;
//        int[] today= dayRet();
//        int month =today[0];
//        int day = today[1];
//        int year = today[2];
//        String dString=""+month+"/"+day+"/"+year;
//        for(int i=0;i<MainActivity.assesmentAr.size();i++) {
//            String id =MainActivity.assesmentAr.get( i ).getATitle()+MainActivity.assesmentAr.get( i ).getaID();
//            if(MainActivity.assesmentAr.get( i ).getAlert().equals( "true" )) {
//                if (didIAlert("alert",id) == false) {
//                int[] pull = dayPacker( MainActivity.assesmentAr.get( i ).getSchedule() );
//                int cMon = pull[0];
//                int cDay = pull[1];
//                int cYear = pull[2];
//                if (cYear == year && cMon == month) {
//                    int remainder = cDay - day;
//                    if (remainder < 8) {
//                        alt = true;
//                        alertA = MainActivity.assesmentAr.get( i );
//                        assessLeft = remainder;
//                    }
//                }
//            }
//        }
//        }
//        return alt;
//
//    }

    static public int[] dayRet(){
        int[] ret = new int[3];
        Calendar today = Calendar.getInstance();
        ret[0]=today.get( Calendar.MONTH )+1;;
        ret[1]=today.get( Calendar.DAY_OF_MONTH );
        ret[2]=today.get( Calendar.YEAR );

        return  ret;
    }


   static public int[] dayPacker(String a){
        int firstM = 0;
        int secM= 0;
        int month = 0;
        int day=0;
        int year=0;
        int[] send=new int[3];
        for(int i=0; i<a.length();i++){
            if(a.charAt( i )=='/'&&firstM==0){
                firstM=i;
                String tester= Character.toString( a.charAt( i ) );
                Log.d( "charAt", tester );
              month= Integer.parseInt( a.substring( 0,firstM ) );
            }
            if(a.charAt( i )=='/'&&i>firstM){
                secM=i;
                day = Integer.parseInt( a.substring( firstM+1,secM  ) );
                year = Integer.parseInt( a.substring( secM+1 ) );
            }


        }
        send[0]= month;
        send[1]=day;
        send[2]=year;
        Log.d( "Month sent=:",""+month );
        Log.d( "Day sent=:",""+day );
        Log.d( "Year sent=:",""+year );
        return send;
    }

}
