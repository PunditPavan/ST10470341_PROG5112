/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package POE2025;

import javax.swing.JOptionPane;

public class MainMethodClass {
    // fixed-size arrays (max 5 entries each)
    static final int MAX = 5;
    static Message[] sentArr        = new Message[MAX];
    static Message[] storedArr      = new Message[MAX];
    static Message[] disregardedArr = new Message[MAX];
    static String[]  idArr          = new String[MAX];
    static String[]  hashArr        = new String[MAX];

    // current counts
    static int sentCount = 0, storedCount = 0, disregardedCount = 0;
    static int idCount = 0, hashCount = 0;

    public static void main(String[] args) {
        Login login = new Login();

        // --- Registration ---
        String u = JOptionPane.showInputDialog(null, "Choose a username:");
        if (u == null) System.exit(0);
        String p = JOptionPane.showInputDialog(null, "Choose a password:");
        if (p == null) System.exit(0);
        String c = JOptionPane.showInputDialog(null, "Enter valid phone (+…):");
        if (c == null) System.exit(0);
        JOptionPane.showMessageDialog(null, login.registerUser(u, p, c));

        // --- Login ---
        boolean loggedIn = false;
        String enteredUsername = null;
        while (!loggedIn) {
            enteredUsername = JOptionPane.showInputDialog(null, "Enter username to login:");
            if (enteredUsername == null) System.exit(0);
            String lp = JOptionPane.showInputDialog(null, "Enter password:");
            if (lp == null) System.exit(0);
            loggedIn = login.loginUser(enteredUsername, lp);
            JOptionPane.showMessageDialog(null, login.returnLoginStatus(loggedIn));
        }
        String currentUser = enteredUsername;  // use the local var, not a missing getter

        // dummy messages
        addToArrays(new Message(1, "+27123456789", "Hello world"));
        addToArrays(new Message(2, "+27111111111", "Test message"));

        // --- Main Menu Loop ---
        while (true) {
            String choice = JOptionPane.showInputDialog(null,
                "=== QuickChat Menu ===\n" +
                "1. Send a Message\n" +
                "2. Show recenty sent message\n" +
                "3. Exit\n" +
                "Enter choice (1–3):");
            if (choice == null) System.exit(0);

            switch (choice) {
                case "1":
                    doSendFlow();
                    break;
                case "2":
                    showArraySubMenu(currentUser);
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option—try 1–3.");
            }
        }
    }

    // Add to ID & hash arrays, and default to sentArr
    static void addToArrays(Message m) {
        if (idCount < MAX)   idArr[idCount++]     = m.getMessageID();
        if (hashCount < MAX) hashArr[hashCount++] = m.getMessageHash();
        if (sentCount < MAX) sentArr[sentCount++] = m;
    }

    static void doSendFlow() {
        String rcpt = JOptionPane.showInputDialog(null, "Enter recipient (+…):");
        if (rcpt == null) return;
        String txt  = JOptionPane.showInputDialog(null, "Enter message text:");
        if (txt == null) return;

        Message msg = new Message(sentCount+1, rcpt, txt);
        if (!msg.checkMessageID() || !msg.checkRecipientCell()) {
            JOptionPane.showMessageDialog(null, "Invalid ID or phone format.");
            return;
        }

        String action = JOptionPane.showInputDialog(null,
            "What to do? (send / store / disregard):");
        if (action == null) return;

        JOptionPane.showMessageDialog(null, msg.sendMessageChoice(action));
        switch (action.toLowerCase()) {
            case "send":
                if (sentCount < MAX) sentArr[sentCount++] = msg;
                break;
            case "store":
                if (storedCount < MAX) storedArr[storedCount++] = msg;
                break;
            case "disregard":
                if (disregardedCount < MAX) disregardedArr[disregardedCount++] = msg;
                break;
        }
        // always track IDs & hashes too
        if (idCount < MAX)   idArr[idCount++]     = msg.getMessageID();
        if (hashCount < MAX) hashArr[hashCount++] = msg.getMessageHash();
    }

    static void showArraySubMenu(String user) {
        String opt = JOptionPane.showInputDialog(null,
            "Sent Mesage Menu\n" +
            "a.Display sender and recipient of all sent message\n" +
            "b.Display longest sent message\n" +
            "c.Search for a Message ID and display corresponding recipent and messsage\n" +
            "d.Search all messages sent to a particular recipient\n" +
            "e. Delete a message uing the message hash\n" +
            "f.Display a report that list the full details of all the sent messages\n" +
            "g. Back to main menu");
        if (opt == null || opt.equalsIgnoreCase("g")) return;

        switch (opt.toLowerCase()) {
            case "a": {
                
                StringBuilder sb = new StringBuilder("📨 Sent Messages:\n\n");
                for (int i = 0; i < sentCount; i++) {
                    sb.append("Recipient: ")
                      .append(sentArr[i].getRecipient()).append("\n")
                      .append("Message:   ")
                      .append(sentArr[i].getMessageText()).append("\n\n");
                }
                JOptionPane.showMessageDialog(null,
                    sb.length() > 0 ? sb.toString() : "No sent messages.");
                break;
            }
            case "b": {
                if (sentCount == 0) {
                    JOptionPane.showMessageDialog(null, "No sent messages.");
                    break;
                }
                Message longest = sentArr[0];
                for (int i = 1; i < sentCount; i++) {
                    if (sentArr[i].getMessageText().length() >
                        longest.getMessageText().length()) {
                        longest = sentArr[i];
                    }
                }
                JOptionPane.showMessageDialog(null,
                    "Longest sent message:\n" + longest.printMessage());
                break;
            }
            case "c": {
                String sid = JOptionPane.showInputDialog(null, "Enter Message ID to search:");
                if (sid == null) break;
                Message f = findByID(sid);
                JOptionPane.showMessageDialog(null,
                    f != null ? "Found:\n" + f.printMessage()
                              : "No message with ID " + sid);
                break;
            }
            case "d": {
                String rc = JOptionPane.showInputDialog(null, "Enter recipient to filter:");
                if (rc == null) break;
                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < sentCount; i++) {
                    if (sentArr[i].getRecipient().equals(rc)) {
                        sb2.append(sentArr[i].printMessage())
                           .append("\n-----\n");
                    }
                }
                JOptionPane.showMessageDialog(null,
                    sb2.length() > 0 ? sb2.toString() : "No messages to " + rc);
                break;
            }
            case "e": {
                String hh = JOptionPane.showInputDialog(null, "Enter Message Hash to delete:");
                if (hh == null) break;
                boolean del = deleteByHash(hh);
                JOptionPane.showMessageDialog(null,
                    del ? "Deleted message with hash " + hh
                        : "No message with hash " + hh);
                break;
            }
            case "f": {
                StringBuilder sb3 = new StringBuilder("Full Sent Msg Report:\n");
                for (int i = 0; i < sentCount; i++) {
                    sb3.append(sentArr[i].printMessage())
                       .append("\n----\n");
                }
                JOptionPane.showMessageDialog(null,
                    sentCount > 0 ? sb3.toString() : "No sent messages.");
                break;
            }
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice a–g.");
        }

        // allow chaining more operations
        showArraySubMenu(user);
    }

    static Message findByID(String id) {
        for (int i = 0; i < sentCount;        i++) if (sentArr[i].getMessageID().equals(id)) return sentArr[i];
        for (int i = 0; i < storedCount;      i++) if (storedArr[i].getMessageID().equals(id)) return storedArr[i];
        for (int i = 0; i < disregardedCount; i++) if (disregardedArr[i].getMessageID().equals(id)) return disregardedArr[i];
        return null;
    }

    static boolean deleteByHash(String hash) {
        for (int i = 0; i < sentCount; i++) {
            if (sentArr[i].getMessageHash().equals(hash)) {
                // shift everything down
                for (int j = i; j < sentCount - 1; j++) {
                    sentArr[j] = sentArr[j + 1];
                }
                sentArr[--sentCount] = null;
                return true;
            }
        }
        return false;
    }
}
