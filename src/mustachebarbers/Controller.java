/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;

/**
 *
 * @author claudiodionisio
 */
public class Controller implements ActionListener{
    
    private StartPage start;
    private Model model;
    private Register register;
    private String userName, password, button;
    public JButton back = new JButton("back");
    
    
    

    public Controller() throws IOException {
        this.back.setBorderPainted(false);
        this.start = new StartPage(this);
        this.model = new Model();
       
       
    }
    private void credentialCheck(){
        
    User user = new User(userName, password);
                
        boolean credential = model.login(user);
       
        
        String resultMessage = "Try again with valid credentials";
        
        
        if(credential){
            resultMessage = "Welcome " + user.getUserName().toUpperCase() + " !";
        
        }
        
        start.setCredential(resultMessage);
        }
    private void register(){ 
        
         register = new Register(this);
    }
    private void start() throws IOException{
        
        StartPage start = new StartPage(this);
    }

//override methods   
@Override
    public void actionPerformed(ActionEvent e) {
        
        userName = start.getName();
        password = start.getPassword();
        button = e.getActionCommand();
        //check which button was pressed
        System.out.println(button);
        switch(button) 
            
        {   //checks credential
            case "SIGN IN": 
                credentialCheck();
                break; 
            //redirects to sign up page    
            case "SIGN UP": 
                register();
                start.setVisible(false);
                break; 
            case "back": 
            {
               register.setVisible(false);
               start.setVisible(true);
               
            }
            break; 
 
 
            default: 
                System.out.println(button); 
        } 
        
        
        }
    
    
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Creates the login page
    //creates the window
   
    //panel for the image
    //panel for the login page with the user name, password, and the option of signing up
    //if sign up is clicked, opens up a new page with two panels, one for a customer, other for the service provider
    //if service provider, sp registration
    //if customer, c registration
    // after signning up,or signing in, redirects to the welcome page
    //the welcome page consists of three panels, one for image, one for displaying the variable name, one for the button next and logout
    // if or else for customer or sp
    
    //if customer, page with 4 panels, one for user name, one for search button, one for viewing booking, one for reviews
    //page with 3 panels(location, barbers name, write review)
    // page with 2 panels(start bookings, cancel button)
    //page with 1 panel(show locations and barbers, buttons)
    //page 1 panel show slot and book
    //alert one booking cancelled
    //alert 2 booking success!
    
    //if sp, page three panels(buttons start appointments, set availability, set status)
    //page with one panel(choose booking and set status
    //page three panels (choose day, hour, set availability
    //page one panel(start appointments)

    






