package com.example.android.reportcard;

import java.util.ArrayList;

/**
 * Created by Alexa on 7/10/2016.
 */
public class ReportCard {

    private double gpa;
    private String name; // of the student
    private ArrayList<Course> courses;

    public ReportCard(String name) {
        gpa = 0;
        this.name = name;
        courses = new ArrayList<Course>();
    }

    public void calculateGPA() {
        double average = 0;
        for (int i = 0 ; i < courses.size() ; i++) {
            average += courses.get(i).getGrade();
        }
        gpa = average/courses.size();
    }

    public double getGPA(){
        calculateGPA(); // because it might have changed since the last course was added
        return gpa;
    }

    public void addCourses(String name, int grade, String semester) {
        courses.add(new Course(name, grade, semester));
    }

    public String toString(){
        String result = "Student Name: " + name + "\n";
        for (int i = 0 ; i < courses.size() ; i++) {
            result += (courses.get(i).toString() + "\n");
        }
        result += "GPA: " + getGPA() + "\n";
        return result;
    }
}
