package java_17_features;

public class pattern_matching_prac {
    public static void main(String[] args) {
        // check("a");

        check2(2);
        check2(1);
        check2(4);
    }

    public static void check(String str){
        String res = switch(str){
            case "a" -> "this is a";
            case "b" -> "this is b";
            default -> "unknown";
        };
        System.out.println(res);
    }

    public static void check2(int i){
        switch(i){
            case 1 -> check("a");
            case 2 -> check("b");
            default -> throw new IllegalArgumentException("This is not a valid input");
        }
    }
}
