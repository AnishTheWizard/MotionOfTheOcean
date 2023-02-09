package frc.libs.motionoftheocean;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    static int i = 0;
    static long last = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException {

    }

    public static void print() {
        System.out.println("deez nuts are huge: " + (System.currentTimeMillis() - last));
        last = System.currentTimeMillis();
    }
}
