public class MinFinder extends Thread{
    int start;
    int end;
    ArrayProcessor arrayProcessor;
    public MinFinder(int start, int end, ArrayProcessor arrayProcessor){
        this.start = start;
        this.end = end;
        this.arrayProcessor = arrayProcessor;
    }
    @Override
    public void run(){
        int index = arrayProcessor.MinOfPart(start,end);
        arrayProcessor.UpdateMin(index);
        arrayProcessor.UpdateCompleted();
    }
}