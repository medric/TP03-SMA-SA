import java.util.ArrayList;
import java.util.Random;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class Provider extends Agent{

	private double minimumPrice;
	private ArrayList<TicketService> listTicket;
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
		this.listTicket = new ArrayList<TicketService>();
	}
	
	public void MakeOffer(double price) {
		Message message = new Message();
		this.getInbox().send(message);
	}
	
	public void addTicket(TicketService ticket) {
		this.listTicket.add(ticket);
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
}
