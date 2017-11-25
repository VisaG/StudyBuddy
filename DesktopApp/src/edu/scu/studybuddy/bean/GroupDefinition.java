/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.bean;

import edu.scu.studybuddy.util.NotificationUtil;
import java.util.Vector;

/**
 *
 * @author pawan modified by thil to facilitate group management
 */
//Simple bean class with getters and setters for TABLE GROUP_DEFINITION
public class GroupDefinition {

    private int groupId = 0;
    private int courseId;
    private boolean Group;
    private String groupName, courseName, courseShort, myStudentId, message;
    Vector<Notification> notifications;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCourseShort() {
        return courseShort;
    }

    public void setCourseShort(String courseShort) {
        this.courseShort = courseShort;
    }

    public String getMyStudentId() {
        return myStudentId;
    }

    public void setMyStudentId(String myStudentId) {
        this.myStudentId = myStudentId;
    }

    public boolean isGroup() {
        return Group;
    }

    public void GroupIs(boolean isGroup) {
        this.Group = isGroup;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getNotifications() {
        if (groupId != 0) {
            notifications = NotificationUtil.getGroupNotifications(groupId);
            return notifications.size();
        } else {
            return 0;
        }
    }

    public Vector<Notification> getNotificationList(){
        return notifications;
    }
    
    @Override
    public String toString() {
        return groupName;
    }

}
