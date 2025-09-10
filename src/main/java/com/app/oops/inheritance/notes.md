Inheritance provides a way to create a new class from an existing class. 
The new class is a specialized version of the existing class such that it 
inherits all the public attributes (variables) and methods of the existing class. 
The existing class is used as a starting point or base to create the new class.


Inheritance allows a class (subclass or derived class) to inherit properties and behaviors from another class (superclass or base class). 
It promotes code reuse and creates a hierarchical relationship between classes. 
Think of inheritance like a family tree, where children inherit traits from their parents, 
and grandchildren inherit traits from both their parents and grandparents. 
The subclass can extend and specialize the functionality of its superclass, reducing code duplication.



Drawbacks of inheritance

While Inheritance promotes code reuse, its overuse can complicate designs. Here are the key drawbacks to consider:

Tight coupling: Subclasses depend heavily on their superclass. Changes to the superclass, such as modifying the Animal class’s eat() method, 
can break subclasses like Dog or Cat, making the code harder to maintain.

Inappropriate behavior inheritance: Inheritance can force subclasses to inherit behaviors that don’t apply. 
For example, adding a fly() method to the Animal superclass assumes all subclasses (e.g., Penguin) can fly, 
leading to errors or awkward workarounds like throwing exceptions.

Limited flexibility: Inheritance locks in relationships at design time. 
If you later need a RobotDog that barks but doesn’t eat, 
it can’t inherit from Animal without inheriting irrelevant methods.


Given inheritance’s limitations, it’s helpful to compare it with composition, another way to structure classes. Inheritance creates an “is-a” relationship, 
where a subclass is a type of its superclass (e.g., Dog is an Animal). 
Composition creates a “has-a” relationship, where a class contains other objects to provide its behavior, such as installing apps on a phone for specific tasks.

Consider the Dog and RobotDog scenario. Using inheritance, 
RobotDog extends Animal to inherit bark(), but it also gets eat(), 
which doesn’t apply, causing issues like exceptions. 
Using composition, you define a BarkBehavior interface with a bark() method. 
Dog and RobotDog each have a BarkBehavior object, implemented differently (e.g., DogBark for “Woof!” and RobotBark for “Beep!”). 
This lets RobotDog bark without inheriting eat().