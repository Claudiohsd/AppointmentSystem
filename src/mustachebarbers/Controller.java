/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private String button, userName, surname, password, e_mail, location, userType, userInput;
    private User user;
    private UserPage userPage;
    private ViewBooking vBooking;
    private boolean booked, cancelled;
    private Search search;

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

    private void book() {
        model.book(userName, search.getdateTime());
        booked = true;
        userPage = new UserPage(this);
        search.setVisible(false);
        userPage.setVisible(true);

    }

    public void viewBooking() {

        vBooking = new ViewBooking(this);
        vBooking.setVisible(true);
    }

    //getters
    public String getUserType() {
        return userType;
    }

    public boolean getBooked() {
        return booked;
    }

    public boolean getCancelled() {
        return cancelled;
    }

    public String getUserName() {
        return userName;
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

                try {

                    //opens up the welcome page
                    welcome();
                    //closes the barber DefineUser page 
                    register.setVisible(false);

                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                viewBooking();
            }
            break;
            case "            Reviews            ": {
                userPage.setVisible(false);

            }
            break;
            case "Search Back": {
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
            case "            Book             ": {

                book();
            }
            break;
            case "Booking Back": {

                vBooking.setVisible(true);
                vBooking.setVisible(false);
            }
            case "            Cancel             ": {
                cancelled = true;
                vBooking.setVisible(false);
                userPage.setVisible(true);
            }
            break;

            default:
                System.out.println(button);

        }

    }

}
