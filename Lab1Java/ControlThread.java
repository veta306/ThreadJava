
public class ControlThread implements Runnable{
    private boolean canBreak = false;
    private final int time;
    ControlThread(int time){
        this.time = time;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canBreak = true;
    }

    synchronized public boolean isCanBreak() {
        return canBreak;
    }
}