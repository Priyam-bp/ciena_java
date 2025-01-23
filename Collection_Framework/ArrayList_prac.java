package Collection_Framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayList_prac {

    public static void main(String[] args) {
        ArrayList<String> cars = new ArrayList<>();
        
        cars.add("Ferrari");
        cars.add("Lamborghini");
        cars.add("Volvo");
        cars.add("BMW");

        System.out.println(cars);

        // Traversing through iterator
        Iterator itr = cars.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        // Traversing through forEach loop

        for(String car: cars){
            System.out.println(car);
        }

        // Accessing elements
        System.out.println(cars.get(0));

        // Changing elements
        cars.set(0, "Audi");

        // Removing elements
        cars.remove(0);

        // Removing all elements
        // cars.clear();

        // Size of the ArrayList
        System.out.println(cars.size());

        // Check if an element exists
        System.out.println(cars.contains("Volvo"));

        // Convert ArrayList to Array
        String[] carsArray = new String[cars.size()];
        cars.toArray(carsArray);

        for(String car: carsArray){
            System.out.println(car);
        }

        // Sort an ArrayList
        Collections.sort(cars);

        // Reverse an ArrayList
        Collections.reverse(cars);

        // Copy an ArrayList
        ArrayList<String> carsCopy = new ArrayList<>();
        carsCopy.addAll(cars);

        // Shuffle an ArrayList
        Collections.shuffle(cars);

        // Swap two elements
        Collections.swap(cars, 0, 1);

        // Compare two ArrayLists
        System.out.println(cars.equals(carsCopy));

        // Get subList
        System.out.println(cars.subList(0, 2));

    }
}