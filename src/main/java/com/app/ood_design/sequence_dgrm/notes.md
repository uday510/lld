Sequence Diagram

    A sequence is a form of communication diagram that illustrates how different
    actors and objects interact with each other or between themselves.

    Elements:   
        1. Lifeline
        2. Activation bars
        3. Messages

    Lifeline
        
        Lifeline represents its existance. (lifeline.png)

    Activation bars
        
        Activation bars indicate the active period of an object, that is, the time when
        an object sends or recieves messages. we draw these using simple vertical boxes on
        the lifeline. (activation_bar.png)


    Messages
        
        A Message is an interaction between two objects. it can be in the form of sending 
        and receiving messages. messages are drawn horizontally in any direction: left to right,
        right to left, or back to themselves

        different kinds of messages:
        
        1. Synchronous message:
            
            Where the sender has to wait for receiver to return a response
            before it can perform another operation. (synchronous_message.png)
        
        2. Asynchronous message:
            
            Where the sender does not have to wait for a response from receiver.
            The sender can continue sending messages to other objects. (asynchronous_message.png)

        3. Synchronous return:
            
            Synchronous message has to be paired with a synchronous return message. (synchronous_return.png)

        4. Create message:
            
            Indicates that a new object is create during an interaction. New objects can be created as result of 
            some message or operation. (create_message.png)

        5. Destroy message:
            
            A destroy message indicates than an object is destroyed during a sequence
            of events. Object can be destroyed as a result of some message or operation,
            and their lifeline ends. (destroy_message.png)

        6. Lost message:
            
            A lost message is a message that initiates from an object but does not
            reach its endpoint. it appears as a message that is terminated. A found message 
            is a message that is received, but the sender is unknown. it appears as a message
            that reaches an endpoint but does not initiate from any object. We draw lost messages 
            as an arrow ending with a circle and found messages with an arrow starting with a circle.
            (lost_message.png)
