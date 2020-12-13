/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
 * student number:2019235
 */
public class SetStatus extends JFrame {
     private JComboBox<String> jComboBox1 = new JComboBox();
    private JPanel mainPanel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel textTf1 = new JLabel();
    private JLabel textTf2 = new JLabel();
    private JButton Completed = new JButton("Completed");
    private JButton noShow = new JButton("No Show");
    private JButton back = new JButton("Back");
    private JButton logout = new JButton("Logout");
    private String selected, title, dateTime,userName;
    GridLayout gLayout = new GridLayout(2, 1);

    Controller controller;
    Model model;
    User user;

    public SetStatus(Controller controller) {
        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "Set Status";
        this.model = new Model();
        this.userName = controller.getUserName();
       //set the text on to the panels
        textTf1.setText("These are your bookings select one");
        textTf2.setText("If the textbox is empty you have no bookings.");
        jComboBox1.addItemListener(listener);
        System.out.println(userName);
        jComboBox1.setModel(new DefaultComboBoxModel<>(model.searchAppointments(userName)));
        // We encapsulated the building process of the window
        setAttributes();
        components();
        // listeners
        Completed.addActionListener(controller);
        noShow.addActionListener(controller);
        logout.addActionListener(controller);
        logout.setActionCommand("SetStatus Logout");
        back.setActionCommand("SetStatus Back");
        back.addActionListener(controller);
        //adds the elements to the panel
        mainPanel.add(textTf1);
        mainPanel.add(jComboBox1);
        panel2.add(Completed);
        panel2.add(noShow);
        panel2.add(textTf2);
        panel2.add(back);
        panel2.add(logout);

        validation();
    }

    // Setting the attributes
    private void setAttributes() {
        //sets the size of the frame
        this.setSize(350, 450);
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
        this.Completed.setVisible(false);
        this.noShow.setVisible(false);

    }
    // Organising the components

    private void components() {

        this.add(mainPanel);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.PAGE_END);
        this.setLayout(gLayout);
        

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
                    //gets the datetime that is shown with the name and stores only the date and time into the variable
                    this.dateTime = selected.substring(selected.indexOf("On:")+4);
                    System.out.println(dateTime);
                    this.Completed.setVisible(true);
                    this.noShow.setVisible(true);

                }
            }

        }

    };

}


   
