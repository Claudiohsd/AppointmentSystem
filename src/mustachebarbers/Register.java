/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.BorderLayout;
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
 * @author claudiodionisio student number:2019235
 */
public class Register extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JLabel message = new JLabel();
    private JLabel textTf1 = new JLabel();
    private JLabel textTf2 = new JLabel();
    private JLabel textTf3 = new JLabel();
    private JLabel textTf4 = new JLabel();
    private JLabel textTf5 = new JLabel();
    private JLabel textTf6 = new JLabel();
    private JLabel textTf7 = new JLabel();
    private JLabel textTf8 = new JLabel();
    private JTextField tf1 = new JTextField(30);
    private JTextField tf2 = new JTextField(29);
    private JTextField tf3 = new JTextField(30);
    private JTextField tf4 = new JTextField(29);
    private JTextField tf5 = new JTextField(25);
    private JTextField tf6 = new JTextField(30);
    private JTextField tf8 = new JTextField(30);
    private JButton signUP = new JButton("SIGN UP");
    private JButton back = new JButton("Back");
    private String title;

    Controller controller;

    public Register(Controller controller) {

        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = controller.getUserType().toUpperCase() + " REGISTER";

        // We encapsulated the building process of the window
        setAttributes();
        components();
        //set the names of the text fields and also the input verifiers to make sure the fields are
        //filled before jumping to the next page.
        //set the input verifiers to make sure the fields are filled up
        message.setText("                                                  " + controller.getUserType().toUpperCase() + "                                               ");
        //runs this if the user is a barber
        if (controller.getUserType() == "barber") {
            textTf1.setText("Name");
            tf1.setName("tf1");
            tf1.setInputVerifier(new PassVerifier());
            textTf2.setText("Location");
            tf2.setName("tf2");
            tf2.setInputVerifier(new PassVerifier());
            textTf3.setText("Phone");
            tf3.setName("tf3");
            tf3.setInputVerifier(new PassVerifier());
            textTf4.setText("Password");
            tf4.setName("tf4");
            tf4.setInputVerifier(new PassVerifier());
            textTf5.setText("Re-enter Password");
            tf5.setName("tf5");
            tf5.setInputVerifier(new PassVerifier());
            textTf6.setText("Surname");
            tf6.setName("tf6");
            tf6.setInputVerifier(new PassVerifier());
            textTf8.setText("E_mail");
            tf8.setName("tf8");
            tf8.setInputVerifier(new PassVerifier());
            // listeners
            signUP.setActionCommand("Barber SIGN UP");
            signUP.addActionListener(controller);
            back.setActionCommand("Register Back");
            back.addActionListener(controller);
            //allows the back button to be pressed without triggering the input verifier
            back.setFocusable(false);

            //adds the elements to the panel
            mainPanel.add(message);
            mainPanel.add(textTf1);
            mainPanel.add(tf1);
            mainPanel.add(textTf6);
            mainPanel.add(tf6);
            mainPanel.add(textTf8);
            mainPanel.add(tf8);
            mainPanel.add(textTf2);
            mainPanel.add(tf2);
            mainPanel.add(textTf3);
            mainPanel.add(tf3);
            mainPanel.add(textTf4);
            mainPanel.add(tf4);
            mainPanel.add(textTf5);
            mainPanel.add(tf5);
            mainPanel.add(textTf7);
            mainPanel.add(signUP);
            mainPanel.add(back);
            mainPanel.add(textTf7);
            //runs this if the user is a customer
        } else {
            textTf1.setText("Name");
            tf1.setName("tf1");
            tf1.setInputVerifier(new PassVerifier());
            textTf3.setText("Phone");
            tf3.setName("tf3");
            tf3.setInputVerifier(new PassVerifier());
            textTf4.setText("Password");
            tf4.setName("tf4");
            tf4.setInputVerifier(new PassVerifier());
            textTf5.setText("Re-enter Password");
            tf5.setName("tf5");
            tf5.setInputVerifier(new PassVerifier());
            textTf6.setText("Surname");
            tf6.setName("tf6");
            tf6.setInputVerifier(new PassVerifier());
            textTf8.setText("E_mail");
            tf8.setName("tf8");
            tf8.setInputVerifier(new PassVerifier());
            // listeners
            signUP.setActionCommand("Customer SIGN UP");
            signUP.addActionListener(controller);
            back.setActionCommand("Register Back");
            back.addActionListener(controller);
            //allows the back button to be pressed without triggering the input verifier
            back.setFocusable(false);

            //adds the elements to the panel
            mainPanel.add(message);
            mainPanel.add(textTf1);
            mainPanel.add(tf1);
            mainPanel.add(textTf6);
            mainPanel.add(tf6);
            mainPanel.add(textTf8);
            mainPanel.add(tf8);
            mainPanel.add(textTf3);
            mainPanel.add(tf3);
            mainPanel.add(textTf4);
            mainPanel.add(tf4);
            mainPanel.add(textTf5);
            mainPanel.add(tf5);
            mainPanel.add(textTf7);
            mainPanel.add(signUP);
            mainPanel.add(back);
            mainPanel.add(textTf7);
        }
        validation();
    }
//makes sure all the fields are filled

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
                    s = tf1.getText();
                }
                break;
                case "tf3": {
                    s = tf3.getText();
                }
                break;
                case "tf4": {
                    s = tf4.getText();
                }
                break;
                case "tf5": {
                    s = tf5.getText();
                }
                break;
                case "tf6": {
                    s = tf6.getText();
                }
                break;
                default:
                    System.out.println(name);
            }

            boolean valid = s.isBlank();
            // makes sure the user enters a number
            if (name == tf3.getName() && !tf3.getText().matches("^\\d+$")) {
                JOptionPane.showMessageDialog(source, "field cannot be empty and it should be a number.",
                        "Input error", JOptionPane.ERROR_MESSAGE);
                return false;
                //makes sure the user enters an email
            } else if (name == tf8.getName()) {
                String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                if (valid = tf8.getText().matches(regex)) {
                    valid = false;

                } else {
                    JOptionPane.showMessageDialog(source, "make sure you enter a valid e_mail.",
                            "Input error", JOptionPane.ERROR_MESSAGE);

                    return false;
                }
            }
            //makes sure the field is not empty
            if (valid) {
                JOptionPane.showMessageDialog(source, "field cannot be empty.",
                        "Input error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                return true;
            }
        }

    }

    // Setting the attributes
    private void setAttributes() {
        //sets the size of the frame
        this.setSize(450, 350);
        //sets title of the frame
        this.setTitle(title);
        //makes the frame be openned in the center of the screen
        this.setLocationRelativeTo(null);
        this.back.setBorderPainted(false);
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

    //getters and setters 
    public String getUserName() {
        return tf1.getText();
    }

    public String getSurname() {
        return tf6.getText();
    }

    public String getPassword() {
        return tf4.getText();
    }

    public int getPhone() {
        int phone = Integer.parseInt(tf3.getText());
        return phone;
    }

    public String getEmail() {
        return tf8.getText();
    }

    public String getUserLocation() {
        return tf2.getText();
    }

    public String getUsertype() {
        if (controller.getUserType() == "barber") {
            return "barber";
        } else {
            return "customer";
        }
    }

    public void setMessage(String message) {

        textTf7.setText(message);
    }

}
