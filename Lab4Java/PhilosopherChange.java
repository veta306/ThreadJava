import java.util.concurrent.Semaphore;

public class PhilosopherChange extends Thread{
    static Semaphore[] forks;
    int id;
    int idLeftFork;
    int idRightFork;
    public PhilosopherChange(int id, int idLeftFork, int idRightFork){
        this.id = id;
        this.idLeftFork = idLeftFork;
        this.idRightFork = idRightFork;
    }
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Philosopher " + id + " thinking " + i + " time");
                if (idLeftFork < idRightFork) {
                    forks[idLeftFork].acquire();
                    System.out.println("Philosopher " + id + " took left fork");

                    forks[idRightFork].acquire();
                    System.out.println("Philosopher " + id + " took right fork");
                }
                else {
                    forks[idRightFork].acquire();
                    System.out.println("Philosopher " + id + " took right fork");

                    forks[idLeftFork].acquire();
                    System.out.println("Philosopher " + id + " took left fork");
                }

                System.out.println("Philosopher " + id + " eating " + i + " time");

                forks[idLeftFork].release();
                System.out.println("Philosopher " + id + " put left fork");

                forks[idRightFork].release();
                System.out.println("Philosopher " + id + " put right fork");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
