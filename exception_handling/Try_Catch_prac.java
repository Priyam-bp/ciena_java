package exception_handling;

public class Try_Catch_prac {
    public static void main(String[] args) {
        int i = 0;
        int j = 10;

        // System.out.println(j/i);// this will give error and stop further execution of code
        // System.out.println("Hello World");

        try {
            System.out.println(j/i); //error but code will not stop
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }

        System.out.println("Hello World");

        try {
            int arr[] = new int[5];
            System.out.println(arr[6]);
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        finally{
            System.out.println("Finally block is always executed.");
        }

        // checkAge(17);

        int a = 3;
        int b = 20;

        try {
            b = b/a;
            if(a == 3){
                throw new ArithmeticException("Special case");
            }
        }
         catch (ArithmeticException e){
            System.out.println("arithemetic exc");
        }
         catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static void checkAge(int age){
        if(age<18){
            throw new ArithmeticException("Under age to vote");
        }
        else{
            System.out.println("Voter");
        }
    }
}
