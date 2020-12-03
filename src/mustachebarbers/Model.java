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

/**
 *
 * @author claudiodionisio
 */
public class Model {

    private String userName, surname, password, e_mail, location, user_type;
    private int phone;

    private String[][] data = new String[20][4];

    User user;
    Search search;

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
                surname = rs.getString("surname");
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
            String query = "INSERT INTO user (name,surname,phone,password,e_mail,location,user_type)"
                    + "VALUES ('" + user.getUserName() + "','" + user.getSurname() + "','" + user.getPhone() + "','" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getUserLocation() + "','" + user.getUsertype() + "');";

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

    public String[][] searchLocation() {
        boolean result = false;

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT DISTINCT location FROM user;";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            int row = 0;
            while (rs.next()) {

                data[row][0] = Integer.toString(row + 1);
                data[row][1] = rs.getString("location");

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
        return data;

    }

    public String[][] searchBarber() {
        boolean result = false;

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT name,surname,id  FROM user WHERE user_type = 'barber';";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            int row = 0;
            while (rs.next()) {

                data[row][0] = rs.getString("id");
                data[row][1] = rs.getString("name");
                data[row][2] = rs.getString("surname");

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
        return data;

    }
    public String[][] searchSlots(int id) {
        boolean result = false;

        try {
            String dbServer = "jdbc:mysql://apontejaj.com:3306/Claudio_2019235?useSSL=false";
            String dbUser = "Claudio_2019235";
            String dbPassword = "2019235";
            String query = "SELECT barber,day,time  FROM bookings WHERE  barber = "+id+" AND status = 'available';";

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            int row = 0;
            while (rs.next()) {

                data[row][0] = rs.getString("barber");
                data[row][1] = rs.getString("day");
                data[row][2] = rs.getString("time")+":00";
            
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
        return data;

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
