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
public class Search extends JFrame {

    private JComboBox<String> jComboBox1 = new JComboBox();
    private JComboBox<String> jComboBox2 = new JComboBox();
    private JComboBox<String> jComboBox3 = new JComboBox();
    private JPanel mainPanel = new JPanel();
    private JLabel textTf1 = new JLabel();
    private JLabel textTf2 = new JLabel();
    private JLabel textTf3 = new JLabel();
    private JLabel textTf4 = new JLabel();
    private JButton book = new JButton("            Book             ");
    private JButton back = new JButton("Back");
    private JButton logout = new JButton("Logout");
    private String selected, title;

    Controller controller;
    Model model;

    public Search(Controller controller) {
        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "Search";
        this.model = new Model();
        //if it is the request comes from the user page, it runs this,

        textTf1.setText("Select a location, a barber and a slot");
        textTf2.setText("Location");
        textTf3.setText("Barber");
        jComboBox1.addItemListener(listener);
        jComboBox1.addItemListener(listener);
        jComboBox1.setModel(new DefaultComboBoxModel<>(model.searchLocation()));
        jComboBox2.setModel(new DefaultComboBoxModel<>(model.searchBarber(selected)));
        jComboBox3.setModel(new DefaultComboBoxModel<>(model.searchSlots(selected)));

        jComboBox2.addItemListener(listener);
        jComboBox3.addItemListener(listener);

        // We encapsulated the building process of the window
        setAttributes();
        components();
        // listeners
        book.addActionListener(controller);
        logout.addActionListener(controller);
        back.setActionCommand("Search Back");
        back.addActionListener(controller);
        //adds the elements to the panel
        mainPanel.add(textTf1);
        mainPanel.add(textTf2);
        mainPanel.add(jComboBox1);
        mainPanel.add(textTf3);
        mainPanel.add(jComboBox2);
        mainPanel.add(textTf4);
        mainPanel.add(jComboBox3);
        mainPanel.add(book);
        mainPanel.add(back);
        mainPanel.add(logout);

        validation();
    }

    public void populateJCB2() {
        jComboBox2.setModel(new DefaultComboBoxModel<>(model.searchBarber(selected)));
    }

    public void populateJCB3() {
        System.out.println(model.searchSlots(selected).toString());
        jComboBox3.setModel(new DefaultComboBoxModel<>(model.searchSlots(selected)));
    }

    // Setting the attributes
    private void setAttributes() {
        //sets the size of the frame
        this.setSize(200, 300);
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

    ItemListener listener = (e) -> {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == jComboBox1) {
                if (jComboBox1.getSelectedIndex() != 0) {
                    selected = jComboBox1.getSelectedItem().toString();
                    jComboBox2.removeAllItems();
                    populateJCB2();

                }
            } else if (e.getSource() == jComboBox2) {
                if (jComboBox2.getSelectedIndex() != 0) {
                    selected = jComboBox2.getSelectedItem().toString();
                    jComboBox3.removeAllItems();
                    populateJCB3();

                }

            } else if (e.getSource() == jComboBox3) {
                if (jComboBox3.getSelectedIndex() != 0) {
                    selected = jComboBox3.getSelectedItem().toString();
                   
                }

            }
        }
    };

}
