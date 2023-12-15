# Behavioral Design Patterns


## Author: Ciumac Alexei, FAF-212

## Objectives:

* Get familiar with the Behavioral Design Patterns;
* Choose a specific domain;
* Develop the project for the specific domain using Behavioral Design Patterns;

## Theory:

`Behavioral design patterns` are concerned with the interaction and responsibility of objects. In these design patterns, the interaction between the objects should be in such a way that they can easily talk to each other and still should be loosely coupled. 

`Strategy` is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.

## Used Behavioral Design Patterns:

* Strategy

## Implementation
Implemented different types of payment strategies:
```
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

```
```
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
```
## Conclusions / Screenshots / Results 
Here is the output
```
Hotel director announces: "Hotel is open now!"
Day 1
Visitor is interacting.
Paid $100.0 using credit card 1234-5678-9876-5432
Reception worker is assisting a visitor.
Visitor gets a room.
Room is being used.
Bar is serving drinks.
Restaurant worker is taking an order from a visitor.
Visitor is having Spaghetti Bolognese in the restaurant.
Visitor is interacting.
Paid $100.0 using credit card 1234-5678-9876-5432
Room is being used.
Bar is serving drinks.
Reception worker is assisting a visitor.
Visitor gets a room.
Restaurant worker is taking an order from a visitor.
Visitor is having Vegetarian Pizza in the restaurant.
Visitor is interacting.
Paid $100.0 using credit card 1234-5678-9876-5432

Day 2
Visitor is interacting.
Paid $100.0 using credit card 1234-5678-9876-5432
Reception worker is assisting a visitor.
Visitor gets a room.
Room is being used.
Bar is serving drinks.
Restaurant worker is taking an order from a visitor.
Visitor is having Vegetarian Pizza in the restaurant.
Visitor is interacting.
Paid $100.0 using credit card 1234-5678-9876-5432
Room is being used.
Bar is serving drinks.
Reception worker is assisting a visitor.
Visitor gets a room.
Restaurant worker is taking an order from a visitor.
Visitor is having Grilled Salmon in the restaurant.
Visitor is interacting.
Paid $100.0 using credit card 1234-5678-9876-5432
```
