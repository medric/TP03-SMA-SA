import java.util.ArrayList;
import java.util.Date;

import javafx.beans.binding.When;

public class Negotiation implements Runnable {
	
	private ArrayList<TicketService> ticketList;
	private Provider provider;
	private Client client;
	private Date dateDesired;
	private double maximumBudget;
	private static double charge;
	private State state; // ouvert ou fermé négociation ?

	// TODO : tant que ce n'est pas fermé ...
	
	public Date getDateDesired() {
		return dateDesired;
	}

	public void setDateDesired(Date dateDesired) {
		this.dateDesired = dateDesired;
	}
	
	public Negotiation(Date dateDesired) {
		this.dateDesired = dateDesired;
		this.ticketList = new ArrayList<TicketService>();
	}
	

	@Override
	public void run() {
		while(state.equals(State.open)) {
			// TODO : checker si message courant
			ArrayList<Message> messages = ;
		}
	}
	
	public void addTicket(TicketService ticket) {
		this.ticketList.add(ticket);
	}
	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
