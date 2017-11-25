/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.util;

import edu.scu.studybuddy.bean.MyConnection;
import edu.scu.studybuddy.dbinteraction.MyConnectionDbInteraction;
import java.util.List;

/**
 *
 * @author pawan
 */
//calls and manipulates data for MY_CONNECTION before calling respective DBInteraction
public class MyConnectionUtil {
    public static List<MyConnection> getMyConnections(String studentId) {
        List<MyConnection> groupList = MyConnectionDbInteraction.selectMyConnectionById(studentId);
        return groupList;
    }

    public static void setFav(int id, boolean fav) {
        MyConnectionDbInteraction.updateMyConnection(id, fav);
    }
}
