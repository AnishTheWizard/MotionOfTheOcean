package frc.libs.motionoftheocean;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MotionOfTheOcean {

    //Subsystem commands
    private static HashMap<String, Supplier<Object>> recorders;
    private static HashMap<String, Consumer<Object>> runners;

    //drivetrain commands
    private static Supplier<double[]> getPose;
    private static Consumer<double[]> toPose;

    //config params
    private static final String[] PATH_FILES = FileManager.locateAllPaths();
    private static String selectedPath = "recording.csv";

    public static void addPositionFunctions(Supplier<double[]> getPose, Consumer<double[]> toPose) {
        MotionOfTheOcean.getPose = getPose;
        MotionOfTheOcean.toPose = toPose;
    }

    public static void addRecorder(String name, Supplier<Object> r) {
        recorders.put(name, r);
    }
    public static void addRunner(String name, Consumer<Object> r) {
        runners.put(name, r);
    }

    public static void selectRecording(String filename) throws Exceptions.PathFileDoesNotExit {
        if(SharkUtility.findIn(PATH_FILES, filename)) {
            selectedPath = filename;
        }
        else {
            throw new Exceptions.PathFileDoesNotExit("File " + filename + " does not exist in memory");
        }
    }


    public static class Recorder {

        private static String[] subsystemOrder;
        private static Object[] otherStates;

        private static String recordingName;

        private static ArrayList<State> recording;

        private static ScheduledExecutorService executorService;

        public enum RecordingType {
            WITH_POSE,
            SUBSYSTEM_ONLY
        }

        private static RecordingType recordingType;

        public static void configureRecording(int recordingSize, String[] so, String name, RecordingType type) {
            recording = new ArrayList<>(recordingSize);
            subsystemOrder = so;
            recordingName = name;
            recordingType = type;

            executorService = Executors.newSingleThreadScheduledExecutor();
        }

        public static void resetRecorder() {
            recording = null;
            subsystemOrder = null;
            recordingName = null;
        }

        //TODO: SHOULD ERROR IF RECORDER ISN"T CONFIGURED
        public static void startRecorder() {
            executorService.scheduleAtFixedRate(MotionOfTheOcean.Recorder::update, 0, 20, TimeUnit.MILLISECONDS);
            System.out.println("RECORDING STARTED W/ EXEC: " + executorService.toString());
        }

        public static void stopRecorder() {
            executorService.shutdownNow(); //might be dangerous
        }

        public static void exportRecording() {
            FileManager.Encoder.export(recordingName, recording);
        }

        public static void update() {
            double[] pose;
            if(recordingType == RecordingType.WITH_POSE) pose = getPose.get();
            else pose = new double[]{0, 0, 0}; // TODO REPAIR THIS

            otherStates = new Object[recorders.size()];

            for(int i = 0; i < recorders.size(); i++) {
                otherStates[i] = recorders.get(subsystemOrder[i]).get();
            }

//            recording.add(new State(pose, otherStates));
            System.out.println("updating recording");
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
