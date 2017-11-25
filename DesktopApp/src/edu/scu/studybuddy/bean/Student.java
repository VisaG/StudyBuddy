/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.bean;

import java.util.List;

/**
 *
 * @author pawan
 */
//Simple bean class with getters and setters for TABLE STUDENT
public class Student {

    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String degree;
    private boolean helpFutStud;
    private List<Calendar> studentAvail;

    public List<Calendar> getStudentAvail() {
        return studentAvail;
    }

    public void setStudentAvail(List<Calendar> studentAvail) {
        this.studentAvail = studentAvail;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public boolean isHelpFutStud() {
        return helpFutStud;
    }

    public void setHelpFutStud(boolean helpFutStud) {
        this.helpFutStud = helpFutStud;
    }

    private boolean isLoggedIn;

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public String toString() {
        return studentId + " - " + firstName + " " + lastName + " (" + degree + ')';
    }
}
