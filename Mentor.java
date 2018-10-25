package com.example.ty.javaap_c196;
import android.content.ContentValues;

import java.util.ArrayList;

import static com.example.ty.javaap_c196.StubSQL.MENTOR_EMAIL;
import static com.example.ty.javaap_c196.StubSQL.MENTOR_NAME;
import static com.example.ty.javaap_c196.StubSQL.MENTOR_PHONE;

/**
 * Created by Ty on 11/12/2017.
 */

public class Mentor {
    private int mID;
    private String name;
    private ArrayList<String> phone = new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();

    public ContentValues mentorData(Mentor ment){
        String buff1="";
        String buff2="";
        ArrayList<String> ph = ment.getPhone();
        ArrayList<String> em= ment.getEmail();

        for(int i = 0; i<ment.getPhone().size();i++){
            String phu = "%"+ph.get(i)+"!";
            buff1+=phu;

        }
        for(int i = 0; i<ment.getEmail().size();i++){
            String ema= "%"+em.get(i)+"!";
            buff2+= ema;
        }

        ContentValues values= new ContentValues();
        values.put(MENTOR_NAME,ment.getName() );
        values.put(MENTOR_PHONE,buff1 );
        values.put(MENTOR_EMAIL, buff2);



    return values;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }

    public ArrayList<String> getEmail() {
        return email;
    }

    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }



    public Mentor(){
        this.mID=-1;
    }

    public Mentor(String n, String p, String e){
        this.name=n;
        this.phone.add(p);
        this.email.add(e);
    }

        public int getmID() {
            return mID;
        }

        public void setmID(int mID) {
            this.mID = mID;
        }
}
