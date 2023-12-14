import java.util.Random;

public class Visitor {

	Id id;
	Baggage b;
	boolean paymentState = false;
	private String type = "New";
	private boolean hungryState = true;
	private boolean tiredState = true;
	private String vName;
	 
	

	//String orderR = "common";
	
	public String getVname(){
		return this.vName;
	}
	
	public void setVname(String vName){
		this.vName = vName;
	}

	public boolean getHungryState(){
		return this.hungryState;
	}

	public boolean getTiredState() {
		return this.tiredState;
	}


	public String getType() {
		return this.type;
	}	

	public Id getId(){
		return this.id;
	}

	public Visitor(boolean hungryState, boolean tiredState) {
		id = new Id();
		b = null;
		this.hungryState = hungryState;
		this.tiredState = tiredState;

		
		//Random rand = new Random();
		
		
		//int n = rand.nextInt(3);
		
		//if (n == 0) {
		//	orderR = "econom";
		//}
		//if (n == 1) {
		//	orderR = "common";
		//}
		//if (n == 2) {
		//	orderR = "luxury";
		//}
		
	}

	
}
