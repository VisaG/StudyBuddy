/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.util;

import edu.scu.studybuddy.bean.StudentCourse;
import edu.scu.studybuddy.dbinteraction.StudentCourseDbInteraction;
import java.util.List;

/**
 *
 * @author visa
 */
//calls and manipulates data for STUDENT_COURSE before calling respective DBInteraction
public class StudentCourseUtil {

    //Get all current courses excluding self
    public static List<StudentCourse> getCurrentCoursesByStuIdExcluSelf(String studentId) {
        List<StudentCourse> courseList = StudentCourseDbInteraction.getCurrentCoursesByStuIdExcluSelf(studentId);
        return courseList;
    }

    //Get all Current Courses
    public static List<StudentCourse> getCurrentCoursesByStuId(String studentId) {
        List<StudentCourse> courseList = StudentCourseDbInteraction.getCurrentCoursesById(studentId);
        return courseList;
    }

    public static List<StudentCourse> getPastCourseByStuId(String studentId) {
        List<StudentCourse> pastCourseList = StudentCourseDbInteraction.getPastCourseById(studentId);
        return pastCourseList;
    }

    public static boolean setCourse(StudentCourse updateCourse) {
        return (StudentCourseDbInteraction.setCourse(updateCourse));
    }

    public static boolean deleteCourse(int deleteCourse) {
        return (StudentCourseDbInteraction.deleteCourse(deleteCourse));
    }
}
