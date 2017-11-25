/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.dbinteraction;

import edu.scu.studybuddy.bean.Calendar;
import edu.scu.studybuddy.framework.JdbcConnection;
import static edu.scu.studybuddy.framework.JdbcConnection.getConnection;
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
//class with DML queries for TABLE CALENDAR
public class CalendarDbInteraction extends JdbcConnection {
    
    //get data from CALENDAR TABLE as per STUDENT_ID
    public static List<Calendar> selectByStuId(String stuId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Calendar> calendarList = new ArrayList<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT * FROM CALENDAR WHERE STUDENT_ID = ?");
            sql.setString(1, stuId);                       
            rs = sql.executeQuery();

            while (rs.next()) {
                Calendar calendar = new Calendar();
                calendar.setStudentId(rs.getString("STUDENT_ID"));
                calendar.setWeekday(rs.getString("WEEKDAY"));
                calendar.setAvailTimeFrom(rs.getTime("AVAIL_TIME_FROM").toString());
                calendar.setAvailTimeTo(rs.getTime("AVAIL_TIME_TO").toString());
                calendarList.add(calendar);
                               
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
        return calendarList;
    }
    
    //insert new row in CALENDAR
    public static boolean insertPreference(Calendar insertTime) {
        
          PreparedStatement sql = null;
        Connection conn = null;
        
        boolean result = false;
 
        try {
            conn = JdbcConnection.getConnection();
           
            sql = conn.prepareStatement("INSERT INTO CALENDAR (STUDENT_ID, WEEKDAY, AVAIL_TIME_FROM, AVAIL_TIME_TO) VALUES (?,?,?,?) ");
            sql.setString(1, insertTime.getStudentId());
            sql.setString(2, insertTime.getWeekday());
            sql.setString(3, insertTime.getAvailTimeFrom());
            sql.setString(4, insertTime.getAvailTimeTo());
            
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
    
    //delete from CALENDAR the time pref of the student
    public static boolean deletePref(Calendar delPref) {
        PreparedStatement sql = null;
        Connection conn = null;
        
        boolean result = false;
 
        try {
            conn = JdbcConnection.getConnection();
           
            sql = conn.prepareStatement("DELETE FROM CALENDAR WHERE STUDENT_ID = ? "
                    + "AND WEEKDAY = ? AND AVAIL_TIME_FROM = ? AND AVAIL_TIME_TO = ?");
            sql.setString(1, delPref.getStudentId());
            sql.setString(2, delPref.getWeekday());
            sql.setString(3, delPref.getAvailTimeFrom());
            sql.setString(4, delPref.getAvailTimeTo());
            
            
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
