/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package POE2025;

import java.util.regex.Pattern;

public class Login {
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCell;

    /** Username must contain '_' and be at most 5 characters long. */
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    /** Password must be at least 8 characters, contain an uppercase letter, a digit, and a special character. */
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8
          &&   Pattern.compile("[A-Z]").matcher(password).find()
          &&  Pattern.compile("[0-9]").matcher(password).find()
          &&  Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();

    }

    /** Cell phone number must start with '+' then 1–3 digit country code, followed by 7–10 digits. */
    public boolean checkCellPhoneNumber(String number) {
        return number.matches("\\+[0-9]{1,3}[0-9]{7,10}");

    }

    /**
     * Attempts to register the given credentials.
     * Returns an error message on first failure, or success text if all checks pass.
     */
    public String registerUser(String username, String password, String cell) {
        if (!checkUserName(username)) {
            return "Username incorrectly formatted: must contain '_' and be ≤5 characters.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password incorrectly formatted: must be ≥8 characters, include uppercase, digit & special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell number incorrectly formatted: must start '+' and be ≤15 characters.";
        }
        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredCell = cell;
        return "Registration successful!";
    }

    /** Verifies entered credentials against the stored ones. */
    public boolean loginUser(String username, String password) {
        return username.equals(registeredUsername)
            && password.equals(registeredPassword);
    }

    /** Returns welcome or error message based on login success. */
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + registeredUsername + ", great to see you again!";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

