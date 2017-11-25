/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.framework;

import edu.scu.studybuddy.bean.Student;

/**
 *
 * @author pawan
 */
//To maintain session of the instance of the application
class AppSession {

    private static Student student;

    private AppSession() {
    }

    public static AppSession getInstance() {
        return AppSessionHolder.INSTANCE;
    }

    private static class AppSessionHolder {

        private static final AppSession INSTANCE = new AppSession();
    }

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student student) {
        AppSession.student = student;
    }

    public static void clearSession() {
        AppSession.student = null;
    }
}
