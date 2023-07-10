import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
            System.out.println("Passenger added successfully.");
        } else {
            System.out.println("The travel package is already full. Cannot add more passengers.");
        }
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        System.out.println("Itinerary:");
        for (Destination destination : itinerary) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Activities:");
            for (Activity activity : destination.getActivities()) {
                System.out.println("- Name: " + activity.getName());
                System.out.println("  Cost: " + activity.getCost());
                System.out.println("  Capacity: " + activity.getCapacity());
                System.out.println("  Description: " + activity.getDescription());
            }
            System.out.println();
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers Enrolled: " + passengers.size());
        System.out.println("Passenger List:");
        for (Passenger passenger : passengers) {
            System.out.println("- Name: " + passenger.getName());
            System.out.println("  Number: " + passenger.getNumber());
        }
    }

    public void printPassengerDetails(Passenger passenger) {
        System.out.println("Passenger Details:");
        System.out.println("Name: " + passenger.getName());
        System.out.println("Number: " + passenger.getNumber());
        if (passenger instanceof StandardPassenger) {
            System.out.println("Balance: " + ((StandardPassenger) passenger).getBalance());
        }
        System.out.println("Activities Signed Up:");
        for (Activity activity : passenger.getActivities()) {
            System.out.println("- Destination: " + activity.getDestination().getName());
            System.out.println("  Price Paid: " + activity.getPriceForPassenger(passenger));
        }
    }

    public void printAvailableActivities() {
        System.out.println("Available Activities:");
        for (Destination destination : itinerary) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Activities with Available Spaces:");
            for (Activity activity : destination.getActivities()) {
                if (!activity.isFull()) {
                    System.out.println("- Name: " + activity.getName());
                    System.out.println("  Available Spaces: " + activity.getAvailableSpaces());
                }
            }
            System.out.println();
        }
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}

class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}

abstract class Passenger {
    private String name;
    private int number;

    public Passenger(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public abstract List<Activity> getActivities();
}

class StandardPassenger extends Passenger {
    private double balance;
    private List<Activity> activities;

    public StandardPassenger(String name, int number, double balance) {
        super(name, number);
        this.balance = balance;
        this.activities = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}

class GoldPassenger extends Passenger {
    private List<Activity> activities;

    public GoldPassenger(String name, int number) {
        super(name, number);
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}

class PremiumPassenger extends Passenger {
    private List<Activity> activities;

    public PremiumPassenger(String name, int number) {
        super(name, number);
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}

class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;
    private List<Passenger> passengers;

    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
        this.passengers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public Destination getDestination() {
        return destination;
    }

    public boolean isFull() {
        return passengers.size() >= capacity;
    }

    public int getAvailableSpaces() {
        return capacity - passengers.size();
    }

    public double getPriceForPassenger(Passenger passenger) {
        if (passenger instanceof GoldPassenger) {
            return cost * 0.9;
        } else {
            return cost;
        }
    }

    public void addPassenger(Passenger passenger) {
        if (!isFull()) {
            passengers.add(passenger);
            if (passenger instanceof StandardPassenger) {
                double price = getPriceForPassenger(passenger);
                ((StandardPassenger) passenger).deductBalance(price);
            } else if (passenger instanceof GoldPassenger) {
                getPriceForPassenger(passenger);
                ((GoldPassenger) passenger).addActivity(this);
            }
            System.out.println("Passenger added to the activity successfully.");
        } else {
            System.out.println("Activity is already full. Cannot add more passengers.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create travel package
        System.out.print("Enter travel package name: ");
        String packageName = scanner.nextLine();
        System.out.print("Enter passenger capacity: ");
        int passengerCapacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        TravelPackage travelPackage = new TravelPackage(packageName, passengerCapacity);

        // Create destinations
        System.out.print("Enter number of destinations: ");
        int numDestinations = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 1; i <= numDestinations; i++) {
            System.out.print("Enter destination name " + i + ": ");
            String destinationName = scanner.nextLine();
            Destination destination = new Destination(destinationName);

            // Create activities
            System.out.print("Enter number of activities for destination " + i + ": ");
            int numActivities = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            for (int j = 1; j <= numActivities; j++) {
                System.out.print("Enter activity name " + j + " for destination " + i + ": ");
                String activityName = scanner.nextLine();
                System.out.print("Enter activity description " + j + " for destination " + i + ": ");
                String activityDescription = scanner.nextLine();
                System.out.print("Enter activity cost " + j + " for destination " + i + ": ");
                double activityCost = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter activity capacity " + j + " for destination " + i + ": ");
                int activityCapacity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                Activity activity = new Activity(activityName, activityDescription, activityCost, activityCapacity, destination);
                destination.addActivity(activity);
            }

            travelPackage.addDestination(destination);
        }

        // Create passengers
        System.out.print("Enter number of passengers: ");
        int numPassengers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 1; i <= numPassengers; i++) {
            System.out.print("Enter passenger name " + i + ": ");
            String passengerName = scanner.nextLine();
            System.out.print("Enter passenger number " + i + ": ");
            int passengerNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.print("Select passenger type (1: Standard, 2: Gold, 3: Premium): ");
            int passengerType = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Passenger passenger;

            switch (passengerType) {
                case 1:
                    System.out.print("Enter passenger balance " + i + ": ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    passenger = new StandardPassenger(passengerName, passengerNumber, balance);
                    break;
                case 2:
                    passenger = new GoldPassenger(passengerName, passengerNumber);
                    break;
                case 3:
                    passenger = new PremiumPassenger(passengerName, passengerNumber);
                    break;
                default:
                    System.out.println("Invalid passenger type. Setting passenger as Standard.");
                    passenger = new StandardPassenger(passengerName, passengerNumber, 0.0);
                    break;
            }

            travelPackage.addPassenger(passenger);
        }

        // Perform operations based on user input
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("Select an operation:");
            System.out.println("1. Print Itinerary");
            System.out.println("2. Print Passenger List");
            System.out.println("3. Print Passenger Details");
            System.out.println("4. Print Available Activities");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    travelPackage.printItinerary();
                    break;
                case 2:
                    travelPackage.printPassengerList();
                    break;
                case 3:
                    System.out.print("Enter passenger number: ");
                    int passengerNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Passenger selectedPassenger = null;

                    for (Passenger passenger : travelPackage.getPassengers()) {
                        if (passenger.getNumber() == passengerNumber) {
                            selectedPassenger = passenger;
                            break;
                        }
                    }

                    if (selectedPassenger != null) {
                        travelPackage.printPassengerDetails(selectedPassenger);
                    } else {
                        System.out.println("Passenger not found.");
                    }

                    break;
                case 4:
                    travelPackage.printAvailableActivities();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
