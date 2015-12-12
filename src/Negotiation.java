import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Negotiation implements Runnable {
	private static int MAX_EXCHANGES_NUMBER = 20;
	private static Random R = new Random();
	private ArrayList<TicketService> ticketList;
	private Provider provider;
	private Client client;
	private Date desiredDate;
	private String departurePlace;
	private String arrivalPlace;
	private double maximumBudget;
	private static double charge;
	private State state;
		
	public Negotiation(String departurePlace, String arrivalPlace, Date desiredDate) {
		this.desiredDate = desiredDate;
		this.departurePlace = departurePlace;
		this.arrivalPlace = arrivalPlace;
		this.ticketList = new ArrayList<TicketService>();
		this.state = State.open;
	}
	
	@Override
	public void run() {
		int exchanges = 0;
		System.out.println("Negotation starts between " + this.client.getName() + " and "  + this.provider.getName());
		// While the negotiation is opened 
		while(state.equals(State.open) && exchanges < MAX_EXCHANGES_NUMBER) {
			processingProvider();
			processingClient();
			exchanges++;
		}
		
		if(exchanges == MAX_EXCHANGES_NUMBER) {
			System.out.println("Too much exchanges");
		}
		
		System.out.println("End of the negotiation between " + this.client.getName() + " and " + this.provider.getName());
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
	
	public ArrayList<TicketService> getTicketList() {
		return ticketList;
	}

	public void setTicketList(ArrayList<TicketService> ticketList) {
		this.ticketList = ticketList;
	}

	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	public String getArrivalPlace() {
		return arrivalPlace;
	}

	public void setArrivalPlace(String arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	public double getMaximumBudget() {
		return maximumBudget;
	}

	public void setMaximumBudget(double maximumBudget) {
		this.maximumBudget = maximumBudget;
	}

	public static double getCharge() {
		return charge;
	}

	public static void setCharge(double charge) {
		Negotiation.charge = charge;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public double getRandomPrice() {
		return 10 + (this.client.getMaximumBudget() - 10) * R.nextDouble();
	}
	
	public Date getDesiredDate() {
		return desiredDate;
	}
	
	public void setDesiredDate(Date desiredDate) {
		this.desiredDate = desiredDate;
	}
	
	private void processingProvider() {
		for(Message message : this.provider.getMessages()) {
			if(message.getEmitter().equals(this.client)) {
				if(!this.provider.acceptOffer(message.getPrice()) && message.getType().equals(MessageType.offer)) {
					System.out.println("The provider " + this.provider.getName() + " refused the offer from " + this.client.getName() + " with an amount of "  + message.getPrice());
					
					if(!this.getTicketList().isEmpty()) {
						// Set minimum price
						this.provider.setMinimumPrice(this.ticketList.get(this.getTicketList().size() - 1).getPrice() * 0.4);
					}
					// Provider makes an offer
					this.provider.makeOffer(this);
				} else {
					System.out.println("The provider accepted the offer");
					this.state = State.close;
				}
				
				// Removes the message
				this.provider.getMessages().remove(message);
			}
		}
	}
	
	private void processingClient() {
		if(this.client.getMessages().isEmpty() && this.ticketList.isEmpty()) {
			// First offer, negotiation starts here
			System.out.println("Client makes first offer");
			this.client.makeOffer(this);
		} 
		
		for(Message message : this.client.getMessages()) {
			if(message.getEmitter().equals(this.provider)) {
				if(!this.client.acceptOffer(message.getPrice()) && message.getType().equals(MessageType.offer)) {
					System.out.println("The client " + this.client.getName() + " refused the offer from " + this.provider.getName() + " with an amount of "  + message.getPrice());
					
					// Client makes an offer
					this.client.makeOffer(this);
				} else {
					System.out.println("The client accepted the offer");
					this.state = State.close;
				}
				
				this.client.getMessages().remove(message);
			}
		}
	}
}
