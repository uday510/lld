
use case diagram describes the specification of users and their possible interactions with the system.
These possible interactions are called use cases.

Components of a use case diagram:

    1. Actor : Users are called actors. they interact with the system. they could be humans, 
                machines/hardware, or other external systems. There are 2 types of actors.

        Primary actors: These are the humans or external systems that interact with that system and are 
                        responsible for initiating the use case. They are places on the left side in a
                        use case diagram. Primary actors are also called active actors.

        Secondary actors: These are the ones that are used by the system to assist the primary actors in a use case. 
                          They cannot interact with the system on their own. They need primary actors to initiate a 
                          use case. Secondary actors are also called passive actors, and they are placed on the right
                          side in a use case diagram.

    
    2. Use case: This is a single function performed on a system by an actor. it is represendted by oval shape.
    
    3. Package: This is a group of different elements. These groups are represented inside a folder icon.
    
    4. Note: This is used to add additional information about any component or relationship in a usecase diagram.


Relationships in use case diagrams

    Four different types of relationships in a use case diagram:
    
    1. Association: This shows the relationship between and among actor(s)
    
    2. Generalization: This relationship is also known as inheritance.

    3. Include: We use this to show the relationship between two use cases. it shows
                one case includes the behavior of another use case (<<include>>).
    
    4. Extend: We use this to show the relationship between two use cases. it shows 
               one case extends the behavior of another case case (<<extend>>).


Example:    

        we have a small ATM (automated teller machine) transaction system where customers can transfer
        funds and make payments. To validate the funds, the transfer system has to check if a sufficient 
        amount of funds is available. Otherwise, an error message will be displayed. To make a payment, 
        a customer has two choices. It can either pay via a current account or a savings account.


Benefits:
    
    - It explains the flow and objective of all use cases.
    - It helps in understanding the high-level functional requirements of the system.
    - It defines a system's context and needs.
    - It explains system behavior from a user perspective.
    - It explains the scope of the system.

