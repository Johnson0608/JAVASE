package thread;

import java.util.concurrent.TimeUnit;

public class ThreadTaskId implements Runnable {

    private final int id;

    public ThreadTaskId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0;i < 5;i++) {
            System.out.println("TaskInPool-["+id+"] is running phase-"+i);
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("TaskInPool-["+id+"] is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
