import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        startPhilosopherChange(5,5);
        //startPhilosopherWaiter(5,5);
    }
    public static void startPhilosopherWaiter(int numForks, int numPhilosophers){
        PhilosopherWaiter.forks = new Semaphore[numForks];
        for (int i = 0; i < numForks; i++) {
            PhilosopherWaiter.forks[i] = new Semaphore(1);
        }
        PhilosopherWaiter.waiter = new Semaphore(numForks - 1);

        PhilosopherWaiter[] philosophers = new PhilosopherWaiter[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new PhilosopherWaiter(i, i, (i + 1) % numForks);
            philosophers[i].start();
        }
    }
    public static void startPhilosopherChange(int numForks, int numPhilosophers) {
        PhilosopherChange.forks = new Semaphore[numForks];
        for (int i = 0; i < numForks; i++) {
            PhilosopherChange.forks[i] = new Semaphore(1);
        }

        PhilosopherChange[] philosophers = new PhilosopherChange[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new PhilosopherChange(i, i, (i + 1) % numForks);
            philosophers[i].start();
        }
    }
}