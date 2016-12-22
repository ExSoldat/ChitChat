package utils;

import java.util.Comparator;

import core.domain.messages.Message;

public class MessageComparator implements Comparator<Message> {
	@Override
    public int compare(Message m1, Message m2) {
        if(m1.getDate() < m2.getDate())
        	return 1;
        else
        	return 0;
    }
}
