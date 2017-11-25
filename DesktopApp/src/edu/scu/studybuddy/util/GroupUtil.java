/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.util;

import edu.scu.studybuddy.bean.GroupDefinition;
import edu.scu.studybuddy.bean.GroupStudent;
import edu.scu.studybuddy.bean.Student;
import edu.scu.studybuddy.dbinteraction.GroupDbInteraction;
import java.util.Vector;

/**
 *
 * @author pawan
 */
//calls and manipulates data for GROUP before calling respective DBInteraction
public class GroupUtil {
    
    public static Vector<GroupDefinition> getGroupsById(int courseId, String studentId) {
        Vector<GroupDefinition> groupList = GroupDbInteraction.selectGroupsById(courseId, studentId);
        return groupList;
    }
    
    //for manage groups
    public static Vector<GroupDefinition> getMyGroupsById(String studentId) {
        Vector<GroupDefinition> myGroupList = GroupDbInteraction.selectMyGroupsById(studentId);
        return myGroupList;
    }
    
    public static Vector<GroupDefinition> getMyRequestsById(String studentId) {
        Vector<GroupDefinition> myGroupList = GroupDbInteraction.selectMyGroupRequestsById(studentId);
        return myGroupList;
    }
    
    public static Vector<Student> getMyGroupMembers(int groupId, String studentId) {
        Vector<Student> myGroupMembers = GroupDbInteraction.findGroupMembers(groupId, studentId);
        return myGroupMembers;
    }
    
    public static void createNewGroup(GroupDefinition group) {
        GroupDbInteraction.insertNewGroup(group);
    }
    
    public static int NewGroupId(GroupDefinition group) {
        int groupId = GroupDbInteraction.getNewGroupId(group);
        return groupId;
    }
    
    public static boolean testLock(int groupId) {
        boolean locked = GroupDbInteraction.checkGroupLock(groupId);
        return locked;
    }
    
    public static void setLock(int groupId, int lockState){
        GroupDbInteraction.setGroupLock(groupId, lockState);
    }
    
    public static boolean joinGroup(GroupStudent group, String studentId) {
        group.setStudentId(studentId);
        boolean joined = GroupDbInteraction.insertInGroup(group);
        return joined;
    }
    
    public static boolean leaveGroup(int groupId, String studentId) {
        boolean left = GroupDbInteraction.removeFromGroup(groupId, studentId);
        return left;    
    }
    
    public static boolean deleteGroup(GroupDefinition group) {
        boolean removed = GroupDbInteraction.removeGroupDefinition(group);
        return removed;    
    }
    
    
}
