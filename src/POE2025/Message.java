/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POE2025;

import java.util.Random;

public class Message {
    private static final Random RAND = new Random();

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Total messages constructed this session
    private static int totalMessages = 0;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient     = recipient;
        this.messageText   = messageText;
        this.messageID     = generateMessageID();
        this.messageHash   = createMessageHash();
        totalMessages++;  // increment counter for unit tests
    }

    /** Generate a random 10-digit ID. */
    private String generateMessageID() {
        StringBuilder id = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            id.append(RAND.nextInt(10));
        }
        return id.toString();
    }

    /** Ensure ID is exactly 10 characters. */
    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10;
    }

    /** Ensure recipient starts '+' and is between 10 and 15 characters. */
    public boolean checkRecipientCell() {
        return recipient != null
            && recipient.startsWith("+")
            && recipient.length() >= 10
            && recipient.length() <= 15;
    }

    /** Hash = first2chars_of_ID : number : firstWord+lastWord, all uppercase. */
    public String createMessageHash() {
        String[] words = messageText.split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last  = words.length > 1 ? words[words.length - 1] : first;
        return (messageID.substring(0, 2)
              + ":" + messageNumber
              + ":" + first + last)
             .toUpperCase();
    }

    /** “send”, “store” or “disregard” action. */
    public String sendMessageChoice(String choice) {
        switch (choice.toLowerCase()) {
            case "send":      return "Message sent.";
            case "store":     return "Message stored for later.";
            case "disregard": return "Message disregarded.";
            default:           return "Invalid choice.";
        }
    }

    /** Nicely formatted single-message printout. */
    public String printMessage() {
        return "Message ID: "    + messageID
             + "\nMessage Hash: " + messageHash
             + "\nRecipient: "    + recipient
             + "\nText: "         + messageText;
    }

    /** Total messages constructed this session. */
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Getters used elsewhere
    public String getMessageID()   { return messageID; }
    public String getRecipient()   { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
}
