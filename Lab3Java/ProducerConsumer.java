import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class ProducerConsumer {
    private Semaphore access;
    private Semaphore full;
    private Semaphore empty;
    private final ConcurrentLinkedQueue<String> storage = new ConcurrentLinkedQueue<>();
    private int producedItemCount = 0;

    public void start(int storageSize, int itemNumbers, int producersCount, int consumersCount) {
        access = new Semaphore(1);
        full = new Semaphore(storageSize);
        empty = new Semaphore(0);

        Thread[] producerThreads = new Thread[producersCount];
        Thread[] consumerThreads = new Thread[consumersCount];

        for (int i = 0; i < producersCount; i++) {
            int producerNumber = i;
            int items = i == producersCount - 1
                    ? itemNumbers - i * (itemNumbers / producersCount)
                    : itemNumbers / producersCount;
            producerThreads[i] = new Thread(() -> producer(items, producerNumber));
            producerThreads[i].start();
        }

        for (int i = 0; i < consumersCount; i++) {
            int consumerNumber = i;
            int items = i == consumersCount - 1
                    ? itemNumbers - i * (itemNumbers / consumersCount)
                    : itemNumbers / consumersCount;
            consumerThreads[i] = new Thread(() -> consumer(items, consumerNumber));
            consumerThreads[i].start();
        }
    }

    private void producer(int itemsToProduce, int producerNumber) {
        for (int i = 0; i < itemsToProduce; i++) {
            try {
                full.acquire();
                access.acquire();
                String item = "item " + producedItemCount;
                storage.add(item);
                System.out.println("-> Producer " + producerNumber + " produced " + item);
                producedItemCount++;
                access.release();
                empty.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consumer(int itemsToConsume, int consumerNumber) {
        for (int i = 0; i < itemsToConsume; i++) {
            try {
                empty.acquire();
                Thread.sleep(1000);
                access.acquire();
                String item = storage.poll();
                System.out.println("<- Consumer " + consumerNumber + " consumed " + item);
                access.release();
                full.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
