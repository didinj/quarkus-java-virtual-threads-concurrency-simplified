package com.djamware.virtualthreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConcurrentService {

    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public String runConcurrentTasks() {
        try {
            Future<String> task1 = executor.submit(this::simulateTask1);
            Future<String> task2 = executor.submit(this::simulateTask2);
            Future<String> task3 = executor.submit(this::simulateTask3);

            return task1.get() + " | " + task2.get() + " | " + task3.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error: " + e.getMessage();
        }
    }

    private String simulateTask1() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Task 1 running on: " + Thread.currentThread());
        return "Task 1 done";
    }

    private String simulateTask2() throws InterruptedException {
        Thread.sleep(1500);
        System.out.println("Task 2 running on: " + Thread.currentThread());
        return "Task 2 done";
    }

    private String simulateTask3() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Task 3 running on: " + Thread.currentThread());
        return "Task 3 done";
    }
}
