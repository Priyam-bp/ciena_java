package java_17_features;

class A {
    public A() {
        // Default constructor for A
        System.out.println("Constructor of A");
    }

    public void show() {
        System.out.println("This is class A");
    }
}

class B extends A {
    public B() {
        // Default constructor for B
        System.out.println("Constructor of B");
    }

    @Override
    public void show() {
        System.out.println("This is class B");
    }
}

public class instanceOf_prac {
    public static void main(String[] args) {
        A obj = new B(); // Polymorphism
        obj.show();

        if (obj instanceof B obj2) {
            obj2.show();
        }
    }
}
