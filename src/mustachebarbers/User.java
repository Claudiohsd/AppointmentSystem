/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustachebarbers;

/**
 *
 * @author claudiodionisio
 */
class User {
     private String userName;
    private String password;
    
    public User(String un, String pw){
        this.userName = un;
        this.password = pw;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
}
