package java_17_features;

sealed class A permits B,C{
    private String name;
    private String age;

    public A(String name, String age){
        this.name = name;
        this.age = age;
    }
    
}

sealed class B extends A permits D{
    private String pno;
    public B(String name,String age, String pno){
        super(name,age);
        this.pno = pno;
    }
}

non-sealed class C extends A{
    public C(String name, String age) {
        super(name, age);
    }
}   

final class D extends B{
    public D(String name, String age, String pno) {
        super(name, age, pno);
    }
}

public class Sealed_classes_prac {

    // sealed class restricts inheritence to only permitted class 
    // permitted class have to be one of 3 types: Sealed, non-sealed, final
    // permitted class which is sealed can permit other classes as well
    // non sealed is open for all to inherit
    // final can not be inherited as it is the final state of that class
}