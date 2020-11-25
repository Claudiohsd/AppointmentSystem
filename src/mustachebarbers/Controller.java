/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author claudiodionisio
 */
public class Controller extends JFrame{
    
    
    public Controller() {
        
        //shows the frame to the screen 
        this.setVisible(true);
        // sets the size of the label
        this.setSize(500,300);
        //gives the frame a name and shows it in the top bar
        this.setTitle("Layout Managers");
        
        //creates a new label(txt)
        JLabel one = new JLabel("one");
        JLabel two = new JLabel("two");
        JLabel three = new JLabel("three");
        JLabel four = new JLabel("four");
        JLabel five = new JLabel("five");
        
        //both Flow and grid layouts organize the labels so that they can be show on to the screen without overlaping
        //FlowLayout manager = new FlowLayout();
        GridLayout manager = new GridLayout(1, 5);
        this.setLayout(manager);
        
        //adds the label to the panel
        this.add(one);
        this.add(two);
        this.add(three);
        this.add(four);
        this.add(five);
        
        //validates and repaint the frame, will be useful in the future
        //for now its only to get used
        this.validate();
        this.repaint();
    
        
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
    // page with 2 panels(view bookings, cancel button)
    //page with 1 panel(show locations and barbers, buttons)
    //page 1 panel show slot and book
    //alert one booking cancelled
    //alert 2 booking success!
    
    //if sp, page three panels(buttons view appointments, set availability, set status)
    //page with one panel(choose booking and set status
    //page three panels (choose day, hour, set availability
    //page one panel(view appointments)
    
    
}






