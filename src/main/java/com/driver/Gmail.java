package com.driver;

import java.util.*;

public class Gmail extends Email {

    private int inboxCapacity; // maximum number of mails inbox can store
    private LinkedList<Mail> inbox; // Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    private List<Mail> trash; // Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    private class Mail {
        private Date date;
        private String sender;
        private String message;

        public Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new LinkedList<>();
        this.trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message) {
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        if (inbox.size() >= inboxCapacity) {
            trash.add(inbox.removeFirst());
        }
        inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message) {
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        Iterator<Mail> iterator = inbox.iterator();
        while (iterator.hasNext()) {
            Mail mail = iterator.next();
            if (mail.getMessage().equals(message)) {
                trash.add(mail);
                iterator.remove();
                return;
            }
        }
    }

    public String findLatestMessage() {
        // If the inbox is empty, return null. Else, return the message of the latest mail present in the inbox.
        if (inbox.isEmpty()) {
            return null;
        }
        return inbox.getLast().getMessage();
    }

    public String findOldestMessage() {
        // If the inbox is empty, return null. Else, return the message of the oldest mail present in the inbox.
        if (inbox.isEmpty()) {
            return null;
        }
        return inbox.getFirst().getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end) {
        // Find number of mails in the inbox which are received between given dates.
        int count = 0;
        for (Mail mail : inbox) {
            if (!mail.getDate().before(start) && !mail.getDate().after(end)) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize() {
        // Return number of mails in inbox.
        return inbox.size();
    }

    public int getTrashSize() {
        // Return number of mails in Trash.
        return trash.size();
    }

    public void emptyTrash() {
        // Clear all mails in the trash.
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox.
        return inboxCapacity;
    }
}
