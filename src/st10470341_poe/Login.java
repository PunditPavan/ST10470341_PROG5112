/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10470341_poe;

import java.util.regex.Pattern;

/**
 *
 * @author lab_services_student
 */
public class Login {
    private User registeredUser;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
        /*A requirement is put to ensure that a _ is a must and a length of 5 or smaller 
        is not allowed*/
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        /*Requirements for the password*/
    }

    public boolean checkCellPhoneNumber(String number) {
        return Pattern.matches("^\\+\\d{1,3}\\d{7,10}$", number);
        /*Requirements for the cellphone*/
    }

    public String registerUser(User user) {
        if (!checkUserName(user.getUsername())) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
            /*prompt for invalid username*/
        }
        if (!checkPasswordComplexity(user.getPassword())) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
            /*Prompt for invalid password*/
        }
        if (!checkCellPhoneNumber(user.getCellPhoneNumber())) {
            return "Cell number number incorrectly formatted or does not contain international code.";
            /*prompt for invalid cell phone code*/
        }

        registeredUser = user;
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    public boolean loginUser(String username, String password) {
        return registeredUser != null &&
               username.equals(registeredUser.getUsername()) &&
               password.equals(registeredUser.getPassword());
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + registeredUser.getUsername() + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
