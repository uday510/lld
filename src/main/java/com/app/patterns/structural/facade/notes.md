The Facade Pattern provides a simplified interface to a complex subsystem.
It hides the complexity of the subsystem from the user,
allowing the client to interact with the system through a simpler, unified interface.

In software engineering, a common real-world example of the Facade Pattern is an API Gateway in a 
microservices' architecture.

**Problem Without Facade**

In a microservices' architecture, each microservice can have its own API for specific business logic,
such as user management, order processing, and inventory, If the client needs to interact with these
microservices, it would need to directly communicate with all the individual services, This would increase
the complexity of the client code, tightly couple the client of all the microservices, and expose the inner
workings of the system.


**Solution(Using Facade)**

The API Gateway acts as a facade, providing a unified interface to the client while handling communication
with the underlying microservices, it simplifies client interactions, reduces network calls, and abstracts
away the complexity of dealing with multiple services.
