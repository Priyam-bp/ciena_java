package java11_features;

import java.util.ArrayList;

public class Local_Var {

    // var z = 10; // Var can only be used locally not globally 
    public static void main(String[] args) {
        int a = 9;
        var b = 10;

        int c;
        // var d; // this gives error
        // var needs to be initialised as the type of the variable is decided based on the value at compile time
    
        int arr[] = new int[10];
        var arr1 = new int[10];// in var no need to tell the type is arry on the left side as long as it is mentioned on the right side

        var al = new ArrayList<>();
    }
}
