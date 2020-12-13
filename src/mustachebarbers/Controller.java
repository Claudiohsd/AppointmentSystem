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
 * @author claudiodionisio student number:2019235
 */
public class Controller implements ActionListener {

    private StartPage start;
    private Model model;
    private Register register;
    private DefineUser defineUser;
    private WelcomePage welcome;
    private int phone;
    private String button, userName, surname, password, e_mail, location, userType, userInput, dateTime;
    private User user;
    private UserPage userPage;
    private ViewBooking vBooking;
    private boolean booked, cancelled, savedReview, update;
    private Search search;
    private Reviews review;
    private ViewAppointments vAppointments;
    private SetStatus sStatus;
    private SetAvailability sAvailability;

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
            e_mail = model.getEmail();
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
            e_mail = register.getEmail();
            user = new User(userName, surname, password, phone, e_mail, location, userType);
            model.newUser(user);
            //must be a customer
        } else {
            userName = register.getUserName();
            phone = register.getPhone();
            password = register.getPassword();
            userType = register.getUsertype();
            e_mail = register.getEmail();
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

    //books an appointment
    private void book() {
        model.book(userName, search.getdateTime());
        booked = true;
        userPage = new UserPage(this);
        search.setVisible(false);
        userPage.setVisible(true);

    }

    //defines an appointment as completed
    private void completed() {
        model.setStatusCompleted(userName, sStatus.getdateTime());
        update = true;
        userPage = new UserPage(this);
        sStatus.setVisible(false);
        userPage.setVisible(true);

    }

    //defines an apointment as no show
    private void noShow() {
        model.setStatusNoSHow(userName, sStatus.getdateTime());
        update = true;
        userPage = new UserPage(this);
        sStatus.setVisible(false);
        userPage.setVisible(true);

    }
//cancel booking from the customer page and restart the user page   

    private void cancelCustomer() {
        if (model.hasBooking(userName)) {

            model.cancelBooking(userName, dateTime);
            booked = false;
            userPage = new UserPage(this);
            vBooking.setVisible(false);
            userPage.setVisible(true);
        }
    }
//cancel booking from barber page

    private void cancelBarber() {
        if (model.hasBookingBarber(userName)) {

            model.cancelBookingBarber(userName, dateTime);
            booked = false;
            userPage = new UserPage(this);
            vAppointments.setVisible(false);
            userPage.setVisible(true);
        }
    }
//starts a new view booking page

    public void viewBooking() {

        vBooking = new ViewBooking(this);
        vBooking.setVisible(true);
    }

    //starts the view appointments page
    public void viewAppointments() {

        vAppointments = new ViewAppointments(this);
        vAppointments.setVisible(true);
    }

    public void sAvailability() {

        sAvailability = new SetAvailability(this);
        sAvailability.setVisible(true);
    }

    //starts the set status page
    public void setStatus() {

        sStatus = new SetStatus(this);
        sStatus.setVisible(true);
    }

    //calls the review page
    public void review() {

        review = new Reviews(this);
        review.setVisible(true);
        //submits the review from the user and restart the user page   
    }

    //submits a review and calls the user page 
    public void submitReview() {

        model.newReview(review.getBarberName(), review.getReview());
        savedReview = true;
        userPage = new UserPage(this);
        review.setVisible(false);
        userPage.setVisible(true);
    }

    //sets the slot as available
    public void slotAvailable() {

        model.setAvailable(userName, sAvailability.getdateTime());
        update = true;
        userPage = new UserPage(this);
        sAvailability.setVisible(false);
        userPage.setVisible(true);
    }

    // sets the slot as not available
    public void slotNotAvailable() {

        model.setNotavailable(userName, dateTime);
        update = true;
        userPage = new UserPage(this);
        sAvailability.setVisible(false);
        userPage.setVisible(true);
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

    public boolean getSavedReview() {
        return savedReview;
    }

    public boolean getUpdate() {
        return update;
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
            //butto back to the start page
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
            //signup button in the main page
            case "Customer SIGN UP": {

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
            //button back from the register page
            case "Register Back": {
                register.setVisible(false);
                defineUser.setVisible(true);
            }
            //button next on the welcome page
            break;
            case "Next": {
                userPage();
                welcome.setVisible(false);
            }
            break;
            //button logout on the user page
            case "Logout": {
                userPage.setVisible(false);
                SwingUtilities.updateComponentTreeUI(start);
                SwingUtilities.updateComponentTreeUI(userPage);
                start.clearTextfield();
                start.setVisible(true);
                booked = false;
                update = false;
                savedReview = false;
                cancelled = false;
            }
            break;
            //logout from the welcome page
            case "Welcome Logout": {
                welcome.setVisible(false);
                SwingUtilities.updateComponentTreeUI(start);
                booked = false;
                update = false;
                savedReview = false;
                cancelled = false;
                start.clearTextfield();
                start.setVisible(true);
            }
            break;
            //logout from reviews page
            case "Reviews Logout": {
                welcome.setVisible(false);
                booked = false;
                update = false;
                savedReview = false;
                cancelled = false;
                SwingUtilities.updateComponentTreeUI(start);
                start.clearTextfield();
                start.setVisible(true);
            }
            break;
            //logout from view appointmens page
            case "ViewAppointment Logout": {
                vAppointments.setVisible(false);
                booked = false;
                update = false;
                savedReview = false;
                cancelled = false;
                SwingUtilities.updateComponentTreeUI(start);
                start.clearTextfield();
                start.setVisible(true);
            }
            break;
            //logout from set availability page
            case "SetAvailability Logout": {
                sAvailability.setVisible(false);
                SwingUtilities.updateComponentTreeUI(start);
                start.clearTextfield();
                start.setVisible(true);
                booked = false;
                update = false;
                savedReview = false;
                cancelled = false;
            }
            break;
            //logout from view bookings page
            case "ViewBookings Logout": {
                vBooking.setVisible(false);
                booked = false;
                SwingUtilities.updateComponentTreeUI(start);
                start.clearTextfield();
                start.setVisible(true);
                booked = false;
                update = false;
                savedReview = false;
                cancelled = false;
            }
            break;
            //logout from set status page
            case "SetStatus Logout": {
                sStatus.setVisible(false);
                SwingUtilities.updateComponentTreeUI(start);
                start.clearTextfield();
                start.setVisible(true);
                booked = false;
                update = false;
                savedReview = false;
                cancelled = false;
            }
            break;
            //button search from the user page
            case "Search Barber/Location": {
                userPage.setVisible(false);
                searchPage();
            }
            break;
            //button view booking from user page
            case "       View Booking        ": {
                userPage.setVisible(false);
                viewBooking();
            }
            break;
            //button set status user page
            case "            Set Status           ": {
                userPage.setVisible(false);
                setStatus();
            }
            break;
            //button set availability from user page
            case "       Set Availability        ": {
                userPage.setVisible(false);
                sAvailability();
            }
            break;
            // button view appointments from user page
            case "    View Appointments    ": {
                userPage.setVisible(false);
                viewAppointments();
            }
            break;
            //button reviews from the user page
            case "            Reviews            ": {
                userPage.setVisible(false);
                review();

            }
            break;
            //button back fro search page
            case "Search Back": {
                userPage.setVisible(true);
                search.setVisible(false);
            }
            break;
            //button back from the view page
            case "View Back": {
                userPage.setVisible(false);

            }
            break;
            //button back from review page
            case "Review Back": {
                userPage.setVisible(true);
                review.setVisible(false);
            }
            break;
            //buuton book from search page
            case "            Book             ": {

                book();
            }
            break;
            //button yest from set availability page
            case "Yes": {

                slotAvailable();
            }
            break;
            //button no from set availability page
            case "No": {

                slotNotAvailable();
            }
            break;
            //button submit review from review page
            case "            Submit Review             ": {

                submitReview();
            }
            break;
            //button back from the booking page
            case "Booking Back": {

                vBooking.setVisible(true);
                vBooking.setVisible(false);

            }
            break;
            //button back from the view booking page
            case "ViewBooking Back": {
                cancelled = false;
                vBooking.setVisible(false);
                userPage.setVisible(true);

            }
            //button back from view appointment page
            break;
            case "ViewAppointment Back": {
                cancelled = false;
                vAppointments.setVisible(false);
                userPage.setVisible(true);

            }
            break;
            //button back from view set availability page
            case "SetAvailability Back": {

                sAvailability.setVisible(false);
                userPage.setVisible(true);

            }
            break;
            //button back from set status page
            case "SetStatus Back": {

                sStatus.setVisible(false);
                userPage.setVisible(true);

            }
            break;
            //button cancell from view bookings page
            case "            Cancel             ": {
                dateTime = vBooking.getdateTime();
                cancelled = true;
                vBooking.setVisible(false);
                cancelCustomer();
            }
            break;
            //button cancell from view appointmens page
            case "ViewAppointments Cancel": {
                dateTime = vAppointments.getdateTime();
                cancelled = true;
                vAppointments.setVisible(false);
                cancelBarber();

            }
            break;
            //button completed from set status page
            case "Completed": {
                dateTime = sStatus.getdateTime();
                update = true;
                sStatus.setVisible(false);
                completed();

            }
            //button no show from set status page
            break;
            case "No Show": {
                dateTime = sStatus.getdateTime();
                update = true;
                sStatus.setVisible(false);
                noShow();

            }
            break;

            default:
                //just prints ou the button, no on the view, just for debug purpouses 
                System.out.println(button);

        }

    }

}
