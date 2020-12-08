/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author claudiodionisio
 */
public class UserPage extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JButton b1 = new JButton();
    private JButton b2 = new JButton();
    private JButton b3 = new JButton();
    private String title;
    private JButton logout = new JButton("Logout");
    private boolean booked, cancelled = false;
    GridLayout gLayout = new GridLayout(4, 1);

    Controller controller;

    public UserPage(Controller controller) {

        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = controller.getUserType().toUpperCase();
        this.booked = controller.getBooked();
        this.cancelled = controller.getCancelled();
        if ("CUSTOMER".equals(title)) {
            this.b1.setText("Search Barber/Location");
            this.b2.setText("       View Booking        ");
            this.b3.setText("            Reviews            ");

        } else {
            this.b1.setText("    View Appointments    ");
            this.b2.setText("       Set Availability        ");
            this.b3.setText("            Set Status           ");
        }
        if (booked) {

            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Slot successfully Booked!");
        } else if (cancelled) {
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Slot successfully Cancelled!");
        }
        // We encapsulated the building process of the window
        setAttributes();
        components();

        // listeners
        b2.addActionListener(controller);
        b1.addActionListener(controller);
        b3.addActionListener(controller);
        logout.addActionListener(controller);

        //adds the elements to the panel
        mainPanel.add(b1);
        panel2.add(b2);
        panel3.add(b3);
        panel4.add(logout);

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

        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(gLayout);

    }
    // Organising the components

    private void components() {

        this.add(mainPanel);
        this.add(mainPanel, BorderLayout.PAGE_START);
        this.add(panel2);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3);
        this.add(panel3, BorderLayout.CENTER);
        this.add(panel4);
        this.add(panel4, BorderLayout.PAGE_END);
    }
    // Validation and repainting

    private void validation() {
        this.validate();
        this.repaint();
        //makes sure the jframe quits when closing window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class OptionPane {

        JFrame f;

        OptionPane() {
            f = new JFrame();
            JOptionPane.showMessageDialog(f, "Slot successfully Booked!");
        }
    }

}
