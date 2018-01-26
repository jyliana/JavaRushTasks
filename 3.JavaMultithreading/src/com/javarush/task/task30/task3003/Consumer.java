package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
        }
        while (true) {
            try {
                ShareItem item = queue.take();
                System.out.format("Processing " + item + "\n");
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
