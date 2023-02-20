package io.github.anishthewizard;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MotionOfTheOcean {

    //Subsystem commands
    private static final HashMap<String, Supplier<Double>> dynamicStateAccessors = new HashMap<>();
    private static final HashMap<String, Supplier<Boolean>> binaryStateAccessors = new HashMap<>();
//    private static final ArrayList<Accessor<Double>> dynamicStateAccessors = new ArrayList<>();
//    private static final ArrayList<Accessor<Boolean>> binaryStateAccessors = new ArrayList<>();

    private static final HashMap<String, Consumer<Double>> dynamicExecutors = new HashMap<>();
    private static final HashMap<String, Consumer<Boolean>> binaryExecutors = new HashMap<>();

//    private static final ArrayList<Mutator<Double>> dynamicExecutors = new ArrayList<>();
//    private static final ArrayList<Mutator<Boolean>> binaryExecutors = new ArrayList<>();

    private static final HashMap<String, Supplier<Boolean>> parallelConditions = new HashMap<>();
//    private static final ArrayList<Accessor<Boolean>> parallelConditions = new ArrayList<>();

    private static ArrayList<String> subsystemOrder = new ArrayList<String>();

    //drivetrain commands
    private static Supplier<double[]> getChassisState = null;
    private static Consumer<double[]> toChassisState = null;

    //config params
    private static final String[] PATH_FILES = FileManager.locateAllPaths();
    private static String selectedPath = "recording.csv";

    public static void addPositionFunctions(Supplier<double[]> getPose, Consumer<double[]> toPose) {
        MotionOfTheOcean.getChassisState = getPose;
        MotionOfTheOcean.toChassisState = toPose;
    }

    public static void addDynamicState(String name, Supplier<Double> state, Consumer<Double> executor) {
        dynamicStateAccessors.put(name, state);
        dynamicExecutors.put(name, executor);

    }

    public static void addBinaryState(String name, Supplier<Boolean> state, Consumer<Boolean> runnable) {
        binaryStateAccessors.put(name, state);
        binaryExecutors.put(name, runnable);
    }

    public static void addParallelCondition(String name, Supplier<Boolean> condition) {
        parallelConditions.put(name, condition);
    }

    public static void addSubsystem(String subsystemName) {
        subsystemOrder.add(subsystemName);
    }

    public static void setSelectedPath(String filename) {
        selectedPath = filename;
    }

    public static void selectRecording(String filename) throws Exceptions.PathFileDoesNotExist {
        if(SharkUtility.findIn(PATH_FILES, filename)) {
            selectedPath = filename;
        }
        else {
            throw new Exceptions.PathFileDoesNotExist("File " + filename + " does not exist in memory");
        }
    }

    public static boolean isParentNotReady() {
        return (getChassisState == null || toChassisState == null);
    }


    public static class Recorder {


        private static ArrayList<State> recording = new ArrayList<>();

        private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        public enum RecordingType {
            TOTAL_SYSTEM,
            SUBSYSTEM_ONLY
        }

        private static RecordingType recordingType = RecordingType.TOTAL_SYSTEM;

        public static void resetRecorder() {
            stopRecorder();
            recording = new ArrayList<>();
        }

        public static void startRecorder() throws Exceptions.MotionOfTheOceanIsNotReady {
            if(isParentNotReady())
                throw new Exceptions.MotionOfTheOceanIsNotReady("Could not start recorder");
            executorService.scheduleAtFixedRate(MotionOfTheOcean.Recorder::update, 0, 20, TimeUnit.MILLISECONDS);
        }

        public static void stopRecorder() {
            executorService.shutdown();
        }

        public static void exportRecording() {
            try {
                String subsystemString = subsystemOrder.toString();
                subsystemString = subsystemString.substring(1, subsystemString.length() - 1);
                String headerString = "x, y, theta, v, a, " + subsystemString + ",";
                FileManager.Encoder.export(selectedPath, recording, headerString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void update() {
            double[] chassisMotion = getChassisState.get();
            HashMap<String, Double> dynamicStates = new HashMap<String, Double>();
            HashMap<String, Boolean> binaryStates = new HashMap<String, Boolean>();

            for(String name : subsystemOrder) {
                char escapeChar = name.charAt(0);
                if(escapeChar == '~')
                    dynamicStates.put(
                            name,
                            dynamicStateAccessors.get(name).get()
                    );
                else if(escapeChar == '-')
                    binaryStates.put(
                            name,
                            binaryStateAccessors.get(name).get()
                    );
                //System.out.println("ima bitch");
            }

            recording.add(
                    new State(
                            chassisMotion,
                            dynamicStates,
                            binaryStates,
                            parallelConditions,
                            subsystemOrder));

        }
    }

    public static class Executor{

        //execution configuration parameters
        private static boolean readyToExecute = false;

        public static void confirm() throws Exceptions.MotionOfTheOceanIsNotReady{
            //run conditions to verify if all commands are loaded
            readyToExecute = true;
        }
    }
}
