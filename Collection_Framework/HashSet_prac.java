package Collection_Framework;

import java.util.HashSet;

public class HashSet_prac {
    
    // HashSet is a collection of items where every item is unique.

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(40);

        System.out.println(set); // order of addition in set is not maintained 

        // Check if an element exists
        System.out.println(set.contains(20));

        // Remove an element
        set.remove(20);
        System.out.println(set);

        // Size of the HashSet
        System.out.println(set.size());

        // Loop through the HashSet
        for(int i: set){
            System.out.println(i);
        }

        // Clear the HashSet
        set.clear();
        System.out.println(set);

        // Check if the HashSet is empty
        System.out.println(set.isEmpty());

        // Convert HashSet to Array
        Integer[] setArray = new Integer[set.size()];
        set.toArray(setArray);

        

    }

}
