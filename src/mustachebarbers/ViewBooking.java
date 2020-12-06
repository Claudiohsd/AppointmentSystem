/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author claudiodionisio
 */
public class ViewBooking extends JFrame {

    private JComboBox<String> jComboBox1 = new JComboBox();
    private JPanel mainPanel = new JPanel();
    private JLabel textTf1 = new JLabel();
    private JButton cancel = new JButton("            Cancel             ");
    private JButton back = new JButton("Back");
    private JButton logout = new JButton("Logout");
    private String selected, title, dateTime,userName;

    Controller controller;
    Model model;
    User user;

    public ViewBooking(Controller controller) {
        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "View Bookings";
        this.model = new Model();
        this.userName = controller.getUserName();
        //if it is the request comes from the user page, it runs this,

        textTf1.setText("This are your bookings select one to cancel");
        jComboBox1.addItemListener(listener);
        System.out.println(userName);
        jComboBox1.setModel(new DefaultComboBoxModel<>(model.searchBooking(userName)));
        // We encapsulated the building process of the window
        setAttributes();
        components();
        // listeners
        cancel.addActionListener(controller);
        logout.addActionListener(controller);
        back.setActionCommand("Booking Back");
        back.addActionListener(controller);
        //adds the elements to the panel
        mainPanel.add(textTf1);
        mainPanel.add(jComboBox1);
        mainPanel.add(cancel);
        mainPanel.add(back);
        mainPanel.add(logout);

        validation();
    }

    // Setting the attributes
    private void setAttributes() {
        //sets the size of the frame
        this.setSize(300, 300);
        //sets title of the frame
        this.setTitle(title);
        //makes the frame be openned in the center of the screen
        this.setLocationRelativeTo(null);
        this.logout.setBorderPainted(false);
        this.back.setBorderPainted(false);
        this.logout.setFocusable(false);
        this.back.setFocusable(false);

        this.setResizable(false);
        this.setVisible(true);
        this.cancel.setVisible(false);

    }
    // Organising the components

    private void components() {

        this.add(mainPanel);
        this.add(mainPanel, BorderLayout.CENTER);

    }
    // Validation and repainting

    private void validation() {
        this.validate();
        this.repaint();
        //makes sure the jframe quits when closing window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getdateTime() {

        return this.dateTime;
    }
    public void setUserName(String us) {

       this.userName = us;
    }
    ItemListener listener = (e) -> {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == jComboBox1) {
                if (jComboBox1.getSelectedIndex() != 0) {
                    selected = jComboBox1.getSelectedItem().toString();
                    dateTime = selected;
                    this.cancel.setVisible(true);

                }
            }

        }

    };

}
