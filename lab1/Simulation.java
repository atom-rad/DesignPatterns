import java.util.Random;
import java.util.concurrent.TimeUnit;

public class  Simulation {
	public static void run_simul() throws InterruptedException {
	
		Hotel hotel = new Hotel();
		BaggagePorterSt bg = new BaggagePorterSt("Daniil");
		ReceptionStaff rs = new ReceptionStaff("Steve");
		CleanerStaff cs = new CleanerStaff("John");
		Table table = new Table();
		Chev chev = new Chev("Boris");
		Cook cook = new Cook("Abdul");
		Barman barman = new Barman("Yurii");

		Cashier cashier = new Cashier("Lena");
		Waiter waiter = new Waiter("Maria");

		String[] vNames = {"Alexandr", "Nick", "Patric", "Kate", "Yaroslava", "Alexei", "Sergey", "Nicolai"};

		Visitor v = new Visitor(true,true);
		Room r = new Room();

		//private static generateVname(Random rand, String[] vNames){
		//	String vName = "";
		//	int index = rand.nextInt(vNames.length);
		//	vName = vNames[index];
		//	return vName;
		//}
		
		System.out.println();
		hotel.open();
		System.out.println();

		System.out.println("INITIAL VISITOR ENTERED THE HOTEL");

		rs.assignRoom(r, v);
		cs.setCleaned(true);
		bg.moveBaggage(r, v.b);
		table.occupy(v);
		chev.setNation();
		cook.servFood(v);
		barman.servDrink(v);
		cashier.payment();
		waiter.service();

		System.out.println();
		
		TimeUnit.SECONDS.sleep(2);

		

		Random rand = new Random();

		while(true){
			Visitor vis = generateVisitor(rand);

			if (vis.getHungryState() && !vis.getTiredState()) {
				table.occupy(v);
				chev.setNation();
				cook.servFood(v);
				barman.servDrink(v);
				cashier.payment();
				waiter.service();
			} else if (!vis.getHungryState() && vis.getTiredState()){
				rs.assignRoom(r, v);
				cs.setCleaned(true);
				bg.moveBaggage(r, v.b);
			} else{
				rs.assignRoom(r, v);
				cs.setCleaned(true);
				bg.moveBaggage(r, v.b);
				table.occupy(v);
				chev.setNation();
				cook.servFood(v);
				barman.servDrink(v);
				cashier.payment();
				waiter.service();
			}
			System.out.println();
			TimeUnit.SECONDS.sleep(2);
		}

		

	}
	
	private static Visitor generateVisitor(Random rand){
		Visitor v;
		int choise = rand.nextInt(10)+1;

		if (choise <= 2){
			v = new Visitor(true, false);
		}else if(choise >2 && choise <=5){
			v = new Visitor(false, true);
		}else{
			v = new Visitor(true, true);
		}
		return v;
	}
	
}
