package com.example.android.reportcard;

/**
 * Created by Alexa on 7/10/2016.
 */
public class Course {

    private String name;
    private double grade;
    private String semester;

    public Course(String name, double grade, String semester) {
        this.name = name;
        this.grade = grade;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public String getSemester() {
        return semester;
    }

    public String toString(){
        return(semester + " / " + name + " / Grade: "+grade);
    }
}
