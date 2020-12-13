/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author claudiodionisio
 * student number:2019235
 */
public class WelcomePage extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JLabel textTf1 = new JLabel();
    private JButton ChangeImg = new JButton("Change Image");
    private JButton next = new JButton("Next");
    private String title;
    private BufferedImage myPicture = ImageIO.read(new File("img/userIcon.jpg"));
    private JLabel icon = new JLabel(new ImageIcon(myPicture));
    private JButton logout = new JButton("Logout");

    Controller controller;

    public WelcomePage(Controller controller) throws IOException {

        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "Welcome Page";

        // We encapsulated the building process of the window
        setAttributes();
        components();
        // listeners
        ChangeImg.addActionListener(controller);
        logout.addActionListener(controller);
        logout.setActionCommand("Welcome Logout");
        next.addActionListener(controller);
        //adds the elements to the panel
        mainPanel.add(icon);
//        mainPanel.add(ChangeImg);
        mainPanel.add(textTf1);
        mainPanel.add(next);
        mainPanel.add(logout);

        validation();
    }

    // Setting the attributes
    private void setAttributes() {
        //sets the size of the frame
        this.setSize(250, 400);
        //sets title of the frame
        this.setTitle(title);
        //makes the frame be openned in the center of the screen
        this.setLocationRelativeTo(null);
        this.logout.setBorderPainted(false);
        this.setResizable(false);
        this.setVisible(true);

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

    //setter
    public void setUserName(String user) {
        // sets the text to the user name
        textTf1.setText("        Welcome " + user.toUpperCase() + "!        ");
    }

}
