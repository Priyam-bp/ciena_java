package Collection_Framework;

import java.util.HashMap;
import java.util.Map;

public class HashMap_prac {
    
    public static void main(String[] args) {
        // HashMap is a collection of key-value pairs

        // Create a HashMap
        HashMap<Integer,String> map = new HashMap<>();

        // Add key-value pairs
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");

        // Access a value
        System.out.println(map.get(1));

        // Remove a key-value pair
        map.remove(1);
        System.out.println(map);

        // Check if a key exists
        System.out.println(map.containsKey(2));

        // Check if a value exists
        System.out.println(map.containsValue("Three"));

        // Size of the HashMap
        System.out.println(map.size());

        // Loop through the HashMap
        for(int i: map.keySet()){
            System.out.println(map.get(i));
        }

        // Clear the HashMap
        // map.clear();

        // Check if the HashMap is empty
        System.out.println(map.isEmpty());

        // Check and put
        if(!map.containsKey(6)){
            map.put(6, "Six");
        }
        System.out.println(map);

        map.putIfAbsent(7,"Seven");

        for(Map.Entry<Integer,String> entry: map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
