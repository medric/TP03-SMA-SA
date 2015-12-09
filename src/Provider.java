import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Provider extends Agent{
	private static ArrayList<TicketService> ticketsList = new ArrayList();
	static {
		ticketsList.add(new TicketService("Lyon", "Berlin", new Date(), 200d));
		ticketsList.add(new TicketService("Paris", "Los Angeles", new Date(), 350d));
		ticketsList.add(new TicketService("Lyon", "Paris", new Date(), 400d));
		ticketsList.add(new TicketService("Lyon", "Bordeaux", new Date(), 150d));
	}
	
	private double minimumPrice;
	private ArrayList<TicketService> ticketService;
	private static double LOW_PROBABILITY = 1.2;
	private static double AVERAGE_PROBABILITY =  1.5;
	
	public double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	
	public Provider(String name, double minimumPrice, Inbox inbox) {
		super(name, inbox);
		this.minimumPrice = minimumPrice;
		this.ticketService = new ArrayList<TicketService>();
	}
	
	public void makeOffer(double price, Negotiation negotiation) {
		// A ticket is found for this negotiation
		TicketService ticket = find(negotiation.getDeparturePlace(), negotiation.getArrivalPlace(), negotiation.getDesiredDate());
		if(ticket != null) {
			price = ticket.getPrice();
			if(price > negotiation.getMaximumBudget()) {
				price = price * 0.2;
			}
			Message message = new Message(negotiation.getClient(), this, negotiation, price, MessageType.offer); 
			this.getInbox().send(message);
		}
	}
	
	public void addTicket(TicketService ticket) {
		this.ticketService.add(ticket);
	}
	
	public boolean acceptOffer(double price) {
		boolean accept = false;
		
		if(price > minimumPrice) {
			Random r = new Random();
			int nbLuck = 1 + r.nextInt(100 - 1);
			double probabilityPrice = price / minimumPrice;
			
			if(probabilityPrice < LOW_PROBABILITY) {
				if(nbLuck < 5) {
					accept = true;
				}
			} else if (probabilityPrice < AVERAGE_PROBABILITY) {
				if(nbLuck < 30){
					accept = true;
				}
			} else {
				if(nbLuck < 66) {
					accept = true;
				}
			}
		}
		
		return accept;
	}
	
	public TicketService find(String departure, String arrival, Date date) {
		TicketService ticket = null;
		for(TicketService _ticket : ticketsList) {
			if(_ticket.getDeparturePlace().equals(departure) && 
					_ticket.getArrivalPlace().equals(arrival) &&
						_ticket.getDepartureDate().after(date)) {
				ticket = _ticket;
				break;
				
			}
		}
		
		return ticket;
	}
}
