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
import javax.swing.JTextField;

/**
 *
 * @author claudiodionisio
 */
public class StartPage extends JFrame  {
    
   private JPanel mainPanel = new JPanel();
   private JLabel textTf1 = new JLabel();
   private JLabel textTf2 = new JLabel();
   private JLabel textTf3 = new JLabel();
   private JTextField tf1 = new JTextField(20);
   private JTextField tf2 = new JTextField(20);
   private JButton signIN = new JButton("SIGN IN");
   private JButton signUP = new JButton("SIGN UP");
   private String title;
   private BufferedImage myPicture = ImageIO.read(new File("logo.png"));
   //image source https://www.google.com/url?sa=i&url=https%3A%2F%2Fmustachebarbershop.com%2F&psig=AOvVaw1bd0UQjY6FjMOACtc6KTkN&ust=1606844315184000&source=images&cd=vfe&ved=0CAMQjB1qFwoTCNiQ56Toqu0CFQAAAAAdAAAAABAD
   private JLabel logo = new JLabel(new ImageIcon(myPicture));
   
   Controller controller;
   
   
    public StartPage(Controller controller) throws IOException{
        
        this.controller = controller;
        // sets the default title to start page which will be the first page to run
        this.title = "Start Page";
       
        // We encapsulated the building process of the window
        setAttributes();
        components();
        //
        textTf1.setText("User Name");
        textTf2.setText("Password");
         // listeners
        signIN.addActionListener(controller);
        signUP.addActionListener(controller);
         //adds the elements to the panel
        mainPanel.add(logo);
        mainPanel.add(textTf1);
        mainPanel.add(tf1);
        mainPanel.add(textTf2);
        mainPanel.add(tf2);
        mainPanel.add(signIN);
        mainPanel.add(signUP);
        mainPanel.add(textTf3);       
        
        validation();
    }
   
    
     // Setting the attributes
    private void setAttributes(){
        //sets the size of the frame
        this.setSize(300,400);
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
    public String getName(){
   
        return tf1.getText();
    }
    public String getPassword(){
       
        return tf2.getText();
    }
    
    public void setCredential(String credential){
       
        textTf3.setText(credential);
    }
    public String getSignIN(){
        
        return signIN.getText();
    }
    public String getSignUP(){
        
        return signUP.getText();
    }

}
