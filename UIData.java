package com.example.ty.javaap_c196;

/**
 * Created by Ty on 12/3/2017.
 */

public class UIData {

    private static Assessment selectedAs = new Assessment();
    private static Mentor selectedMentor= new Mentor();
    private static Course slectedCourse = new Course();
    private static Term selectedTerm=new Term();
    private static Notes selectedNote = new Notes();


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






}
