/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.bean;

import java.sql.Timestamp;

/**
 *
 * @author visa
 */
//Simple bean class with getters and setters for TABLE COURSE
public class Course {

    private int courseId;
    private String courseShort;
    private String courseName;
    private String courseDesc;
    private String quarter;
    private Timestamp startDate;
    private Timestamp endDate;

    public String getCourseShort() {
        return courseShort;
    }

    public void setCourseShort(String courseShort) {
        this.courseShort = courseShort;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return courseShort + " : " + courseName;
    }
}
