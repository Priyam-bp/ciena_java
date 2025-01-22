package fundamentals;

// Arithmetic Operators: +, -, *, /, %.
// Relational Operators: ==, !=, <, >, <=, >=.
// Logical Operators: &&, ||, !.
// Assignment Operators: =, +=, -=, *=, /=

public class operators {
    public static void main(String args[]){
        int num1 = 10;
        int num2 = 20;
        int result = 0;

        result = num1 + num2;
        System.out.println("Addition: " + result);

        result = num1 - num2;
        System.out.println("Subtraction: " + result);

        result = num1 * num2;
        System.out.println("Multiplication: " + result);

        result = num1 / num2;
        System.out.println("Division: " + result);

        result = num1 % num2;
        System.out.println("Modulus: " + result);

        num1++;
        System.out.println("Increment: " + num1);

        num2--;
        System.out.println("Decrement: " + num2);

        boolean flag = (num1 == num2);
        System.out.println("Equal: " + flag);
        
        flag = (num1 != num2);
        System.out.println("Not Equal: " + flag);

        flag = (num1 > num2);
        System.out.println("Greater: " + flag);

        flag = (num1 < num2);
        System.out.println("Lesser: " + flag);

        flag = (num1 >= num2);
        System.out.println("Greater or Equal: " + flag);

        flag = (num1 <= num2);
        System.out.println("Lesser or Equal: " + flag);

        flag = (num1 > num2 && num1 < num2);
        System.out.println("Logical AND: " + flag);

        flag = (num1 > num2 || num1 < num2);
        System.out.println("Logical OR: " + flag);

        flag = !(num1 > num2);
        System.out.println("Logical NOT: " + flag);

    }
}
