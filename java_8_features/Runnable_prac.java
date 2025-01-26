package java_8_features;

class Test1 implements Runnable{

    @Override
    public void run(){
        System.out.println("This is overridden run");
    }
}

public class Runnable_prac {
    public static void main(String[] args) {
        Test1 obj  = new Test1();   
        Thread t1 = new Thread(obj);
        t1.start();
    }
}
