/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.dbinteraction;

import edu.scu.studybuddy.bean.GroupDefinition;
import edu.scu.studybuddy.bean.GroupStudent;
import edu.scu.studybuddy.bean.Student;
import edu.scu.studybuddy.framework.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author pawan
 */
//class with DML queries for TABLE GROUP_DEFINITION/GROUP_STUDENT
public class GroupDbInteraction extends JdbcConnection {
    
    //get groups by COURSE_ID and STUDENT_ID
    public static Vector<GroupDefinition> selectGroupsById(int courseId, String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        Vector<GroupDefinition> groupList = new Vector<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT DISTINCT GD.GROUP_ID, GD.GROUP_NAME, GD.COURSE_ID FROM GROUP_DEFINITION GD, COURSE C, GROUP_STUDENT GS, MY_CONNECTION M "
                    + "WHERE GD.COURSE_ID = C.COURSE_ID AND GD.GROUP_ID = GS.GROUP_ID AND DATE(C.END_DATE) >= CURDATE() AND GD.COURSE_ID = ? AND GS.STUDENT_ID <> ? AND GD.LOCK_STATE <> TRUE");
            sql.setInt(1, courseId);
            sql.setString(2, studentId);

            rs = sql.executeQuery();

            while (rs.next()) {
                GroupDefinition group = new GroupDefinition();
                group.setGroupId(rs.getInt("GROUP_ID"));
                group.setGroupName(rs.getString("GROUP_NAME"));
                group.setCourseId(rs.getInt("COURSE_ID"));
                groupList.add(group);
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
        return groupList;
    }
    
    //get groups by STUDENT_ID
    public static Vector<GroupDefinition> selectMyGroupsById(String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        Vector<GroupDefinition> groupList = new Vector<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT DISTINCT GD.GROUP_ID, GD.GROUP_NAME, GD.COURSE_ID, C.COURSE_SHORT, C.COURSE_NAME "
                    + "FROM GROUP_DEFINITION GD, COURSE C, GROUP_STUDENT GS "
                    + "WHERE GD.COURSE_ID = C.COURSE_ID AND GD.GROUP_ID = GS.GROUP_ID AND GS.STUDENT_ID = ?");
            sql.setString(1, studentId);

            rs = sql.executeQuery();

            while (rs.next()) {
                GroupDefinition group = new GroupDefinition();
                group.setGroupId(rs.getInt("GROUP_ID"));
                group.setGroupName(rs.getString("GROUP_NAME"));
                group.setCourseId(rs.getInt("COURSE_ID"));
                group.setCourseShort(rs.getString("COURSE_SHORT"));
                group.setCourseName(rs.getString("COURSE_NAME"));
                group.setMyStudentId(studentId);
                group.GroupIs(true);
                groupList.add(group);
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
        return groupList;
    }
    
    //gets new group requests by STUDENT_ID
    public static Vector<GroupDefinition> selectMyGroupRequestsById(String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        Vector<GroupDefinition> groupList = new Vector<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT DISTINCT N.GROUP_NAME, N.MESSAGE, "
                    + "N.COURSE_ID, C.COURSE_SHORT, C.COURSE_NAME "
                    + "FROM NOTIFICATION N, COURSE C "
                    + "WHERE N.COURSE_ID = C.COURSE_ID "
                    + "AND N.NOTIFICATION_TYPE = 'CREATE_GROUP_REQUEST' "
                    + "AND N.STUDENT_ID = ?");
            sql.setString(1, studentId);

            rs = sql.executeQuery();

            while (rs.next()) {
                GroupDefinition group = new GroupDefinition();
                group.setGroupName(rs.getString("GROUP_NAME"));
                group.setCourseId(rs.getInt("COURSE_ID"));
                group.setCourseShort(rs.getString("COURSE_SHORT"));
                group.setCourseName(rs.getString("COURSE_NAME"));
                group.setMessage(rs.getString("MESSAGE"));
                group.setMyStudentId(studentId);
                group.GroupIs(false);
                groupList.add(group);
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
        return groupList;
    }

    //get group members by GROUP_ID
    public static Vector<Student> findGroupMembers(int groupId, String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        Vector<Student> studentList = new Vector<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT S.FIRST_NAME, S.LAST_NAME, "
                    + "S.EMAIL, S.STUDENT_ID FROM STUDENT S "
                    + "LEFT JOIN GROUP_STUDENT GS "
                    + "ON GS.STUDENT_ID = S.STUDENT_ID "
                    + "WHERE GS.STUDENT_ID <> ? AND GROUP_ID = ?");
            sql.setString(1, studentId);
            sql.setInt(2, groupId);


            rs = sql.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setFirstName(rs.getString("FIRST_NAME"));
                student.setLastName(rs.getString("LAST_NAME"));
                student.setEmail(rs.getString("EMAIL"));
                student.setStudentId(rs.getString("STUDENT_ID"));
                studentList.add(student);
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
        return studentList;
    }
    
    //check if group is locked
    public static boolean checkGroupLock(int groupId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        boolean locked = false;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT DISTINCT GD.LOCK_STATE "
                    + "FROM GROUP_DEFINITION GD "
                    + "WHERE GD.GROUP_ID = ?");
            sql.setInt(1, groupId);

            
            rs = sql.executeQuery();
            while (rs.next()) {
            locked = rs.getBoolean("LOCK_STATE");
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
        return locked;
    }
    
    //lock/unlock the group
    public static void setGroupLock(int groupId, int lockState) {
        PreparedStatement sql = null;
        Connection conn = null;

        try {
            conn = getConnection();
            // low pritority to prevent collisions
            sql = conn.prepareStatement("UPDATE LOW_PRIORITY GROUP_DEFINITION "
                    + "SET GROUP_DEFINITION.LOCK_STATE = ? "
                    + "WHERE GROUP_DEFINITION.GROUP_ID = ? ");
            sql.setInt(1, lockState);
            sql.setInt(2, groupId);
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
    
    //insert new row in GROUP_STUDENT
    public static boolean insertInGroup(GroupStudent group) {
        PreparedStatement sql = null;
        Connection conn = null;
        boolean joined = false;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("INSERT INTO GROUP_STUDENT(GROUP_ID, STUDENT_ID) VALUES(?, ?)");
            sql.setInt(1, group.getGroupId());
            sql.setString(2, group.getStudentId());

            int count = sql.executeUpdate();
            if (count == 1) {
                joined = true;
            }

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
        return joined;
    }
    
    //insert new row in GROUP_DEFINITION
    public static void insertNewGroup(GroupDefinition group) {
        PreparedStatement sql = null;
        Connection conn = null;
        

        try {
            conn = getConnection();
            sql = conn.prepareStatement("INSERT INTO GROUP_DEFINITION(GROUP_NAME, COURSE_ID, LOCK_STATE) "
                    + "VALUES(?, ?, ?)");
            sql.setString(1, group.getGroupName());
            sql.setInt(2, group.getCourseId());
            sql.setInt(3, 0);
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
    
    public static int getNewGroupId(GroupDefinition group) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        int groupId = 0;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT GROUP_ID FROM GROUP_DEFINITION WHERE COURSE_ID = ? AND GROUP_NAME = ? ");
            sql.setInt(1, group.getCourseId());
            sql.setString(2, group.getGroupName());
            rs = sql.executeQuery();
            
            while (rs.next()) {
                groupId = rs.getInt("GROUP_ID");
            }
            
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
        return groupId;
    }
    
    //remove student from GROUP_STUDENT
    public static boolean removeFromGroup(int groupId, String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        boolean removed = false;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("DELETE GROUP_STUDENT.* "
                    + "FROM GROUP_STUDENT "
                    + "WHERE GROUP_ID = ? AND STUDENT_ID = ? ");
            sql.setInt(1, groupId);
            sql.setString(2, studentId);

            int count = sql.executeUpdate();
            if (count == 1) {
                removed = true;
            }

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
        return removed;
    }
    
    public static boolean removeGroupDefinition(GroupDefinition group) {
        PreparedStatement sql = null;
        Connection conn = null;
        boolean removed = false;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("DELETE GROUP_DEFINITION.* "
                    + "FROM GROUP_DEFINITION "
                    + "WHERE GROUP_ID = ? ");
            sql.setInt(1, group.getGroupId());

            int count = sql.executeUpdate();
            if (count == 1) {
                removed = true;
            }

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
        return removed;
    }
    
}
