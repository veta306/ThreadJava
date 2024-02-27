public class Main {
    public static void main(String[] args) {
        ControlThread controlThread = new ControlThread(10000);
        for (int i = 1; i <= 4; i++){
            new MainThread(i, 1, controlThread).start();
        }
        new Thread(controlThread).start();
    }
}