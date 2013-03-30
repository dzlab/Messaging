package com.dzlab.messaging.data;

import java.util.ArrayList;
import java.util.List;

/**
* An interface for finding information about conversations and/or creating new ones.
*/
public class Conversation {
	
	
	// The thread ID of this conversation. Can be zero in the case of a
    // new conversation where the recipient set is changing as the user
    // types and we have not hit the database yet to create a thread.
    public long threadId;
    public long date; // The last update time.
    public int count; // Number of messages.
    public boolean hasUnreadMessages; // True if there are unread messages.
    
    public Contact recipient; // The current recipient.    
    public boolean hasError; // True if any message is in an error state.
    public boolean isChecked; // True if user has selected the conversation for a multi-operation such as delete.
    
    public List<Sms> out, in;
    
    public Conversation() {
    	in  = new ArrayList<Sms>();
    	out = new ArrayList<Sms>();
    	recipient = new Contact();
    	hasUnreadMessages = false;
    }
    /**
    * Returns the time of the last update to this conversation in milliseconds,
    * on the {@link System#currentTimeMillis} timebase.
    */
    public long getDate() {    
    	return date;        
    }
	@Override
	public String toString() {
		return "Conversation [threadId=" + threadId + ", date=" + date
				+ ", count=" + count + ", hasUnreadMessages="
				+ hasUnreadMessages + ", recipient=" + recipient
				+ ", hasError=" + hasError + ", isChecked=" + isChecked + "]";
	}
    
    
}
