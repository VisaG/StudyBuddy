/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.util;

import edu.scu.studybuddy.bean.Calendar;
import edu.scu.studybuddy.bean.Student;
import edu.scu.studybuddy.dbinteraction.CalendarDbInteraction;
import edu.scu.studybuddy.dbinteraction.StudentDbInteraction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author pawan/visa
 */
//calls and manipulates data for STUDENT before calling respective DBInteraction
public class StudentUtil {

    public static Student getStudent(String email) {
        Student student = StudentDbInteraction.selectStudentByEmail(email);
        return student;
    }

    public static boolean setStudent(Student student) {
        return (StudentDbInteraction.setStudent(student));

    }

    /*public static Vector<Student> getStudentsById(int courseId, String studentId) {
        Vector<Student> students = StudentDbInteraction.selectStudentsById(courseId, studentId);
        return students;
  
    }*/
    public static Student verifyStudent(String stuId, String email) {
        Student student = StudentDbInteraction.verifyStuExist(stuId, email);
        return student;
    }

    public static boolean addNewStudent(Student newStudent) {
        return (StudentDbInteraction.addNewStudent(newStudent));

    }

    public static void setIsLoggedIn(String studentId, boolean isLoogedIn) {
        StudentDbInteraction.updateIsLoggedIn(studentId, isLoogedIn);
    }

    public static Vector<Student> getStudentsById(int courseId, String studentId) {
        Map<String, List<Calendar>> myAvailMap = new HashMap<>();
        List<Calendar> calList = CalendarDbInteraction.selectByStuId(studentId);

        for (Calendar calendar : calList) {
            if (myAvailMap.containsKey(calendar.getWeekday())) {
                List<Calendar> availList = myAvailMap.get(calendar.getWeekday());
                availList.add(calendar);
            } else {
                List<Calendar> availList = new ArrayList<>();
                availList.add(calendar);
                myAvailMap.put(calendar.getWeekday(), availList);
            }
        }

        Vector<Calendar> availList = StudentDbInteraction.selectStudentsById(courseId, studentId);
        Vector<Student> students = new Vector<>();
        Map<String, Student> studMap = new HashMap<>();

        for (Calendar avail : availList) {
            if (studMap.containsKey(avail.getStudentId()) && myAvailMap.containsKey(avail.getWeekday())) {
                int timeFrom = Integer.parseInt(avail.getAvailTimeFrom().substring(0, 2));
                int timeTo = Integer.parseInt(avail.getAvailTimeTo().substring(0, 2));

                Student student = avail.getStudent();
                List<Calendar> studentAvail = student.getStudentAvail();
                if (studentAvail == null) {
                    studentAvail = new ArrayList<>();
                }

                for (Calendar cal : myAvailMap.get(avail.getWeekday())) {
                    int myTimeFrom = Integer.parseInt(cal.getAvailTimeFrom().substring(0, 2));
                    int myTimeTo = Integer.parseInt(cal.getAvailTimeTo().substring(0, 2));

                    if ((timeFrom > myTimeFrom && timeFrom < myTimeTo)
                            || (timeTo > myTimeFrom && timeTo < myTimeTo)
                            || (timeFrom <= myTimeFrom && timeTo >= myTimeTo)
                            || (timeFrom >= myTimeFrom && timeTo <= myTimeTo)) {
                        studentAvail.add(avail);
                    }
                }
            } else if (myAvailMap.containsKey(avail.getWeekday())) {
                int timeFrom = Integer.parseInt(avail.getAvailTimeFrom().substring(0, 2));
                int timeTo = Integer.parseInt(avail.getAvailTimeTo().substring(0, 2));

                Student student = avail.getStudent();
                List<Calendar> studentAvail = new ArrayList<>();

                for (Calendar cal : myAvailMap.get(avail.getWeekday())) {
                    int myTimeFrom = Integer.parseInt(cal.getAvailTimeFrom().substring(0, 2));
                    int myTimeTo = Integer.parseInt(cal.getAvailTimeTo().substring(0, 2));

                    if ((timeFrom > myTimeFrom && timeFrom < myTimeTo)
                            || (timeTo > myTimeFrom && timeTo < myTimeTo)
                            || (timeFrom <= myTimeFrom && timeTo >= myTimeTo)
                            || (timeFrom >= myTimeFrom && timeTo <= myTimeTo)) {
                        studentAvail.add(avail);
                        student.setStudentAvail(studentAvail);
                        studMap.put(avail.getStudentId(), student);
                    }
                }
            }
        }

        for (String key : studMap.keySet()) {
            if (studMap.get(key).getStudentAvail().isEmpty()) {
                studMap.remove(key);
            } else {
                students.add(studMap.get(key));
            }
        }

        return students;
    }
}
