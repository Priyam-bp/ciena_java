package Threading_Concurrency;

class A extends Thread{ // extending class thread makes it a thread class, normal class cannot be used to run parallel methods
    
    public void run(){ // every thread class should have a run method 
        for(int i = 0;i<=25;i++){
            System.out.println("Class A");
        }
    }
}

class B extends Thread{
    public void run(){
        for(int i = 0;i<=25;i++){
            System.out.println("Class B");
        }
    }
}

class C implements Runnable{
    public void run(){
        for(int i = 0;i<=25;i++){
            System.out.println("Class C");
        }
    }
}


public class Threads_Prac {
    public static void main(String[] args) {
        A obj1 = new A();
        B obj2 = new B();
        Runnable obj3 = new C();

        obj1.start(); // start function executes the run method from the class whose object it is called for
        obj2.start();
        
        Thread t1 = new Thread(obj3);

        t1.start();
    }
}
