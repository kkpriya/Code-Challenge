package com.newrelic.codingchallenge;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author priya
 *
 */

public class Queue implements Runnable{
    private BinarySearch<Integer> bstInt = new BinarySearch<>();
    private BlockingQueue<Integer> blockingQueue;
    private Timer timer;

    /*
     * Calls Report method every 10 seconds.
     */
    public Queue(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        timer = new Timer();
        timer.schedule(new Report(bstInt), 0, 10000); 
    }

    @Override
    public void run() {
        while(true){
            try {
                int buffer = blockingQueue.take();
                bstInt.insert(buffer);
            } catch (InterruptedException e) {
                System.out.println("Connection interrupted.");
            }
        }
    }

    /*
     * Application prints report every 10 seconds with number of unique numbers, duplicates, and a total count of unique numbers.
     */
    class Report extends TimerTask {
        private BinarySearch<Integer> BST;
        private int preUnique = 0;
        private int preDuplicate = 0;
        private int uniqueDiff;
        private int duplicateDiff;
        public Report(BinarySearch<Integer> BST) {
            this.BST = BST;
        }

        @Override
        public void run() {
            uniqueDiff = BST.size() - preUnique; 
            duplicateDiff = BST.getDuplicates() - preDuplicate;
            preUnique = BST.size();
            preDuplicate = BST.getDuplicates();
            System.out.printf("Received %d unique numbers,%d duplicates. Unique total: %d\n", uniqueDiff, duplicateDiff, BST.size());
        }
    }
}
