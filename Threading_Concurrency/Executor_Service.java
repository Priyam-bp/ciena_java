package Threading_Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Executor_Service {
    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
    
        // es.execute(new A());
        // es.execute(new B());
        // es.execute(new C());
        
        es.shutdown();
        
        ExecutorService es2 = Executors.newFixedThreadPool(2);

        // es2.execute(new A());
        // es2.execute(new B());
        // es2.execute(new C());
           
        es2.shutdown();


        ExecutorService ces = Executors.newCachedThreadPool();

        // ces.execute(new A());
        // ces.execute(new B());
        // ces.execute(new C());

        ces.shutdown();


        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);

        for(int i = 10;i>=0;i--){
            ses.schedule(new A(), 5, TimeUnit.SECONDS);
        }

        ses.shutdown();
    }
}
