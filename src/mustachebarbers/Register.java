/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author claudiodionisio
 */
public class Register extends JFrame  {
    
   private JPanel mainPanel = new JPanel();
   private JButton sp = new JButton("I am a Service Provider");
   private JButton customer = new JButton("I am a Customer");
   private String title;
 
   Controller controller;
   
    public Register (Controller controller) {
        
        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "Register";
        controller.back.addActionListener(controller);
       
        // We encapsulated the building process of the window
        setAttributes();
        components();
       
       // listeners
        customer.addActionListener(controller);
        sp.addActionListener(controller);
        controller.back.addActionListener(controller);
       //adds the elements to the panel
        mainPanel.add(customer);
        mainPanel.add(sp);
        mainPanel.add(controller.back);
    
        validation();
    }

    
     // Setting the attributes
    private void setAttributes(){
        //sets the size of the frame
        this.setSize(250,400);
        //sets title of the frame
        this.setTitle(title);
        //makes the frame be openned in the center of the screen
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
     
    }
     // Organising the components
    private void components(){
       
        this.add(mainPanel);
        this.add(mainPanel, BorderLayout.CENTER);
        
        
    }
     // Validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
        //makes sure the jframe quits when closing window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    // Getters and setters 
    public String getCustomer(){
        
        return customer.getText();
    }
    public String getSp(){
        
        return sp.getText();
    }

}

  