package frc.libs.motionoftheocean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
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
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        MotionOfTheOcean.addPositionFunctions(Main::getPose, Main::toPose);
        MotionOfTheOcean.addSubsystem("-intake");
        MotionOfTheOcean.addSubsystem("~roller");
        MotionOfTheOcean.addBinaryState("-intake", Main::getIntake, Main::setIntake);
        MotionOfTheOcean.addDynamicState("~roller", Main::getRoller, Main::setRoller);
        try {
            MotionOfTheOcean.Recorder.startRecorder();
        } catch (Exceptions.MotionOfTheOceanIsNotReady e) {
            e.printStackTrace();
        }

        Thread.sleep(100);

        MotionOfTheOcean.Recorder.stopRecorder();
        MotionOfTheOcean.Recorder.exportRecording();
    }

    public static void print() {
        System.out.println("deez nuts are huge: " + (System.currentTimeMillis() - last));
        last = System.currentTimeMillis();
    }

    public static double[] getPose() {
        return new double[]{r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble()};
    }

    public static void toPose(double[] arr) {
        System.out.println("I'm going to " + Arrays.toString(arr));
    }

    public static boolean getIntake() {
        return r.nextBoolean();
    }

    public static void setIntake(boolean p) {
        System.out.println("intake pose now to " + p);
    }

    public static double getRoller() {
        return r.nextDouble();
    }

    public static void setRoller(double s) {
        System.out.println("the roller is spinnig at " + s);
    }
}
