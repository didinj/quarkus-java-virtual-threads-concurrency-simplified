package com.djamware.virtualthreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PerformanceService {

    public String comparePerformance() {
        int taskCount = 100;
        long startFixed = System.currentTimeMillis();
        runTasks(Executors.newFixedThreadPool(10), taskCount);
        long fixedTime = System.currentTimeMillis() - startFixed;

        long startVirtual = System.currentTimeMillis();
        runTasks(Executors.newFixedThreadPool(3), taskCount);
        long virtualTime = System.currentTimeMillis() - startVirtual;

        return "Fixed threads: " + fixedTime + " ms | Virtual threads: " + virtualTime + " ms";
    }

    private void runTasks(ExecutorService executor, int count) {
        try {
            Future<?>[] tasks = new Future<?>[count];
            for (int i = 0; i < count; i++) {
                tasks[i] = executor.submit(() -> {
                    try {
                        simulateTask();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                });
            }
            for (Future<?> task : tasks) {
                task.get();
            }
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void simulateTask() throws InterruptedException {
        Thread.sleep(200);
    }
}
