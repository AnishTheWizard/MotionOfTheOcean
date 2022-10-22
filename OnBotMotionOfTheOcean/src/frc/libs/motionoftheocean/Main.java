package frc.libs.motionoftheocean;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        MotionOfTheOcean.addPositionFunctions(Main::getPose, Main::toPose);
        MotionOfTheOcean.Recorder.configureRecording(100, new String[]{"drivetrain"}, "testRec", MotionOfTheOcean.Recorder.RecordingType.WITH_POSE);
        MotionOfTheOcean.Recorder.startRecorder();
        while(i < 3) {

        }
        System.out.println("recording finished");
        MotionOfTheOcean.Recorder.stopRecorder();
        MotionOfTheOcean.Recorder.exportRecording();
    }

    public static double[] getPose() {
        i++;
        return new double[] {i, i*2};
    }

    public static void toPose(double[] vec) {
        System.out.println(vec[0] + "" + vec[1]);
    }
}
