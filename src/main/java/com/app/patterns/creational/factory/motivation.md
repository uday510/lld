Consider an example of a transportation service app where users can request
different types of transport vehicles (e.g., Car, Bike, Bus). You might initially
create separate classes for each type, and create instances like this:

Car car = new Car();
Bike bike = new Bike();

but as the system evolves, managing object creation directly like this can
become complex, especially when adding new types of vehicles