
Class diagrams are used to show the shared roles and responsibilities of the entities that produce the behavior of the system.


Why use class diagrams ?
    
    - Represents the system's static structure.
    - Directly maps with object-oriented languages.
    - Represents what the system's duties or responsibilites are.
    - Uses in both forward and reverse engineering.

Most used notations in the class diagram

    - Class notation
    - Interface, abstract class, and enumeration
    - Access modifiers


Class notation:
    
    A class is represented by a rectangle with 3 sections. The first section
    holds the class name, the second one lists the attributes, and third one 
    shows the methods.

    check movie.png


Interface, abstract class, and enumeration
        
    We can declare a class as abstract using abstract keyword. The class name 
    printed in italic. we can use the interface, annotation,and enum keywords too.

    check iae.png


Access modifiers

    Public: A public member can be seen anywhere in the system. represented by + symbol.
    
    Private: Members can only be accessible from within the class. represented by - symbol.

    Protected: Only access accessible within the class and derived class. represented by # symbol.
    
    check access_modifiers.png

Association:
    
    Association provides a mechanism to one object with another object.
    
    Association represents the relationship between classes.

    Association can be divided into two categories
    1. Class association (inheritance), 2. Object association

    check association.png

Class Association
    
    Apart from its own behaviors and attributes, the child class inherits the 
    characterstics of its parent(s). A solid line leads from the child class 
    to the parent class with a hollow arrowhead representing the inheritance
    relationship.
    
    check class_association.png
    

Object Association 
    
    1. Simple association
        
        The weakest connections between objects are made through simple association.
        it is achieved through reference, which one object can inherit from another.
        
        check simple_association.png


    2. Aggregation  
        
        Aggregation describes the relationship between the container and the object it contains.
        And object may contains an aggregate of another object. Aggregation is denoted by a line an 
        unfilled diagram head towards the container.

        Aggregation is a weeker relationship because: 

        - Aggregate objects are not a part of the container.
        - Aggregate objects can exist independently.

        check aggregation.png

    3. Composition

        An object may be composed of smaller objects, and the relationship between the "part" objects
        and whole "objects" is known as composition.

        Composition is a strong relationship because:
        
        - The composed object becomes a part of the composer.
        - Composed objects cannot exist independently.


        Example: composition.png

            Chair class can be composed of other objects of Arm, Seat and Leg types.
            Composition is denoted by a line with a filled diamond head at the composer class 
            pointing to the component class.

            


    

        