import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Negotiation implements Runnable {
	private static Random r = new Random();
	private ArrayList<TicketService> ticketList;
	private Provider provider;
	private Client client;
	private Date desiredDate;
	private String departurePlace;
	private String arrivalPlace;
	private double maximumBudget;
	private static double charge;
	private State state; // ouvert ou fermé négociation ?
	
	public Date getDesiredDate() {
		return desiredDate;
	}
	
	public void setDesiredDate(Date desiredDate) {
		this.desiredDate = desiredDate;
	}
	
	public Negotiation(String departurePlace, String arrivalPlace, Date desiredDate) {
		this.desiredDate = desiredDate;
		this.departurePlace = departurePlace;
		this.arrivalPlace = arrivalPlace;
		this.ticketList = new ArrayList<TicketService>();
	}
	
	@Override
	public void run() {
		while(state.equals(State.open)) {
			if(this.ticketList.isEmpty()) {
				this.client.makeOffer(getRandomPrice(), this);
			} else {
				for(Message message : this.provider.getMessages()) {
					if(message.getRecipient().equals(this.client)) {
						if(!this.provider.acceptOffer(message.getPrice()) && message.getType().equals(MessageType.offer)) {
							this.client.makeOffer(, this);
						}
					}
				}
			}
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
	
	private double getRandomPrice() {
		return 10 + (this.maximumBudget - 10) * r.nextDouble();
	}
}
