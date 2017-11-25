/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.dbinteraction;

import edu.scu.studybuddy.bean.Course;
import edu.scu.studybuddy.framework.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visa
 */
//class with DML queries for TABLE COURSE
public class CourseDbInteraction extends JdbcConnection {

    //get courses by quarter
    public static List<Course> selectCourseByQtr(String quarter) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList<>();

        try {
            String connStr = "SELECT * FROM COURSE WHERE QUARTER = " + "'" + quarter + "'";
            conn = getConnection();
            sql = conn.prepareStatement(connStr);

            rs = sql.executeQuery();

            while (rs.next()) {

                Course course = new Course();
                course.setCourseId(rs.getInt("COURSE_ID"));
                course.setCourseShort(rs.getString("COURSE_SHORT"));
                course.setCourseName(rs.getString("COURSE_NAME"));
                course.setCourseDesc(rs.getString("COURSE_DESC"));
                course.setQuarter(rs.getString("QUARTER"));
                course.setStartDate(rs.getTimestamp("START_DATE"));
                course.setEndDate(rs.getTimestamp("END_DATE"));
                courseList.add(course);
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

    //gets current quarer
    public static String getCurrQtr() {

        String qtr = new String();
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = getConnection();

            sql = conn.prepareStatement("SELECT DISTINCT QUARTER FROM COURSE WHERE DATE(END_DATE) >= CURDATE()");

            rs = sql.executeQuery();
            
            while (rs.next()) {

                qtr = rs.getString("QUARTER");
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
        return qtr;
    }
}
