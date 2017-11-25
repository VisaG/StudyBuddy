/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.scu.studybuddy.bean;

import edu.scu.studybuddy.dbinteraction.GroupDbInteraction;
import edu.scu.studybuddy.jform.ManageGroup;
import edu.scu.studybuddy.util.GroupUtil;
import edu.scu.studybuddy.util.NotificationUtil;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class handles the rendering of a student's groups and handles the UI in
 * managing groups by allowing users to lock/unlock/leave/accept/deny group
 * related activities. It also calls the a NotificationListRenderer which
 * manages requests to join existing groups.
 *
 * @author thilina
 */
public class ManageGroupRenderer extends DefaultListCellRenderer {

    // These are all the icons used for groups
    private static final ImageIcon groupIcon = new ImageIcon(ManageGroupRenderer.class.getResource("/resources/images/group.png"));
    private static final ImageIcon requestIcon = new ImageIcon(ManageGroupRenderer.class.getResource("/resources/images/request.png"));
    private static final ImageIcon acceptIcon = new ImageIcon(ManageGroupRenderer.class.getResource("/resources/images/accept.png"));
    private static final ImageIcon checkedIcon = new ImageIcon(ManageGroupRenderer.class.getResource("/resources/images/checked.png"));
    private static final ImageIcon deleteIcon = new ImageIcon(ManageGroupRenderer.class.getResource("/resources/images/delete.png"));
    private static final ImageIcon lockIcon = new ImageIcon(ManageGroupRenderer.class.getResource("/resources/images/lock.png"));
    private static final ImageIcon unlockIcon = new ImageIcon(ManageGroupRenderer.class.getResource("/resources/images/unlock.png"));

    // This is the renderer component
    private GroupLabel renderer;

    public ManageGroupRenderer(
            final JList list,
            final JList notificationList,
            final JButton acceptButton,
            final JButton denyButton,
            final JTextArea text,
            final ManageGroup aThis) {
        renderer = new GroupLabel();

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent aClick) {
                if (SwingUtilities.isLeftMouseButton(aClick)) {

                    int index = list.locationToIndex(aClick.getPoint());

                    if (index != -1 && list.isSelectedIndex(index)) {

                        Rectangle rect = list.getCellBounds(index, index);
                        Point pointWithinCell = new Point(
                                aClick.getX() - rect.x,
                                aClick.getY() - rect.y);

                        DefaultListModel model = (DefaultListModel) list.getModel();
                        GroupDefinition groupAtIndex = (GroupDefinition) model.getElementAt(index);

                        //Set the click area locations
                        Rectangle crossRect = new Rectangle(
                                rect.width - 9 - 5 - deleteIcon.getIconWidth() / 2,
                                rect.height / 2 - deleteIcon.getIconHeight() / 2,
                                deleteIcon.getIconWidth(), deleteIcon.getIconHeight());

                        Rectangle lockRect = new Rectangle(
                                rect.width - 9 - 25 - lockIcon.getIconWidth() / 2,
                                rect.height / 2 - lockIcon.getIconHeight() / 2,
                                lockIcon.getIconWidth(), lockIcon.getIconHeight());

                        Rectangle acceptRect = new Rectangle(
                                rect.width - 9 - 25 - acceptIcon.getIconWidth() / 2,
                                rect.height / 2 - acceptIcon.getIconHeight() / 2,
                                acceptIcon.getIconWidth(), acceptIcon.getIconHeight());

                        Rectangle unlockRect = new Rectangle(
                                rect.width - 9 - 45 - unlockIcon.getIconWidth() / 2,
                                rect.height / 2 - unlockIcon.getIconHeight() / 2,
                                unlockIcon.getIconWidth(), unlockIcon.getIconHeight());

                        // EXISITING GROUPS
                        if (groupAtIndex.isGroup()) {
                            //delete group
                            if (crossRect.contains(pointWithinCell)) {
                                // leave group
                                GroupUtil.leaveGroup(
                                        groupAtIndex.getGroupId(),
                                        groupAtIndex.getMyStudentId());

                                //check if group has < 2 members
                                Vector<Student> remainingMembers = GroupUtil.getMyGroupMembers(
                                        groupAtIndex.getGroupId(),
                                        groupAtIndex.getMyStudentId());
                                //remove them too if so
                                if (remainingMembers.size() < 2) {
                                    GroupUtil.leaveGroup(
                                            groupAtIndex.getGroupId(),
                                            remainingMembers.elementAt(0).getStudentId());

                                    //delete group definition
                                    GroupUtil.deleteGroup(groupAtIndex);

                                    //delete all notifications
                                    NotificationUtil.deleteNotification(groupAtIndex,
                                            remainingMembers);
                                }
                                model.remove(index);
                            } else {
                                //return the object, and populatie the info pane
                                GroupInfoRenderer(groupAtIndex, text);
                                
                                //Pass any notifications to the Notifications 
                                //renderer
                                if (groupAtIndex.getNotifications() > 0) {
                                    NotificationListRenderer(
                                            groupAtIndex.getNotificationList(),
                                            notificationList);
                                    acceptButton.setEnabled(true);
                                    denyButton.setEnabled(true);
                                } else {
                                    notificationList.setListData(new Notification[0]);
                                    acceptButton.setEnabled(false);
                                    denyButton.setEnabled(false);
                                }
                            }

                            // Lock and unlock groups
                            if (lockRect.contains(pointWithinCell)
                                    && GroupUtil.testLock(groupAtIndex.getGroupId())) {
                                //unlock
                                GroupUtil.setLock(groupAtIndex.getGroupId(), 0);
                                list.repaint();
                            } else if (unlockRect.contains(pointWithinCell)
                                    && !(GroupUtil.testLock(groupAtIndex.getGroupId()))) {
                                //lock
                                GroupUtil.setLock(groupAtIndex.getGroupId(), 1);
                                list.repaint();
                            }
                        }//END EXISITNG GROUPS
                        // NEW GROUP REQUESTS
                        else if (!(groupAtIndex.isGroup())) {

                            // The user has clickeed on the deny request icon
                            if (crossRect.contains(pointWithinCell)) {

                                //Remove your request
                                NotificationUtil.removeRequest(
                                        groupAtIndex.getCourseId(),
                                        0,
                                        groupAtIndex.getMyStudentId(), false);

                                //Check if all others had accepted, given minimum group size of two exists
                                Vector<Notification> recipientsLeft
                                        = NotificationUtil.getNewGroupNotifications(groupAtIndex.getCourseId());

                                // If the number of students is 2 or more, 
                                // let them create a group if they had accepted
                                if (recipientsLeft.size() > 1) {
                                    boolean allAccepted = true;

                                    // For each potential member check if accepted
                                    for (Notification recipient : recipientsLeft) {
                                        if (!(recipient.isAccepted())) {
                                            allAccepted = false;
                                        }
                                    }
                                    int groupId;

                                    if (allAccepted) {
                                        // Create the group, via GroupUtil dbIf
                                        GroupUtil.createNewGroup(groupAtIndex);
                                        groupId = GroupUtil.NewGroupId(groupAtIndex);
                                        // Add  accepted members
                                        for (Notification recipient : recipientsLeft) {
                                            GroupStudent group = new GroupStudent();
                                            group.setGroupId(groupId);
                                            group.setStudentId(recipient.getStudentId());
                                            GroupDbInteraction.insertInGroup(group);
                                            NotificationUtil.removeRequest(
                                                    recipient.getCourseId(),
                                                    0,
                                                    recipient.getStudentId(), true);
                                        }
                                        model.remove(index);
                                        list.repaint();
                                    }
                                    model.remove(index);
                                    list.repaint();

                                } else {
                                    // Not enough members to form group, 
                                    // delete the originators request which removes
                                    // the request.
                                    NotificationUtil.removeRequest(
                                            recipientsLeft.elementAt(0).getCourseId(),
                                            0,
                                            recipientsLeft.elementAt(0).getStudentId(), false);
                                }

                                model.remove(index);
                                list.repaint();

                            } else {
                                //Show the group request status for the student
                                GroupInfoRenderer(groupAtIndex, text);
                            }
                            if (acceptRect.contains(pointWithinCell)
                                    && !((NotificationUtil.seeIfAccepted(
                                            groupAtIndex.getCourseId(),
                                            groupAtIndex.getMyStudentId())))) {

                                // Accept the request via the Notification dbIf
                                NotificationUtil.accept(groupAtIndex.getCourseId(),
                                        groupAtIndex.getMyStudentId());

                                list.repaint(); //Only you have accepted, update list

                                //Create a new group with the request details, if all accepted
                                //get reciepients in notification util object
                                Vector<Notification> recipients
                                        = NotificationUtil.getNewGroupNotifications(groupAtIndex.getCourseId());
                                boolean allAccepted = true;

                                // For each potential member, check if accepted
                                for (Notification recipient : recipients) {
                                    if (!(recipient.isAccepted())) {
                                        allAccepted = false;
                                    }
                                }

                                if (allAccepted) {
                                    // Create the group via the GroupUtil dbIf
                                    GroupUtil.createNewGroup(groupAtIndex);
                                    int groupId = GroupUtil.NewGroupId(groupAtIndex);
                                    // Add members to the group
                                    for (Notification recipient : recipients) {
                                        GroupStudent group = new GroupStudent();
                                        group.setGroupId(groupId);
                                        group.setStudentId(recipient.getStudentId());
                                        GroupDbInteraction.insertInGroup(group);
                                        NotificationUtil.removeRequest(
                                                recipient.getCourseId(),
                                                0,
                                                recipient.getStudentId(), true);

                                    }
                                    model.remove(index);
                                    list.repaint();
                                    // Resets the passed frame to update all
                                    // changes.
                                    aThis.reset();
                                }

                            }

                        }   // END NEW GROUP REQUESTS
                    }
                }
                super.mouseClicked(aClick); //To change body of generated methods, choose Tools | Templates.
            }

            // Display Infomation of each GroupLabel (JLabel) when clicked in
            // the passed JTextArea
            private void GroupInfoRenderer(
                    GroupDefinition group,
                    JTextArea infoArea) {

                infoArea.setText(null);
                infoArea.setLineWrap(true);
                infoArea.setWrapStyleWord(true);

                // It is a exisiting group
                if (group.isGroup()) {
                    infoArea.append("GROUP INFORMATION:" + "\n\n");
                    infoArea.append("Group Name:\t" + group + "\n");
                    infoArea.append("Course Name:\t" + group.getCourseName() + "\n\n");
                    infoArea.append("Group Members:" + "\n\n");

                    Vector<Student> members = GroupUtil.getMyGroupMembers(
                            group.getGroupId(),
                            group.getMyStudentId());

                    for (Student member : members) {
                        infoArea.append(member.getFirstName()
                                + "\t: " + member.getEmail() + "\n");
                    }
                } // It is a request
                else if (!(group.isGroup())) {
                    infoArea.append("REQUEST INFORMATION:" + "\n\n");
                    infoArea.append("Group Name:\t" + group + "\n");
                    infoArea.append("Course Name:\t"
                            + group.getCourseName()
                            + "\n\n");
                    infoArea.append("REQUEST MESSAGE: \n\n"
                            + group.getMessage()
                            + "\n\n");
                    if (((NotificationUtil.seeIfAccepted(
                            group.getCourseId(),
                            group.getMyStudentId())))) {

                        infoArea.append("REQUEST STATUS: You have accepted"
                                + " the request and it is pending on"
                                + " acceptance of all recipients.");

                    } else {
                        infoArea.append("REQUEST STATUS: Please accept or"
                                + " deny the request.");
                    }

                }
                infoArea.setEditable(false);
                infoArea.setCaretPosition(TOP);
            }

            private void NotificationListRenderer(
                    Vector<Notification> notifications,
                    JList notifList) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                //DefaultListModel listModel = new DefaultListModel();
                //for each notification add it to the list
                /*for (Notification notification : notifications) {
                    listModel.addElement(notification.getMessage());
                }

                notifList.setModel(listModel);*/
                notifList.setListData(notifications);
                notifList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                notifList.setSelectedIndex(0);
                //make the list selectable

            }

        });

    }

    /**
     * Returns a group renderer for each cell of the list.
     *
     * @param list list to process
     * @param value cell value (GroupListModel object in our case)
     * @param index cell index
     * @param isSelected whether cell is selected or not
     * @param cellHasFocus whether cell has focus or not
     * @return custom renderer for each cell of the list
     */
    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        renderer.setSelected(isSelected);
        renderer.setInfo((GroupDefinition) value);
        return renderer;
    }

    // A Group Label that has custome decorations, and selected actions
    private static class GroupLabel extends JLabel {

        public GroupLabel() {
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 70));
        }

        private static final Color selectionColor = Color.LIGHT_GRAY;
        private GroupDefinition groupLabel;
        private boolean isSelected;

        private void setSelected(boolean selected) {
            this.isSelected = selected;
            setForeground(selected ? Color.WHITE : Color.BLACK);
            //Populate Info

        }

        private void setInfo(GroupDefinition groupLabel) {
            this.groupLabel = groupLabel;
            setText(groupLabel.getCourseShort());
        }

        @Override
        protected void paintComponent(Graphics graphic) {
            Graphics2D graphics = (Graphics2D) graphic;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            if (isSelected) {
                Area area = new Area(new RoundRectangle2D.Double(
                        40,
                        3,
                        getWidth() - 43,
                        30,
                        5,
                        5));
                graphics.setPaint(selectionColor);
                graphics.fill(area);

            }

            //should get icon from groupmodel
            if (groupLabel.isGroup()) {
                graphics.drawImage(
                        groupIcon.getImage(),
                        5 + 13 - groupIcon.getIconWidth() / 2,
                        5 + 13 - groupIcon.getIconHeight() / 2,
                        null);
                if (GroupUtil.testLock(groupLabel.getGroupId())) {
                    graphics.drawImage(
                            lockIcon.getImage(),
                            getWidth() - 9 - 25 - lockIcon.getIconWidth() / 2,
                            getHeight() / 2 - lockIcon.getIconHeight() / 2,
                            null);
                } else {
                    graphics.drawImage(
                            unlockIcon.getImage(),
                            getWidth() - 9 - 45 - unlockIcon.getIconWidth() / 2,
                            getHeight() / 2 - unlockIcon.getIconHeight() / 2,
                            null);
                }

            } else {
                graphics.drawImage(
                        requestIcon.getImage(),
                        5 + 13 - requestIcon.getIconWidth() / 2,
                        5 + 13 - requestIcon.getIconHeight() / 2,
                        null);
                //if not accepted
                if ((NotificationUtil.seeIfAccepted(
                        groupLabel.getCourseId(),
                        groupLabel.getMyStudentId()))) {
                    graphics.drawImage(
                            acceptIcon.getImage(),
                            getWidth() - 9 - 45 - acceptIcon.getIconWidth() / 2,
                            getHeight() / 2 - acceptIcon.getIconHeight() / 2,
                            null);
                } else {
                    //nothing
                }
            }

            //show relevent graphics when clicked, otherwise show the notification count
            //common
            if (isSelected) {
                // draw relevent icons for group
                graphics.drawImage(
                        deleteIcon.getImage(),
                        getWidth() - 9 - 5 - deleteIcon.getIconWidth() / 2,
                        getHeight() / 2 - deleteIcon.getIconHeight() / 2,
                        null);

                if (!(groupLabel.isGroup()) && !((NotificationUtil.seeIfAccepted(
                        groupLabel.getCourseId(),
                        groupLabel.getMyStudentId())))) {

                    graphics.drawImage(
                            checkedIcon.getImage(),
                            getWidth() - 9 - 25 - checkedIcon.getIconWidth() / 2,
                            getHeight() / 2 - checkedIcon.getIconHeight() / 2,
                            null);

                }
            } //if locked draw lock icon
            //NOTIFICATION AREA
            //we can continually check when clicked here for now
            else if (groupLabel.getNotifications() > 0) {
                graphics.setPaint(Color.red);
                graphics.fill(new Ellipse2D.Double(
                        getWidth() - 18 - 150,
                        getHeight() / 2 - 15,
                        18,
                        18));
                final String text = "" + groupLabel.getNotifications();
                final Font oldFont = graphics.getFont();
                graphics.setFont(oldFont.deriveFont(oldFont.getSize() - 1f));
                final FontMetrics fm = graphics.getFontMetrics();
                graphics.setPaint(Color.WHITE);
                graphics.drawString(
                        text,
                        getWidth() - 9 - 150 - fm.stringWidth(text) / 2,
                        getHeight() / 2 - 5 + (fm.getAscent() - fm.getLeading() - fm.getDescent()) / 2);
                graphics.setFont(oldFont);
            }
            super.paintComponent(graphic);
        }

        @Override
        public Dimension getPreferredSize() {
            final Dimension prefSize = super.getPreferredSize();
            prefSize.height = 36;
            return prefSize;
        }

    }
}
