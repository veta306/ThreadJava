public class ArrayProcessor {
    private final int length;
    private final int threadCount;
    private final int[] array;
    private int completedCount = 0;
    private int minIndex = 0;

    public ArrayProcessor(int length, int threadCount){
        this.length = length;
        this.threadCount = threadCount;
        array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        array[length/2] = -1000;
    }
    public String FindMin(){
        for (int i = 0; i < threadCount; i++) {
            int start = i * length / threadCount;
            int end = i == threadCount - 1 ? length - 1 : (i + 1) * length / threadCount;
            new MinFinder(start, end, this).start();
        }
        return ReturnMin();
    }
    public int MinOfPart(int start, int end){
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = start; i <= end; i++) {
            if (array[i] < min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }
    synchronized public void UpdateMin(int index){
        if (array[index] < array[minIndex]) minIndex = index;
    }
    synchronized public void UpdateCompleted(){
        completedCount++;
        notify();
    }
    synchronized public String ReturnMin() {
        while (completedCount < threadCount) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "Index: " + minIndex + " Value: " + array[minIndex];
    }
}