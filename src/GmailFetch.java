//Chandler Phillips
//Ethan Thomason

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.*;

public class GmailFetch {

    public static void main(String[] args) throws Exception {

        String content[];

        Session session = Session.getDefaultInstance(new Properties());
        Store store = session.getStore("imaps");
        store.connect("imap.googlemail.com", 993, "EmailHERE@murraystate.edu", "passwordHERE");
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
