Abstraction can simplify complex systems by hiding unnecessary details. 
It separates the "what" an object does from the "how" it does it, 
enabling users to interact with objects through simplified interfaces. 
For example, the volume button on a television remote control provides
a simple way to adjust sound without exposing the TV’s internal circuitry. 
In programming, abstraction is achieved using mechanisms like abstract classes and interfaces.




When to use abstraction?

Abstraction is particularly useful in the following scenarios:

Simplifying complex systems: Abstraction helps provide a clean and consistent interface for complex functionality. For example, in the Shape class, abstraction allows users to call area() without understanding the mathematical calculations, making the system easier to use.
Promoting code flexibility: When you anticipate that subclasses will provide specific implementations of generalized behavior, abstraction becomes essential. The Shape class’s abstract area() method ensures that shapes like circles or rectangles implement their area calculations, allowing flexibility in design.
Supporting extensibility: Abstraction makes it easier to extend systems without modifying existing code. For instance, adding a new shape like Triangle to the Shape hierarchy only requires implementing area(), without changing existing code that uses shapes.