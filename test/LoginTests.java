
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import st10470341_poe.Login;
import st10470341_poe.User;

public class LoginTests {
    private Login login;
    private User user;

    @Before
    public void setUp() {
        login = new Login();
        user = new User();
    }

    @Test
    public void testUsernameCorrectlyFormattedEquals() {
        user.setUsername("kyl_1");
        user.setPassword("Ch&&sec@ke99!");
        user.setCellPhoneNumber("+27839868976");

        login.registerUser(user);
        String message = login.returnLoginStatus(true);

        assertEquals("Welcome kyl_1, it is great to see you again.", message);
    }

    @Test
    public void testUsernameIncorrectlyFormattedEquals() {
        user.setUsername("kyle!!!!!!!");
        user.setPassword("Ch&&sec@ke99!");
        user.setCellPhoneNumber("+27839868976");

        String message = login.registerUser(user);
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", message);
    }

    @Test
    public void testUsernameIncorrectlyFormattedReturnsFalse() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexityReturnsTrue() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordFailsComplexityReturnsFalse() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellNumberCorrectFormatReturnsTrue() {
        assertTrue(login.checkCellPhoneNumber("+27839868976"));
    }

    @Test
    public void testCellNumberIncorrectFormatReturnsFalse() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testLoginSuccessReturnsTrue() {
        user.setUsername("kyl_1");
        user.setPassword("Ch&&sec@ke99!");
        user.setCellPhoneNumber("+27839868976");
        login.registerUser(user);

        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailureReturnsFalse() {
        user.setUsername("kyl_1");
        user.setPassword("Ch&&sec@ke99!");
        user.setCellPhoneNumber("+27839868976");
        login.registerUser(user);

        assertFalse(login.loginUser("kyl_1", "wrongpass"));
    }

    @Test
    public void testReturnLoginStatusFailMessage() {
        String message = login.returnLoginStatus(false);
        assertEquals("Username or password incorrect, please try again.", message);
    }
}
