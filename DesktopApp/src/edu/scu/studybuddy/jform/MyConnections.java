/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.jform;

import edu.scu.studybuddy.bean.MyConnection;
import edu.scu.studybuddy.framework.MyFrame;
import edu.scu.studybuddy.framework.OnClickListener;
import edu.scu.studybuddy.util.MyConnectionUtil;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author pawan
 */
//JFrame for My Connections
public class MyConnections extends MyFrame {

    /**
     * Creates new form Connections
     */
    private MyConnections() {
        TabPage.tabs.add(this);
        //reset();
    }

    public static MyConnections getInstance() {
        return MyConnectionsHolder.INSTANCE;
    }

    @Override
    public void reset() {
        try {
            ScrollPane scrollPane1 = new ScrollPane();
            JPanel mainPanel = new JPanel(new BorderLayout());
            JPanel jPanelOut = new JPanel(new GridLayout(0, 1));
            jPanelOut.setBorder(new EmptyBorder(10, 20, 10, 20));

            List<MyConnection> myConnList = MyConnectionUtil.getMyConnections(session.getStudent().getStudentId());

            for (MyConnection myConn : myConnList) {
                JPanel jPanelIn = new JPanel(new GridLayout(0, 2));
                JLabel lbl = new JLabel();
                lbl.setText(myConn.getConnection().toString());
                JLabel picLabel;
                if (myConn.isFav()) {
                    BufferedImage myPicture = ImageIO.read(new File("img/heart_clicked.png"));
                    picLabel = new JLabel(new ImageIcon(new ImageIcon(myPicture).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
                    picLabel.setName(myConn.getId() + "-ON");
                } else {
                    BufferedImage myPicture = ImageIO.read(new File("img/heart.png"));
                    picLabel = new JLabel(new ImageIcon(new ImageIcon(myPicture).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
                    picLabel.setName(myConn.getId() + "-OFF");
                }
                picLabel.addMouseListener(new OnClickListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            JLabel picLabel = (JLabel) e.getSource();
                            boolean fav;
                            if (picLabel.getName().contains("-ON")) {
                                BufferedImage myPicture = ImageIO.read(new File("img/heart.png"));
                                picLabel.setName(picLabel.getName().replace("-ON", "-OFF"));
                                picLabel.setIcon(new ImageIcon(new ImageIcon(myPicture).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
                                fav = false;
                            } else {
                                BufferedImage myPicture = ImageIO.read(new File("img/heart_clicked.png"));
                                picLabel.setName(picLabel.getName().replace("-OFF", "-ON"));
                                picLabel.setIcon(new ImageIcon(new ImageIcon(myPicture).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
                                fav = true;
                            }
                            int id = Integer.parseInt(picLabel.getName().split("-")[0]);
                            MyConnectionUtil.setFav(id, fav);
                        } catch (IOException ex) {
                            Logger.getLogger(MyConnections.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                jPanelIn.add(lbl);
                jPanelIn.add(picLabel);
                jPanelOut.add(jPanelIn);
            }
            mainPanel.add(jPanelOut, BorderLayout.NORTH);
            scrollPane1.add(mainPanel);
            getContentPane().removeAll();
            add(scrollPane1);
            pack();
        } catch (IOException ex) {
            Logger.getLogger(MyConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class MyConnectionsHolder {

        private static final MyConnections INSTANCE = new MyConnections();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyConnections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyConnections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyConnections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyConnections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MyConnections.getInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
