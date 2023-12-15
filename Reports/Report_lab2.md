# Creational Design Patterns


## Author: Ciumac Alexei, FAF-212

## Objectives:

* Get familiar with the Creational Design Patterns;
* Choose a specific domain;
* Develop the project for the specific domain using Creational Design Patterns;

## Theory:
`Creational design patterns` are concerned with the way of creating objects. These design patterns are used when a decision must be made at the time of instantiation of a class (i.e. creating an object of a class). 


`Factory` is the design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

`Prototype` pattern lets you copy existing objects without making your code dependent on their classes.

`Singleton` pattern lets you ensure that a class has only one instance, while providing a global access point to this instance.

## Used Creational Design Patterns: 

* Factory
* Prototype
* Singleton


## Implementation
In this laboratory work I have `Singleton` implementaion at the HotelDirector class:
```

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

    // Other methods...

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

```
```
HotelDirector hotelDirector = HotelDirector.getInstance();
```
This line demonstrates the use of the `Singleton` pattern to obtain the single instance of the *HotelDirector* class. The *getInstance* method ensures that there is only one instance of the *HotelDirector* throughout the program.

Also I added the `Factory` to create different types of workers:
```
// HotelComponentFactory interface
interface HotelComponentFactory {
    HotelComponent createComponent();
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

```

```
HotelComponentFactory receptionWorkerFactory = new ReceptionWorkerFactory();
HotelComponent receptionWorker = receptionWorkerFactory.createComponent();
```

Here, the *ReceptionWorkerFactory* is used to create an instance of *ReceptionWorker*. This approach allows for easy extension or modification of the system by introducing new factories without changing the client code.


The last thing I added is `Prototype` here is its implemetation, I implemented the *HotelComponent* interface that declares the *cloneComponent* method, which is used to create a clone of the implementing class:
```
  interface HotelComponent extends Cloneable {
    void interact();
    HotelComponent cloneComponent();
}

// Worker abstract class
abstract class Worker implements HotelComponent {
    // ... other methods ...

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

// Concrete classes implementing cloneComponent
class ReceptionWorker extends Worker {
    // ... other methods ...

    @Override
    public HotelComponent cloneComponent() {
        return new ReceptionWorker();
    }
}

class RestaurantWorker extends Worker {
    // ... other methods ...

    @Override
    public HotelComponent cloneComponent() {
        return new RestaurantWorker();
    }
}
```
```
HotelComponent clonedVisitor = visitor.cloneComponent();
HotelComponent clonedRoom = room.cloneComponent();
HotelComponent clonedBar = bar.cloneComponent();
HotelComponent clonedReceptionWorker = receptionWorker.cloneComponent();
HotelComponent clonedRestaurantWorker = restaurantWorker.cloneComponent();
```

These lines demonstrate the use of the `Prototype` pattern by creating clones of existing components. The *cloneComponent* method allows for the creation of new instances of components that are identical to the original ones.


## Conclusions / Screenshots / Results 
Here is example of output:
```
Hotel director announces: "Hotel is open now!"
Day 1
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
Visitor is having Grilled Salmon in the restaurant.
```
In conclusion I can say the program runs succesfully. However, I have to say that it is not necessary to use all of the creational patterns at the same time, while it can be not quite convenient and in fact not bring any benefits. It is a good practice to use creational design patterns if they are realy needed and simplify our work.
