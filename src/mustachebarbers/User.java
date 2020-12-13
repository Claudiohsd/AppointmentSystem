/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

/**
 *
 * @author claudiodionisio
 * student number:2019235
 */
class User {
    
    private int phone;
    private String userName,surname,password,e_mail,location,user_type;
    
    
    //initialize the variables with the values from the model
    public User(String un, String sn, String pw,  int pn, String em, String lo, String ut){
        this.userName = un;
        this.surname = sn;
        this.password = pw;
        this.phone = pn;
        this.e_mail = em;
        this.location = lo;
        this.user_type = ut;
        
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
    
    } public String getEmail() {
        return e_mail;
    
    } public String getUserLocation() {
        return location;
    
    } public String getUsertype() {
        return user_type;
    
    }
    
}
