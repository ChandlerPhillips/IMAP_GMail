//Chandler Phillips
//Ethan Thomason

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.*;
import javax.swing.JOptionPane;

public class GmailFetch {

    public static void main(String[] args) throws Exception {

        String username = "";
        String password = "";
        //I have a lot to work on this, this will be replaced soon.
        username = JOptionPane.showInputDialog("Email: ");
        password = JOptionPane.showInputDialog("Password: ");

        if (username != "" && password != "") {
            Session session = Session.getDefaultInstance(new Properties());
            Store store = session.getStore("imaps");
            store.connect("imap.googlemail.com", 993, username, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Fetch unseen messages from inbox folder
            Message[] messages = inbox.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            // Sort messages from recent to oldest
            Arrays.sort(messages, (m1, m2) -> {
                try {
                    return m2.getSentDate().compareTo(m1.getSentDate());
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });

            for (Message message : messages) {
                System.out.println(
                        "Date Received: " + message.getSentDate()
                        + "\nEmail Subject: " + message.getSubject()
                        + "\n" + "Content of email: \n" + message.getContent() + "\n");
                String s = message.getContent().toString();
                String[] ss = s.split(" ");
                System.out.println(Arrays.toString(ss));
            }
        }
    }
}
