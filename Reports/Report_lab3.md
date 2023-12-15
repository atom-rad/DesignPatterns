# Structural Design Patterns


## Author: Ciumac Alexei, FAF-212

## Objectives:

* Get familiar with the Structural Design Patterns;
* Choose a specific domain;
* Develop the project for the specific domain using Structural Design Patterns;

## Theory:
`Structural design patterns` are concerned with how classes and objects can be composed, to form larger structures. The structural design patterns simplify the structure by identifying the relationships. These patterns focus on, how the classes inherit from each other and how they are composed from other classes.

`Bridge` pattern that decouple an abstraction from its implementation so that the two can vary independently

`Facade` pattern provides a unified and simplified interface to a set of interfaces in a subsystem, therefore it hides the complexities of the subsystem from the client. In other words, Facade pattern describes a higher-level interface that makes the sub-system easier to use.

`Proxy` pattern provides the control for accessing the original object. So, we can perform many operations like hiding the information of original object, on demand loading etc.


## Used Structural Design Patterns: 

* Abstract Factory
* Bridge
* Facade
* Proxy


## Implementation

One of design patterns that i added is `Abstract Factory`:

```
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
```

```
            HotelComponent rawFood = rawFoodFactory.createComponent();
            rawFood.interact();

            HotelComponent rawDrinks = rawDrinksFactory.createComponent();
            rawDrinks.interact();

            HotelComponent cleanser = cleanserFactory.createComponent();
            cleanser.interact();

```
I've added the new classes *RawFood*, *RawDrinks*, and *Cleanser* representing the raw materials, and their corresponding factories *RawFoodFactory*, *RawDrinksFactory*, and *CleanserFactory*. The simulation now includes the arrival of raw materials every day, and the factories create instances of these raw materials.




Another pattern used is `Facade`. I created a *HotelFacade* class that acts as a simplified interface to interact with various components of the hotel. The *HotelFacade* class can delegate the operations to the existing classes in the simulation. Here is implemetnation:
```
// HotelFacade class
class HotelFacade {
    private HotelDirector hotelDirector;
    private HotelComponentFactory visitorFactory;
    private HotelComponentFactory roomFactory;
    private HotelComponentFactory barFactory;
    private HotelComponentFactory receptionWorkerFactory;
    private HotelComponentFactory restaurantWorkerFactory;

    public HotelFacade() {
        this.hotelDirector = HotelDirector.getInstance();
        this.visitorFactory = new VisitorFactory();
        this.roomFactory = new RoomFactory();
        this.barFactory = new BarFactory();
        this.receptionWorkerFactory = new ReceptionWorkerFactory();
        this.restaurantWorkerFactory = new RestaurantWorkerFactory();
    }

    public void openHotel() {
        hotelDirector.interact();
    }

    public void simulateDay() {
        HotelComponent visitor = visitorFactory.createComponent();
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
    }
}
```

```
// Modified HotelSimulation class
public class HotelSimulation {
    public static void main(String[] args) {
        HotelFacade hotelFacade = new HotelFacade();

        // Open the hotel
        hotelFacade.openHotel();

        for (int i = 0; i < 5; i++) {
            System.out.println("Day " + (i + 1));

            // Simulate a day using the Facade
            hotelFacade.simulateDay();

            System.out.println(); // Add a line break for better readability
        }
    }
}
```
In this modification, the *HotelFacade* class encapsulates the interactions with the hotel components. The *simulateDay* method of the *HotelFacade* simplifies the process of simulating a day at the hotel, and the client code interacts with the facade rather than individual components.


## Conclusions / Screenshots / Results 

Here is the output:
```
Hotel director announces: "Hotel is open now!"
Day 1
Visitor is interacting.
Reception worker is assisting a visitor.
Visitor gets a room.
Room is being used.
Bar is serving drinks.
Restaurant worker is taking an order from a visitor.
Visitor is having Spaghetti Bolognese in the restaurant.
Visitor is interacting.
Room is being used.
Bar is serving drinks.
Reception worker is assisting a visitor.
Visitor gets a room.
Restaurant worker is taking an order from a visitor.
Visitor is having Chicken Alfredo in the restaurant.

Day 2
Visitor is interacting.
Reception worker is assisting a visitor.
Visitor gets a room.
Room is being used.
Bar is serving drinks.
Restaurant worker is taking an order from a visitor.
Visitor is having Chicken Alfredo in the restaurant.
Visitor is interacting.
Room is being used.
Bar is serving drinks.
Reception worker is assisting a visitor.
Visitor gets a room.
Restaurant worker is taking an order from a visitor.
Visitor is having Caesar Salad in the restaurant.
```
Incorporating the Abstract Factory pattern enhanced the hotel simulation by introducing a systematic approach to creating families of related objects, such as workers, visitors, and raw materials. This design pattern promotes flexibility, scalability, and encapsulation in managing the instantiation of diverse components.

The addition of the Facade pattern further improved the simulation's overall structure by simplifying the client's interactions. The HotelFacade class provides a unified and straightforward interface, shielding the client from the complexities of individual components. This results in a more intuitive and maintainable codebase, facilitating the simulation's extension and modification in the future. Together, these design patterns contribute to a more modular, organized, and extensible hotel simulation system.
