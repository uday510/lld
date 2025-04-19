Why use SOLID principles?
        - The code may become tightly coupled with several components, which makes it difficult to integrate new 
          features or bug fixes and sometimes leads to unidentified problems.
        - The code will be untestable, which effectively means that every change will need end-to-end testing.
        - The code may have a lot of duplication.
        - Fixing one issue results in additional errors.



Liskov Substitution Principle (LSP):
    The Liskov Substitution Principle (LSP) is one of the fundamental design principles of object-oriented design. 
    The LSP helps guide the use of inheritance in design so that the application does not break. 
    It states that the objects of a subclass should behave the same way as the objects of the superclass,
    such that they are replaceable. This rule generally applies to abstraction concepts like inheritance and polymorphism.


The Interface Segregation Principle (ISP)
    is a design principle that does not recommend having methods that an interface would not use and require.
    Therefore, it goes against having fat interfaces in classes and prefers having small interfaces with a
    group of methods, each serving a particular purpose.


The Dependency Inversion Principle (DIP)
    states that high-level modules should not depend on low-level modules, but rather both should depend on abstractions. 
    The abstractions should not depend on details. Instead, the details should depend on abstractions.
    The DIP reduces the number of dependencies among modules. It provides a layer of abstraction between lower and higher classes, allowing for changes in the lower class without making changes in the higher class. A few benefits of the DIP are as follows:
    It allows for the flexibility and stability of the software. 
    It allows for the reusability of the application modules.



