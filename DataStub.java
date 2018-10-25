package com.example.ty.javaap_c196;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Ty on 11/24/2017.
 * This is just to load information for testing
 */

public class DataStub {

    public static void  dataLoader(){
        ArrayList<String> phn = new ArrayList<>();
        phn.add("907-529-7673");
        phn.add("907-555-5555");
        ArrayList<String> em = new ArrayList<>();
        em.add("test@test.com");
        em.add("personal@me.com");


        Mentor ment = new Mentor();
        ment.setName("Steve");
        ment.setPhone(phn);
        ment.setEmail(em);
        ment.setmID(1);
        MainActivity.mentors.add(ment);




        Mentor ment1 = new Mentor();
        ment1.setName("John");
        ment1.setPhone(phn);
        ment1.setEmail(em);
        ment1.setmID(2);
        MainActivity.mentors.add(ment1);



        Course cr = new Course();
        cr.setcID(1);
        cr.setcTitle("Science");
        cr.setcStart("2017-11-01");
        cr.setcAEnd("2017-12-01");
        cr.setStatus("Good");
        ArrayList<Mentor> men = cr.getMentors();
        men.add(ment);
//        cr.setOjbective("87");
//        cr.setOjbectiveBool("true");
//        cr.setPerformance("");
//        cr.setPerformanceBool("false");

        MainActivity.courses.add(cr);


        Course cr1 = new Course();
        cr1.setcID(2);
        cr1.setcTitle("Math");
        cr1.setcStart("2018-01-01");
        cr1.setcAEnd("2018-03-01");
        cr1.setStatus("Good");
        ArrayList<Mentor> men1 = cr.getMentors();
        men1.add(ment1);
//        cr1.setOjbective("");
//        cr1.setOjbectiveBool("false");
//        cr1.setPerformance("99");
//        cr1.setPerformanceBool("true");

        MainActivity.courses.add(cr1);




        Term ter = new Term();
        ter.settID(1);
        ter.setTitle("First");
        ter.setStart("2017-06-01");
        ter.setEnd("2017-12-30");
        ArrayList<Course> cor = ter.getCourses();
        cor.add(cr);
        ter.setCourses(cor);
        MainActivity.terms.add(ter);

        Term ter1 = new Term();
        ter1.settID(2);
        ter1.setTitle("Second");
        ter1.setStart("2018-01-01");
        ter1.setEnd("2018-06-01");
        ArrayList<Course> cor1 = ter1.getCourses();
        cor1.add(cr1);
        ter.setCourses(cor1);
        MainActivity.terms.add(ter1);



    }




}
