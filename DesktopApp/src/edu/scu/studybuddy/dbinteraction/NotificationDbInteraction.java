/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.dbinteraction;

import edu.scu.studybuddy.bean.GroupDefinition;
import edu.scu.studybuddy.bean.Notification;
import edu.scu.studybuddy.bean.Student;
import edu.scu.studybuddy.framework.Constants;
import edu.scu.studybuddy.framework.JdbcConnection;
import static edu.scu.studybuddy.framework.JdbcConnection.getConnection;
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
//class with DML queries for TABLE NOTIFICATION
public class NotificationDbInteraction {

    //get notifications as per group_id
    public static Vector<Notification> selectGroupNotifications(int groupId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs = null;
        Vector<Notification> notifications = new Vector<>();
        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT * FROM NOTIFICATION WHERE GROUP_ID = ? AND NOTIFICATION_TYPE = ?");
            sql.setInt(1, groupId);
            sql.setString(2, Constants.JOIN_GROUP_REQUEST);

            rs = sql.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt("ID"));
                notification.setGroupId(rs.getInt("GROUP_ID"));
                notification.setCourseId(rs.getInt("COURSE_ID"));
                notification.setStudentId(rs.getString("STUDENT_ID"));
                notification.setNotificationType(rs.getString("NOTIFICATION_TYPE"));
                notification.setMessage(rs.getString("MESSAGE"));
                notification.setAccepted(rs.getBoolean("ACCEPTED"));

                notifications.add(notification);
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
        return notifications;
    }

    //insert new notifications
    public static boolean insertInNotification(List<Notification> notifications) {
        PreparedStatement sql = null;
        Connection conn = null;
        boolean joined = false;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("INSERT INTO NOTIFICATION (GROUP_ID, GROUP_NAME, COURSE_ID, STUDENT_ID, NOTIFICATION_TYPE, MESSAGE, ACCEPTED) VALUES(?, ?, ?, ?, ?, ?, ?)");
            conn.setAutoCommit(false);

            for (Notification notif : notifications) {
                sql.setInt(1, notif.getGroupId());
                sql.setString(2, notif.getGroupName());
                sql.setInt(3, notif.getCourseId());
                sql.setString(4, notif.getStudentId());
                sql.setString(5, notif.getNotificationType());
                sql.setString(6, notif.getMessage());
                sql.setBoolean(7, notif.isAccepted());
                sql.addBatch();
            }
            int[] counts = sql.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);

            int rows = 0;
            for (int count : counts) {
                if (count == 1) {
                    rows++;
                }
            }
            if (rows == counts.length) {
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

    //update notifications
    public static boolean updateNotification(Notification notification) {
        PreparedStatement sql = null;
        Connection conn = null;
        boolean result = false;

        try {
            conn = JdbcConnection.getConnection();
            sql = conn.prepareStatement("UPDATE NOTIFICATION SET GROUP_ID = ?, GROUP_NAME = ?, NOTIFICATION_TYPE = ?, MESSAGE = ?, ACCEPTED = ? WHERE ID = ?");
            sql.setInt(1, notification.getGroupId());
            sql.setString(2, notification.getGroupName());
            sql.setString(3, notification.getNotificationType());
            sql.setString(4, notification.getMessage());
            sql.setBoolean(5, notification.isAccepted());
            sql.setInt(6, notification.getId());

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

    //get notification by STUDENT_ID
    public static Vector<Notification> selectAllMyNotifications(String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs = null;
        Vector<Notification> notifications = new Vector<>();
        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT * FROM NOTIFICATION WHERE STUDENT_ID = ? AND NOTIFICATION_TYPE = ?");
            sql.setString(1, studentId);
            sql.setString(2, Constants.NOTIFICATION);

            rs = sql.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt("ID"));
                notification.setGroupId(rs.getInt("GROUP_ID"));
                notification.setCourseId(rs.getInt("COURSE_ID"));
                notification.setStudentId(rs.getString("STUDENT_ID"));
                notification.setNotificationType(rs.getString("NOTIFICATION_TYPE"));
                notification.setMessage(rs.getString("MESSAGE"));
                notification.setAccepted(rs.getBoolean("ACCEPTED"));

                notifications.add(notification);
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
        return notifications;
    }

    //get notification by COURSE_ID
    public static Vector<Notification> findNewGroupRequests(int courseId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs = null;
        Vector<Notification> notifications = new Vector<>();
        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT * FROM NOTIFICATION WHERE COURSE_ID = ? AND NOTIFICATION_TYPE = ?");
            sql.setInt(1, courseId);
            sql.setString(2, Constants.CREATE_GROUP_REQUEST);

            rs = sql.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt("ID"));
                notification.setGroupId(rs.getInt("GROUP_ID"));
                notification.setCourseId(rs.getInt("COURSE_ID"));
                notification.setStudentId(rs.getString("STUDENT_ID"));
                notification.setNotificationType(rs.getString("NOTIFICATION_TYPE"));
                notification.setMessage(rs.getString("MESSAGE"));
                notification.setAccepted(rs.getBoolean("ACCEPTED"));

                notifications.add(notification);
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
        return notifications;
    }

    public static Vector<Student> findRequestMembers(int groupId, String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        Vector<Student> studentList = new Vector<>();

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT S.FIRST_NAME, S.LAST_NAME, "
                    + "S.EMAIL FROM STUDENT S "
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

    // checks if new
    public static boolean checkAcceptState(int courseId, String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;
        ResultSet rs;
        boolean accepted = false;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("SELECT DISTINCT N.ACCEPTED "
                    + "FROM NOTIFICATION N "
                    + "WHERE N.COURSE_ID = ? AND N.STUDENT_ID = ?");
            sql.setInt(1, courseId);
            sql.setString(2, studentId);

            rs = sql.executeQuery();
            while (rs.next()) {
                accepted = rs.getBoolean("ACCEPTED");
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
        return accepted;
    }

    public static void acceptRequest(int courseId, String studentId) {
        PreparedStatement sql = null;
        Connection conn = null;

        try {
            conn = getConnection();
            // low pritority to prevent collisions
            sql = conn.prepareStatement("UPDATE NOTIFICATION "
                    + "SET NOTIFICATION.ACCEPTED = 1 "
                    + "WHERE NOTIFICATION.COURSE_ID = ? "
                    + "AND NOTIFICATION.STUDENT_ID = ? ");
            sql.setInt(1, courseId);
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

    public static void removeNewGroupRequest(int courseId, int groupId, String studentId, boolean isAccepted) {
        PreparedStatement sql = null;
        Connection conn = null;

        try {
            conn = getConnection();
            // low pritority to prevent collisions
            sql = conn.prepareStatement("UPDATE NOTIFICATION "
                    + "SET NOTIFICATION.NOTIFICATION_TYPE = ? "
                    + "WHERE NOTIFICATION.COURSE_ID = ? "
                    + "AND NOTIFICATION.GROUP_ID = ? "
                    + "AND NOTIFICATION.STUDENT_ID = ? ");
            if (isAccepted) {
                sql.setString(1, Constants.NOTIFICATION_ACCEPT);
            } else {
                sql.setString(1, Constants.NOTIFICATION_DENY);
            }
            sql.setInt(2, courseId);
            sql.setInt(3, groupId);
            sql.setString(4, studentId);
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

    public static boolean removeNotifications(GroupDefinition group, String students) {
        PreparedStatement sql = null;
        Connection conn = null;
        boolean removed = false;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("DELETE FROM NOTIFICATION "
                    + "WHERE GROUP_ID = ? AND COURSE_ID = ? AND STUDENT_ID IN (?) ");
            sql.setInt(1, 0);
            sql.setInt(2, group.getCourseId());
            sql.setString(3, students);

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

    public static boolean scrubAllAcceptedDenied() {
        PreparedStatement sql = null;
        Connection conn = null;
        boolean removed = false;

        try {
            conn = getConnection();
            sql = conn.prepareStatement("DELETE NOTIFICATION.* "
                    + "FROM NOTIFICATION "
                    + "WHERE NOTIFICATION_TYPE != ? "
                    + "AND NOTIFICATION_TYPE != ? ");
            sql.setString(1, Constants.CREATE_GROUP_REQUEST);
            sql.setString(2, Constants.JOIN_GROUP_REQUEST);
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
