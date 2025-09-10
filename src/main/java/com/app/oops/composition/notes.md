Given inheritance’s limitations, it’s helpful to compare it with composition, another way to structure classes. 
Inheritance creates an “is-a” relationship, where a subclass is a type of its superclass (e.g., Dog is an Animal). 
Composition creates a “has-a” relationship, where a class contains other objects to provide its behavior, 
such as installing apps on a phone for specific tasks.

Consider the Dog and RobotDog scenario. Using inheritance, RobotDog extends Animal to inherit bark(), 
but it also gets eat(), which doesn’t apply, causing issues like exceptions. Using composition, 
you define a BarkBehavior interface with a bark() method. Dog and RobotDog each have a BarkBehavior object, 
implemented differently (e.g., DogBark for “Woof!” and RobotBark for “Beep!”). 
This lets RobotDog bark without inheriting eat().



When to use composition?

To choose between inheritance and composition, follow these guidelines:

Design Choice: Use inheritance for clear “is-a” relationships with stable, shared behaviors. 
Choose composition for “has-a” relationships or when you need flexible, swappable behaviors,
as it’s easier to modify and maintain.

Interview Strategy: In OOD interviews, favor composition when flexibility or loose coupling is key, 
as it’s preferred in modern design. 
For example, explain how RobotDog uses BarkBehavior to avoid inheritance’s tight coupling. 
Highlight inheritance’s use for simple hierarchies, but note its drawbacks.