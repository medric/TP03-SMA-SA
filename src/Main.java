import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		Inbox inbox = new Inbox();
		Client client1 = new Client("toto", 350, inbox);
		Provider provider1 = new Provider("Air France", 100, inbox);
		TicketService ticket1 = new TicketService("Lyon", "Berlin", new Date());
		
		Negotiation negotiation1 = new Negotiation(new Date());
		negotiation1.addTicket(ticket1);
		negotiation1.setProvider(provider1);
		negotiation1.setClient(client1);
	}

}
