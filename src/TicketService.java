import java.util.Date;

public class TicketService {

	private String placeDeparture;
	private String placeArrival;
	private Date dateDeparture;
	
	public String getPlaceDeparture() {
		return placeDeparture;
	}
	
	public void setPlaceDeparture(String placeDeparture) {
		this.placeDeparture = placeDeparture;
	}
	
	public String getPlaceArrival() {
		return placeArrival;
	}
	
	public void setPlaceArrival(String placeArrival) {
		this.placeArrival = placeArrival;
	}
	
	public Date getDateDeparture() {
		return dateDeparture;
	}
	
	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}
	
	public TicketService(String placeDeparture, String placeArrival, Date dateDeparture) {
		this.placeDeparture = placeDeparture;
		this.placeArrival = placeArrival;
		this.dateDeparture = dateDeparture;
	}
}
