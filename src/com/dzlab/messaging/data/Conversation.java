package com.dzlab.messaging.data;

/**
* An interface for finding information about conversations and/or creating new ones.
*/
public class Conversation {
	
	
	// The thread ID of this conversation. Can be zero in the case of a
    // new conversation where the recipient set is changing as the user
    // types and we have not hit the database yet to create a thread.
    public long mThreadId;
    public long mDate; // The last update time.
    public int mMessageCount; // Number of messages.
    public boolean mHasUnreadMessages; // True if there are unread messages.
    
    public Contact mRecipient; // The current recipient.    
    public boolean mHasError; // True if any message is in an error state.
    public boolean mIsChecked; // True if user has selected the conversation for a multi-operation such as delete.
    
    /**
    * Returns the time of the last update to this conversation in milliseconds,
    * on the {@link System#currentTimeMillis} timebase.
    */
    public long getDate() {    
    	return mDate;        
    }
}
