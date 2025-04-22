/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package st10470341_poe;

import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class ST10470341_POE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Main method that got the user input
        and,or for registration and login*/
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();
        User user = new User();

        System.out.println("----------------- Register -----------------");

        System.out.print("Enter username: ");//prompt for entering username
        user.setUsername(scanner.nextLine());

        System.out.print("Enter password: ");//prompt for entering password
        user.setPassword(scanner.nextLine());

        System.out.print("Enter cell number: ");//prompt for entering cell number
        user.setCellPhoneNumber(scanner.nextLine());

        String registrationMessage = loginSystem.registerUser(user);
        System.out.println(registrationMessage);

        if (!registrationMessage.contains("successfully")) return;

        System.out.println("\n----------------- Login -----------------");

        System.out.print("Enter username: ");//Allows user to create their own username
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");//Allows a user to create thei own password
        String loginPassword = scanner.nextLine();

        boolean loginSuccess = loginSystem.loginUser(loginUsername, loginPassword);
        String loginMessage = loginSystem.returnLoginStatus(loginSuccess);
        /*This stores the input entered by the user and if entered correctly
        it allows access as well as displays a login messsage,if entered incoreectly
        a invalid login message is showed*/

        System.out.println(loginMessage);
    }
}

