/**
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Inbox {
	private HashMap<Agent, ArrayList<Message>> messages;
	
	public Inbox() {
		this.messages = new HashMap<Agent, ArrayList<Message>>();
	}
	
	/**
	 * @return the messages
	 */
	ArrayList<Message> getMessages(Agent agent) {
		return this.messages.get(agent);
	}
	
	/**
	 * 
	 * @param agent
	 */
	public void addAgent(Agent agent) {
		this.messages.put(agent, new ArrayList<Message>());
	}
	
	/**
	 * 
	 * @param message
	 */
	public void send(Message message) {
		ArrayList<Message> recipientMessages = this.messages.get(message.getRecipient());
		
		if(recipientMessages != null) {
			recipientMessages.add(message);
			this.messages.put(message.getRecipient(), recipientMessages);
		}
	}
}
