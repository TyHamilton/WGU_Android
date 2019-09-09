package com.example.ty.javaap_c196;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;

import static com.example.ty.javaap_c196.StubSQL.ASSESSMENT_ID;
import static com.example.ty.javaap_c196.StubSQL.COURSE_ID;
import static com.example.ty.javaap_c196.StubSQL.COURSE_TITLE;
import static com.example.ty.javaap_c196.StubSQL.MENTOR_ID;
import static com.example.ty.javaap_c196.StubSQL.NOTES_ID;
import static com.example.ty.javaap_c196.StubSQL.TABLE_ASSESSMENT;
import static com.example.ty.javaap_c196.StubSQL.TABLE_COURSE;
import static com.example.ty.javaap_c196.StubSQL.TABLE_MENTOR;
import static com.example.ty.javaap_c196.StubSQL.TABLE_NOTES;
import static com.example.ty.javaap_c196.StubSQL.TABLE_TERM;
import static com.example.ty.javaap_c196.StubSQL.TERM_ID;


/**
 * Created by Ty on 11/24/2017.
 */

public class DataHandler {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mMyDB;

    public DataHandler(Context con){
        this.mContext= con;
        mMyDB = new StubSQL(mContext);
        mDatabase= mMyDB.getWritableDatabase();

    }


    public void open(){
        mDatabase= mMyDB.getWritableDatabase();
    }

    public void close(){
        mMyDB.close();

    }

    //add row to db for course bool converts it to update

    public void saveMentor(Mentor ment, boolean update){
        ContentValues v =ment.mentorData(ment);
        Log.i( "Save start", "The save" );
        if(update){
            String mID=Integer.toString(ment.getmID());

            mDatabase.update(TABLE_MENTOR,v,MENTOR_ID+"=?",new String[]{mID});
        }else{
            mDatabase.insert(TABLE_MENTOR,null,v);}

    }

    public void saveCourse(Course cor,boolean update){
        ContentValues v =cor.courseData(cor);
        if(update){
            String cID=Integer.toString(cor.getcID());
            Log.i( "Save start", "The save" );
            mDatabase.update(TABLE_COURSE,v,COURSE_ID+"=?",new String[]{cID});

        }else {

            mDatabase.insert(TABLE_COURSE,null,v);
        }


    }

    public void saveTerm(Term ter, boolean update){

        ContentValues v= ter.termData(ter);
        if(update){
            String tID=Integer.toString(ter.gettID());
            mDatabase.update(TABLE_TERM,v,TERM_ID+"=?",new String[]{tID});

        }else{
            mDatabase.insert(TABLE_TERM,null,v);
        }

    }


    public void saveNote(Notes no, boolean update){
        ContentValues v = no.notesData(no);
        if(update){
            String nID=Integer.toString(no.getnID());
            mDatabase.update(TABLE_NOTES,v,NOTES_ID+"=?", new String[]{nID});
        }else{
            mDatabase.insert(TABLE_NOTES,null,v);
        }

    }


    public void saveNote(Assessment asse, boolean update){
        ContentValues v = asse.assessmentData(asse);
        if(update){
            String aID=Integer.toString(asse.getaID());
            mDatabase.update(TABLE_ASSESSMENT,v,ASSESSMENT_ID+"=?", new String[]{aID});
        }else{
            mDatabase.insert(TABLE_ASSESSMENT,null,v);
        }

    }



    //get data
    public Cursor getMentor(){

        Cursor data =mDatabase.rawQuery("select * from "+TABLE_MENTOR,null);
        return data;

    }

    public Cursor getTerm(){


        Cursor data =mDatabase.rawQuery("select * from "+TABLE_TERM,null);
        return data;
    }

    public Cursor getCourse(){


        Cursor data =mDatabase.rawQuery("select * from "+TABLE_COURSE,null);
        return data;
    }


    public Cursor getNotes(){
        Cursor data = mDatabase.rawQuery( "select * from "+TABLE_NOTES, null);
        return data;
    }

    public Cursor getAssessment(){
        Cursor data = mDatabase.rawQuery( "select * from "+TABLE_ASSESSMENT, null);
        return data;
    }





    public void createAssessment(){
        Cursor dbPull=getAssessment();
        if(dbPull.getCount()==0){return;}
        while(dbPull.moveToNext()) {
        Assessment assess = new Assessment();
            assess.setaID(dbPull.getInt(0));
            assess.setcIDa(dbPull.getInt(1));
            assess.setATitle(dbPull.getString(2));
            assess.setOjbectiveBoolean(dbPull.getString(3));
            assess.setSchedule(dbPull.getString(4));
            assess.setAlert(dbPull.getString(5));
            assess.setGrade(dbPull.getString(6));
            MainActivity.assesmentAr.add(assess);
        }



    }

    //this loads courses from db creates new class of courses in array in main



    public void createCourse(){
        Cursor dbPull=getCourse();
        if(dbPull.getCount()==0){return;}
        while(dbPull.moveToNext()){
            Course cour = new Course();
            ArrayList<Mentor> mBuff = new ArrayList<>();
            if(dbPull.getString(5).length()>0){
                ArrayList<String> buff = unpackerString(dbPull.getString(5));
                for(int i = 0; i<buff.size();i++){
                    int check= Integer.parseInt(buff.get(i));

                    for(int j = 0; j<MainActivity.mentors.size();j++){
                        if(MainActivity.mentors.get(j).getmID()==check){
                            mBuff.add(MainActivity.mentors.get(j));
                        }
                    }


                }

            }
            ArrayList<Assessment> aBuff= new ArrayList<>();
            if(dbPull.getString(6).length()>0){
                ArrayList<String>buff = unpackerString(dbPull.getString(6));

            for(int i = 0; i<buff.size();i++){
                int check= Integer.parseInt(buff.get(i));

                for(int j=0; j<MainActivity.assesmentAr.size();j++){
                    if(MainActivity.assesmentAr.get(j).getaID()== check){
                        aBuff.add(MainActivity.assesmentAr.get(j));
                    }

                }


            }
            }
            cour.setcID(Integer.parseInt(dbPull.getString(0)));
            cour.setcTitle(dbPull.getString(1));
            cour.setcStart(dbPull.getString(2));
            cour.setcAEnd(dbPull.getString(3));
            cour.setStatus(dbPull.getString(4));
            cour.setMentors(mBuff);
            cour.setAssesssment(aBuff);
            cour.setAlertS(dbPull.getString(7));
            cour.setAlertE(dbPull.getString(8));

            MainActivity.courses.add(cour);


        }

    }


    //load courses first this method pulls data and creates new term classes in an array in main
    public void createTerm(){
        Cursor dbPull = getTerm();

        if(dbPull.getCount()==0){return;}
        while(dbPull.moveToNext()){
//            ArrayList<String> buff=new ArrayList<>();
            ArrayList<Course> cBuff = new ArrayList<>();

            if(dbPull.getString(4).length()>0) {
                ArrayList<String>   buff = unpackerString(dbPull.getString(4));

                for (int i = 0; i < buff.size(); i++) {
                    String check = buff.get(i);
                    int check1= Integer.parseInt(check);

                    Log.d( "The check is ",check);
                    for(int j = 0;j< MainActivity.courses.size(); j ++){
                        String dataC = Integer.toString(MainActivity.courses.get(j).getcID())+" VS "+ check;
                        Log.d("Checking ", dataC);
                        if(MainActivity.courses.get(j).getcID()==check1){


                            cBuff.add(MainActivity.courses.get(j));
                        }


                    }
                }


            }




            Term ter = new Term();
            ter.settID(Integer.parseInt(dbPull.getString(0)));
            ter.setTitle(dbPull.getString(1));
            ter.setStart(dbPull.getString(2));
            ter.setEnd(dbPull.getString(3));
            ArrayList<Course> added = ter.getCourses();
            for(int z = 0; z<cBuff.size();z++){
               added.add( cBuff.get(z));
            }

            MainActivity.terms.add(ter);

        }
    }

    //pulls data from db and creates new classes in array in main
    public void createMentors(){

        Cursor dbPull = getMentor();
        if(dbPull.getCount()==0){return;}
        while(dbPull.moveToNext()){
            Mentor mentBuff = new Mentor();
            mentBuff.setmID(dbPull.getInt( 0 ));
            mentBuff.setName(dbPull.getString(1));
            mentBuff.setPhone(unpackerString(dbPull.getString(2)));
            mentBuff.setEmail(unpackerString(dbPull.getString(3)));
            MainActivity.mentors.add(mentBuff);
        }
    }

    public void createNote(){
        Cursor dbPUll = getNotes();
        while(dbPUll.moveToNext()){
            Notes noteBuff = new Notes();
            noteBuff.setnID(dbPUll.getInt(0));
            noteBuff.setNotes(unpackerString(dbPUll.getString(1)));
            noteBuff.setCourse(dbPUll.getInt(2));
            MainActivity.noteAr.add(noteBuff);

        }

    }


    public ArrayList unpackerString(String pack){
        ArrayList<String> unpacked = new ArrayList<>();
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();


        for(int i = 0 ; i< pack.length(); i ++){
            char check = pack.charAt(i);
            if(check == '%'){
                start.add(i);
            }
            if(check =='!'){
                end.add(i);
            }
        }
        for(int i =0; i < start.size();i++){
            String buff = pack.substring(start.get(i)+1,end.get(i));
            unpacked.add(buff);

        }

        return unpacked;

    }



    //delete data
    public void deleteCourse(int courseN){

        mDatabase.execSQL("DELETE FROM "+TABLE_COURSE+" WHERE "+COURSE_TITLE + "=\""+ courseN+"\";" );


    }


    //delete course
    public void deleteTerm(int termD){


        mDatabase.execSQL("DELETE FROM "+TABLE_TERM+" WHERE "+TERM_ID + "=\""+ termD+"\";" );


    }

    //delete course
    public void deleteMentor(int mentD){



            mDatabase.execSQL("DELETE FROM "+TABLE_MENTOR +" WHERE "+MENTOR_ID+" = "+ mentD   );


    }

    //delete note
    public void deleteNote(int noteD){


        mDatabase.execSQL("DELETE FROM "+TABLE_NOTES+" WHERE "+NOTES_ID + "=\""+ noteD+"\";" );


    }

    //delete assesment
    public void deleteAssessment(int assesD){


        mDatabase.execSQL("DELETE FROM "+TABLE_ASSESSMENT+" WHERE "+ASSESSMENT_ID + "=\""+ assesD+"\";" );

    }



}
