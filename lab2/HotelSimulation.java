import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// HotelComponent interface
interface HotelComponent extends Cloneable {
    void interact();
    HotelComponent cloneComponent();
}

// Worker abstract class
abstract class Worker implements HotelComponent {
    @Override
    public void interact() {
        System.out.println("Worker is performing general duties.");
    }

    @Override
    public HotelComponent cloneComponent() {
        try {
            return (Worker) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// ReceptionWorker concrete class
class ReceptionWorker extends Worker {
    @Override
    public void interact() {
        System.out.println("Reception worker is assisting a visitor.");
        System.out.println("Visitor gets a room.");
    }
}

// RestaurantWorker concrete class
class RestaurantWorker extends Worker {
    private static final List<String> foodMenu = createFoodMenu();

    @Override
    public void interact() {
        System.out.println("Restaurant worker is taking an order from a visitor.");
        serveFood();
    }

    private void serveFood() {
        Random random = new Random();
        int index = random.nextInt(foodMenu.size());
        String foodItem = foodMenu.get(index);
        System.out.println("Visitor is having " + foodItem + " in the restaurant.");
    }

    private static List<String> createFoodMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Spaghetti Bolognese");
        menu.add("Chicken Alfredo");
        menu.add("Vegetarian Pizza");
        menu.add("Grilled Salmon");
        menu.add("Caesar Salad");
        return menu;
    }

    @Override
    public HotelComponent cloneComponent() {
        return new RestaurantWorker();
    }
}

// Visitor concrete class
class Visitor implements HotelComponent {
    @Override
    public void interact() {
        System.out.println("Visitor is interacting.");
    }

    @Override
    public HotelComponent cloneComponent() {
        return new Visitor();
    }
}

// Room concrete class
class Room implements HotelComponent {
    @Override
    public void interact() {
        System.out.println("Room is being used.");
    }

    @Override
    public HotelComponent cloneComponent() {
        return new Room();
    }
}

// Bar concrete class
class Bar implements HotelComponent {
    @Override
    public void interact() {
        System.out.println("Bar is serving drinks.");
    }

    @Override
    public HotelComponent cloneComponent() {
        return new Bar();
    }
}

// Factories for Workers
class ReceptionWorkerFactory implements HotelComponentFactory {
    @Override
    public HotelComponent createComponent() {
        return new ReceptionWorker();
    }
}

class RestaurantWorkerFactory implements HotelComponentFactory {
    @Override
    public HotelComponent createComponent() {
        return new RestaurantWorker();
    }
}

// Factories for Visitors, Rooms, and Bars
class VisitorFactory implements HotelComponentFactory {
    @Override
    public HotelComponent createComponent() {
        return new Visitor();
    }
}

class RoomFactory implements HotelComponentFactory {
    @Override
    public HotelComponent createComponent() {
        return new Room();
    }
}

class BarFactory implements HotelComponentFactory {
    @Override
    public HotelComponent createComponent() {
        return new Bar();
    }
}

// HotelDirector class
class HotelDirector implements HotelComponent {
    // Singleton implementation
    private static HotelDirector instance;

    private HotelDirector() {
        // Private constructor to prevent instantiation
    }

    public static HotelDirector getInstance() {
        if (instance == null) {
            instance = new HotelDirector();
        }
        return instance;
    }

    @Override
    public void interact() {
        System.out.println("Hotel director announces: \"Hotel is open now!\"");
    }

    @Override
    public HotelComponent cloneComponent() {
        // Not applicable for the director
        return null;
    }
}

// HotelComponentFactory interface
interface HotelComponentFactory {
    HotelComponent createComponent();
}

// HotelSimulation class
public class HotelSimulation {
    public static void main(String[] args) {
        HotelDirector hotelDirector = HotelDirector.getInstance();

        // Announce the hotel is open
        hotelDirector.interact();

        HotelComponentFactory visitorFactory = new VisitorFactory();
        HotelComponentFactory roomFactory = new RoomFactory();
        HotelComponentFactory barFactory = new BarFactory();
        HotelComponentFactory receptionWorkerFactory = new ReceptionWorkerFactory();
        HotelComponentFactory restaurantWorkerFactory = new RestaurantWorkerFactory();

        for (int i = 0; i < 5; i++) {
            System.out.println("Day " + (i + 1));

            // Simulate visitor interaction
            HotelComponent visitor = visitorFactory.createComponent();
            visitor.interact();

            // Simulate reception worker interaction
            HotelComponent receptionWorker = receptionWorkerFactory.createComponent();
            receptionWorker.interact();

            // Simulate room usage
            HotelComponent room = roomFactory.createComponent();
            room.interact();

            // Simulate bar interaction
            HotelComponent bar = barFactory.createComponent();
            bar.interact();

            // Simulate restaurant worker interaction
            HotelComponent restaurantWorker = restaurantWorkerFactory.createComponent();
            restaurantWorker.interact();

            // Demonstrate Prototype pattern by cloning existing components
            HotelComponent clonedVisitor = visitor.cloneComponent();
            clonedVisitor.interact();

            HotelComponent clonedRoom = room.cloneComponent();
            clonedRoom.interact();

            HotelComponent clonedBar = bar.cloneComponent();
            clonedBar.interact();

            HotelComponent clonedReceptionWorker = receptionWorker.cloneComponent();
            clonedReceptionWorker.interact();

            HotelComponent clonedRestaurantWorker = restaurantWorker.cloneComponent();
            clonedRestaurantWorker.interact();

            System.out.println(); // Add a line break for better readability
        }
    }
}
