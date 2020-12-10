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
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class Reviews extends JFrame {

    private JComboBox<String> jComboBox1 = new JComboBox();
    private JPanel mainPanel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel textTf1 = new JLabel();
    private JLabel textTf2 = new JLabel();
    private JTextField tf1 = new JTextField(20);
    private JButton submitReview = new JButton("            Submit Review             ");
    private JButton back = new JButton("Back");
    private JButton logout = new JButton("Logout");
    private String selected, title,  barberName;
    GridLayout gLayout = new GridLayout(2, 1);

    Controller controller;
    Model model;
    User user;

    public Reviews(Controller controller) {
        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "Reviews";
        this.model = new Model();
      
        textTf1.setText("Please enter the barber name");
        textTf2.setText("Write down your review.");
        jComboBox1.addItemListener(listener);
        jComboBox1.setModel(new DefaultComboBoxModel<>(model.barberReview()));
        // We encapsulated the building process of the window
        setAttributes();
        components();
        // listeners
        submitReview.addActionListener(controller);
        logout.addActionListener(controller);
        back.setActionCommand("Review Back");
        logout.setActionCommand("Reviews Logout");
        back.addActionListener(controller);
        //adds the elements to the panel
        mainPanel.add(textTf1);
        mainPanel.add(jComboBox1);
        panel2.add(textTf2);
        panel2.add(tf1);
        tf1.setInputVerifier(new PassVerifier());
        panel2.add(submitReview);
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
        this.submitReview.setVisible(false);
        this.textTf2.setVisible(false);
        this.tf1.setVisible(false);
        

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
    //getters
    public String getBarberName() {

        return this.barberName;
    }
    public String getReview() {
        return tf1.getText();
    }


    ItemListener listener = (e) -> {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == jComboBox1) {
                if (jComboBox1.getSelectedIndex() != 0) {
                    barberName = jComboBox1.getSelectedItem().toString();
                    //gets the datetime that is shown with the name and stores only the date and time into the variable

                    this.submitReview.setVisible(true);
                    this.tf1.setVisible(true);
                    this.textTf2.setVisible(true);
                    this.tf1.requestFocusInWindow();
                    
                }
            }

        }

    };

    class PassVerifier extends InputVerifier {

        public boolean verify(JComponent input) {
            //it will make sure the fields get input from the user
            final JTextComponent source = (JTextComponent) input;
            String name = input.getName();
            String s = "";

            s = tf1.getText();
            boolean valid = s.isBlank();
            if (valid) {

                JOptionPane.showMessageDialog(source, "Field cannot be empty",
                        "Input error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                return true;
            }
        }

    }
}
