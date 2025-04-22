/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10470341_poe;

/**
 *
 * @author lab_services_student
 */
public class User {//The class User captures the credentials
    private String username;
    private String password;
    private String cellPhoneNumber;

    public String getUsername() {//to retrieve the username
        return username;
    }

    public void setUsername(String username) {//to assign the username value
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }
}
