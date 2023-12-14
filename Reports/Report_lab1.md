# SOLID Principles


## Author: Ciumac Alexei, FAF-212

## Objectives:

* Get familiar with the SOLID Principles;
* Choose a specific domain;
* Develop the project for the specific domain taking into account SOLID Principles;

## Theory:
SOLID is a set of five object-oriented design principles intended to make software designs more maintainable, flexible, and easy to understand. The acronym stands for Single Responsibility Principle, Open-Closed Principle, Liskov Substitution Principle, Interface Segregation Principle, and Dependency Inversion Principle. Each principle addresses a specific aspect of software design, such as the organization of responsibilities, the handling of dependencies, and the design of interfaces. By following these principles, software developers can create more modular, testable, and reusable code that is easier to modify and extend over time. These principles are widely accepted and adopted in the software development community and can be applied to any object-oriented programming language.

## Used SOLID Principles: 

* Single Responsibility principle
* Open-Closed principle
* Liskov Substitution principle
* Interface Segregation principle
* Dependency Inversion principle


## Project Description and SOLID Implementation

The project I decided to develop throughout this course is nothiing else but a Hotel simulation. Here, for example, I have the `Single Responsibility  principle`:
```
import java.util.Random;

public class Barman extends RestStaff{

    public Barman(String name) {
        super(name);
    }
   

	private String[] drink = {"Water", "lemon jucie", "orange juice", "green tea", "latte", "Ice coffe"};


    public void servDrink(Visitor v) {
				
		Random r = new Random();
		int d = r.nextInt(drink.length);
		
		System.out.println(v.getType()+" visitor was served by "+name+" with " + drink[d]);
	}
}


```
`Open-Closed principle` is realized through inheritance. Objects `Chev`, `Barman`, `Cook` extend functionality of `Staff`, but do not modify it. And `Liskov Substitution principle` cause they can do what the parent class can.
```
import java.util.Random;

public class Chev extends RestStaff{

    public Chev(String name) {
        super(name);
    }

    private String[] newNation = {"italian", "russian", "moldovan", "french"};
    
    public String[] getNationState(){
        return this.newNation;
      }

    public void setNation() {

        Random r = new Random();
        int n = r.nextInt(newNation.length);
		System.out.println("The main food is from " + newNation[n] + " cuisine");

    }


}
```

```
import java.util.Random;

public class Cook extends RestStaff{

    public Cook(String name) {
        super(name);
    }

	private String[] food = {"Steak", "Mamaliga", "Soup", "Pizza", "Borsch", "Ice cream"};


    public void servFood(Visitor v) {
		
		Random r = new Random();
		int f = r.nextInt(food.length);
		
		System.out.println(v.getType()+" visitor was served by "+name+" with " + food[f]);
	}
}
```

In this way I managed to make my project corresponding to the `interface segregation principle`.
```
public class Hotel implements WorkingStateHotel {

    private boolean opened;

    public boolean getHotelState() {
        return this.opened;
    }

    @Override
    public void open() {
        this.opened = true;
        System.out.println("The hotel is opened");    
    }

    @Override
    public void close() {
        this.opened = false;
        System.out.println("The hotel is closed");    
    }

}
```
```
public class HotelStaff extends Staff {

    public HotelStaff(String name) {
        super(name);
    }
    

}
```



 By using interfaces in my project I also reached `dependency inversion` principle because my classes depend on abstract "sets of rules".




## Conclusions / Screenshots / Results

Below I am going to show the output of those methods:
```
The hotel is opened

INITIAL VISITOR ENTERED THE HOTEL
The room was assigned to visitor with id 696223110
The room is cleaned by TBCS
The baggage was moved to a room by Daniil
A table was occupied by the visitor with id 696223110
The main food is from french cuisine
New visitor was served by Abdul with Ice cream
New visitor was served by Yurii with orange juice
The visitor paid for the meal
The table was served by waiter Maria

A table was occupied by the visitor with id 696223110
The main food is from russian cuisine
New visitor was served by Abdul with Soup
New visitor was served by Yurii with orange juice
The visitor paid for the meal
The table was served by waiter Maria

The room was assigned to visitor with id 696223110
The room is cleaned by TBCS
The baggage was moved to a room by Daniil
A table was occupied by the visitor with id 696223110
The main food is from french cuisine
New visitor was served by Abdul with Borsch
New visitor was served by Yurii with Ice coffe
The visitor paid for the meal
The table was served by waiter Maria

The room was assigned to visitor with id 696223110
The room is cleaned by TBCS
The baggage was moved to a room by Daniil
A table was occupied by the visitor with id 696223110
The main food is from french cuisine
New visitor was served by Abdul with Pizza
New visitor was served by Yurii with lemon jucie
The visitor paid for the meal
The table was served by waiter Maria

A table was occupied by the visitor with id 696223110
The main food is from italian cuisine
New visitor was served by Abdul with Steak
New visitor was served by Yurii with lemon jucie
The visitor paid for the meal
The table was served by waiter Maria

The room was assigned to visitor with id 696223110
The room is cleaned by TBCS
The baggage was moved to a room by Daniil
A table was occupied by the visitor with id 696223110
The main food is from russian cuisine
New visitor was served by Abdul with Steak
New visitor was served by Yurii with lemon jucie
The visitor paid for the meal
The table was served by waiter Maria

```

To sum up, the project works as desired and respects all five SOLID Principles.
