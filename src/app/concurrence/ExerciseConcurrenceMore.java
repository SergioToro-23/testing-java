package app.concurrence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExerciseConcurrenceMore {
    public static void executeHundredTasks() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> results = new ArrayList<>();

        try {
        for (int i = 0; i < 100; i++) {
            final int taskId = i;

            Callable<String> task = () ->
                "Task " + taskId + " processed by "
                    + Thread.currentThread().getName();

            results.add(executor.submit(task));
        }
        for (Future<String> future : results){
            System.out.println(future.get());
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
        }
    }
}
