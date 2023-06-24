package frc.libs.motionoftheocean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SharkExecutor {

    private static int iterator;
    private static ArrayList<SharkState> executable;

    private static Consumer<double[]> toPose;

    public static void loadAndConfigurePath(String filepath, Consumer<double[]> toPose) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        executable = new ArrayList<>();
        SharkExecutor.toPose = toPose;
        iterator = 0;
        sc.nextLine(); //move it past the path planner text
        sc.nextLine(); //move it past the labels
        while(sc.hasNext()) {
            String raw = sc.nextLine();
            String[] data = raw.split(",");//TODO CHANGE THE INDEX NUMBERS TO THE PROPER HEADERS INDICES
            SharkState state = new SharkState(
                    Double.parseDouble(data[0]),//t
                    Double.parseDouble(data[1]),//x
                    Double.parseDouble(data[2]),//y
                    Double.parseDouble(data[4]),//linearVel
                    Double.parseDouble(data[7]),//heading
                    Double.parseDouble(data[9])//angularVel
            );
            executable.add(state);
        }
    }

    public static boolean hasNext() {return false;}
    public static boolean isFinished() {return iterator >= executable.size();}

    public static void executeNextAvailableStep(double currentTime) {
        boolean hasExecuted = false;
        while(!hasExecuted) {
            SharkState nextState = executable.get(iterator);
            iterator++;
            if(nextState.getTime() < currentTime) {
                System.out.println("had to skip cuz current="+(currentTime*1000)+" and the time expected was " + nextState.getTime()*1000);
                continue;
            }
//            System.out.println("iterator is at "+iterator);
            toPose.accept(nextState.getAsArray());
            hasExecuted = true;

        }
    }

    public static void restartIterator() {
        iterator = 0;
    }

    public static void main(String[] args) {
        loadAndConfigurePath("C:\\Users\\mchan\\Documents\\Programming\\Robotics\\MotionOfTheOcean\\OnBotMotionOfTheOcean\\src\\frc\\libs\\motionoftheocean\\input.csv", SharkExecutor::toPose);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        System.out.println("ready to start program");
        startTime = System.currentTimeMillis()/1000.;
        executor.scheduleAtFixedRate(SharkExecutor::run, 0, 20, TimeUnit.MILLISECONDS);


    }

    private static double startTime;

    public static void run() {
//        System.out.println("iterated once!: " + isFinished());
        if(!isFinished()) {
            double currentTime = System.currentTimeMillis()/1000. - startTime;
            executeNextAvailableStep(currentTime);
        }
    }

    public static void toPose(double[] state) {
        System.out.println(Arrays.toString(state));
    }

}
