/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author claudiodionisio
 */
public class Controller implements ActionListener {

    private StartPage start;
    private Model model;
    private Register register;
    private DefineUser defineUser;
    private WelcomePage welcome;
    private int phone;
    private String button, userName, surname, password, e_mail, location, userType;
    private User user;
    private UserPage userPage;
    private boolean locationNumber = false;
    private Search search;
    private SearchSlots sSlots;

    public Controller() throws IOException {
        //initialize the variables
        this.start = new StartPage(this);
        this.model = new Model();

    }

    //checks if the credential is valid when log in is clicked
    private void credentialCheck() throws IOException {

        user = new User(userName, surname, password, phone, e_mail, location, userType);

        boolean credential = model.login(user);

        String resultMessage = "Try again with valid credentials";

        if (credential) {
            resultMessage = "Welcome ";
            userName = model.getUserName();
            surname = model.getSurname();
            phone = model.getPhone();
            location = model.getUserLocation();
            userType = model.getUsertype();
            welcome();
        }

        start.setCredential(resultMessage);

    }private void searchSlots() {
        
        sSlots = new SearchSlots(this);
        

    }

    // calls the page define user
    private void UserType() {

        defineUser = new DefineUser(this);

    }

    //calls the start page
    private void start() throws IOException {

        StartPage start = new StartPage(this);
    }

    //calls the register page
    private void register() {

        register = new Register(this);

        defineUser.setVisible(false);
    }

    //calls the welcome page
    private void welcome() throws IOException {
        welcome = new WelcomePage(this);

        if (button == "SIGN IN") {
            //if welcome page is called by the start page using the sign in button
            welcome.setUserName(userName);
            start.setVisible(false);
            //if the welcome page is called by the barber DefineUser page
        } else if (button == "Barber SIGN UP") {

            userName = register.getUserName();
            phone = register.getPhone();
            location = register.getUserLocation();
            password = register.getPassword();
            userType = register.getUsertype();
            welcome.setUserName(userName);
            user = new User(userName, surname, password, phone, e_mail, location, userType);
            model.newUser(user);

        }
    }

    //calls the user page
    private void userPage() {   
       
        userPage = new UserPage(this);
        userPage.setVisible(true);
    }

    //calls the search page
    private void searchPage() {
        search = new Search(this);
        search.setVisible(true);

    }

    //getters
    public String getUserType() {
        return userType;
    }

    public boolean getLocationNumber() {
        return locationNumber;
    }

//override methods   
    @Override
    public void actionPerformed(ActionEvent e) {

//       
        button = e.getActionCommand();
        //check which button was pressed
        System.out.println(button);
        switch (button) {   //start page command
            //checks credential
            case "SIGN IN": {
                password = start.getPassword();
                e_mail = start.getEmail();
                try {
                    credentialCheck();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            //start page command
            //redirects to sign up page    
            case "SIGN UP":
                UserType();
                start.setVisible(false);
                break;
            case "Back": {
                defineUser.setVisible(false);
                start.setVisible(true);

            }
            break;
            //register page command
            case "I am a Customer": {
                userType = "customer";
                register();
            }
            break;
            //register page command
            case "I am a Service Provider": {
                userType = "barber";
                register();
            }
            break;
            // command comes from the barber DefineUser page
            case "Barber SIGN UP": {

//               
                try {

                    //opens up the welcome page
                    welcome();
                    //closes the barber DefineUser page 
                    register.setVisible(false);

                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
//               
            }
            break;
            case "Register Back": {
                register.setVisible(false);
                defineUser.setVisible(true);
            }
            break;
            case "Next": {
                userPage();
                welcome.setVisible(false);
            }
            break;
            case "Logout": {
                userPage.setVisible(false);
                SwingUtilities.updateComponentTreeUI(start);
                start.clearTextfield();
                start.setVisible(true);
            }
            break;
            case "Search Barber/Location": {
                userPage.setVisible(false);
                searchPage();
            }
            break;
            case "       View Booking        ": {
                userPage.setVisible(false);

            }
            break;
            case "            Reviews            ": {
                userPage.setVisible(false);

            }
            break;
            case "Search Back": {
                locationNumber = false;

                userPage.setVisible(true);
                search.setVisible(false);
            }
            break;
            case "View Back": {
                userPage.setVisible(false);

            }
            break;
            case "Review Back": {
                userPage.setVisible(false);
            }
            break;
            case "            Search             ": {
                search.setVisible(false);
                locationNumber = true;
//                SwingUtilities.updateComponentTreeUI(search);
                searchPage();
//              

            }
            break;
            case "            FindSlots           ": {
           search.setVisible(false);
           searchSlots();
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

