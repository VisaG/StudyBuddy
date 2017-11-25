/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.dbinteraction;

import edu.scu.studybuddy.bean.Course;
import edu.scu.studybuddy.bean.StudentCourse;
import edu.scu.studybuddy.framework.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pawan/visa
 */
//class with DML queries for TABLE STUDENT_COURSE
public class StudentCourseDbInteraction extends JdbcConnection {

    //get student's current courses excluding student's own courses
    public static List<StudentCourse> getCurrentCoursesByStuIdExcluSelf(String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        List<StudentCourse> courseList = new ArrayList<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT DISTINCT STUDENT_ID, C.COURSE_ID, COURSE_SHORT, COURSE_NAME, QUARTER, START_DATE, END_DATE "
                    + "FROM STUDENT_COURSE SC, COURSE C "
                    + "WHERE SC.COURSE_ID = C.COURSE_ID AND DATE(C.END_DATE) >= CURDATE() AND SC.STUDENT_ID = ? "
                    + "AND C.COURSE_ID NOT IN (SELECT GD.COURSE_ID FROM GROUP_DEFINITION GD, GROUP_STUDENT GS WHERE GD.GROUP_ID = GS.GROUP_ID AND GS.STUDENT_ID = ?)");

            sql.setString(1, studentId);
            sql.setString(2, studentId);
            rs = sql.executeQuery();

            while (rs.next()) {
                StudentCourse studCourse = new StudentCourse();
                studCourse.setStudentId(rs.getString("STUDENT_ID"));
                studCourse.setCourseId(rs.getInt("COURSE_ID"));

                Course course = new Course();
                course.setCourseId(rs.getInt("COURSE_ID"));
                course.setCourseShort(rs.getString("COURSE_SHORT"));
                course.setCourseName(rs.getString("COURSE_NAME"));
                course.setQuarter(rs.getString("QUARTER"));
                course.setStartDate(rs.getTimestamp("START_DATE"));
                course.setEndDate(rs.getTimestamp("END_DATE"));

                studCourse.setCourse(course);
                courseList.add(studCourse);
            }
            rs.close();
            sql.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sql != null) {
                    sql.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courseList;
    }

    public static List<StudentCourse> getCurrentCoursesById(String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        List<StudentCourse> courseList = new ArrayList<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT DISTINCT STUDENT_ID, C.COURSE_ID, COURSE_SHORT, COURSE_NAME, QUARTER, START_DATE, END_DATE "
                    + "FROM STUDENT_COURSE SC, COURSE C "
                    + "WHERE SC.COURSE_ID = C.COURSE_ID AND DATE(C.END_DATE) >= CURDATE() AND SC.STUDENT_ID = ? ");

            sql.setString(1, studentId);
            rs = sql.executeQuery();

            while (rs.next()) {
                StudentCourse studCourse = new StudentCourse();
                studCourse.setStudentId(rs.getString("STUDENT_ID"));
                studCourse.setCourseId(rs.getInt("COURSE_ID"));

                Course course = new Course();
                course.setCourseId(rs.getInt("COURSE_ID"));
                course.setCourseShort(rs.getString("COURSE_SHORT"));
                course.setCourseName(rs.getString("COURSE_NAME"));
                course.setQuarter(rs.getString("QUARTER"));
                course.setStartDate(rs.getTimestamp("START_DATE"));
                course.setEndDate(rs.getTimestamp("END_DATE"));

                studCourse.setCourse(course);
                courseList.add(studCourse);
            }
            rs.close();
            sql.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sql != null) {
                    sql.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courseList;
    }

    //get student's past courses
    public static List<StudentCourse> getPastCourseById(String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        List<StudentCourse> pastCourseList = new ArrayList<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT STUDENT_ID, C.COURSE_ID, COURSE_SHORT, COURSE_NAME, QUARTER, START_DATE, END_DATE "
                    + "FROM STUDENT_COURSE SC, COURSE C "
                    + "WHERE SC.COURSE_ID = C.COURSE_ID AND DATE(C.END_DATE) <= CURDATE() AND SC.STUDENT_ID = ? ORDER BY QUARTER ");

            sql.setString(1, studentId);
            rs = sql.executeQuery();

            while (rs.next()) {
                StudentCourse studCourse = new StudentCourse();
                studCourse.setStudentId(rs.getString("STUDENT_ID"));
                studCourse.setCourseId(rs.getInt("COURSE_ID"));

                Course course = new Course();
                course.setCourseId(rs.getInt("COURSE_ID"));
                course.setCourseShort(rs.getString("COURSE_SHORT"));
                course.setCourseName(rs.getString("COURSE_NAME"));
                course.setQuarter(rs.getString("QUARTER"));

                studCourse.setCourse(course);
                pastCourseList.add(studCourse);
            }
            rs.close();
            sql.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sql != null) {
                    sql.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pastCourseList;
    }

    public static boolean setCourse(StudentCourse updateCourse) {
        PreparedStatement sql = null;
        Connection conn = null;

        boolean result = false;

        try {
            conn = JdbcConnection.getConnection();

            sql = conn.prepareStatement("INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (?,?) ");
            sql.setString(1, updateCourse.getStudentId());
            sql.setInt(2, updateCourse.getCourseId());

            sql.executeUpdate();

            sql.close();
            conn.close();

            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sql != null) {
                    sql.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static boolean deleteCourse(int delCourseId) {
        PreparedStatement sql = null;
        Connection conn = null;

        boolean result = false;

        try {
            conn = JdbcConnection.getConnection();

            sql = conn.prepareStatement("DELETE FROM STUDENT_COURSE WHERE COURSE_ID = ?");
            sql.setInt(1, delCourseId);

            sql.executeUpdate();

            sql.close();
            conn.close();

            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sql != null) {
                    sql.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
