/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author claudiodionisio
 */
public class Model {

    private String userName, surname, password, e_mail, location, user_type;
    private int phone, size;

    User user;

    public boolean login(User user) {

        boolean result = false;

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT * FROM user WHERE e_mail= '" + user.getEmail() + "' AND password = '" + user.getPassword() + "';";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            if (rs.next()) {

                userName = rs.getString("name");
                password = rs.getString("password");
                phone = rs.getInt("phone");
                e_mail = rs.getString("e_mail");
                location = rs.getString("location");
                user_type = rs.getString("user_type");
                result = true;
                System.out.println(rs.getString("id") + "\t" + rs.getString("name") + "\t" + rs.getString("e_mail"));

            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;

    }

    public void newUser(User user) {

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "INSERT INTO user (name,phone,password,e_mail,location,user_type)"
                    + "VALUES ('" + user.getUserName() + "','" + user.getPhone() + "','" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getUserLocation() + "','" + user.getUsertype() + "');";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String[] searchLocation() {
        boolean result = false;
        ArrayList<String> data1 = new ArrayList<String>();
        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT DISTINCT location FROM user ORDER BY location ASC;";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            int row = 0;
            data1.add("---select---");
            while (rs.next()) {
                data1.add(rs.getString("location"));
                row++;
            }
            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        size = data1.size();
        String[] data = data1.toArray(new String[size]);
        return data;
    }

    public String[] searchBarber(String location) {
        ArrayList<String> data1 = new ArrayList<String>();
        boolean result = false;

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT name FROM user WHERE location ='" + location + "' ORDER BY name ASC;";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            int row = 0;
            data1.add("---select---");
            while (rs.next()) {
                data1.add(rs.getString("name"));
                row++;
            }
            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        size = data1.size();
        String[] data = data1.toArray(new String[size]);
        return data;
    }

    public String[] searchSlots(String barber) {

        ArrayList<String> data1 = new ArrayList<String>();
        boolean result = false;

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT datetime FROM bookings WHERE status = 'available' AND barber ='" + barber + "';";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            int row = 0;
            data1.add("---select---");
            while (rs.next()) {
                data1.add(rs.getString("datetime"));
                row++;
            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        size = data1.size();
        String[] data = data1.toArray(new String[size]);
        return data;

    }

    public void book(String user, String datetime) {

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "UPDATE bookings SET customer = '" + user + "',status = 'booked' WHERE datetime = '" + datetime + "';";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            stmt.executeUpdate(query);
            // Close the result set, statement and the connection

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public String[] searchBooking(String customer) {

        ArrayList<String> data1 = new ArrayList<String>();
        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT barber, datetime FROM bookings WHERE customer = '" + customer + "';";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            int row = 0;
            data1.add("---select---");
            while (rs.next()) {
                data1.add("Barber: " + rs.getString("barber") + "- On: " + rs.getString("datetime"));
                row++;
            }
            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        size = data1.size();
        String[] data = data1.toArray(new String[size]);
        return data;
    }

    public void cancelBooking(String user, String dateTime) {

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "UPDATE bookings SET customer = Null ,status = 'available' WHERE customer = '" + user + "' AND datetime = '" + dateTime + "';";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            stmt.executeUpdate(query);
            // Close the result set, statement and the connection

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public boolean hasBooking(String userName) {
        boolean result = false;

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT * FROM bookings WHERE customer = '" + userName + "';";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            if (rs.next()) {

                result = true;

            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    // getters 
    public String getUserName() {
        return userName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;

    }

    public String getEmail() {
        return e_mail;

    }

    public String getUserLocation() {
        return location;

    }

    public String getUsertype() {
        return user_type;
    }
}
