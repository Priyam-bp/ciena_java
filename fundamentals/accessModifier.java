package fundamentals;

// Private: The access level of a private modifier is only within the class. It cannot be accessed from outside the class.
// Default: The access level of a default modifier is only within the package. It cannot be accessed from outside the package. If you do not specify any access level, it will be the default.
// Protected: The access level of a protected modifier is within the package and outside the package through child class. If you do not make the child class, it cannot be accessed from outside the package.
// Public: The access level of a public modifier is everywhere. It can be accessed from within the class, outside the class, within the package and outside the package.

class Test{
    private int num = 10;
    public int num2 = 20;

    public void getNum(){
        System.out.println("Private: " + num);
    }
}

public class accessModifier {
    public static void main(String[] args) {
        Test obj = new Test();
        obj.getNum();

        System.out.println("Public: " + obj.num2);
    }
}
