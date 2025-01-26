package java11_features;

import java.util.stream.Stream;

public class newString_func {
    public static void main(String[] args) {
        String str1 = "Hello this is not blank \n next line \r next line \n last line";
        String str2 = "";

        // is blank
        System.out.println(str1.isBlank());
        System.out.println(str2.isBlank());

        //line
        Stream<String> lines = str1.lines();
        lines.forEach(n->System.out.println(n));

        //strip()

        String str3 = "                before this is blank space              ";
        System.out.println(str3.strip()); //befre & after both
        System.out.println(str3.stripLeading()); // only before
        System.out.println(str3.stripTrailing()); // only after

        // repeat()

        String str4 = "@";
        String newStr4 = str4.repeat(4);
        System.out.println(newStr4);


        
    }
}
