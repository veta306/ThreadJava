public class Main {
    public static void main(String[] args) {
        int length = 10000000;
        int threadCount = 4;
        ArrayProcessor arrayProcessor = new ArrayProcessor(length, threadCount);
        System.out.println(arrayProcessor.FindMin());
    }
}