# travelmanagementinjava
This is a travel agency management system using JAVA
This project is a Java program that manages a travel package. It allows users to create travel packages with multiple destinations and activities, add passengers to the package, and perform various operations such as printing the itinerary, passenger list, passenger details, and available activities.

The program consists of several classes:

TravelPackage: Represents a travel package and contains information such as the package name, passenger capacity, itinerary (list of destinations), and list of passengers. It provides methods to add destinations, add passengers, and perform operations like printing the itinerary, passenger list, passenger details, and available activities.

Destination: Represents a destination within the travel package. It has a name and a list of activities. It provides methods to add activities and retrieve the list of activities.

Passenger: An abstract class representing a passenger. It has attributes like name and number. It also has an abstract method getActivities() that needs to be implemented by subclasses.

StandardPassenger, GoldPassenger, and PremiumPassenger: Subclasses of Passenger representing different types of passengers. They have additional attributes specific to each type, such as balance for the StandardPassenger. They also have a list of activities they have signed up for.

Activity: Represents an activity within a destination. It contains information such as name, description, cost, capacity, and the destination it belongs to. It also keeps track of the passengers who have signed up for the activity. It provides methods to add passengers, check if it is full, get available spaces, and calculate the price for a passenger.

Main: The main class that contains the main method. It interacts with the user through the command line to create the travel package, destinations, activities, and passengers. It also provides a menu-driven interface for the user to perform operations on the travel package.

The program uses Scanner to read input from the user and presents a menu with options for the user to choose from. The chosen operation is then executed using the methods defined in the TravelPackage class.
