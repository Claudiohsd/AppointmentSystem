/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author claudiodionisio
 */
public class Test extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JLabel textTf1 = new JLabel();
    private JLabel textTf2 = new JLabel();
    private JLabel textTf3 = new JLabel();
    private JButton search = new JButton("            Search             ");
    private JButton fSlot = new JButton("            FindSlots           ");
    private String title;
    private JButton back = new JButton("Back");
    private JButton logout = new JButton("Logout");
    private JTextField tf1 = new JTextField(14);
    private JTextField tf2 = new JTextField(30);
    private JTable table;
    private JScrollPane scrollPane;

    Controller controller;
    Model model;

    public Test(Controller controller) {

        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "Search";
        this.model = new Model();
        //if it is the request comes from the user page, it runs this,
     

            String[] columnNames = {"Number ", "Location"};
  
     

            scrollPane.setPreferredSize(new Dimension(200, 100));
            textTf1.setText("The following locations were found");
            textTf2.setText("Please enter the location number. ");
            textTf3.setText("The following locations were found");
            tf1.setInputVerifier(new PassVerifier());
            tf1.setName("tf1");

            // We encapsulated the building process of the window
            setAttributes();
            components();
            // listeners
            search.addActionListener(controller);
            logout.addActionListener(controller);
            back.setActionCommand("Search Back");
            back.addActionListener(controller);
            //adds the elements to the panel
            mainPanel.add(textTf1);
            mainPanel.add(scrollPane);
            mainPanel.add(textTf2);
            mainPanel.add(tf1);
            mainPanel.add(search);

            mainPanel.add(back);
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
        this.back.setBorderPainted(false);
        this.logout.setFocusable(false);
        this.back.setFocusable(false);

        this.setResizable(true);
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
               
                default:
                    System.out.println(name);
            }

            boolean valid = s.isBlank();

            if (name == tf1.getName() && !tf1.getText().matches("^\\d+$")) {
                JOptionPane.showMessageDialog(source, "field cannot be empty and it should be a number.",
                        "Input error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                return true;
            }
        }

    }

    //getters
    public String getUserInput() {
        
        String input = tf1.getText();
        if (input.isBlank()){
        return "no imput";}
        
             return input;
        
    }

}
