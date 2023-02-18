package frc.libs.motionoftheocean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    static int i = 0;
    static long last = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException {
        HashMap<String, Double> d = new HashMap<>();
        HashMap<String, Boolean> b = new HashMap<>();
        d.put("deez", 6.0);
        b.put("an", false);

        ArrayList<String> j = new ArrayList<>();
        j.add("deez");
        j.add("an");

        State state = new State(
                new double[]{1, 2, 3, 4, 5},
                d,
                b,
                new HashMap<String, Supplier<Boolean>>(),
                j
        );

        State state2 = new State(
                new double[]{6, 7, 8, 8, 9},
                d,
                b,
                new HashMap<String, Supplier<Boolean>>(),
                j
        );

        ArrayList<State> recording = new ArrayList<>();
        recording.add(state);
        recording.add(state2);

        System.out.println(state.toString());
        try {
            FileManager.Encoder.export("filename.csv", recording);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print() {
        System.out.println("deez nuts are huge: " + (System.currentTimeMillis() - last));
        last = System.currentTimeMillis();
    }
}
