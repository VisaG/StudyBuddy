/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.dbinteraction;

import edu.scu.studybuddy.bean.Calendar;
import edu.scu.studybuddy.bean.Student;
import edu.scu.studybuddy.framework.JdbcConnection;
import static edu.scu.studybuddy.framework.JdbcConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author pawan/visa
 */
//class with DML queries for TABLE STUDENT
public class StudentDbInteraction extends JdbcConnection {

    //get student by EMAIL
    public static Student selectStudentByEmail(String email) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs = null;
        Student student = null;
        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT * FROM STUDENT WHERE EMAIL = ?");
            sql.setString(1, email);
            rs = sql.executeQuery();
            
            while (rs.next()) {
    
                student = new Student();
                student.setStudentId(rs.getString("STUDENT_ID"));
                student.setFirstName(rs.getString("FIRST_NAME"));
                student.setLastName(rs.getString("LAST_NAME"));
                student.setEmail(rs.getString("EMAIL"));
                student.setPassword(rs.getString("PASSWORD"));
                student.setDegree(rs.getString("DEGREE"));
                student.setHelpFutStud(rs.getBoolean("HELP_FUT_STUD"));
                student.setIsLoggedIn(rs.getBoolean("IS_LOGGED_IN"));
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
        return student;
    }

    //update STUDENT information
    public static boolean setStudent(Student student) {
        PreparedStatement sql = null;
        Connection conn = null;
        
        String email = student.getEmail();
        boolean result = false;
 
        try {
            conn = JdbcConnection.getConnection();
            sql = conn.prepareStatement("UPDATE STUDENT SET FIRST_NAME = ?, LAST_NAME = ?, PASSWORD = ?, DEGREE = ? WHERE EMAIL = ? ");
            sql.setString(1, student.getFirstName());
            sql.setString(2, student.getLastName());
            sql.setString(3, student.getPassword());
            sql.setString(4, student.getDegree());
            sql.setString(5, email);
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

    //get students by COURSE_ID
    public static Vector<Calendar> selectStudentsById(int courseId, String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        Vector<Calendar> availList = new Vector<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT S.STUDENT_ID, S.FIRST_NAME, S.LAST_NAME, S.DEGREE, M.FAV, CL.WEEKDAY, CL.AVAIL_TIME_FROM, CL.AVAIL_TIME_TO "
                    + "FROM STUDENT S LEFT JOIN MY_CONNECTION M ON M.CONNECTION_ID = S.STUDENT_ID, STUDENT_COURSE SC, COURSE C, CALENDAR CL "
                    + "WHERE SC.COURSE_ID = C.COURSE_ID AND S.STUDENT_ID = SC.STUDENT_ID "
                    + "AND CL.STUDENT_ID = S.STUDENT_ID "
                    + "AND DATE(C.END_DATE) >= CURDATE() AND C.COURSE_ID = ? AND S.STUDENT_ID NOT IN "
                    + "(SELECT GS.STUDENT_ID FROM GROUP_DEFINITION GD, GROUP_STUDENT GS WHERE GD.GROUP_ID = GS.GROUP_ID "
                    + "AND GD.COURSE_ID = ?) AND S.STUDENT_ID <> ? ORDER BY CASE WHEN FAV IS NULL THEN 1 ELSE 0 END, FIRST_NAME, LAST_NAME");
            sql.setInt(1, courseId);
            sql.setInt(2, courseId);
            sql.setString(3, studentId);

            rs = sql.executeQuery();

            while (rs.next()) {
                Calendar cal = new Calendar();
                Student student = new Student();
                cal.setStudentId(rs.getString("STUDENT_ID"));
                cal.setWeekday(rs.getString("WEEKDAY"));
                cal.setAvailTimeFrom(rs.getTime("AVAIL_TIME_FROM").toString());
                cal.setAvailTimeTo(rs.getTime("AVAIL_TIME_TO").toString());
                student.setStudentId(rs.getString("STUDENT_ID"));
                student.setFirstName(rs.getString("FIRST_NAME"));
                student.setLastName(rs.getString("LAST_NAME"));
                student.setDegree(rs.getString("DEGREE"));
                cal.setStudent(student);
                availList.add(cal);
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
        return availList;
    }

    //check if student already exists
    public static Student verifyStuExist(String stuId, String email) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs = null;
        Student student = null;
        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT * FROM STUDENT WHERE STUDENT_ID = ?  OR EMAIL = ? LIMIT 1");
            sql.setString(1, stuId);
            sql.setString(2, email);
            rs = sql.executeQuery();
            
            while (rs.next()) {
    
                student = new Student();
                student.setStudentId(rs.getString("STUDENT_ID"));                
                student.setEmail(rs.getString("EMAIL"));
                
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
        return student;
    }
    
    //insert new row in STUDENT
    public static boolean addNewStudent(Student newStudent) {
        PreparedStatement sql = null;
        Connection conn = null;
        boolean result = false;
 
       try {
            conn = JdbcConnection.getConnection();
            sql = conn.prepareStatement("INSERT INTO STUDENT (STUDENT_ID, FIRST_NAME , LAST_NAME, EMAIL, PASSWORD, DEGREE, HELP_FUT_STUD, IS_LOGGED_IN) VALUES (?,?,?,?,?,?,?,?) ");
           
            sql.setString(1, newStudent.getStudentId());
            sql.setString(2, newStudent.getFirstName());
            sql.setString(3, newStudent.getLastName());
            sql.setString(4, newStudent.getEmail());
            sql.setString(5, newStudent.getPassword());
            sql.setString(6, newStudent.getDegree());                       
            sql.setBoolean(7, newStudent.isHelpFutStud());
            sql.setBoolean(8, false);
            
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
    
    //update column IS_LOGGED_IN
    public static void updateIsLoggedIn(String studentId, boolean isLoggedIn) {
        PreparedStatement sql = null;
        Connection conn = null;

        try {
            conn = JdbcConnection.getConnection();
            sql = conn.prepareStatement("UPDATE STUDENT SET IS_LOGGED_IN = ? WHERE STUDENT_ID = ? ");

            sql.setBoolean(1, isLoggedIn);
            sql.setString(2, studentId);

            sql.executeUpdate();

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
    }  
}
