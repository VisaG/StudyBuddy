/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.framework;

import edu.scu.studybuddy.bean.Student;
import edu.scu.studybuddy.util.StudentUtil;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author pawan
 */
//parent JFrame
public abstract class MyFrame extends JFrame {

    private String TITLE = "Study Buddy";

    protected static AppSession session;

    protected class AppSession {

        private Student student;

        private AppSession() {
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            if (student != null) {
                this.student = student;
            }
        }

        public void clearSession() {
            this.student = null;
        }
    }

    public MyFrame() {
        try {
            if (session == null) {
                session = new AppSession();
            }
            setTitle(TITLE);
            setResizable(false);

            WindowListener exitListener = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int confirm = JOptionPane.showOptionDialog(
                            null, "Are you sure to close the application?",
                            "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            StudentUtil.setIsLoggedIn(session.getStudent().getStudentId(), false);
                        } catch (NullPointerException ex) {
                            //ex.printStackTrace();
                        } finally {
                            System.exit(0);
                        }
                    } else {
                        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    }
                }
            };
            this.addWindowListener(exitListener);
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public abstract void reset();
}
