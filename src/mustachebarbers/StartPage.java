/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author claudiodionisio
 */
public class StartPage extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel textTf1 = new JLabel();
    private JLabel textTf2 = new JLabel();
    private JLabel textTf3 = new JLabel();
    private JTextField tf1 = new JTextField(20);
    private JTextField tf2 = new JTextField(20);
    private JButton signIN = new JButton("SIGN IN");
    private JButton signUP = new JButton("SIGN UP");
    private String title;
    private BufferedImage myPicture = ImageIO.read(new File("img/logo.png"));
    //image source https://www.google.com/url?sa=i&url=https%3A%2F%2Fmustachebarbershop.com%2F&psig=AOvVaw1bd0UQjY6FjMOACtc6KTkN&ust=1606844315184000&source=images&cd=vfe&ved=0CAMQjB1qFwoTCNiQ56Toqu0CFQAAAAAdAAAAABAD
    private JLabel logo = new JLabel(new ImageIcon(myPicture));
    GridLayout gLayout = new GridLayout(2, 1);

    Controller controller;

    public StartPage(Controller controller) throws IOException {

        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "Start Page";

        // We encapsulated the building process of the window
        setAttributes();
        components();
        //
        textTf1.setText("E_mail");
        tf1.setName("tf1");
        //tf1.setInputVerifier(new PassVerifier());
        textTf2.setText("Password");
        tf2.setName("tf2");
        //tf2.setInputVerifier(new PassVerifier());
        // listeners
        signIN.addActionListener(controller);
        signUP.addActionListener(controller);
        signUP.setFocusable(false);
        //adds the elements to the panel
        mainPanel.add(logo);
        panel2.add(textTf1);
        panel2.add(tf1);
        panel2.add(textTf2);
        panel2.add(tf2);
        panel2.add(signIN);
        panel2.add(signUP);
        panel2.add(textTf3);
        tf1.grabFocus();

        validation();
    }

    private void setAttributes() {
        //sets the size of the frame
        this.setSize(350, 450);
        panel2.setSize(WIDTH, 50);
        //sets title of the frame
        this.setTitle(title);
        //makes the frame be openned in the center of the screen
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(gLayout);
    }
    // Organising the components

    private void components() {

        this.add(mainPanel);
        this.add(mainPanel, BorderLayout.PAGE_START);
        this.add(panel2);
        this.add(panel2, BorderLayout.PAGE_END);
       
    }
    // Validation and repainting

    private void validation() {
        this.validate();
        this.repaint();
        //makes sure the jframe quits when closing window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class PassVerifier extends InputVerifier {

        public boolean verify(JComponent input) {
            //it will make sure the fields get input from the user
            final JTextComponent source = (JTextComponent) input;
            String name = input.getName();
            String s = "";

            //switch to determine which field has the focus
            switch (name) {
                case "tf1": {
                    s = tf1.getText();
                }
                break;
                case "tf2": {
                    s = tf2.getText();
                }
                default:
                    System.out.println(name);
            }

            boolean valid = s.isBlank();

            if (name == tf1.getName()) {
                String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                if (valid = tf1.getText().matches(regex)) {
                    valid = false;

                } else {
                    JOptionPane.showMessageDialog(source, "make sure you enter a valid e_mail.",
                            "Input error", JOptionPane.ERROR_MESSAGE);

                    return false;
                }
            }
            if (valid) {
                JOptionPane.showMessageDialog(source, "field cannot be empty.",
                        "Input error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                return true;
            }
        }

    }

    // Getters and setters 
    public void setCredential(String credential) {

        textTf3.setText(credential);
    }

    public String getPassword() {
        return tf2.getText();
    }

    public String getEmail() {
        return tf1.getText();
    }

    //clears the text fields
    public void clearTextfield() {
        tf1.setText("");
        tf2.setText("");
    }

}
