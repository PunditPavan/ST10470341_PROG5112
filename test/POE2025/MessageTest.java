/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POE2025;
// MessageTest.java


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Field;

public class MessageTest extends TestCase {

    public static Test suite() {
        return new TestSuite(MessageTest.class);
    }

    /** Reset the private static totalMessages before each test */
    protected void setUp() throws Exception {
        Field totalField = Message.class.getDeclaredField("totalMessages");
        totalField.setAccessible(true);
        totalField.setInt(null, 0);
    }

    public void testGenerateMessageID_LengthAndDigits() {
        Message msg = new Message(1, "+1234567890", "hello world");
        String id = msg.getMessageID();
        assertEquals("ID must be 10 chars", 10, id.length());
        assertTrue("ID must be all digits", id.matches("\\d{10}"));
    }

    public void testCheckMessageID_Valid() {
        Message msg = new Message(1, "+1234567890", "test");
        assertTrue("checkMessageID should be true for 10-digit ID", msg.checkMessageID());
    }

    public void testCheckRecipientCell_Various() {
        Message valid   = new Message(1, "+1234567890", "hey");
        Message noPlus  = new Message(1, "1234567890",  "hey");
        Message tooShort= new Message(1, "+1234",       "hey");
        Message tooLong = new Message(1, "+1234567890123456", "hey");

        assertTrue ("Valid number fails",     valid.checkRecipientCell());
        assertFalse("Missing '+' should fail", noPlus.checkRecipientCell());
        assertFalse("Too short should fail",   tooShort.checkRecipientCell());
        assertFalse("Too long should fail",    tooLong.checkRecipientCell());
    }

    public void testCreateMessageHash_Format() {
        int num = 5;
        String text = "Hello world test";
        Message msg = new Message(num, "+1234567890", text);

        String first = "Hello";
        String last  = "test";
        String expected = (msg.getMessageID().substring(0,2)
                         + ":" + num
                         + ":" + first + last).toUpperCase();

        assertEquals("Hash format incorrect", expected, msg.getMessageHash());
    }

    public void testSendMessageChoice_AllCases() {
        Message msg = new Message(1, "+1234567890", "x");
        assertEquals("send",      "Message sent.",               msg.sendMessageChoice("send"));
        assertEquals("store",     "Message stored for later.",   msg.sendMessageChoice("STORE"));
        assertEquals("disregard", "Message disregarded.",         msg.sendMessageChoice("DisReGard"));
        assertEquals("invalid",   "Invalid choice.",             msg.sendMessageChoice("foo"));
    }

    public void testPrintMessage_ContainsAllParts() {
        Message msg = new Message(7, "+0987654321", "hey there");
        String out = msg.printMessage();

        assertTrue(out.indexOf("Message ID: "   + msg.getMessageID())   >= 0);
        assertTrue(out.indexOf("Message Hash: " + msg.getMessageHash()) >= 0);
        assertTrue(out.indexOf("Recipient: "     + msg.getRecipient())   >= 0);
        assertTrue(out.indexOf("Text: "          + msg.getMessageText()) >= 0);
    }

    public void testReturnTotalMessages_Counter() {
        assertEquals("Should start at 0", 0, Message.returnTotalMessages());
        new Message(1, "+1111111111", "a");
        assertEquals("After 1 message", 1, Message.returnTotalMessages());
        new Message(2, "+2222222222", "b");
        new Message(3, "+3333333333", "c");
        assertEquals("After 3 messages", 3, Message.returnTotalMessages());
    }
}
