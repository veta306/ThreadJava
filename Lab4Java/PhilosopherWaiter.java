import java.util.concurrent.Semaphore;

public class PhilosopherWaiter extends Thread{
    static Semaphore[] forks;
    static Semaphore waiter;
    int id;
    int idLeftFork;
    int idRightFork;
    public PhilosopherWaiter(int id, int idLeftFork, int idRightFork){
        this.id = id;
        this.idLeftFork = idLeftFork;
        this.idRightFork = idRightFork;
    }
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Philosopher " + id + " thinking " + i + " time");

                waiter.acquire();
                System.out.println("Philosopher " + id + " got permission from waiter");

                forks[idLeftFork].acquire();
                System.out.println("Philosopher " + id + " took left fork");

                forks[idRightFork].acquire();
                System.out.println("Philosopher " + id + " took right fork");

                System.out.println("Philosopher " + id + " eating " + i + " time");

                forks[idLeftFork].release();
                System.out.println("Philosopher " + id + " put left fork");

                forks[idRightFork].release();
                System.out.println("Philosopher " + id + " put right fork");

                waiter.release();
                System.out.println("Philosopher " + id + " released permission to waiter");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
