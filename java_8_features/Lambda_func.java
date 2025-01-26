package java_8_features;

@FunctionalInterface
interface A{
    public void show();
}

@FunctionalInterface
interface C{
    public int add(int i,int j);
}

class B implements A{
    public void show(){
        System.out.println("hello");
    }
}

public class Lambda_func {

    public static void main(String[] args) {
        A obj0 = new B();
        obj0.show();

        //Better way; instansiate class in main instead of making new class which implements the functional interface

        A obj = new A(){
            public void show(){
                System.out.println("hello");
            }
        }; 
        obj.show();


        // Better way is lambda function

        A obj2 = () -> {System.out.println("hello");};

        obj2.show();



        C obj3 = new C(){
            public int add(int i, int j){
                return i+j;
            }
        };

        C obj4 = (int i,int j) -> {return i+j;};

        // when there is just the return statement in curly brackets no need for return just write the statement

        C obj5 = (int i,int j) -> i+j;

        System.out.println(obj5.add(1, 5));
    }
}