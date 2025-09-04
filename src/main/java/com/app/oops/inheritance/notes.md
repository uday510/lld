Inheritance

Inheritance allows a class (subclass or derived class) to inherit properties and behaviors from another class (superclass or base class). It promotes code reuse and creates a hierarchical relationship between classes. Think of inheritance like a family tree, where children inherit traits from their parents, and grandchildren inherit traits from both their parents and grandparents. The subclass can extend and specialize the functionality of its superclass, reducing code duplication.


Common patterns of class hierarchy

Building on the concept of inheritance, we now explore common patterns for structuring class hierarchies in Java.
Single inheritance

A subclass extends only one superclass. This is the standard type of inheritance supported in Java. Below is an example of single inheritance.


Single inheritance

A subclass extends only one superclass. This is the standard type of inheritance supported in Java. Below is an example of single inheritance.


Multilevel Inheritance

A subclass that inherits from another subclass, creating a chain of inheritance, is called multilevel inheritance. Consider a scenario with three classes: Animal, Mammal, and Dog, where Animal is the superclass of Mammal, and Mammal is the superclass of Dog.


Hierarchical Inheritance

In hierarchical inheritance, multiple subclasses inherit from the same superclass, forming a hierarchical structure. In the example below, you can see two subclasses, Car and Motorcycle, both inherit from the Vehicle class, forming a hierarchical inheritance relationship.



When to use inheritance?

Inheritance is particularly useful in the following scenarios:

    Whenever we encounter an 'is-a' relationship between objects, we can use inheritance.
    When multiple classes share common attributes or methods, a superclass can define them once, allowing all subclasses to inherit them and avoid duplication.
    When classes form a natural hierarchy, such as Animal being a parent to Dog and Cat, inheritance organizes the structure clearly.

Drawbacks of inheritance

While Inheritance promotes code reuse, its overuse can complicate designs. Here are the key drawbacks to consider:

    Tight coupling: Subclasses depend heavily on their superclass. Changes to the superclass, such as modifying the Animal class’s eat() method, can break subclasses like Dog or Cat, making the code harder to maintain.
    Inappropriate behavior inheritance: Inheritance can force subclasses to inherit behaviors that don’t apply. For example, adding a fly() method to the Animal superclass assumes all subclasses (e.g., Penguin) can fly, leading to errors or awkward workarounds like throwing exceptions.
    Limited flexibility: Inheritance locks in relationships at design time. If you later need a RobotDog that barks but doesn’t eat, it can’t inherit from Animal without inheriting irrelevant methods.

To address these issues, consider alternatives like composition (combining objects) or interfaces, which offer flexibility and loose coupling.