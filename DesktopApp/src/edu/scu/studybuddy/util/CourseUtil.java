/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.util;

import edu.scu.studybuddy.bean.Course;
import edu.scu.studybuddy.dbinteraction.CourseDbInteraction;
import java.util.List;

/**
 *
 * @author visa
 */
//calls and manipulates data for COURSE before calling respective DBInteraction
public class CourseUtil {
    
    public static List<Course> getCourse(String quarter) {
        List<Course> courseList = CourseDbInteraction.selectCourseByQtr(quarter);
        return courseList;
    } 
    public static String getCurrQtr() {
        String currQtr = CourseDbInteraction.getCurrQtr();
        return currQtr;
    }
}