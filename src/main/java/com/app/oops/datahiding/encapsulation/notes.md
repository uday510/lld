Encapsulation is a fundamental programming technique used to achieve data hiding in OOP. Encapsulation in OOP refers to binding data and the methods to manipulate that data together in a single unit—class.

Encapsulation is usually done to hide the state and representation of an object from the outside. A class can be thought of as a capsule with methods and attributes inside it.

When encapsulating classes, a good convention is to declare all variables of a class private. This will restrict direct access by the code outside that class.

At this point, a question can be raised. If the methods and variables are encapsulated in a class, how can they be used outside that class? The answer to this is simple. One has to implement public methods to let the outside world communicate with this class. These methods are called getters and setters. We can also implement other custom methods.