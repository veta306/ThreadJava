public class MainThread extends Thread{
    private final int id;
    private  final int step;
    private final ControlThread controlThread;

    public MainThread(int id, int step,  ControlThread controlThread) {
        this.id = id;
        this.step = step;
        this.controlThread = controlThread;
    }

    @Override
    public void run() {
        long sum = 0;
        long elements = 0;

        while (!controlThread.isCanBreak()) {
            sum += elements * step;
            elements++;
        }
        System.out.println("Thread " + id + ": Sum = " + sum + ", Elements = " + (elements - 1));
    }
}