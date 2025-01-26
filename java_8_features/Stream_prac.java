package java_8_features;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Stream_prac {

    // Stream does not change original data it provides result based on the operation
    // 2 step process: input ->[ intermediate operation -> terminal operation ]-> output
    // intermediate operation: filter, map, sorted
    // terminal operation: forEach, reduce, collect
    
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(4);
        list.add(3);

        // Stream data = list.stream();
        // data.forEach(n -> System.out.println(n));
        
        
        // once stream is used/ operated on it cannot be used again 
        // data.forEach(n -> System.out.println(n)); 

        // Builder variable 
        list.stream() // stream 1
                    .filter(n -> n%2 ==1) // stream 2
                    .sorted() // stream 3
                    .map(n -> n*2) // stream 4
                    .forEach(n -> System.out.println(n));

        int res = list.stream()
                    .filter(n -> n%2 == 1)
                    .map(n -> n*2)
                    .reduce(0, (n1,n2)->(n1+n2));

        System.out.println(res);
    }
}
