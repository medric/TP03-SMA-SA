import java.util.Random;

public class Client extends Agent{

	private double maximumBudget;
	private static double HIGHT_PROBABILITY = 2;
	private static double AVERAGE_PROBABILITY = 1.5;
	
	public double getMaximumBudget() {
		return maximumBudget;
	}

	public void setMaximumBudget(double maximumBudget) {
		this.maximumBudget = maximumBudget;
	}
	
	public Client(String name, double maximumBudget, Inbox inbox) {
		super(name, inbox);
		this.maximumBudget = maximumBudget;
	}
	
	public void makeOffer(double price, Negotiation negotiation) {
		if(price <= maximumBudget) {
			Message message = new Message(this, negotiation.getProvider(), negotiation, price, MessageType.offer); 
			this.getInbox().send(message);
		}
	}
	
	public boolean acceptOffer(double price) {
		
		// TODO : param en fonction du nombre de négociation
		
		boolean accept = false;
		
		if(price < maximumBudget) {
			Random r = new Random();
			int nbLuck = 1 + r.nextInt(100 - 1);
			double probabilityPrice = maximumBudget / price;
			
			if(probabilityPrice > HIGHT_PROBABILITY) {
				if(nbLuck < 5) {
					accept = true;
				}
			} else if (probabilityPrice > AVERAGE_PROBABILITY) {
				if(nbLuck < 30){
					accept = true;
				}
			} else {
				if(nbLuck < 5) {
					accept = true;
				}
			}
		}
		
		return accept;
	}
}
