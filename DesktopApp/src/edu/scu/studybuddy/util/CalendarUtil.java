/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.util;

import edu.scu.studybuddy.bean.Calendar;
import edu.scu.studybuddy.dbinteraction.CalendarDbInteraction;
import java.util.List;

/**
 *
 * @author visa
 */
//calls and manipulates data for CALENDAR before calling respective DBInteraction
public class CalendarUtil {

    public static List<Calendar> getCalendarByStuId(String stuId) {
        List<Calendar> calendarList = CalendarDbInteraction.selectByStuId(stuId);
        return calendarList;
    }

    public static boolean insertTimePref(Calendar insertTime) {
        return (CalendarDbInteraction.insertPreference(insertTime));
    }

    public static boolean delTimePref(Calendar delPref) {
        return (CalendarDbInteraction.deletePref(delPref));
    }
}
