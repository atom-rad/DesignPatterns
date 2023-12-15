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
    private PaymentStrategy paymentStrategy;

    // Default constructor
    public Visitor() {
    }

    // Setter for PaymentStrategy
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public void interact() {
        System.out.println("Visitor is interacting.");
        if (paymentStrategy != null) {
            paymentStrategy.pay(100.0); // You can adjust the amount as needed
        } else {
            System.out.println("No payment strategy set for the visitor.");
        }
    }

    @Override
    public HotelComponent cloneComponent() {
        Visitor clonedVisitor = new Visitor();
        clonedVisitor.setPaymentStrategy(this.paymentStrategy); // Copy the payment strategy to the cloned visitor
        return clonedVisitor;
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

// RawFood class
class RawFood implements HotelComponent {
    @Override
    public void interact() {
        System.out.println("Raw food has arrived at the hotel.");
    }

    @Override
    public HotelComponent cloneComponent() {
        return new RawFood();
    }
}

// RawDrinks class
class RawDrinks implements HotelComponent {
    @Override
    public void interact() {
        System.out.println("Raw drinks have arrived at the hotel.");
    }

    @Override
    public HotelComponent cloneComponent() {
        return new RawDrinks();
    }
}

// Cleanser class
class Cleanser implements HotelComponent {
    @Override
    public void interact() {
        System.out.println("Cleanser has arrived at the hotel.");
    }

    @Override
    public HotelComponent cloneComponent() {
        return new Cleanser();
    }
}

// RawFoodFactory class
class RawFoodFactory implements HotelComponentFactory {
    @Override
    public HotelComponent createComponent() {
        return new RawFood();
    }
}

// RawDrinksFactory class
class RawDrinksFactory implements HotelComponentFactory {
    @Override
    public HotelComponent createComponent() {
        return new RawDrinks();
    }
}

// CleanserFactory class
class CleanserFactory implements HotelComponentFactory {
    @Override
    public HotelComponent createComponent() {
        return new Cleanser();
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

// PaymentStrategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// ConcreteStrategy implementations
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using credit card " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal with email " + email);
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

// HotelFacade class with PaymentStrategy
class HotelFacade {
    private HotelDirector hotelDirector;
    private HotelComponentFactory visitorFactory;
    private HotelComponentFactory roomFactory;
    private HotelComponentFactory barFactory;
    private HotelComponentFactory receptionWorkerFactory;
    private HotelComponentFactory restaurantWorkerFactory;
    private PaymentStrategy paymentStrategy;

    public HotelFacade(PaymentStrategy paymentStrategy) {
        this.hotelDirector = HotelDirector.getInstance();
        this.visitorFactory = new VisitorFactory();
        this.roomFactory = new RoomFactory();
        this.barFactory = new BarFactory();
        this.receptionWorkerFactory = new ReceptionWorkerFactory();
        this.restaurantWorkerFactory = new RestaurantWorkerFactory();
        this.paymentStrategy = paymentStrategy;
    }

    public void openHotel() {
        hotelDirector.interact();
    }

    public void simulateDay() {

        HotelComponent visitor = visitorFactory.createComponent();

        // Set the payment strategy for the visitor
        if (visitor instanceof Visitor) {
            ((Visitor) visitor).setPaymentStrategy(paymentStrategy);
        }

        visitor.interact();


        HotelComponent receptionWorker = receptionWorkerFactory.createComponent();
        receptionWorker.interact();

        HotelComponent room = roomFactory.createComponent();
        room.interact();

        HotelComponent bar = barFactory.createComponent();
        bar.interact();

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

        // Simulate payment using the chosen strategy
        HotelComponent visitorWithPayment = visitor.cloneComponent();
        visitorWithPayment.interact();
    }
}

// Modified HotelSimulation class
public class HotelSimulation {
    public static void main(String[] args) {
        PaymentStrategy paymentStrategy = new CreditCardPayment("1234-5678-9876-5432");

        HotelFacade hotelFacade = new HotelFacade(paymentStrategy);

        hotelFacade.openHotel();

        for (int i = 0; i < 5; i++) {
            System.out.println("Day " + (i + 1));

            hotelFacade.simulateDay();

            System.out.println();
        }
    }
}
