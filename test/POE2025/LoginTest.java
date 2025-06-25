/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POE2025;

/**
 *
 * @author lab_services_student
 */

 import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
   


// Test 1: Message recipient is valid
    @Test
    public void testValidRecipientFormat() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        assertTrue(message.checkRecipientCell());
    }

    // Test 2: Message recipient is invalid
    @Test
    public void testInvalidRecipientFormat() {
        Message message = new Message(2, "08575975889", "Hi Keegan, did you receive the payment?");
        assertFalse(message.checkRecipientCell());
    }

    // Test 3: Message text below 250 characters
    @Test
    public void testMessageWithinLimit() {
        String shortMessage = "Hi Mike, can you join us for dinner tonight";
        assertTrue(shortMessage.length() <= 250);
    }

    // Test 4: Message text over 250 characters
    @Test
    public void testMessageExceedsLimit() {
        String longMessage = "A".repeat(251); // 251 characters
        assertTrue(longMessage.length() > 250);
    }

    // Test 5: Message Hash structure
    @Test
    public void testMessageHashStructure() {
        Message message = new Message(0, "+27718693002", "Hi tonight");
        String expectedStart = message.getMessageID().substring(0, 2) + ":0:HI" + "TONIGHT";
        assertEquals(expectedStart.toUpperCase(), message.getMessageHash());
    }

    // Test 6: Message sent choice returns expected
    @Test
    public void testSendChoiceSend() {
        Message message = new Message(1, "+27718693002", "Hello there");
        assertEquals("Message sent.", message.sendMessageChoice("Send"));
    }

    @Test
    public void testSendChoiceStore() {
        Message message = new Message(1, "+27718693002", "Hello again");
        assertEquals("Message stored for later.", message.sendMessageChoice("Store"));
    }

    @Test
    public void testSendChoiceDisregard() {
        Message message = new Message(1, "+27718693002", "Ignore this");
        assertEquals("Message disregarded.", message.sendMessageChoice("Disregard"));
    }
}


