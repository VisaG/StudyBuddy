/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.dbinteraction;

import edu.scu.studybuddy.bean.MyConnection;
import edu.scu.studybuddy.bean.Student;
import edu.scu.studybuddy.framework.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author pawan
 */
//class with DML queries for TABLE MY_CONNECTION
public class MyConnectionDbInteraction extends JdbcConnection {
    //get my connections
    public static List<MyConnection> selectMyConnectionById(String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        List<MyConnection> myConnList = new Vector<>();
        
        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT M.STUDENT_ID AS SELF_ID, M.ID, M.CONNECTION_ID, M.FAV, S.*  FROM MY_CONNECTION M, STUDENT S WHERE M.CONNECTION_ID = S.STUDENT_ID AND M.STUDENT_ID = ?");
            sql.setString(1, studentId);
            
            rs = sql.executeQuery();
            
            while (rs.next()) {
                MyConnection myConn = new MyConnection();
                myConn.setId(rs.getInt("ID"));
                myConn.setStudentId(rs.getString("SELF_ID"));
                myConn.setConnectionId(rs.getString("CONNECTION_ID"));
                myConn.setFav(rs.getBoolean("FAV"));
                
                Student student = new Student();
                student.setStudentId(rs.getString("CONNECTION_ID"));
                student.setFirstName(rs.getString("FIRST_NAME"));
                student.setLastName(rs.getString("LAST_NAME"));
                student.setEmail(rs.getString("EMAIL"));
                student.setDegree(rs.getString("DEGREE"));
                student.setHelpFutStud(rs.getBoolean("HELP_FUT_STUD"));
                myConn.setConnection(student);
                
                myConnList.add(myConn);
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
        return myConnList;
    }
    
    public static void updateMyConnection(int id, boolean fav) {
        PreparedStatement sql = null;
        Connection conn = null;
        
        try {
            conn = getConnection();
            sql = conn.prepareStatement("UPDATE MY_CONNECTION SET FAV = ? WHERE ID = ?");
            sql.setBoolean(1, fav);
            sql.setInt(2, id);
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
    }
}
