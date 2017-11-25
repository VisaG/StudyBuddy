/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.util;

import edu.scu.studybuddy.bean.GroupDefinition;
import edu.scu.studybuddy.bean.GroupStudent;
import edu.scu.studybuddy.dbinteraction.NotificationDbInteraction;
import edu.scu.studybuddy.bean.Notification;
import edu.scu.studybuddy.bean.Student;
import edu.scu.studybuddy.dbinteraction.GroupDbInteraction;
import edu.scu.studybuddy.framework.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author pawan
 */
//calls and manipulates data for NOTIFICATION before calling respective DBInteraction
public class NotificationUtil {

    public static boolean sendJoinGroupRequest(GroupDefinition group, Student student) {
        Notification notif = new Notification();
        notif.setGroupId(group.getGroupId());
        notif.setStudentId(student.getStudentId());
        notif.setNotificationType(Constants.JOIN_GROUP_REQUEST);
        notif.setMessage("Please accept my request to join this group - " + student);
        notif.setAccepted(false);

        List<Notification> notifications = new ArrayList<>();
        notifications.add(notif);

        boolean joined = NotificationDbInteraction.insertInNotification(notifications);
        return joined;
    }

    public static boolean modifyNotification(Notification notification) {
        boolean isSuccess = NotificationDbInteraction.updateNotification(notification);
        if (notification.isAccepted()) {
            GroupStudent group = new GroupStudent();
            group.setGroupId(notification.getGroupId());
            group.setStudentId(notification.getStudentId());
            GroupDbInteraction.insertInGroup(group);
        }
        return isSuccess;
    }

    public static boolean createGroupRequest(List<Student> students, int courseId, String groupName, Student student) {
        List<Notification> notifications = new ArrayList<>();

        Notification notif = new Notification();
        notif.setStudentId(student.getStudentId());
        notif.setGroupName(groupName);
        notif.setCourseId(courseId);
        notif.setNotificationType(Constants.CREATE_GROUP_REQUEST);
        notif.setMessage("Please accept my request to create a group called " + groupName + " - " + student);
        notif.setAccepted(true);
        notifications.add(notif);

        for (Student stud : students) {
            notif = new Notification();
            notif.setStudentId(stud.getStudentId());
            notif.setGroupName(groupName);
            notif.setCourseId(courseId);
            notif.setNotificationType(Constants.CREATE_GROUP_REQUEST);
            notif.setMessage("Please accept my request to create a group called " + groupName + " - " + student);
            notif.setAccepted(false);
            notifications.add(notif);
        }

        boolean joined = NotificationDbInteraction.insertInNotification(notifications);
        return joined;
    }

    public static Vector<Notification> getGroupNotifications(int groupId) {
        Vector<Notification> notifications = NotificationDbInteraction.selectGroupNotifications(groupId);
        return notifications;
    }

    public static Vector<Notification> getAllMyNotifications(String studentId) {
        Vector<Notification> notifications = NotificationDbInteraction.selectAllMyNotifications(studentId);
        return notifications;
    }

    public static Vector<Notification> getNewGroupNotifications(int courseId) {
        Vector<Notification> notifications = NotificationDbInteraction.findNewGroupRequests(courseId);
        return notifications;
    }

    public static boolean seeIfAccepted(int courseId, String studentId) {
        boolean accepted = NotificationDbInteraction.checkAcceptState(courseId, studentId);
        return accepted;
    }

    public static void accept(int courseId, String studentId) {
        NotificationDbInteraction.acceptRequest(courseId, studentId);
    }

    public static void removeRequest(int courseId, int groupId, String studentId, boolean isAccepted) {
        NotificationDbInteraction.removeNewGroupRequest(courseId, groupId, studentId, isAccepted);
    }

    public static void deleteNotification(GroupDefinition group, Vector<Student> members) {
        String stuList = "";
        for (Student member : members) {
            stuList = stuList + "'" + member.getStudentId() + "', ";
        }
        stuList = stuList + "'" + group.getMyStudentId() + "', ";
        NotificationDbInteraction.removeNotifications(group, stuList.substring(0, stuList.length() - 2));
    }
    
    public static void cleanNotifications(){
        NotificationDbInteraction.scrubAllAcceptedDenied();
    }
}
