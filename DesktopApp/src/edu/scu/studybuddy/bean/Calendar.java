/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.bean;

/**
 *
 * @author visa
 */
//Simple bean class with getters and setters for TABLE CALENDAR
public class Calendar {

    private String studentId;
    private String weekday;
    private String availTimeFrom;
    private String availTimeTo;
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getWeekday() {
        return weekday;

    }

    public String getAvailTimeFrom() {
        return availTimeFrom;
    }

    public String getAvailTimeTo() {
        return availTimeTo;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public void setAvailTimeFrom(String availTimeFrom) {
        this.availTimeFrom = availTimeFrom;
    }

    public void setAvailTimeTo(String availTimeTo) {
        this.availTimeTo = availTimeTo;
    }
}
